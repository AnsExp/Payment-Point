package com.paymentpoint.controller.filler;

/**
 * This is an interface that represents a selectable object. It provides methods to select an object and get the ID of the selected object.
 */
public interface Selectable {
    /**
     * Selects an object by its identifier.
     *
     * @param id The identifier of the object to select.
     */
    void select(int id);

    /**
     * Retrieves the identifier of the selected object.
     *
     * @return The identifier of the selected object.
     */
    int idSelection();
}
