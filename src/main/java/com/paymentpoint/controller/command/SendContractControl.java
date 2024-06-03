package com.paymentpoint.controller.command;

import java.util.Date;

import com.paymentpoint.controller.validation.ContractValidator;
import com.paymentpoint.controller.validation.IValidator;
import com.paymentpoint.data.access.ISource;
import com.paymentpoint.data.access.source.ContractSource;
import com.paymentpoint.data.build.ContractBuilder;
import com.paymentpoint.data.entities.Contract;

public class SendContractControl implements IControl {

    private int idContract;
    private final Date dateStart;
    private final int idClient, idPlan;

    public SendContractControl(int idContract, int idClient, int idPlan, Date dateStart) {
        this(idClient, idPlan, dateStart);
        this.idContract = idContract;
    }

    public SendContractControl(int idClient, int idPlan, Date dateStart) {
        this.idPlan = idPlan;
        this.idClient = idClient;
        this.dateStart = dateStart;
    }

    @Override
    public void execute() throws Exception {

        ContractBuilder builder = ContractBuilder.getInstance()
                .idPlan(idPlan)
                .idClient(idClient)
                .dateStart(dateStart)
                .idContract(idContract);

        IValidator<Contract> validator = new ContractValidator();
        ISource<Contract> source = new ContractSource();

        if (validator.verify(builder.build()))
            source.save(builder.build());
        else
            throw new Exception(validator.messageInvalidation());
    }
}
