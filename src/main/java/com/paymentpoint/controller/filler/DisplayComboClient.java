package com.paymentpoint.controller.filler;

import com.paymentpoint.data.entities.Client;
import com.paymentpoint.data.entities.Registrable;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class DisplayComboClient extends AbstractDisplayCombo<String, Client> {

    @Override
    protected Class<? extends Registrable> classRegister() {
        return Client.class;
    }

    @Override
    protected String displayItem(@NotNull Client register) {
        return register.getName() + " " + register.getLastname();
    }

    @Override
    protected Comparator<Client> comparator() {
        return Comparator.comparing(Client::getName);
    }
}
