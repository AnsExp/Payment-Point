package com.paymentpoint.controller.filler;

import com.paymentpoint.data.entities.Registrable;

import javax.swing.JComboBox;

public abstract class AbstractDisplayCombo<T, E extends Registrable> extends AbstractFiller<JComboBox<T>, E> {

    @Override
    protected void refresh() {
        component.removeAllItems();
        fill();
    }

    @Override
    protected void fill() {
        for (E register : registers)
            component.addItem(displayItem(register));
    }

    @Override
    public void select(int id) {
        component.setSelectedIndex(index(id));
    }

    @Override
    public int idSelection() {
        return component.getSelectedIndex() != -1 ? registers[component.getSelectedIndex()].getIdRegister() : -1;
    }

    protected abstract T displayItem(E register);
}
