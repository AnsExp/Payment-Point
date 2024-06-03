package com.paymentpoint.data.build;

import java.util.Date;

import com.paymentpoint.data.entities.Contract;
import com.paymentpoint.data.entities.State;

public class ContractBuilder implements IBuild<Contract> {

    private final Contract contract;

    private ContractBuilder() {
        contract = new Contract();
    }

    public static ContractBuilder getInstance() {
        return new ContractBuilder();
    }

    public ContractBuilder idContract(int idContract) {
        contract.setIdContract(idContract);
        return this;
    }

    public ContractBuilder idClient(int idClient) {
        contract.setIdClient(idClient);
        return this;
    }

    public ContractBuilder idPlan(int idPlan) {
        contract.setIdPlan(idPlan);
        return this;
    }

    public ContractBuilder dateStart(Date dateStart) {
        contract.setDateStart(dateStart);
        return this;
    }

    public ContractBuilder dateEnd(Date dateEnd) {
        contract.setDateEnd(dateEnd);
        return this;
    }

    public ContractBuilder amountPaid(float amountPaid) {
        contract.setAmountPaid(amountPaid);
        return this;
    }

    public ContractBuilder amountTotal(float amountTotal) {
        contract.setAmountTotal(amountTotal);
        return this;
    }

    public ContractBuilder state(State amountTotal) {
        contract.setState(amountTotal);
        return this;
    }

    @Override
    public Contract build() {
        return contract;
    }
}
