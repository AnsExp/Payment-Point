package com.paymentpoint.app.dialog;

import com.paymentpoint.app.component.CommandButton;
import com.paymentpoint.app.viewer.TableView;
import com.paymentpoint.controller.command.ControlService;
import com.paymentpoint.controller.command.IControl;
import com.paymentpoint.controller.command.RemoveContractControl;
import com.paymentpoint.controller.filler.DisplayTableContract;
import com.paymentpoint.controller.filler.filter.FilterContractByClientID;
import com.paymentpoint.controller.log.ControlLogger;
import com.paymentpoint.controller.log.ILogger;
import com.paymentpoint.controller.log.ReporterLogger;
import com.paymentpoint.controller.report.ContractReporter;
import com.paymentpoint.controller.report.IReporter;
import com.paymentpoint.controller.report.ReporterService;
import com.paymentpoint.controller.settings.Language;

import java.awt.Window;
import java.awt.event.WindowEvent;
import java.util.function.Consumer;

import javax.swing.JDialog;

public class ViewContractDialog extends ParentDialog implements Consumer<Integer> {

    private Language lang;
    private TableView table;
    private CommandButton insertContract;
    private CommandButton deleteContract;
    private CommandButton exportContract;
    private CommandButton cancelContract;
    private DisplayTableContract displayContract;

    public ViewContractDialog(Window parent) {
        super(parent);
        setSize(500, 350);
        setResizable(false);
        setLocationRelativeTo(parent);
        initComponents();
        initEvents();
        iniLanguage();
    }

    private int idClient;

    @Override
    public void accept(Integer idClient) {
        if (idClient == null)
            return;

        this.idClient = idClient;

        displayContract.bind();
        displayContract.fill(table, new FilterContractByClientID(idClient));

        table.getColumnModel().getColumn(0).setMaxWidth(30);
        table.getColumnModel().getColumn(0).setHeaderValue(null);
        table.getColumnModel().getColumn(1).setHeaderValue(lang.getText("date-start"));
        table.getColumnModel().getColumn(2).setHeaderValue(lang.getText("amount-paid"));
        table.getColumnModel().getColumn(3).setHeaderValue(lang.getText("amount-total"));
        table.getColumnModel().getColumn(4).setHeaderValue(lang.getText("date-end"));
        table.getColumnModel().getColumn(5).setHeaderValue(lang.getText("state"));
    }

    private void initComponents() {
        lang = new Language();
        table = new TableView();
        insertContract = new CommandButton();
        exportContract = new CommandButton();
        deleteContract = new CommandButton();
        cancelContract = new CommandButton();
        displayContract = new DisplayTableContract();

        contentPane.setContent(table.getScrollTable());
        contentPane.addButton(exportContract);
        contentPane.addButton(deleteContract);
        contentPane.addButton(insertContract);
        contentPane.addButton(cancelContract);

        deleteContract.setEnabled(false);
        exportContract.setEnabled(false);
    }

    private void initEvents() {

        table.getSelectionModel().addListSelectionListener(_ -> {
            deleteContract.setEnabled(table.getSelectedRow() != -1);
            exportContract.setEnabled(table.getSelectedRow() != -1);
        });

        cancelContract.setCommand(this::dispose);

        exportContract.setCommand(() -> {
            IReporter reporter = new ContractReporter(displayContract.idSelection());
            ILogger logger = new ReporterLogger("Generate Reporter Client");
            ReporterService rs = new ReporterService(reporter, logger);
            rs.generateReport();
        });

        insertContract.setCommand(() -> {
            Consumer<Integer> dialog = new SendContractDialog(this);
            dialog.accept(idClient);
            ((JDialog) dialog).setVisible(true);
        });

        deleteContract.setCommand(() -> {
            IControl control = new RemoveContractControl(displayContract.idSelection());
            ILogger logger = new ControlLogger("Remove Contract ID: " + displayContract.idSelection());
            ControlService cs = new ControlService(control, logger);
            cs.controlService();
        });

        table.addDoubleClickEvent(() -> {
            Consumer<Integer> dialog = new ViewPaymentDialog(this);
            dialog.accept(displayContract.idSelection());
            ((JDialog) dialog).setVisible(true);
        });
    }

    private void iniLanguage() {
        contentPane.setTitle(lang.getText("contracts"), lang.getText("viewer"));

        deleteContract.setText(lang.getText("delete"));
        exportContract.setText(lang.getText("export"));
        insertContract.setText(lang.getText("insert"));
        cancelContract.setText(lang.getText("cancel"));
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING)
            displayContract.unbind();
        super.processWindowEvent(e);
    }
}
