package com.paymentpoint.data.access.source;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;

import com.paymentpoint.data.access.Data;
import com.paymentpoint.data.access.Observer;
import com.paymentpoint.data.access.AbstractSource;
import com.paymentpoint.data.entities.Balance;
import com.paymentpoint.data.utils.ObjectUtils;

@SuppressWarnings("unchecked")
public class BalanceSource extends AbstractSource<Balance> {

    private static final BalanceSource instance;
    private static final List<Balance> REPOSITORY;
    private static final String URL_FILE = "src/main/resources/source/balances.ppj";

    static {
        instance = new BalanceSource();
        Object data = ObjectUtils.inportObject(URL_FILE);
        if (data != null)
            REPOSITORY = (List<Balance>) data;
        else
            REPOSITORY = new ArrayList<>();
    }

    @Override
    protected IntFunction<Balance[]> getClassData() {
        return Balance[]::new;
    }

    @Override
    protected List<Balance> data() {
        return REPOSITORY;
    }

    @Override
    protected void processChanges() {
        ObjectUtils.exportObject(REPOSITORY, URL_FILE);
        Observer.update(Data.BALANCE);
    }

    @Override
    protected void processRemoveRegister(Balance register) {
    }

    @Override
    protected void processInsertRegister(Balance register) {
    }

    @Override
    protected void processModifyRegister(Balance oldRegister, Balance newRegister) {
    }

    public static void creditAmount(int idBalance, float amount) {
        Balance balance = instance.get(idBalance);
        if (balance == null)
            return;
        balance.setCredit(balance.getCredit() + amount);
        instance.save(balance);
    }

    public static void debitAmount(int idBalance, float amount) {
        Balance balance = instance.get(idBalance);
        if (balance == null)
            return;
        balance.setDebit(balance.getDebit() + amount);
        instance.save(balance);
    }
}
