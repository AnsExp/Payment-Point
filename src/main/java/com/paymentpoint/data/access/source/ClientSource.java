package com.paymentpoint.data.access.source;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;

import com.paymentpoint.data.access.Data;
import com.paymentpoint.data.access.Observer;
import com.paymentpoint.data.access.AbstractSource;
import com.paymentpoint.data.entities.Client;
import com.paymentpoint.data.utils.ClientUtils;
import com.paymentpoint.data.utils.ListUtils;
import com.paymentpoint.data.utils.ObjectUtils;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unchecked")
public class ClientSource extends AbstractSource<Client> {

    private static final List<Client> REPOSITORY;
    private static final String URL_FILE = "src/main/resources/source/clients.ppj";

    static {
        Object data = ObjectUtils.inportObject(URL_FILE);
        if (data != null)
            REPOSITORY = (List<Client>) data;
        else
            REPOSITORY = new ArrayList<>();
    }

    @Override
    protected IntFunction<Client[]> getClassData() {
        return Client[]::new;
    }

    @Override
    protected List<Client> data() {
        return REPOSITORY;
    }

    @Override
    protected void processChanges() {
        ObjectUtils.exportObject(REPOSITORY, URL_FILE);
        Observer.update(Data.CLIENT);
    }

    @Override
    protected void processRemoveRegister(Client register) {
        ClientUtils.removeContracts(register);
        ClientUtils.removeBalance(register);
        ClientUtils.removeExtra(register);
    }

    @Override
    protected void processInsertRegister(Client register) {
        ClientUtils.createExtra(register);
        ClientUtils.createBalance(register);
    }

    @Override
    protected void processModifyRegister(Client oldRegister, Client newRegister) {
    }

    public static @Nullable String clientDisplay(int idClient) {

        Client client = ListUtils.findRegisterInList(REPOSITORY, idClient);

        if (client == null)
            return null;

        return client.getName() + " " + client.getLastname();
    }
}
