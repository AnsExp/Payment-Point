package com.paymentpoint.data.access;

import java.util.HashSet;
import java.util.Set;

/**
 * This is a final class that represents an observer in the Observer design pattern. It maintains a set of observable objects and provides methods to add, remove, and update them.
 */
public final class Observer {

    /**
     * Private constructor to prevent instantiation of this class.
     */
    private Observer() {
    }

    /**
     * A set of observable objects that this observer is observing.
     */
    private static final Set<IObservable> OBSERVABLES = new HashSet<>();

    /**
     * Adds an observable object to the set of observables.
     *
     * @param observable The observable object to add.
     */
    public static void addObservable(IObservable observable) { OBSERVABLES.add(observable); }

    /**
     * Removes an observable object from the set of observables.
     *
     * @param observable The observable object to remove.
     */
    public static void removeObservable(IObservable observable) { OBSERVABLES.remove(observable); }

    /**
     * Updates all observable objects in the set with new data.
     *
     * @param data The new data.
     */
    public static void update(Data data) { OBSERVABLES.forEach(observable -> observable.update(data)); }
}
