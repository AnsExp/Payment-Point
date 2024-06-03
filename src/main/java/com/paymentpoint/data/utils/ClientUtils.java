package com.paymentpoint.data.utils;

import com.paymentpoint.data.access.ISource;
import com.paymentpoint.data.access.source.BalanceSource;
import com.paymentpoint.data.access.source.ContractSource;
import com.paymentpoint.data.access.source.ExtraSource;
import com.paymentpoint.data.entities.Balance;
import com.paymentpoint.data.entities.Client;
import com.paymentpoint.data.entities.Contract;
import com.paymentpoint.data.entities.Extra;
import org.jetbrains.annotations.NotNull;

public final class ClientUtils {

    @org.jetbrains.annotations.Contract(pure = true)
    private ClientUtils() {
    }

    public static void createBalance(@NotNull Client client) {
        ISource<Balance> source = new BalanceSource();
        Balance balance = new Balance(client.getIdClient());
        source.save(balance);
    }

    public static void createExtra(@NotNull Client client) {
        ISource<Extra> source = new ExtraSource();
        Extra extra = new Extra(client.getIdClient());
        source.save(extra);
    }

    public static void removeContracts(Client client) {
        ISource<Contract> source = new ContractSource();
        source.removeBy(contract -> contract.getIdClient() == client.getIdClient());
    }

    public static void removeBalance(@NotNull Client client) {
        ISource<Balance> source = new BalanceSource();
        source.remove(client.getIdClient());
    }

    public static void removeExtra(Client client) {
        ISource<Extra> source = new ExtraSource();
        source.remove(client.getIdClient());
    }
}
