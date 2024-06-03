package com.paymentpoint.controller.validation;

import com.paymentpoint.data.entities.Contract;

public class ContractValidator implements IValidator<Contract> {

    @Override
    public boolean verify(Contract o) {
        return o != null;
    }

    @Override
    public String messageInvalidation() {
        return "";
    }
}
