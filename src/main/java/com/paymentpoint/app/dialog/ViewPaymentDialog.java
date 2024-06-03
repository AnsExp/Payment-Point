package com.paymentpoint.app.dialog;

import java.awt.Window;
import java.awt.event.WindowEvent;
import java.util.function.Consumer;

import javax.swing.JDialog;

import com.paymentpoint.app.component.CommandButton;
import com.paymentpoint.app.viewer.TableView;
import com.paymentpoint.controller.filler.DisplayTablePayment;
import com.paymentpoint.controller.filler.filter.FilterPaymentByContractID;
import com.paymentpoint.controller.log.ILogger;
import com.paymentpoint.controller.log.ReporterLogger;
import com.paymentpoint.controller.report.IReporter;
import com.paymentpoint.controller.report.PaymentReporter;
import com.paymentpoint.controller.report.ReporterService;
import com.paymentpoint.controller.settings.Language;

public class ViewPaymentDialog extends ParentDialog implements Consumer<Integer> {

    private Language lang;
    private TableView table;
    private CommandButton exportButton;
    private CommandButton cancelButton;
    private CommandButton sanctionButton;
    private DisplayTablePayment displayPayment;

    public ViewPaymentDialog(Window parent) {
        super(parent);
        setSize(450, 350);
        setLocationRelativeTo(parent);
        setResizable(false);
        initComponents();
        initEvents();
        iniLanguage();
    }

    private int idContract;

    @Override
    public void accept(Integer idContract) {
        if (idContract == null)
            return;

        this.idContract = idContract;

        displayPayment.bind();
        displayPayment.fill(table, new FilterPaymentByContractID(idContract));

        table.getColumnModel().getColumn(0).setMaxWidth(30);
        table.getColumnModel().getColumn(0).setHeaderValue(null);
        table.getColumnModel().getColumn(1).setHeaderValue(lang.getText("date-mandatory"));
        table.getColumnModel().getColumn(2).setHeaderValue(lang.getText("amount-paid"));
        table.getColumnModel().getColumn(3).setHeaderValue(lang.getText("date-payment"));
        table.getColumnModel().getColumn(4).setHeaderValue(lang.getText("state"));
    }

    private void initComponents() {
        lang = new Language();
        table = new TableView();
        exportButton = new CommandButton();
        cancelButton = new CommandButton();
        sanctionButton = new CommandButton();
        displayPayment = new DisplayTablePayment();

        contentPane.setContent(table.getScrollTable());
        contentPane.addButton(exportButton);
        contentPane.addButton(sanctionButton);
        contentPane.addButton(cancelButton);

        exportButton.setEnabled(false);
    }

    private void initEvents() {
        cancelButton.setCommand(this::dispose);

        table.getSelectionModel().addListSelectionListener(_ -> {
            exportButton.setEnabled(!displayPayment.paymentDone());
        });

        exportButton.setCommand(() -> {
            IReporter reporter = new PaymentReporter(displayPayment.idSelection());
            ILogger logger = new ReporterLogger("Generate Reporter Payment");
            ReporterService rs = new ReporterService(reporter, logger);
            rs.generateReport();
        });

        sanctionButton.setCommand(() -> {
            SendSanctionDialog dialog = new SendSanctionDialog(this);
            dialog.accept(idContract);
            dialog.setVisible(true);
        });

        table.addDoubleClickEvent(() -> {
            if (displayPayment.idSelection() == -1)
                return;

            if (table.getModel().getValueAt(table.getSelectedRow(), 4) == lang.getText("SANCTION"))
                return;

            Consumer<Integer> dialog = new SendPaymentDialog(this);
            dialog.accept(displayPayment.idSelection());
            ((JDialog) dialog).setVisible(true);
        });
    }

    private void iniLanguage() {
        contentPane.setTitle(lang.getText("payments"), lang.getText("viewer"));

        exportButton.setText(lang.getText("export"));
        cancelButton.setText(lang.getText("cancel"));
        sanctionButton.setText(lang.getText("sanction"));
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING)
            displayPayment.unbind();
        super.processWindowEvent(e);
    }
}
