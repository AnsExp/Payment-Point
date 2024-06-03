package com.paymentpoint.controller.report;

public class ContractReporter implements IReporter {

    private final int idContract;

    public ContractReporter(int idContract) {
        this.idContract = idContract;
    }

    @Override
    public void generate() throws Exception {
        System.out.println(idContract);
    }
}
