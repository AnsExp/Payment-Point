package com.paymentpoint.controller.filler;

import com.paymentpoint.data.access.Data;
import com.paymentpoint.data.access.ISource;
import com.paymentpoint.data.access.source.*;
import com.paymentpoint.data.entities.Balance;
import com.paymentpoint.data.entities.Client;
import com.paymentpoint.data.entities.Contract;
import com.paymentpoint.data.entities.Extra;
import com.paymentpoint.data.entities.Payment;
import com.paymentpoint.data.entities.Plan;
import com.paymentpoint.data.entities.Registrable;
import com.paymentpoint.data.utils.ListUtils;
import org.jetbrains.annotations.Nullable;

import java.awt.Component;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public abstract class AbstractFiller<C extends Component, R extends Registrable>
        implements IFillerComponent<C, R>, Selectable {

    protected C component;
    protected R[] registers;
    protected Predicate<R> filter;

    @Override
    public void fill(C component, Predicate<R> filter) {

        registers = source().getBy(filter);

        Comparator<R> comparator = comparator();

        if (comparator != null)
            Arrays.sort(registers, comparator());

        if (this.component == component && this.filter == filter) {
            refresh();
            return;
        }

        this.filter = filter;
        this.component = component;

        fill();
    }

    @Override
    public void update(Data data) {
        if (data == data())
            fill(component, filter);
    }

    protected abstract void refresh();

    protected abstract void fill();

    protected abstract Class<? extends Registrable> classRegister();

    private @Nullable Data data() {

        if (registers == null)
            return null;

        Class<?> clazz = classRegister();

        if (clazz == Plan.class)
            return Data.PLAN;
        if (clazz == Extra.class)
            return Data.EXTRA;
        if (clazz == Client.class)
            return Data.CLIENT;
        if (clazz == Payment.class)
            return Data.PAYMENT;
        if (clazz == Balance.class)
            return Data.BALANCE;
        if (clazz == Contract.class)
            return Data.CONTRACT;

        return null;
    }

    protected abstract Comparator<R> comparator();

    protected int index(int id) {
        return ListUtils.findIndexRegisterInList(
                List.of(registers),
                Registrable.getInstance(id));
    }

    @SuppressWarnings("unchecked")
    protected ISource<R> source() {

        Class<? extends Registrable> clazz = classRegister();

        if (clazz == Plan.class)
            return (ISource<R>) new PlanSource();
        if (clazz == Extra.class)
            return (ISource<R>) new ExtraSource();
        if (clazz == Client.class)
            return (ISource<R>) new ClientSource();
        if (clazz == Payment.class)
            return (ISource<R>) new PaymentSource();
        if (clazz == Balance.class)
            return (ISource<R>) new BalanceSource();
        if (clazz == Contract.class)
            return (ISource<R>) new ContractSource();

        return null;
    }
}
