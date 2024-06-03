package com.paymentpoint.controller.validation;

import com.paymentpoint.data.entities.Plan;

public class PlanValidator implements IValidator<Plan> {

    @Override
    public boolean verify(Plan o) {
        return o != null;
    }

    @Override
    public String messageInvalidation() {
        return "";
    }
}
