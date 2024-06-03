package com.paymentpoint.app.dialog;

import com.paymentpoint.app.viewer.Pane;

import javax.swing.JDialog;
import java.awt.Window;

public abstract class ParentDialog extends JDialog {

    protected Pane contentPane;

    public ParentDialog(Window parent) {
        super(parent);
        contentPane = new Pane();
        setContentPane(contentPane);
    }
}