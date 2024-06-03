package com.paymentpoint.app.viewer;

import com.paymentpoint.controller.settings.Settings;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class PrincipalWindow extends JFrame {

    public PrincipalWindow() {
        setTitle("Payment Point");
        setIconImage(new ImageIcon("src/main/resources/icon.png").getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setAlwaysOnTop(
                Settings.getInstance().getPropertyBoolean("always-on-top"));
        setExtendedState(
                Settings.getInstance().getPropertyInteger("extended-state-screen"));
        setSize(
                Settings.getInstance().getPropertyInteger("width-size-frame"),
                Settings.getInstance().getPropertyInteger("height-size-frame"));
        setLocation(
                Settings.getInstance().getPropertyInteger("location-horizontal-frame"),
                Settings.getInstance().getPropertyInteger("location-vertical-frame"));
        setMinimumSize(
                new Dimension(
                        Settings.getInstance().getPropertyInteger("min-width-size-frame"),
                        Settings.getInstance().getPropertyInteger("min-height-size-frame")));
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {

        if (e.getID() == WindowEvent.WINDOW_CLOSING) {

            Settings.getInstance().setProperty("always-on-top", isAlwaysOnTop());
            Settings.getInstance().setProperty("extended-state-screen", getExtendedState());

            if (getExtendedState() != 6) {

                Settings.getInstance().setProperty("width-size-frame", getWidth());
                Settings.getInstance().setProperty("height-size-frame", getHeight());
                Settings.getInstance().setProperty("location-vertical-frame", getLocation().y);
                Settings.getInstance().setProperty("location-horizontal-frame", getLocation().x);

            }

            try {
                Settings.getInstance().storePreferenceFile();
            } catch (IOException e1) {
                e1.printStackTrace(System.out);
            }
        }

        super.processWindowEvent(e);
    }
}
