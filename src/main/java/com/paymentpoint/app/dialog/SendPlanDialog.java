package com.paymentpoint.app.dialog;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.text.JTextComponent;

import com.paymentpoint.app.component.Module;
import com.paymentpoint.app.component.UISetting;
import com.paymentpoint.controller.command.ControlService;
import com.paymentpoint.controller.command.FindPlanControl;
import com.paymentpoint.controller.command.IControl;
import com.paymentpoint.controller.command.SendPlanControl;
import com.paymentpoint.controller.log.ControlLogger;
import com.paymentpoint.controller.log.ILogger;
import com.paymentpoint.controller.settings.Language;

import java.awt.Window;

public class SendPlanDialog extends FormDialog {

    private Language lang;

    private JSpinner taxSpinner;
    private JSpinner stepsSpinner;
    private JSpinner amountSpinner;
    private JSpinner paymentSpinner;
    private JTextComponent nameField;
    private JComboBox<String> stateCombo;

    private JLabel taxLabel;
    private JLabel nameLabel;
    private JLabel stateLabel;
    private JLabel stepsLabel;
    private JLabel amountLabel;
    private JLabel paymentLabel;

    private final String[] states = {"ENABLED", "DEPRECATED"};

    public SendPlanDialog(Window parent) {
        super(parent);
        setSize(400, 400);
        setLocationRelativeTo(parent);
        setResizable(false);
        initComponents();
        initLanguage();
    }

    private void initComponents() {
        Module module = new Module();
        lang = new Language();

        taxSpinner = new JSpinner();
        nameField = new JTextField();
        stepsSpinner = new JSpinner();
        amountSpinner = new JSpinner();
        stateCombo = new JComboBox<>();
        paymentSpinner = new JSpinner();

        taxLabel = new JLabel();
        nameLabel = new JLabel();
        stateLabel = new JLabel();
        stepsLabel = new JLabel();
        amountLabel = new JLabel();
        paymentLabel = new JLabel();

        UISetting.settingComponent(nameField);
        UISetting.settingComponent(taxSpinner);
        UISetting.settingComponent(stateCombo);
        UISetting.settingComponent(stepsSpinner);
        UISetting.settingComponent(amountSpinner);
        UISetting.settingComponent(paymentSpinner);

        UISetting.settingLabel(taxLabel, SwingConstants.RIGHT, SwingConstants.CENTER);
        UISetting.settingLabel(nameLabel, SwingConstants.RIGHT, SwingConstants.CENTER);
        UISetting.settingLabel(stateLabel, SwingConstants.RIGHT, SwingConstants.CENTER);
        UISetting.settingLabel(stepsLabel, SwingConstants.RIGHT, SwingConstants.CENTER);
        UISetting.settingLabel(amountLabel, SwingConstants.RIGHT, SwingConstants.CENTER);
        UISetting.settingLabel(paymentLabel, SwingConstants.RIGHT, SwingConstants.CENTER);

        module.addComponent(stateLabel, 0, 0, 1, 1, 0.3, (double) 1 / 6);
        module.addComponent(nameLabel, 0, 1, 1, 1, 0.3, (double) 1 / 6);
        module.addComponent(taxLabel, 0, 2, 1, 1, 0.3, (double) 1 / 6);
        module.addComponent(stepsLabel, 0, 3, 1, 1, 0.3, (double) 1 / 6);
        module.addComponent(amountLabel, 0, 4, 1, 1, 0.3, (double) 1 / 6);
        module.addComponent(paymentLabel, 0, 5, 1, 1, 0.3, (double) 1 / 6);

        module.addComponent(stateCombo, 1, 0, 1, 1, 0.7, (double) 1 / 6);
        module.addComponent(nameField, 1, 1, 1, 1, 0.7, (double) 1 / 6);
        module.addComponent(taxSpinner, 1, 2, 1, 1, 0.7, (double) 1 / 6);
        module.addComponent(stepsSpinner, 1, 3, 1, 1, 0.7, (double) 1 / 6);
        module.addComponent(amountSpinner, 1, 4, 1, 1, 0.7, (double) 1 / 6);
        module.addComponent(paymentSpinner, 1, 5, 1, 1, 0.7, (double) 1 / 6);

        contentPane.setContent(module);

        submitButton.setCommand(() -> {
            IControl control = new SendPlanControl(
                    idForm, nameField.getText(),
                    ((Number) stepsSpinner.getValue()).intValue(),
                    ((Number) taxSpinner.getValue()).floatValue() / 100f,
                    ((Number) amountSpinner.getValue()).floatValue(),
                    ((Number) paymentSpinner.getValue()).floatValue(),
                    states[stateCombo.getSelectedIndex()]);
            ILogger logger = new ControlLogger("Send Plan ID: " + idForm);
            ControlService cs = new ControlService(control, logger);
            if (cs.controlService())
                dispose();
        });

        SpinnerNumberModel taxModel = new SpinnerNumberModel(0f, 0f, 100f, 0.1f);
        SpinnerNumberModel stepsModel = new SpinnerNumberModel(1, 1, null, 1);
        SpinnerNumberModel amountModel = new SpinnerNumberModel(5, 5, null, 5);
        SpinnerNumberModel paymentModel = new SpinnerNumberModel(5f, 5f, null, 1f);

        taxSpinner.setModel(taxModel);
        stepsSpinner.setModel(stepsModel);
        amountSpinner.setModel(amountModel);
        paymentSpinner.setModel(paymentModel);

        for (String states : states)
            stateCombo.addItem(lang.getText(states));
    }

    private void initLanguage() {

        contentPane.setTitle(lang.getText("plan"), lang.getText("form"));

        taxLabel.setText(lang.getText("tax") + " (%)");
        nameLabel.setText(lang.getText("name"));
        stateLabel.setText(lang.getText("state"));
        stepsLabel.setText(lang.getText("steps"));
        amountLabel.setText(lang.getText("amount") + " ($)");
        paymentLabel.setText(lang.getText("payment") + " ($)");
    }

    @Override
    public void accept(Integer idPlan) {

        if (idPlan == null)
            return;

        IControl control = new FindPlanControl(
                idPlan, null,
                nameField::setText, stepsSpinner::setValue,
                amountSpinner::setValue, x -> taxSpinner.setValue(x * 100),
                paymentSpinner::setValue, x -> {
            for (int i = 0; i < states.length; i++) {
                if (states[i].equals(x)) {
                    stateCombo.setSelectedIndex(i);
                    break;
                }
            }
        });
        ILogger logger = new ControlLogger(
                "Find Plan ID: " + idPlan);
        ControlService cs = new ControlService(control, logger);
        cs.controlService();
        idForm = idPlan;
    }
}
