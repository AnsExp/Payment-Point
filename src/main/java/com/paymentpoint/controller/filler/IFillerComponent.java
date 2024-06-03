package com.paymentpoint.controller.filler;

import java.awt.Component;
import java.util.function.Predicate;

import com.paymentpoint.data.access.IObservable;
import com.paymentpoint.data.access.Observer;
import com.paymentpoint.data.entities.Registrable;

/**
 * This is a generic interface that represents a component filler. It extends the IObservable interface and provides methods to fill a component, bind, and unbind.
 *
 * @param <T> The type of component this filler handles.
 * @param <K> The type of registrable object this filler handles.
 */
public interface IFillerComponent<T extends Component, K extends Registrable> extends IObservable {
    /**
     * Fills a component based on a given filter.
     *
     * @param component The component to fill.
     * @param filter    The filter to apply.
     */
    void fill(T component, Predicate<K> filter);

    /**
     * Fills a component without any filter.
     *
     * @param component The component to fill.
     */
    default void fillAll(T component) {
        fill(component, _ -> true);
    }

    /**
     * Binds this filler to the observer.
     */
    default void bind() {
        Observer.addObservable(this);
    }

    /**
     * Unbinds this filler from the observer.
     */
    default void unbind() {
        Observer.removeObservable(this);
    }
}
