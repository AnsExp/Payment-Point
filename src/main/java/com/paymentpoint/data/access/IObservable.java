package com.paymentpoint.data.access;

/**
 * This is a functional interface that represents an observable object in the Observer design pattern. It has a single abstract method {@code update} which is called to update the observer with new data.
 */
@FunctionalInterface
public interface IObservable {
    /**
     * Updates the observer with new data.
     *
     * @param data The new data.
     */
    void update(Data data);
}
