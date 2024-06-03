package com.paymentpoint.data.build;

import com.paymentpoint.data.entities.Plan;
import com.paymentpoint.data.entities.State;

public class PlanBuilder implements IBuild<Plan> {

    private final Plan plan;

    private PlanBuilder() {
        this.plan = new Plan();
    }

    public static PlanBuilder getInstance() {
        return new PlanBuilder();
    }

    public PlanBuilder id(int id) {
        plan.setIdPlan(id);
        return this;
    }

    public PlanBuilder name(String name) {
        plan.setName(name);
        return this;
    }

    public PlanBuilder steps(int steps) {
        plan.setSteps(steps);
        return this;
    }

    public PlanBuilder amount(float amount) {
        plan.setAmount(amount);
        return this;
    }

    public PlanBuilder tax(float tax) {
        plan.setTax(tax);
        return this;
    }

    public PlanBuilder amountPayment(float amountPayment) {
        plan.setPayment(amountPayment);
        return this;
    }

    public PlanBuilder state(State state) {
        plan.setState(state);
        return this;
    }

    @Override
    public Plan build() {
        return plan;
    }
}
