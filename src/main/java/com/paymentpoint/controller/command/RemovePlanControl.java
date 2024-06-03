package com.paymentpoint.controller.command;

import com.paymentpoint.data.access.ISource;
import com.paymentpoint.data.access.source.PlanSource;
import com.paymentpoint.data.entities.Plan;

public class RemovePlanControl implements IControl {

    private final int idPlan;

    public RemovePlanControl(int idPlan) {
        this.idPlan = idPlan;
    }

    @Override
    public void execute() throws Exception {
        if (idPlan == -1)
            throw new Exception("Plan Not Exist");
        ISource<Plan> repository = new PlanSource();
        repository.remove(idPlan);
    }
}
