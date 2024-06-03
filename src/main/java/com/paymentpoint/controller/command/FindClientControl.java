package com.paymentpoint.controller.command;

import com.paymentpoint.data.access.ISource;
import com.paymentpoint.data.access.source.ClientSource;
import com.paymentpoint.data.entities.Client;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class FindClientControl implements IControl {

    private final Supplier<Integer> idGetter;
    private final Consumer<Integer> idSetter;
    private final Consumer<String> nameSetter, phoneSetter, idCardSetter, lastnameSetter;

    public FindClientControl(
            Supplier<Integer> idGetter, Consumer<Integer> idSetter, Consumer<String> nameSetter,
            Consumer<String> lastnameSetter, Consumer<String> phoneSetter, Consumer<String> idCardSetter) {
        this.idGetter = idGetter;
        this.idSetter = idSetter;
        this.nameSetter = nameSetter;
        this.phoneSetter = phoneSetter;
        this.idCardSetter = idCardSetter;
        this.lastnameSetter = lastnameSetter;
    }

    @Override
    public void execute() throws Exception {

        ISource<Client> finder = new ClientSource();

        Client client = finder.get(idGetter.get());

        if (client == null)
            throw new Exception("Client Not Found");

        if (idSetter != null)
            idSetter.accept(client.getIdClient());
        if (nameSetter != null)
            nameSetter.accept(client.getName());
        if (phoneSetter != null)
            phoneSetter.accept(client.getPhone());
        if (idCardSetter != null)
            idCardSetter.accept(client.getIdCard());
        if (lastnameSetter != null)
            lastnameSetter.accept(client.getLastname());
    }
}
