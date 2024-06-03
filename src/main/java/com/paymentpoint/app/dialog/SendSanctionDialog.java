package com.paymentpoint.app.dialog;

import com.paymentpoint.app.component.Module;
import com.paymentpoint.app.component.UISetting;
import com.paymentpoint.controller.command.ControlService;
import com.paymentpoint.controller.command.IControl;
import com.paymentpoint.controller.command.SendSanctionControl;
import com.paymentpoint.controller.log.ControlLogger;
import com.paymentpoint.controller.log.ILogger;
import com.paymentpoint.controller.settings.Language;
import com.toedter.calendar.JDateChooser;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.JTextComponent;
import java.awt.Window;
import java.util.Date;

public class SendSanctionDialog extends FormDialog {

    private Language lang;

    private JLabel dateLabel;
    private JLabel amountLabel;
    private JLabel methodLabel;
    private JLabel observationLabel;

    private JTextComponent amountField;
    private JDateChooser dateChooser;
    private JTextArea observationField;

    private JComboBox<String> methodCombo;

    private final String[] methods = {"CASH", "TRANSFER", "OTHER"};

    public SendSanctionDialog(Window parent) {
        super(parent);
        setResizable(false);
        setSize(400, 400);
        setLocationRelativeTo(parent);
        initComponents();
        initLanguage();
    }

    private void initComponents() {
        Module module = new Module();
        JScrollPane scrollPane = new JScrollPane();

        lang = new Language();

        dateLabel = new JLabel();
        amountLabel = new JLabel();
        methodLabel = new JLabel();
        observationLabel = new JLabel();

        amountField = new JTextField();
        methodCombo = new JComboBox<>();
        dateChooser = new JDateChooser();
        observationField = new JTextArea();

        scrollPane.setViewportView(observationField);

        UISetting.settingLabel(dateLabel, SwingConstants.RIGHT, SwingConstants.CENTER);
        UISetting.settingLabel(methodLabel, SwingConstants.RIGHT, SwingConstants.CENTER);
        UISetting.settingLabel(amountLabel, SwingConstants.RIGHT, SwingConstants.CENTER);
        UISetting.settingLabel(observationLabel, SwingConstants.RIGHT, SwingConstants.CENTER);

        UISetting.settingComponent(amountField);
        UISetting.settingComponent(methodCombo);
        UISetting.settingComponent(dateChooser);
        UISetting.settingComponent(scrollPane);

        contentPane.setContent(module);

        module.addComponent(methodLabel, 0, 0, 1, 1, 0.3, 0.2);
        module.addComponent(amountLabel, 0, 1, 1, 1, 0.3, 0.2);
        module.addComponent(dateLabel, 0, 2, 1, 1, 0.3, 0.2);
        module.addComponent(observationLabel, 0, 3, 1, 1, 0.3, 0.2);

        module.addComponent(methodCombo, 1, 0, 1, 1, 0.7, 0.2);
        module.addComponent(amountField, 1, 1, 1, 1, 0.7, 0.2);
        module.addComponent(dateChooser, 1, 2, 1, 1, 0.7, 0.2);
        module.addComponent(scrollPane, 1, 3, 3, 1, 0.7, 0.4);

        submitButton.setCommand(() -> {

            try {
                Float.parseFloat(amountField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, lang.getText("error-format"));
                return;
            }

            IControl control = new SendSanctionControl(idForm, dateChooser.getDate(), Float.parseFloat(amountField.getText()), methods[methodCombo.getSelectedIndex()], observationField.getText());
            ILogger logger = new ControlLogger("Send Sanction");
            ControlService cs = new ControlService(control, logger);
            if (cs.controlService())
                dispose();
        });

        for (String method : methods)
            methodCombo.addItem(lang.getText(method));

        dateChooser.setDate(new Date());
    }

    private void initLanguage() {
        contentPane.setTitle(lang.getText("sanction"), lang.getText("form"));

        methodLabel.setText(lang.getText("method"));
        amountLabel.setText(lang.getText("amount"));
        dateLabel.setText(lang.getText("date-payment"));
        observationLabel.setText(lang.getText("observation"));
    }

    @Override
    public void accept(Integer idSanction) {
        this.idForm = idSanction;
    }
}
