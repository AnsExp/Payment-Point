package com.paymentpoint.app.viewer;

import javax.swing.JTabbedPane;
import java.awt.Component;

public class TabPane extends JTabbedPane {

    public int getComponentIndex(Component comp) {
        for (int i = 0; i < getComponents().length; i++)
            if (getComponent(i) == comp)
                return i;
        return -1;
    }
}
