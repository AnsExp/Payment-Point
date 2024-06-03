package com.paymentpoint.controller.filler;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.paymentpoint.data.entities.Registrable;

public abstract class AbstractDisplayTable<K extends Registrable> extends AbstractFiller<JTable, K> {

    @Override
    public void select(int id) {
        component.setRowSelectionInterval(index(id), index(id));
    }

    @Override
    public int idSelection() {
        return component.getSelectedRow() != -1 ?
                registers[this.component.getSelectedRow()].getIdRegister() : -1;
    }

    @Override
    protected void refresh() {
        ((AbstractTableModel) component.getModel()).fireTableDataChanged();
    }

    @Override
    protected void fill() {
        component.setModel(model());
    }

    protected abstract AbstractTableModel model();
}
