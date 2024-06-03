package com.paymentpoint.controller.filler.filter;

import com.paymentpoint.data.entities.Plan;
import com.paymentpoint.data.entities.State;

import java.util.function.Predicate;

public class FilterPlanEnabled implements Predicate<Plan> {
    @Override
    public boolean test(Plan o) {
        return o.getState() == State.ENABLED;
    }
}
