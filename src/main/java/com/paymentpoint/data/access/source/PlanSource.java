package com.paymentpoint.data.access.source;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;

import com.paymentpoint.data.access.Data;
import com.paymentpoint.data.access.Observer;
import com.paymentpoint.data.access.AbstractSource;
import com.paymentpoint.data.entities.Plan;
import com.paymentpoint.data.utils.ObjectUtils;
import com.paymentpoint.data.utils.PlanUtils;

@SuppressWarnings("unchecked")
public class PlanSource extends AbstractSource<Plan> {

    private static final List<Plan> REPOSITORY;
    private static final String URL_FILE = "src/main/resources/source/plans.ppj";

    static {
        Object data = ObjectUtils.inportObject(URL_FILE);
        if (data != null)
            REPOSITORY = (List<Plan>) data;
        else
            REPOSITORY = new ArrayList<>();
    }

    @Override
    protected IntFunction<Plan[]> getClassData() {
        REPOSITORY.toArray(Plan[]::new);
        return Plan[]::new;
    }

    @Override
    protected List<Plan> data() {
        return REPOSITORY;
    }

    @Override
    protected void processChanges() {
        ObjectUtils.exportObject(REPOSITORY, URL_FILE);
        Observer.update(Data.PLAN);
    }

    @Override
    protected void processRemoveRegister(Plan register) {
        PlanUtils.removeContractAssignedPlan(register);
    }

    @Override
    protected void processInsertRegister(Plan register) {
    }

    @Override
    protected void processModifyRegister(Plan oldRegister, Plan newRegister) {
    }
}
