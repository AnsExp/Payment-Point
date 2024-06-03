package com.paymentpoint.data.access.source;

import com.paymentpoint.data.access.Data;
import com.paymentpoint.data.access.Observer;
import com.paymentpoint.data.access.AbstractSource;
import com.paymentpoint.data.entities.Contract;
import com.paymentpoint.data.entities.State;
import com.paymentpoint.data.utils.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.IntFunction;

@SuppressWarnings("unchecked")
public class ContractSource extends AbstractSource<Contract> {

    private static final List<Contract> REPOSITORY;
    private static final String URL_FILE = "src/main/resources/source/contracts.ppj";

    static {
        Object data = ObjectUtils.inportObject(URL_FILE);
        if (data != null)
            REPOSITORY = (List<Contract>) data;
        else
            REPOSITORY = new ArrayList<>();
    }

    @Override
    protected IntFunction<Contract[]> getClassData() {
        return Contract[]::new;
    }

    @Override
    protected List<Contract> data() {
        return REPOSITORY;
    }

    @Override
    protected void processChanges() {
        ObjectUtils.exportObject(REPOSITORY, URL_FILE);
        Observer.update(Data.CONTRACT);
    }

    @Override
    protected void processRemoveRegister(Contract register) {
        ContractUtils.removePayments(register);
    }

    @Override
    protected void processInsertRegister(Contract register) {
        register.setState(State.ON_COURSE);
        register.setAmountTotal(PlanUtils.calculateAmount(register.getIdPlan()));
        ContractUtils.createPayments(register);
        BalanceSource.creditAmount(register.getIdClient(), PlanUtils.getAmount(register.getIdPlan()));
    }

    @Override
    protected void processModifyRegister(Contract oldRegister, Contract newRegister) {

        if (oldRegister.getState() == State.COMPLETED)
            return;

        if (newRegister.getAmountPaid() >= newRegister.getAmountTotal()) {
            ExtraUtils.addExtraAmount(newRegister.getIdClient(),
                    newRegister.getAmountPaid() - newRegister.getAmountTotal());
            newRegister.setDateEnd(new Date());
            newRegister.setState(State.COMPLETED);
        }
    }
}
