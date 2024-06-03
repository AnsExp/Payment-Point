package com.paymentpoint.controller.command;

import com.paymentpoint.data.access.ISource;
import com.paymentpoint.data.access.source.PlanSource;
import com.paymentpoint.data.entities.Plan;

import java.util.function.Consumer;

public class FindPlanControl implements IControl {

    private final int id;
    private final Consumer<String> nameFiller, stateFiller;
    private final Consumer<Integer> idFiller, stepsFiller;
    private final Consumer<Float> amountFiller, taxFiller, paymentFiller;

    public FindPlanControl(int id, Consumer<Integer> idFiller, Consumer<String> nameFiller, Consumer<Integer> stepsFiller,
                           Consumer<Float> amountFiller, Consumer<Float> taxFiller, Consumer<Float> paymentFiller,
                           Consumer<String> stateFiller) {
        this.id = id;
        this.idFiller = idFiller;
        this.taxFiller = taxFiller;
        this.nameFiller = nameFiller;
        this.stateFiller = stateFiller;
        this.stepsFiller = stepsFiller;
        this.amountFiller = amountFiller;
        this.paymentFiller = paymentFiller;
    }

    @Override
    public void execute() throws Exception {

        ISource<Plan> finder = new PlanSource();

        Plan plan = finder.get(id);

        if (plan == null)
            throw new Exception("Plan Not Found");

        if (taxFiller != null)
            taxFiller.accept(plan.getTax());
        if (idFiller != null)
            idFiller.accept(plan.getIdPlan());
        if (nameFiller != null)
            nameFiller.accept(plan.getName());
        if (stepsFiller != null)
            stepsFiller.accept(plan.getSteps());
        if (amountFiller != null)
            amountFiller.accept(plan.getAmount());
        if (paymentFiller != null)
            paymentFiller.accept(plan.getPayment());
        if (stateFiller != null)
            stateFiller.accept(plan.getState().name());
    }
}
