package com.paymentpoint.controller.command;

import java.util.Date;

import com.paymentpoint.controller.associate.ConvertMethod;
import com.paymentpoint.controller.validation.IValidator;
import com.paymentpoint.controller.validation.PaymentValidator;
import com.paymentpoint.data.access.ISource;
import com.paymentpoint.data.access.AbstractSource;
import com.paymentpoint.data.access.source.ClientSource;
import com.paymentpoint.data.access.source.ContractSource;
import com.paymentpoint.data.access.source.ExtraSource;
import com.paymentpoint.data.access.source.PaymentSource;
import com.paymentpoint.data.entities.Client;
import com.paymentpoint.data.entities.Contract;
import com.paymentpoint.data.entities.Extra;
import com.paymentpoint.data.entities.Payment;

public class SendPaymentControl implements IControl {

    private Extra extra = null;
    private AbstractSource<Extra> extraSource = null;

    private final int idPayment;
    private final Date datePayment;
    private final boolean amountExtra;
    private final String method, observation;
    private final float amountPayment, extraAmount;

    public SendPaymentControl(int idPayment,float amountPayment, Date datePayment, String method,
            String observation,
            boolean amountExtra, float extraAmount) {
        this.method = method;
        this.idPayment = idPayment;
        this.amountExtra = amountExtra;
        this.extraAmount = extraAmount;
        this.datePayment = datePayment;
        this.observation = observation;
        this.amountPayment = amountPayment;
    }

    private Payment getPayment(int idPayment) {
        ISource<Payment> finder = new PaymentSource();
        Payment payment = finder.get(idPayment);
        return payment == null ? new Payment() : payment;
    }

    private Client getClient(int idContract) {
        ISource<Client> finderClient = new ClientSource();
        ISource<Contract> finderContract = new ContractSource();
        Contract contract = finderContract.get(idContract);
        return finderClient.get(contract.getIdClient());
    }

    private Extra getExtra(int idClient) {
        ISource<Extra> finder = new ExtraSource();
        return finder.get(idClient);
    }

    @Override
    public void execute() throws Exception {

        ConvertMethod convert = new ConvertMethod();

        Payment payment = (Payment) getPayment(idPayment).prototype();
        payment.setDatePayment(datePayment);
        payment.setObservation(observation);
        payment.setAmountPayment(amountPayment);
        payment.setMethod(convert.getMethod(method));

        if (amountExtra) {
            extra = getExtra(
                    getClient(payment.getIdContract())
                            .getIdClient());
            if (extra.getAmountExtra() < extraAmount)
                throw new Exception("Extra Amount Field Overflow.");
            extra.setAmountExtra(extra.getAmountExtra() - extraAmount);
            payment.setAmountPayment(payment.getAmountPayment() + extraAmount);
            extraSource = new ExtraSource();
        }

        IValidator<Payment> validator = new PaymentValidator();

        if (validator.verify(payment)) {
            ISource<Payment> repository = new PaymentSource();
            repository.save(payment);

            if (extra != null && extraSource != null)
                extraSource.save(extra);
        } else {
            throw new Exception(validator.messageInvalidation());
        }
    }
}
