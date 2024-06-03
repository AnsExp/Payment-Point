package com.paymentpoint.controller.filler;

import javax.swing.table.AbstractTableModel;

import com.paymentpoint.controller.settings.Language;
import com.paymentpoint.data.entities.Contract;
import com.paymentpoint.data.entities.Registrable;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Comparator;

public class DisplayTableContract extends AbstractDisplayTable<Contract> {

    @Override
    protected Class<? extends Registrable> classRegister() {
        return Contract.class;
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
                return 6;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return switch (columnIndex) {
                    case 0 -> rowIndex + 1;
                    case 1 -> registers[rowIndex].getDateStart() == null ? null
                            : format.format(registers[rowIndex].getDateStart());
                    case 2 -> String.format("$ %.2f", registers[rowIndex].getAmountPaid());
                    case 3 -> String.format("$ %.2f", registers[rowIndex].getAmountTotal());
                    case 4 -> registers[rowIndex].getDateEnd() == null ? null
                            : format.format(registers[rowIndex].getDateEnd());
                    case 5 -> language.getText(registers[rowIndex].getState().name());
                    default -> null;
                };
            }
        };
    }

    public DisplayTableContract() {
        language = new Language();
        format = new SimpleDateFormat("dd/MM/yyyy");
    }

    private final Format format;
    private final Language language;

    @Override
    protected Comparator<Contract> comparator() {
        return Comparator.comparing(Contract::getDateStart);
    }
}
