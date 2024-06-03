package com.paymentpoint.controller.command;

import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.paymentpoint.data.access.ISource;
import com.paymentpoint.data.access.source.PaymentSource;
import com.paymentpoint.data.entities.Payment;

public class FindPaymentControl implements IControl {

    private final Supplier<Integer> idGetter;
    private final Consumer<Integer> idSetter, idContractSetter;
    private final Consumer<Date> dateMandatorySetter, datePaymentSetter;
    private final Consumer<Float> amountMandatorySetter, amountPaymentSetter;
    private final Consumer<String> methodSetter, observationSetter, stateSetter;

    public FindPaymentControl(
            Supplier<Integer> idGetter, Consumer<Integer> idSetter, Consumer<Integer> idContractSetter,
            Consumer<Date> dateMandatorySetter, Consumer<Float> amountMandatorySetter, Consumer<Date> datePaymentSetter,
            Consumer<Float> amountPaymentSetter, Consumer<String> methodSetter, Consumer<String> observationSetter,
            Consumer<String> stateSetter) {
        this.idGetter = idGetter;
        this.idSetter = idSetter;
        this.stateSetter = stateSetter;
        this.methodSetter = methodSetter;
        this.idContractSetter = idContractSetter;
        this.datePaymentSetter = datePaymentSetter;
        this.observationSetter = observationSetter;
        this.dateMandatorySetter = dateMandatorySetter;
        this.amountPaymentSetter = amountPaymentSetter;
        this.amountMandatorySetter = amountMandatorySetter;
    }

    @Override
    public void execute() throws Exception {

        ISource<Payment> finder = new PaymentSource();

        Payment payment = finder.get(idGetter.get());

        if (payment == null)
            throw new Exception("Client Not Found");

        if (idSetter != null)
            idSetter.accept(payment.getIdPayment());
        if (stateSetter != null)
            stateSetter.accept(payment.getState().name());
        if (idContractSetter != null)
            idContractSetter.accept(payment.getIdContract());
        if (observationSetter != null)
            observationSetter.accept(payment.getObservation());
        if (dateMandatorySetter != null)
            dateMandatorySetter.accept(payment.getDateMandatory());
        if (amountPaymentSetter != null)
            amountPaymentSetter.accept(payment.getAmountPayment());
        if (amountMandatorySetter != null)
            amountMandatorySetter.accept(payment.getAmountMandatory());
        if (methodSetter != null)
            methodSetter.accept(payment.getMethod() == null ? null : payment.getMethod().name());
        if (datePaymentSetter != null)
            datePaymentSetter.accept(payment.getDatePayment() == null ? new Date() : payment.getDatePayment());
    }
}
