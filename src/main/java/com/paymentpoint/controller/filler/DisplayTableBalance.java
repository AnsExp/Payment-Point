package com.paymentpoint.controller.filler;

import java.util.Comparator;

import javax.swing.table.AbstractTableModel;

import com.paymentpoint.data.access.source.ClientSource;
import com.paymentpoint.data.entities.Balance;
import com.paymentpoint.data.entities.Registrable;

public class DisplayTableBalance extends AbstractDisplayTable<Balance> {

    @Override
    protected AbstractTableModel model() {
        return new AbstractTableModel() {

            @Override
            public int getRowCount() {
                return registers.length;
            }

            @Override
            public int getColumnCount() {
                return 5;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                float debit = registers[rowIndex].getDebit();
                float credit = registers[rowIndex].getCredit();
                float difference = debit - credit;
                return switch (columnIndex) {
                    case 0 -> rowIndex + 1;
                    case 1 -> ClientSource.clientDisplay(registers[rowIndex].getIdClient());
                    case 2 -> String.format("$ %.2f", credit);
                    case 3 -> String.format("$ %.2f", debit);
                    case 4 -> String.format("$ %c%.2f", difference == 0 ? 0 : difference > 0 ? '+' : 0, difference);
                    default -> null;
                };
            }
        };
    }

    @Override
    protected Class<? extends Registrable> classRegister() {
        return Balance.class;
    }

    @Override
    protected Comparator<Balance> comparator() {
        return null;
    }
}
