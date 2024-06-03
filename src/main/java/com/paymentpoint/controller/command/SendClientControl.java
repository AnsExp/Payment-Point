package com.paymentpoint.controller.command;

import com.paymentpoint.controller.validation.ClientValidator;
import com.paymentpoint.controller.validation.IValidator;
import com.paymentpoint.data.access.ISource;
import com.paymentpoint.data.access.source.ClientSource;
import com.paymentpoint.data.build.ClientBuilder;
import com.paymentpoint.data.entities.Client;

public class SendClientControl implements IControl {

    private final int idClient;
    private final String name, phone, idCard, lastname;

    public SendClientControl(int idClient, String name, String lastname, String phone, String idCard) {
        this.name = name;
        this.phone = phone;
        this.idCard = idCard;
        this.idClient = idClient;
        this.lastname = lastname;
    }

    @Override
    public void execute() throws Exception {

        ClientBuilder builder = ClientBuilder.getInstance()
                .name(name)
                .phone(phone)
                .idCard(idCard)
                .idClient(idClient)
                .lastname(lastname);

        IValidator<Client> validator = new ClientValidator();
        ISource<Client> source = new ClientSource();

        if (validator.verify(builder.build()))
            source.save(builder.build());
        else
            throw new Exception(validator.messageInvalidation());
    }
}
