package com.paymentpoint.controller.filler;

import java.util.Comparator;

import javax.swing.table.AbstractTableModel;

import com.paymentpoint.data.entities.Client;
import com.paymentpoint.data.entities.Registrable;

public class DisplayTableClient extends AbstractDisplayTable<Client> {

    @Override
    protected Class<? extends Registrable> classRegister() {
        return Client.class;
    }

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
                return switch (columnIndex) {
                    case 0 -> rowIndex + 1;
                    case 1 -> registers[rowIndex].getName();
                    case 2 -> registers[rowIndex].getLastname();
                    case 3 -> registers[rowIndex].getPhone();
                    case 4 -> registers[rowIndex].getIdCard();
                    default -> null;
                };
            }
        };
    }

    @Override
    protected Comparator<Client> comparator() {
        return Comparator.comparing(Client::getName);
    }
}
