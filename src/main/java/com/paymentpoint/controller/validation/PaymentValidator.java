package com.paymentpoint.controller.validation;

import com.paymentpoint.data.entities.Payment;

public class PaymentValidator implements IValidator<Payment> {

    private String msg;

    @Override
    public boolean verify(Payment o) {

        if (o.getAmountPayment() != o.getAmountMandatory() && o.getAmountMandatory() != 0) {
            msg = "Amount Not Mandatory";
            return false;
        }

        return o != null;
    }

    @Override
    public String messageInvalidation() {
        return msg;
    }
}
