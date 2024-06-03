package com.paymentpoint.controller.filler;

import java.util.Comparator;

import javax.swing.table.AbstractTableModel;

import com.paymentpoint.controller.settings.Language;
import com.paymentpoint.data.entities.Plan;
import com.paymentpoint.data.entities.Registrable;

public class DisplayTablePlan extends AbstractDisplayTable<Plan> {

    private final Language lang = new Language();

    @Override
    protected Class<? extends Registrable> classRegister() {
        return Plan.class;
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
                    case 1 -> registers[rowIndex].getName();
                    case 2 -> String.format("$ %.2f", registers[rowIndex].getAmount());
                    case 3 -> String.format("%.2f %%", registers[rowIndex].getTax() * 100);
                    case 4 -> registers[rowIndex].getSteps();
                    case 5 -> lang.getText(registers[rowIndex].getState().name());
                    default -> null;
                };
            }
        };
    }

    @Override
    protected Comparator<Plan> comparator() {
        return Comparator.comparing(Plan::getName);
    }
}
