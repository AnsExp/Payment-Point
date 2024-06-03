package com.paymentpoint.controller.validation;

import com.paymentpoint.data.entities.Client;

public class ClientValidator implements IValidator<Client> {
    @Override
    public boolean verify(Client o) {
        return o != null;
    }

    @Override
    public String messageInvalidation() {
        return "";
    }
}
