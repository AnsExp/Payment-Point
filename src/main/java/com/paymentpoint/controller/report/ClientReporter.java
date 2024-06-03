package com.paymentpoint.controller.report;

public class ClientReporter implements IReporter {

    private final int idClient;

    public ClientReporter(int idClient) {
        this.idClient = idClient;
    }

    @Override
    public void generate() throws Exception {
        System.out.println(idClient);
    }
}
