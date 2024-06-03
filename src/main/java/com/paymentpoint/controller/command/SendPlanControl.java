package com.paymentpoint.controller.command;

import com.paymentpoint.controller.associate.ConvertState;
import com.paymentpoint.controller.validation.IValidator;
import com.paymentpoint.controller.validation.PlanValidator;
import com.paymentpoint.data.access.ISource;
import com.paymentpoint.data.access.source.PlanSource;
import com.paymentpoint.data.build.PlanBuilder;
import com.paymentpoint.data.entities.Plan;
import com.paymentpoint.data.entities.State;

public class SendPlanControl implements IControl {

    private int id;
    private final int steps;
    private final String name, state;
    private final float tax, amount, payment;

    public SendPlanControl(int id, String name, int steps, float tax, float amount, float payment, String state) {
        this(name, steps, tax, amount, payment, state);
        this.id = id;
    }

    public SendPlanControl(String name, int steps, float tax, float amount, float payment, String state) {
        this.tax = tax;
        this.name = name;
        this.state = state;
        this.steps = steps;
        this.amount = amount;
        this.payment = payment;
    }

    @Override
    public void execute() throws Exception {

        ConvertState cs = new ConvertState();
        State state = cs.getState(this.state);

        PlanBuilder builder = PlanBuilder.getInstance()
                .id(id)
                .tax(tax)
                .name(name)
                .state(state)
                .steps(steps)
                .amount(amount)
                .amountPayment(payment);

        IValidator<Plan> validator = new PlanValidator();
        ISource<Plan> source = new PlanSource();

        if (validator.verify(builder.build()))
            source.save(builder.build());
        else
            throw new Exception(validator.messageInvalidation());
    }
}
