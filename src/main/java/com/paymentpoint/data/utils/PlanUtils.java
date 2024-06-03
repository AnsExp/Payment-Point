package com.paymentpoint.data.utils;

import com.paymentpoint.data.access.ISource;
import com.paymentpoint.data.access.source.ContractSource;
import com.paymentpoint.data.access.source.PlanSource;
import com.paymentpoint.data.entities.Plan;
import org.jetbrains.annotations.Contract;

public final class PlanUtils {

    @Contract(pure = true)
    private PlanUtils() {
    }

    public static void removeContractAssignedPlan(Plan plan) {
        var source = new ContractSource();
        source.removeBy(contract -> contract.getIdPlan() == plan.getIdPlan());
    }

    public static float calculateAmount(int idPlan) {
        ISource<Plan> finder = new PlanSource();
        Plan plan = finder.get(idPlan);
        if (plan == null)
            return 0;
        return calculateAmount(plan);
    }

    public static float getAmount(int idPlan) {
        ISource<Plan> finder = new PlanSource();
        Plan plan = finder.get(idPlan);
        if (plan == null) return 0;
        return plan.getAmount();
    }

    public static float calculateAmount(Plan plan) {
        if (plan == null)
            return 0;
        float result = plan.getTax() / 4;
        result *= plan.getSteps();
        result += 1;
        result *= plan.getAmount();
        return result;
    }
}
