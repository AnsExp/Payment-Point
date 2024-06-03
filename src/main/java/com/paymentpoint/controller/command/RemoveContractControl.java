package com.paymentpoint.controller.command;

import com.paymentpoint.data.access.source.ContractSource;
import com.paymentpoint.data.access.ISource;
import com.paymentpoint.data.entities.Contract;

public class RemoveContractControl implements IControl {

    private final int idContract;

    public RemoveContractControl(int idContract) {
        this.idContract = idContract;
    }

    @Override
    public void execute() throws Exception {
        if (idContract == -1)
            throw new Exception("Contract Not Exits");
        ISource<Contract> repository = new ContractSource();
        repository.remove(idContract);
    }
}
