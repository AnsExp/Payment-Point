package com.paymentpoint.controller.command;

import com.paymentpoint.data.access.ISource;
import com.paymentpoint.data.access.source.ClientSource;
import com.paymentpoint.data.entities.Client;

public class RemoveClientControl implements IControl {

    private final int idClient;

    public RemoveClientControl(int idClient) {
        this.idClient = idClient;
    }

    @Override
    public void execute() throws Exception {
        if (idClient == -1)
            throw new Exception("Client Not Exist");
        ISource<Client> repository = new ClientSource();
        repository.remove(idClient);
    }
}
