package com.paymentpoint.data.access;

import java.util.Iterator;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.Predicate;

import com.paymentpoint.data.entities.Registrable;
import com.paymentpoint.data.utils.ListUtils;
import org.jetbrains.annotations.NotNull;

/**
 * This is an abstract class that provides a basic implementation of the ISource
 * interface.
 * It handles objects of type E that extend the Registrable interface.
 *
 * @param <E> The type of object this source handles.
 */
public abstract class AbstractSource<E extends Registrable> implements ISource<E> {

    /**
     * Checks if a given object exists in the data source.
     *
     * @param o The object to check.
     * @return true if the object exists, false otherwise.
     */
    private boolean exists(final E o) {
        return ListUtils.registerExistsInList(data(), o);
    }

    /**
     * Inserts a given object into the data source.
     *
     * @param o The object to insert.
     */
    private void insert(final @NotNull E o) {

        int id = data().isEmpty()
                ? 1
                : data()
                .getLast()
                .getIdRegister() + 1;

        o.setIdRegister(id);

        data().add(o);

        processInsertRegister(o);
    }

    /**
     * Updates a given object in the data source by its identifier.
     *
     * @param id The identifier of the object to update.
     * @param o  The new object to replace the old one.
     */
    private void update(final int id, final E o) {

        int index = ListUtils
                .findIndexRegisterInList(data(), o);

        E regFound = data().get(index);

        processModifyRegister(regFound, o);

        o.setIdRegister(id);

        data().set(index, o);
    }

    /**
     * Saves a given object to the data source.
     *
     * @param o The object to save.
     */
    @Override
    public void save(E o) {

        if (o == null)
            return;

        if (exists(o)) {

            update(o.getIdRegister(), o);
        } else {

            insert(o);
        }

        processChanges();
    }

    /**
     * Retrieves an object from the data source by its identifier.
     *
     * @param id The identifier of the object to retrieve.
     * @return The retrieved object.
     */
    @SuppressWarnings("unchecked")
    @Override
    public E get(int id) {

        E registerFound = ListUtils.findRegisterInList(data(), id);

        if (registerFound == null)
            return null;

        return (E) registerFound.prototype();
    }

    /**
     * Retrieves all objects from the data source that meet a given filter.
     *
     * @param filter The filter to apply.
     * @return An array of objects that meet the filter.
     */
    @Override
    public E[] getBy(Predicate<E> filter) {

        if (filter == null)
            return null;

        @SuppressWarnings("unchecked")
        E[] elements = data().stream()
                .filter(filter).map(x -> (E) x.prototype())
                .toArray(getClassData());

        return elements;
    }

    protected abstract IntFunction<E[]> getClassData();

    /**
     * Removes an object from the data source by its identifier.
     *
     * @param id The identifier of the object to remove.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void remove(int id) {

        int index = ListUtils.findIndexRegisterInList(data(),
                (E) Registrable.getInstance(id));

        if (index == -1)
            return;

        E register = data().get(index);

        processRemoveRegister(register);

        E e = data().remove(index);

        if (e != null) {
            processChanges();
        }
    }

    /**
     * Removes all objects from the data source that meet a given filter.
     *
     * @param filter The filter to apply.
     */
    @Override
    public void removeBy(Predicate<E> filter) {

        boolean remove = false;
        final Iterator<E> iterator = data().iterator();

        while (iterator.hasNext()) {

            E o = iterator.next();

            if (filter.test(o)) {

                processRemoveRegister(o);
                iterator.remove();
                remove = true;

            }

        }

        if (remove) {
            processChanges();
        }
    }

    /**
     * Retrieves the data from the data source.
     *
     * @return A list of objects from the data source.
     */
    protected abstract List<E> data();

    /**
     * Processes changes to the data source.
     */
    protected abstract void processChanges();

    /**
     * Processes the removal of a register from the data source.
     *
     * @param register The register to remove.
     */
    protected abstract void processRemoveRegister(E register);

    /**
     * Processes the insertion of a register into the data source.
     *
     * @param register The register to insert.
     */
    protected abstract void processInsertRegister(E register);

    /**
     * Processes the modification of a register in the data source.
     *
     * @param oldRegister The old register to replace.
     * @param newRegister The new register to replace the old one.
     */
    protected abstract void processModifyRegister(E oldRegister, E newRegister);
}
