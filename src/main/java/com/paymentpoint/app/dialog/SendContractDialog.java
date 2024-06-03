package com.paymentpoint.app.dialog;

import java.awt.Window;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.paymentpoint.app.component.Module;
import com.paymentpoint.app.component.UISetting;
import com.paymentpoint.controller.command.ControlService;
import com.paymentpoint.controller.command.IControl;
import com.paymentpoint.controller.command.SendContractControl;
import com.paymentpoint.controller.filler.DisplayComboClient;
import com.paymentpoint.controller.filler.DisplayComboPlan;
import com.paymentpoint.controller.filler.filter.FilterPlanEnabled;
import com.paymentpoint.controller.log.ControlLogger;
import com.paymentpoint.controller.log.ILogger;
import com.paymentpoint.controller.settings.Language;
import com.toedter.calendar.JDateChooser;
import org.jetbrains.annotations.NotNull;

public class SendContractDialog extends FormDialog {

    private JDateChooser dateChooser;
    private JComboBox<String> planCombo;
    private JComboBox<String> clientCombo;

    private JLabel dateLabel;
    private JLabel planLabel;
    private JLabel clientLabel;

    private DisplayComboPlan fillPlan;
    private DisplayComboClient fillClient;

    public SendContractDialog(Window parent) {
        super(parent);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        initComponents();
        initLanguage();
    }

    private void initComponents() {
        Module module = new Module();

        planCombo = new JComboBox<>();
        clientCombo = new JComboBox<>();
        dateChooser = new JDateChooser();

        planLabel = new JLabel();
        dateLabel = new JLabel();
        clientLabel = new JLabel();

        fillPlan = new DisplayComboPlan();
        fillClient = new DisplayComboClient();

        UISetting.settingComponent(planCombo);
        UISetting.settingComponent(clientCombo);
        UISetting.settingComponent(dateChooser);

        UISetting.settingLabel(planLabel, SwingConstants.RIGHT, SwingConstants.CENTER);
        UISetting.settingLabel(dateLabel, SwingConstants.RIGHT, SwingConstants.CENTER);
        UISetting.settingLabel(clientLabel, SwingConstants.RIGHT, SwingConstants.CENTER);

        module.addComponent(clientLabel, 0, 0, 1, 1, 0.3, (double) 1 / 3);
        module.addComponent(planLabel, 0, 1, 1, 1, 0.3, (double) 1 / 3);
        module.addComponent(dateLabel, 0, 2, 1, 1, 0.3, (double) 1 / 3);

        module.addComponent(clientCombo, 1, 0, 1, 1, 0.7, (double) 1 / 3);
        module.addComponent(planCombo, 1, 1, 1, 1, 0.7, (double) 1 / 3);
        module.addComponent(dateChooser, 1, 2, 1, 1, 0.7, (double) 1 / 3);

        contentPane.setContent(module);

        fillPlan.fill(planCombo, new FilterPlanEnabled());
        fillClient.fillAll(clientCombo);

        fillPlan.bind();
        fillClient.bind();

        dateChooser.setDate(new Date());

        submitButton.setCommand(() -> {
            if (clientCombo.getSelectedItem() == null || planCombo.getSelectedItem() == null)
                return;
            IControl control = new SendContractControl(idForm,
                    fillClient.idSelection(), fillPlan.idSelection(),
                    dateChooser.getDate());
            ILogger logger = new ControlLogger("Send Contract ID: " + idForm);
            ControlService cs = new ControlService(control, logger);
            if (cs.controlService())
                dispose();
        });
    }

    private void initLanguage() {
        Language lang = new Language();

        contentPane.setTitle(lang.getText("contract"), lang.getText("form"));

        planLabel.setText(lang.getText("plan"));
        dateLabel.setText(lang.getText("date-start"));
        clientLabel.setText(lang.getText("client"));
    }

    @Override
    protected void processWindowEvent(@NotNull WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            fillPlan.unbind();
            fillClient.unbind();
        }
        super.processWindowEvent(e);
    }

    public void accept(Integer idClient) {
        idForm = idClient;
        fillClient.select(idForm);
    }
}
