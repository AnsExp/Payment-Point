package com.paymentpoint.controller.filler;

import com.paymentpoint.controller.settings.Language;
import com.paymentpoint.data.entities.Payment;
import com.paymentpoint.data.entities.Registrable;

import javax.swing.table.AbstractTableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;

public class DisplayTablePayment extends AbstractDisplayTable<Payment> {

    private final Language lang = new Language();
    private final DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

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
                    case 1 -> registers[rowIndex].getDateMandatory() == null ? null
                            : format.format(registers[rowIndex].getDateMandatory());
                    case 2 -> String.format("$ %.2f", registers[rowIndex].getAmountPayment());
                    case 3 -> registers[rowIndex].getDatePayment() == null ? null
                            : format.format(registers[rowIndex].getDatePayment());
                    case 4 -> registers[rowIndex].getState() == null ? null
                            : lang.getText(registers[rowIndex].getState().name());
                    default -> null;
                };
            }
        };
    }

    public boolean paymentDone() {
        if (component.getSelectedRow() == -1)
            return false;
        return registers[component.getSelectedRow()].getAmountPayment() == 0;
    }

    @Override
    protected Class<? extends Registrable> classRegister() {
        return Payment.class;
    }

    @Override
    protected Comparator<Payment> comparator() {
        return Comparator.comparing(Payment::getAmountMandatory);
    }
}
