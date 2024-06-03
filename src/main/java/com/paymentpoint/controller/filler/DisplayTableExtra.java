package com.paymentpoint.controller.filler;

import com.paymentpoint.data.access.source.ClientSource;
import com.paymentpoint.data.entities.Extra;
import com.paymentpoint.data.entities.Registrable;

import java.util.Comparator;

import javax.swing.table.AbstractTableModel;

public class DisplayTableExtra extends AbstractDisplayTable<Extra> {

    @Override
    protected AbstractTableModel model() {
        return new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return registers.length;
            }

            @Override
            public int getColumnCount() {
                return 3;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return switch (columnIndex) {
                    case 0 -> rowIndex + 1;
                    case 1 -> ClientSource.clientDisplay(registers[rowIndex].getIdClient());
                    case 2 -> String.format("$ %.2f", registers[rowIndex].getAmountExtra());
                    default -> null;
                };
            }
        };
    }

    @Override
    protected Class<? extends Registrable> classRegister() {
        return Extra.class;
    }

    @Override
    protected Comparator<Extra> comparator() {
        return null;
    }
}
