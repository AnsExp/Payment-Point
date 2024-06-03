package com.paymentpoint.app.dialog;

import java.awt.Window;
import java.util.Date;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.JTextComponent;

import com.paymentpoint.app.component.Module;
import com.paymentpoint.app.component.UISetting;
import com.paymentpoint.controller.command.ControlService;
import com.paymentpoint.controller.command.FindPaymentControl;
import com.paymentpoint.controller.command.IControl;
import com.paymentpoint.controller.command.SendPaymentControl;
import com.paymentpoint.controller.log.ControlLogger;
import com.paymentpoint.controller.log.ILogger;
import com.paymentpoint.controller.settings.Language;
import com.toedter.calendar.JDateChooser;

public class SendPaymentDialog extends FormDialog {

    private Language lang;

    private JLabel dateLabel;
    private JLabel extraLabel;
    private JLabel methodLabel;
    private JLabel amountLabel;
    private JLabel observationLabel;

    private JCheckBox extraCheck;
    private JDateChooser dateChooser;
    private JTextComponent extraField;
    private JTextComponent amountField;
    private JTextArea observationField;
    private JComboBox<String> methodCombo;

    private final String[] methods = {"CASH", "TRANSFER", "OTHER"};

    public SendPaymentDialog(Window parent) {
        super(parent);
        setResizable(false);
        setSize(400, 450);
        setLocationRelativeTo(parent);
        initComponents();
        initLanguage();
    }

    private void initComponents() {
        Module module = new Module();
        JScrollPane scrollPane = new JScrollPane();

        lang = new Language();

        dateLabel = new JLabel();
        extraLabel = new JLabel();
        methodLabel = new JLabel();
        amountLabel = new JLabel();
        observationLabel = new JLabel();

        extraCheck = new JCheckBox();
        extraField = new JTextField();
        amountField = new JTextField();
        methodCombo = new JComboBox<>();
        dateChooser = new JDateChooser();
        observationField = new JTextArea();

        scrollPane.setViewportView(observationField);

        UISetting.settingLabel(dateLabel, SwingConstants.RIGHT, SwingConstants.CENTER);
        UISetting.settingLabel(extraLabel, SwingConstants.RIGHT, SwingConstants.CENTER);
        UISetting.settingLabel(methodLabel, SwingConstants.RIGHT, SwingConstants.CENTER);
        UISetting.settingLabel(amountLabel, SwingConstants.RIGHT, SwingConstants.CENTER);
        UISetting.settingLabel(observationLabel, SwingConstants.RIGHT, SwingConstants.CENTER);

        UISetting.settingComponent(extraCheck);
        UISetting.settingComponent(extraField);
        UISetting.settingComponent(amountField);
        UISetting.settingComponent(methodCombo);
        UISetting.settingComponent(dateChooser);
        UISetting.settingComponent(scrollPane);

        dateChooser.setForeground(amountField.getForeground());

        module.addComponent(methodLabel, 0, 0, 1, 1, 0.3, 0.1428);
        module.addComponent(amountLabel, 0, 1, 1, 1, 0.3, 0.1428);
        module.addComponent(dateLabel, 0, 2, 1, 1, 0.3, 0.1428);
        module.addComponent(observationLabel, 0, 3, 1, 1, 0.3, 0.1428);
        module.addComponent(extraLabel, 0, 6, 1, 1, 0.3, 0.1432);

        module.addComponent(methodCombo, 1, 0, 1, 1, 0.7, 0.1428);
        module.addComponent(amountField, 1, 1, 1, 1, 0.7, 0.1428);
        module.addComponent(dateChooser, 1, 2, 1, 1, 0.7, 0.1428);
        module.addComponent(scrollPane, 1, 3, 2, 1, 0.7, 0.2856);
        module.addComponent(extraCheck, 1, 5, 1, 1, 0.7, 0.1428);
        module.addComponent(extraField, 1, 6, 1, 1, 0.7, 0.1432);

        contentPane.setContent(module);

        submitButton.setCommand(() -> {

            String fieldError = null;

            try {

                if (!extraField.getText().isEmpty()) {
                    extraField.setText(extraField.getText().replace(",", "."));
                }
                fieldError = lang.getText("amount");
                Float.parseFloat(amountField.getText());

                if (extraCheck.isSelected() && !extraField.getText().isEmpty()) {
                    if (!amountField.getText().isEmpty()) {
                        amountField.setText(amountField.getText().replace(",", "."));
                    }
                    fieldError = lang.getText("extra");
                    Float.parseFloat(extraField.getText());
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, lang.getText("error-format") + ": " + fieldError);
                return;
            }

            IControl control = new SendPaymentControl(idForm, Float.parseFloat(amountField.getText()),
                    dateChooser.getDate(), methods[methodCombo.getSelectedIndex()], observationField.getText(),
                    extraCheck.isSelected(),
                    extraField.getText().isEmpty() ? 0 : Float.parseFloat(extraField.getText()));
            ILogger logger = new ControlLogger("Send Payment ID: " + idForm);
            ControlService cs = new ControlService(control, logger);
            if (cs.controlService())
                dispose();
        });

        extraField.setEnabled(false);

        extraCheck.addChangeListener(_ -> extraField.setEnabled(extraCheck.isSelected()));

        for (String method : methods)
            methodCombo.addItem(lang.getText(method));

        dateChooser.setDate(new Date());
    }

    private void initLanguage() {
        contentPane.setTitle(lang.getText("payment"), lang.getText("form"));

        methodLabel.setText(lang.getText("method"));
        amountLabel.setText(lang.getText("amount"));
        dateLabel.setText(lang.getText("date-payment"));
        extraCheck.setText(lang.getText("amount-extra"));
        extraLabel.setText(lang.getText("extra"));
        observationLabel.setText(lang.getText("observation"));
    }

    @Override
    public void accept(Integer idPayment) {

        if (idPayment == null)
            return;

        IControl control = new FindPaymentControl(
                () -> idPayment, x -> this.idForm = x,
                null, null,
                null, dateChooser::setDate,
                x -> amountField.setText(String.valueOf(x)),
                x -> {
                    for (int i = 0; i < methods.length; i++) {
                        if (methods[i].equals(x)) {
                            methodCombo.setSelectedIndex(i);
                            break;
                        }
                    }
                }, observationField::setText, null);
        ILogger logger = new ControlLogger("Find Payment ID: " + idForm);
        ControlService cs = new ControlService(control, logger);
        if (cs.controlService())
            dispose();
    }
}
