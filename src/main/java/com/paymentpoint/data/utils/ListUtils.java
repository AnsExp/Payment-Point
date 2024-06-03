package com.paymentpoint.data.utils;

import com.paymentpoint.data.entities.Registrable;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class ListUtils {

    @Contract(pure = true)
    private ListUtils() {
    }

    public static <T extends Registrable> int findIndexRegisterInList(List<T> list, T reg) {

        if (list == null || reg == null)
            return -1;

        int valueDown = 0;
        int valueTop = list.size() - 1;
        int valueFind = reg.getIdRegister();
        int valueIndex = (valueDown + valueTop) / 2;
        int valueCurrent;

        while (valueDown <= valueTop) {

            valueCurrent = list.get(valueIndex).getIdRegister();

            if (valueCurrent == valueFind)
                return valueIndex;
            else if (valueCurrent > valueFind)
                valueTop = valueIndex - 1;
            else
                valueDown = valueIndex + 1;

            valueIndex = (valueDown + valueTop) / 2;
        }

        return -1;
    }

    @Contract("null, _ -> null")
    public static <T extends Registrable> @Nullable T findRegisterInList(List<T> list, int id) {

        if (list == null)
            return null;

        @SuppressWarnings("unchecked")
        int index = findIndexRegisterInList(list, (T) Registrable.getInstance(id));

        if (index == -1)
            return null;

        return list.get(index);
    }

    @Contract("null, _ -> false; !null, null -> false")
    public static <T extends Registrable> boolean registerExistsInList(List<T> list, T reg) {

        if (list == null || reg == null)
            return false;

        return ListUtils.findIndexRegisterInList(list, reg) != -1;
    }
}
