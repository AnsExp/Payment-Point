package com.paymentpoint.data.build;

import com.paymentpoint.data.entities.Client;

public class ClientBuilder implements IBuild<Client> {
    private final Client client;

    private ClientBuilder() {
        client = new Client();
    }

    public static ClientBuilder getInstance() {
        return new ClientBuilder();
    }

    public ClientBuilder idClient(int idClient) {
        client.setIdClient(idClient);
        return this;
    }

    public ClientBuilder name(String name) {
        client.setName(name);
        return this;
    }

    public ClientBuilder lastname(String lastname) {
        client.setLastname(lastname);
        return this;
    }

    public ClientBuilder phone(String phone) {
        client.setPhone(phone);
        return this;
    }

    public ClientBuilder idCard(String idCard) {
        client.setIdCard(idCard);
        return this;
    }

    @Override
    public Client build() {
        return client;
    }
}
