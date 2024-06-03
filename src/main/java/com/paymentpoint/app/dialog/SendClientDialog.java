package com.paymentpoint.app.dialog;

import com.paymentpoint.app.component.Module;
import com.paymentpoint.app.component.UISetting;
import com.paymentpoint.controller.command.ControlService;
import com.paymentpoint.controller.command.FindClientControl;
import com.paymentpoint.controller.command.IControl;
import com.paymentpoint.controller.command.SendClientControl;
import com.paymentpoint.controller.log.ControlLogger;
import com.paymentpoint.controller.log.ILogger;
import com.paymentpoint.controller.settings.Language;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.JTextComponent;
import java.awt.Window;

public class SendClientDialog extends FormDialog {

    private JLabel nameLabel;
    private JLabel phoneLabel;
    private JLabel idCardLabel;
    private JLabel lastnameLabel;

    protected JTextComponent nameField;
    protected JTextComponent phoneField;
    protected JTextComponent idCardField;
    protected JTextComponent lastnameField;

    public SendClientDialog(Window parent) {
        super(parent);
        setResizable(false);
        setSize(400, 350);
        setLocationRelativeTo(parent);
        initComponents();
        initLanguage();
    }

    private void initComponents() {
        Module module = new Module();

        nameLabel = new JLabel();
        phoneLabel = new JLabel();
        idCardLabel = new JLabel();
        lastnameLabel = new JLabel();

        nameField = new JTextField();
        phoneField = new JTextField();
        idCardField = new JTextField();
        lastnameField = new JTextField();

        UISetting.settingComponent(nameField);
        UISetting.settingComponent(phoneField);
        UISetting.settingComponent(idCardField);
        UISetting.settingComponent(lastnameField);

        UISetting.settingLabel(nameLabel, SwingConstants.RIGHT, SwingConstants.CENTER);
        UISetting.settingLabel(phoneLabel, SwingConstants.RIGHT, SwingConstants.CENTER);
        UISetting.settingLabel(idCardLabel, SwingConstants.RIGHT, SwingConstants.CENTER);
        UISetting.settingLabel(lastnameLabel, SwingConstants.RIGHT, SwingConstants.CENTER);

        module.addComponent(nameLabel, 0, 0, 1, 1, 0.3, 0.25);
        module.addComponent(phoneLabel, 0, 2, 1, 1, 0.3, 0.25);
        module.addComponent(idCardLabel, 0, 3, 1, 1, 0.3, 0.25);
        module.addComponent(lastnameLabel, 0, 1, 1, 1, 0.3, 0.25);

        module.addComponent(nameField, 1, 0, 1, 1, 0.7, 0.25);
        module.addComponent(phoneField, 1, 2, 1, 1, 0.7, 0.25);
        module.addComponent(idCardField, 1, 3, 1, 1, 0.7, 0.25);
        module.addComponent(lastnameField, 1, 1, 1, 1, 0.7, 0.25);

        contentPane.setContent(module);

        submitButton.setCommand(() -> {
            IControl control = new SendClientControl(
                    idForm,
                    nameField.getText(), lastnameField.getText(),
                    phoneField.getText(), idCardField.getText());
            ILogger logger = new ControlLogger("Send Client ID: " + idForm);
            ControlService cs = new ControlService(control, logger);
            if (cs.controlService()) {
                dispose();
            }
        });
    }

    private void initLanguage() {
        Language lang = new Language();

        contentPane.setTitle(lang.getText("client"), lang.getText("form"));

        nameLabel.setText(lang.getText("name"));
        phoneLabel.setText(lang.getText("phone"));
        idCardLabel.setText(lang.getText("id-card"));
        lastnameLabel.setText(lang.getText("lastname"));
    }

    @Override
    public void accept(Integer idClient) {

        if (idClient == null)
            return;

        IControl control = new FindClientControl(
                () -> idClient, x -> this.idForm = x,
                nameField::setText, lastnameField::setText,
                phoneField::setText, idCardField::setText);
        ILogger logger = new ControlLogger(
                "Find Client ID: " + idClient);
        ControlService cs = new ControlService(control, logger);
        cs.controlService();
    }
}
