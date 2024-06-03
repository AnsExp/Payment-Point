package com.paymentpoint.controller.filler.filter;

import com.paymentpoint.data.entities.Contract;

import java.util.function.Predicate;

public class FilterContractByClientID implements Predicate<Contract> {

    private final int idClient;

    public FilterContractByClientID(int idClient) {
        this.idClient = idClient;
    }

    @Override
    public boolean test(Contract o) {
        return o.getIdClient() == idClient;
    }
}
