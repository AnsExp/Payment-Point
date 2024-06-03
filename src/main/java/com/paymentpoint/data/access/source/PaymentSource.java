package com.paymentpoint.data.access.source;

import java.util.*;
import java.util.function.IntFunction;

import com.paymentpoint.data.access.Data;
import com.paymentpoint.data.access.Observer;
import com.paymentpoint.data.access.AbstractSource;
import com.paymentpoint.data.entities.Payment;
import com.paymentpoint.data.entities.State;
import com.paymentpoint.data.utils.ContractUtils;
import com.paymentpoint.data.utils.DateUtils;
import com.paymentpoint.data.utils.ObjectUtils;

@SuppressWarnings("unchecked")
public class PaymentSource extends AbstractSource<Payment> {

    private static final List<Payment> REPOSITORY;
    private static final String URL_FILE = "src/main/resources/source/payments.ppj";

    static {
        Object data = ObjectUtils.inportObject(URL_FILE);
        if (data != null)
            REPOSITORY = (List<Payment>) data;
        else
            REPOSITORY = new ArrayList<>();
    }

    @Override
    protected IntFunction<Payment[]> getClassData() {
        return Payment[]::new;
    }

    @Override
    protected List<Payment> data() {
        return REPOSITORY;
    }

    @Override
    protected void processChanges() {
        ObjectUtils.exportObject(REPOSITORY, URL_FILE);
        Observer.update(Data.PAYMENT);
    }

    @Override
    protected void processRemoveRegister(Payment register) {
    }

    @Override
    protected void processInsertRegister(Payment register) {
    }

    @Override
    protected void processModifyRegister(Payment oldRegister, Payment newRegister) {
        int result = DateUtils.compareDate(newRegister.getDateMandatory(),
                newRegister.getDatePayment() == null ? new Date() : newRegister.getDatePayment());
        State state = result >= 0 ? State.ON_TIME : State.LATE;
        newRegister.setState(state);
        if (newRegister.getAmountPayment() != oldRegister.getAmountPayment()) {
            float amount = newRegister.getAmountPayment() - oldRegister.getAmountPayment();
            ContractUtils.addAmountContract(newRegister.getIdContract(), amount);
        }
    }

}
