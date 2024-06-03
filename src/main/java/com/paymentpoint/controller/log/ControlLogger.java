package com.paymentpoint.controller.log;

import javax.swing.JOptionPane;

public class ControlLogger implements ILogger {

    public ControlLogger(String action) {
    }

    @Override
    public void info(String info) {
    }

    @Override
    public void warning(String warning) {
    }

    @Override
    public void error(String msg, Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), msg, JOptionPane.ERROR_MESSAGE);
    }
}
