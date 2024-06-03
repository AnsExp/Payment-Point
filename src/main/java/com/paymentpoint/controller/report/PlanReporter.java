package com.paymentpoint.controller.report;

public class PlanReporter implements IReporter {

    private final int idPlan;

    public PlanReporter(int idPlan) {
        this.idPlan = idPlan;
    }

    @Override
    public void generate() throws Exception {
        System.out.println(idPlan);
    }
}
