package com.paymentpoint.data.utils;

import com.paymentpoint.data.access.AbstractSource;
import com.paymentpoint.data.access.ISource;
import com.paymentpoint.data.access.source.ExtraSource;
import com.paymentpoint.data.entities.Extra;
import org.jetbrains.annotations.Contract;

public final class ExtraUtils {

    @Contract(pure = true)
    private ExtraUtils() {
    }

    public static void addExtraAmount(int idClient, float amount) {
        AbstractSource<Extra> source = new ExtraSource();
        Extra extra = source.get(idClient);
        if (extra == null) return;
        extra.setAmountExtra(extra.getAmountExtra() + amount);
        source.save(extra);
    }

    /**
     * Retrieves the extra amount of client.
     *
     * @param idClient ID unique of client;
     * @return Amount extra of client.
     */
    public static float getAmountExtra(int idClient) {
        ISource<Extra> source = new ExtraSource();
        Extra extra = source.get(idClient);
        if (extra == null)
            return 0;
        return extra.getAmountExtra();
    }
}
