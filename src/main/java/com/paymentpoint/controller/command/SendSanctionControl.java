package com.paymentpoint.controller.command;

import com.paymentpoint.controller.associate.ConvertMethod;
import com.paymentpoint.data.access.ISource;
import com.paymentpoint.data.access.source.PaymentSource;
import com.paymentpoint.data.build.PaymentBuilder;
import com.paymentpoint.data.entities.Payment;
import com.paymentpoint.data.entities.State;

import java.util.Date;

public class SendSanctionControl implements IControl {

    private final int idContract;
    private final Date dateSanction;
    private final float amountSanction;
    private final String method, observation;

    public SendSanctionControl(int idContract, Date dateSanction, float amountSanction, String method, String observation) {
        this.method = method;
        this.idContract = idContract;
        this.observation = observation;
        this.dateSanction = dateSanction;
        this.amountSanction = amountSanction;
    }

    @Override
    public void execute() throws Exception {

        Payment payment = PaymentBuilder.getInstance()
                .state(State.SANCTION)
                .idContract(idContract)
                .observation(observation)
                .datePayment(dateSanction)
                .amountPayment(amountSanction)
                .method(new ConvertMethod().getMethod(method))
                .build();

        ISource<Payment> source = new PaymentSource();
        source.save(payment);
    }
}
