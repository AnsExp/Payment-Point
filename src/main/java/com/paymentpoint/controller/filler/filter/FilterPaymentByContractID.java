package com.paymentpoint.controller.filler.filter;

import com.paymentpoint.data.entities.Payment;

import java.util.function.Predicate;

public class FilterPaymentByContractID implements Predicate<Payment> {

    private final int idContract;

    public FilterPaymentByContractID(int idContract) {
        this.idContract = idContract;
    }

    @Override
    public boolean test(Payment o) {
        return o.getIdContract() == idContract;
    }
}
