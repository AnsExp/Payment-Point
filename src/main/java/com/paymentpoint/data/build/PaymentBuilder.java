package com.paymentpoint.data.build;

import java.util.Date;

import com.paymentpoint.data.entities.Method;
import com.paymentpoint.data.entities.Payment;
import com.paymentpoint.data.entities.State;

public class PaymentBuilder implements IBuild<Payment> {

    private final Payment payment;

    private PaymentBuilder() {
        payment = new Payment();
    }

    public static PaymentBuilder getInstance() {
        return new PaymentBuilder();
    }

    public PaymentBuilder idPayment(int idPayment) {
        payment.setIdPayment(idPayment);
        return this;
    }

    public PaymentBuilder idContract(int idContract) {
        payment.setIdContract(idContract);
        return this;
    }

    public PaymentBuilder dateMandatory(Date dateMandatory) {
        payment.setDateMandatory(dateMandatory);
        return this;
    }

    public PaymentBuilder amountMandatory(float amountMandatory) {
        payment.setAmountMandatory(amountMandatory);
        return this;
    }

    public PaymentBuilder datePayment(Date datePayment) {
        payment.setDatePayment(datePayment);
        return this;
    }

    public PaymentBuilder amountPayment(float amountPayment) {
        payment.setAmountPayment(amountPayment);
        return this;
    }

    public PaymentBuilder method(Method method) {
        payment.setMethod(method);
        return this;
    }

    public PaymentBuilder observation(String observation) {
        payment.setObservation(observation);
        return this;
    }

    public PaymentBuilder state(State state) {
        payment.setState(state);
        return this;
    }

    @Override
    public Payment build() {
        return payment;
    }
}
