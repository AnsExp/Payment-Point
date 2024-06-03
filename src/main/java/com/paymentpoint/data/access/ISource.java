package com.paymentpoint.data.access;

import java.util.function.Predicate;

/**
 * This is a generic interface that defines the basic operations of a data
 * source.
 *
 * @param <T> The type of object this source handles.
 */
public interface ISource<T> {
    /**
     * Saves a record to the data source.
     *
     * @param register The record to be saved.
     */
    void save(T register);

    /**
     * Retrieves a record from the data source by its identifier.
     *
     * @param id The identifier of the record to retrieve.
     * @return The retrieved record.
     */
    T get(int id);

    /**
     * Removes a record from the data source by its identifier.
     *
     * @param id The identifier of the record to remove.
     */
    void remove(int id);

    /**
     * Retrieves all records from the data source that meet a given filter.
     *
     * @param filter The filter to apply.
     * @return An array of records that meet the filter.
     */
    T[] getBy(Predicate<T> filter);

    /**
     * Removes all records from the data source that meet a given filter.
     *
     * @param filter The filter to apply.
     */
    void removeBy(Predicate<T> filter);
}
