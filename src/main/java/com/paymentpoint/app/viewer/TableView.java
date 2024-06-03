package com.paymentpoint.app.viewer;

import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableView extends JTable {

    public TableView() {
        setBorder(null);
        setFocusable(false);
        setRowHeight(30);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        getTableHeader().setReorderingAllowed(false);
        // setRowSorter(new TableRowSorter<>());
    }

    public Component getScrollTable() {
        JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setBorder(null);
        scrollPane.setFocusable(false);
        return scrollPane;
    }

    public void addDoubleClickEvent(Runnable runnable) {

        if (runnable == null)
            return;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() < 2)
                    return;
                runnable.run();
            }
        });
    }
}
