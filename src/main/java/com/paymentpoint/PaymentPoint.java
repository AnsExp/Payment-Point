package com.paymentpoint;

import com.paymentpoint.app.component.CommandButton;
import com.paymentpoint.app.dialog.SendClientDialog;
import com.paymentpoint.app.dialog.SendPlanDialog;
import com.paymentpoint.app.dialog.ViewContractDialog;
import com.paymentpoint.app.viewer.Pane;
import com.paymentpoint.app.viewer.PrincipalWindow;
import com.paymentpoint.app.viewer.TabPane;
import com.paymentpoint.app.viewer.TableView;
import com.paymentpoint.controller.command.ControlService;
import com.paymentpoint.controller.command.IControl;
import com.paymentpoint.controller.command.RemoveClientControl;
import com.paymentpoint.controller.command.RemovePlanControl;
import com.paymentpoint.controller.filler.DisplayTableBalance;
import com.paymentpoint.controller.filler.DisplayTableClient;
import com.paymentpoint.controller.filler.DisplayTableExtra;
import com.paymentpoint.controller.filler.DisplayTablePlan;
import com.paymentpoint.controller.log.ControlLogger;
import com.paymentpoint.controller.log.ILogger;
import com.paymentpoint.controller.log.ReporterLogger;
import com.paymentpoint.controller.report.ClientReporter;
import com.paymentpoint.controller.report.IReporter;
import com.paymentpoint.controller.report.ReporterService;
import com.paymentpoint.controller.settings.Language;
import com.paymentpoint.controller.settings.Settings;

import java.awt.Window;
import java.util.function.Consumer;

import javax.swing.JDialog;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

final class PaymentPoint implements Runnable {

    private final Window window;
    private final TabPane tabPane;

    private final Pane panePlans;
    private final Pane paneExtras;
    private final Pane paneClients;
    private final Pane paneBalances;

    private final TableView tablePlans;
    private final TableView tableExtras;
    private final TableView tableClients;
    private final TableView tableBalances;

    private final CommandButton insertPlan;
    private final CommandButton deletePlan;
    private final CommandButton updatePlan;
    private final CommandButton insertClient;
    private final CommandButton deleteClient;
    private final CommandButton updateClient;
    private final CommandButton exportClient;

    private final DisplayTablePlan displayPlan;
    private final DisplayTableExtra displayExtra;
    private final DisplayTableClient displayClient;
    private final DisplayTableBalance displayBalance;

    static {
        try {
            UIManager.setLookAndFeel(Settings.getInstance().getPropertyString("theme-system"));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace(System.out);
        }
    }

    private PaymentPoint() {
        window = new PrincipalWindow();
        tabPane = new TabPane();

        panePlans = new Pane();
        paneExtras = new Pane();
        paneClients = new Pane();
        paneBalances = new Pane();

        tablePlans = new TableView();
        tableExtras = new TableView();
        tableClients = new TableView();
        tableBalances = new TableView();

        insertPlan = new CommandButton();
        deletePlan = new CommandButton();
        updatePlan = new CommandButton();
        insertClient = new CommandButton();
        deleteClient = new CommandButton();
        updateClient = new CommandButton();
        exportClient = new CommandButton();

        displayPlan = new DisplayTablePlan();
        displayExtra = new DisplayTableExtra();
        displayClient = new DisplayTableClient();
        displayBalance = new DisplayTableBalance();
    }

    private void initComponents() {
        window.add(tabPane);

        tabPane.add(paneClients);
        tabPane.add(panePlans);
        tabPane.add(paneBalances);
        tabPane.add(paneExtras);

        panePlans.setContent(tablePlans.getScrollTable());
        paneExtras.setContent(tableExtras.getScrollTable());
        paneClients.setContent(tableClients.getScrollTable());
        paneBalances.setContent(tableBalances.getScrollTable());

        panePlans.addButton(deletePlan);
        panePlans.addButton(updatePlan);
        panePlans.addButton(insertPlan);

        paneClients.addButton(exportClient);
        paneClients.addButton(deleteClient);
        paneClients.addButton(updateClient);
        paneClients.addButton(insertClient);

        deletePlan.setEnabled(false);
        updatePlan.setEnabled(false);
        exportClient.setEnabled(false);
        deleteClient.setEnabled(false);
        updateClient.setEnabled(false);

        window.setVisible(true);
    }

    private void initTables() {
        displayPlan.bind();
        displayExtra.bind();
        displayClient.bind();
        displayBalance.bind();
        displayPlan.fillAll(tablePlans);
        displayExtra.fillAll(tableExtras);
        displayClient.fillAll(tableClients);
        displayBalance.fillAll(tableBalances);
        tablePlans.getColumnModel().getColumn(0).setMaxWidth(30);
        tableExtras.getColumnModel().getColumn(0).setMaxWidth(30);
        tableClients.getColumnModel().getColumn(0).setMaxWidth(30);
        tableBalances.getColumnModel().getColumn(0).setMaxWidth(30);
    }

    private void initEvents() {
        tablePlans.getSelectionModel().addListSelectionListener(_ -> {
            deletePlan.setEnabled(tablePlans.getSelectedRow() != -1);
            updatePlan.setEnabled(tablePlans.getSelectedRow() != -1);
        });

        tableClients.getSelectionModel().addListSelectionListener(_ -> {
            deleteClient.setEnabled(tableClients.getSelectedRow() != -1);
            exportClient.setEnabled(tableClients.getSelectedRow() != -1);
            updateClient.setEnabled(tableClients.getSelectedRow() != -1);
        });

        deletePlan.setCommand(() -> {
            IControl control = new RemovePlanControl(displayPlan.idSelection());
            ILogger logger = new ControlLogger("Remove Plan ID: " + displayPlan.idSelection());
            ControlService cs = new ControlService(control, logger);
            cs.controlService();
        });

        deleteClient.setCommand(() -> {
            IControl control = new RemoveClientControl(displayClient.idSelection());
            ILogger logger = new ControlLogger("Remove Client ID: " + displayClient.idSelection());
            ControlService cs = new ControlService(control, logger);
            cs.controlService();
        });

        exportClient.setCommand(() -> {
            IReporter reporter = new ClientReporter(displayClient.idSelection());
            ILogger logger = new ReporterLogger("Export Client ID: " + displayClient.idSelection());
            ReporterService rs = new ReporterService(reporter, logger);
            rs.generateReport();
        });

        updatePlan.setCommand(() -> {
            Consumer<Integer> dialog = new SendPlanDialog(window);
            dialog.accept(displayPlan.idSelection());
            ((JDialog) dialog).setVisible(true);
        });

        updateClient.setCommand(() -> {
            Consumer<Integer> dialog = new SendClientDialog(window);
            dialog.accept(displayClient.idSelection());
            ((JDialog) dialog).setVisible(true);
        });

        tableClients.addDoubleClickEvent(() -> {
            Consumer<Integer> dialog = new ViewContractDialog(window);
            dialog.accept(displayClient.idSelection());
            ((JDialog) dialog).setVisible(true);
        });

        insertPlan.setCommand(() -> new SendPlanDialog(window).setVisible(true));

        insertClient.setCommand(() -> new SendClientDialog(window).setVisible(true));
    }

    private void initLanguage() {
        Language lang = new Language();

        panePlans.setTitle(lang.getText("plans"));
        paneExtras.setTitle(lang.getText("extras"));
        paneClients.setTitle(lang.getText("clients"));
        paneBalances.setTitle(lang.getText("balances"));

        insertPlan.setText(lang.getText("insert"));
        deletePlan.setText(lang.getText("delete"));
        updatePlan.setText(lang.getText("update"));
        insertClient.setText(lang.getText("insert"));
        deleteClient.setText(lang.getText("delete"));
        updateClient.setText(lang.getText("update"));
        exportClient.setText(lang.getText("export"));

        tabPane.setTitleAt(tabPane.getComponentIndex(panePlans), lang.getText("plans"));
        tabPane.setTitleAt(tabPane.getComponentIndex(paneExtras), lang.getText("extras"));
        tabPane.setTitleAt(tabPane.getComponentIndex(paneClients), lang.getText("clients"));
        tabPane.setTitleAt(tabPane.getComponentIndex(paneBalances), lang.getText("balances"));

        tablePlans.getColumnModel().getColumn(0).setHeaderValue(null);
        tablePlans.getColumnModel().getColumn(1).setHeaderValue(lang.getText("name"));
        tablePlans.getColumnModel().getColumn(2).setHeaderValue(lang.getText("amount"));
        tablePlans.getColumnModel().getColumn(3).setHeaderValue(lang.getText("tax"));
        tablePlans.getColumnModel().getColumn(4).setHeaderValue(lang.getText("steps"));
        tablePlans.getColumnModel().getColumn(5).setHeaderValue(lang.getText("state"));

        tableExtras.getColumnModel().getColumn(0).setHeaderValue(null);
        tableExtras.getColumnModel().getColumn(1).setHeaderValue(lang.getText("client"));
        tableExtras.getColumnModel().getColumn(2).setHeaderValue(lang.getText("amount-extra"));

        tableClients.getColumnModel().getColumn(0).setHeaderValue(null);
        tableClients.getColumnModel().getColumn(1).setHeaderValue(lang.getText("name"));
        tableClients.getColumnModel().getColumn(2).setHeaderValue(lang.getText("lastname"));
        tableClients.getColumnModel().getColumn(3).setHeaderValue(lang.getText("phone"));
        tableClients.getColumnModel().getColumn(4).setHeaderValue(lang.getText("id-card"));

        tableBalances.getColumnModel().getColumn(0).setHeaderValue(null);
        tableBalances.getColumnModel().getColumn(1).setHeaderValue(lang.getText("client"));
        tableBalances.getColumnModel().getColumn(2).setHeaderValue(lang.getText("credit"));
        tableBalances.getColumnModel().getColumn(3).setHeaderValue(lang.getText("debit"));
    }

    @Override
    public void run() {
        initComponents();
        initTables();
        initEvents();
        initLanguage();
    }

    public static void main(String[] args) {
        new PaymentPoint().run();
    }
}
