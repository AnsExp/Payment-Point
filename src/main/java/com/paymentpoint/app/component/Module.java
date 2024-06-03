package com.paymentpoint.app.component;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 * This class represents a module that extends JComponent.
 */
public class Module extends JComponent {
    /**
     * Constructor for the Module class.
     * Sets the layout to GridBagLayout.
     */
    public Module() {
        setLayout(new GridBagLayout());
    }

    /**
     * This method adds a component to the module with specified constraints.
     *
     * @param component   The component to be added.
     * @param gridX       The cell, in the grid bag layout, where the leading edge of the component is to be placed.
     * @param gridY       The cell, in the grid bag layout, where the top edge of the component is to be placed.
     * @param rowCount    The number of cells in a row for the component's display area.
     * @param columnCount The number of cells in a column for the component's display area.
     * @param weightX     Specifies how to distribute extra horizontal space.
     * @param weightY     Specifies how to distribute extra vertical space.
     */
    public void addComponent(Component component, int gridX, int gridY, int rowCount, int columnCount, double weightX, double weightY) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = gridX;
        c.gridy = gridY;
        c.weightx = weightX;
        c.weighty = weightY;
        c.gridheight = rowCount;
        c.gridwidth = columnCount;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(2, 2, 2, 2);
        add(component == null ? new JPanel() : component, c);
    }
}
