package com.paymentpoint.controller.filler;

import com.paymentpoint.data.entities.Plan;
import com.paymentpoint.data.entities.Registrable;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class DisplayComboPlan extends AbstractDisplayCombo<String, Plan> {

    @Override
    protected String displayItem(@NotNull Plan register) {
        return register.getName() + " — $" + register.getAmount();
    }

    @Override
    protected Comparator<Plan> comparator() {
        return Comparator.comparing(Plan::getName);
    }

    @Override
    protected Class<? extends Registrable> classRegister() {
        return Plan.class;
    }
}
