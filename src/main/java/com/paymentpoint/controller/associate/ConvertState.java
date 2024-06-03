package com.paymentpoint.controller.associate;

import com.paymentpoint.data.entities.State;

public class ConvertState {

    public State getState(final String state) {
        return switch (state) {
            case "LATE" -> State.LATE;
            case "ENABLED" -> State.ENABLED;
            case "ON_TIME" -> State.ON_TIME;
            case "PENDING" -> State.PENDING;
            case "ON_COURSE" -> State.ON_COURSE;
            case "COMPLETED" -> State.COMPLETED;
            case "DEPRECATED" -> State.DEPRECATED;
            default -> null;
        };
    }
}
