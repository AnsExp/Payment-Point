package com.paymentpoint.controller.associate;

import com.paymentpoint.data.entities.Method;

public class ConvertMethod {

    public Method getMethod(final String state) {
        return switch (state) {
            case "CASH" -> Method.CASH;
            case "EXTRA" -> Method.EXTRA;
            case "OTHER" -> Method.OTHER;
            case "TRANSFER" -> Method.TRANSFER;
            default -> null;
        };
    }
}
