package com.paymentpoint.controller.report;

public class PaymentReporter implements IReporter {

    private final int idPayment;

    public PaymentReporter(int idPayment) {
        this.idPayment = idPayment;
    }

    @Override
    public void generate() throws Exception {
        System.out.println(idPayment);
    }
}
