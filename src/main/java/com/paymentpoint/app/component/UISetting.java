package com.paymentpoint.app.component;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public final class UISetting {

    private UISetting() {
    }

    public static void settingLabel(JLabel label, int horizontalPosition, int verticalPosition) {
        label.setVerticalAlignment(verticalPosition);
        label.setHorizontalAlignment(horizontalPosition);
    }

    public static void settingComponent(JComponent component) {
        component.setBorder(new EmptyBorder(5, 5, 5, 5));
    }
}
