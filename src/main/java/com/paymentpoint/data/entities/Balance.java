package com.paymentpoint.data.entities;

import com.paymentpoint.data.build.IPrototype;

/**
 * La clase {@code Balance} representa el estado financiero de un cliente o
 * entidad.
 * Hereda de la clase {@code Registrable}, lo que significa que puede ser
 * registrado.
 *
 * @see Registrable
 */
public final class Balance extends Registrable {

    private float credit;
    private float debit;

    /**
     * Devuelve una representación en cadena de la instancia {@code Balance}.
     *
     * @return Una cadena que muestra los detalles del estado financiero.
     */
    @Override
    public String toString() {
        return "Balance [idClient=" + idRegister + ", credit=" + credit + ", debit=" + debit + "]";
    }

    /**
     * Crea una instancia de {@code Balance} con un ID de cliente específico y los
     * montos
     * de crédito y débito.
     *
     * @param idClient El ID de cliente.
     * @param credit   El monto de crédito asociado al cliente.
     * @param debit    El monto de débito asociado al cliente.
     */
    public Balance(int idClient, float credit, float debit) {
        this(credit, debit);
        this.idRegister = idClient;
    }

    /**
     * Crea una instancia de {@code Balance} con los montos de crédito y débito.
     *
     * @param credit El monto de crédito asociado al cliente.
     * @param debit  El monto de débito asociado al cliente.
     */
    public Balance(float credit, float debit) {
        this.credit = credit;
        this.debit = debit;
    }

    /**
     * Crea una instancia de {@code Balance} con un ID de cliente específico.
     *
     * @param idClient El ID de cliente.
     */
    public Balance(int idClient) {
        this.idRegister = idClient;
    }

    /**
     * Crea una instancia vacía de {@code Balance}.
     */
    public Balance() {
    }

    /**
     * Obtiene el ID del cliente.
     *
     * @return El ID del cliente.
     */
    public int getIdClient() {
        return idRegister;
    }

    /**
     * Establece el ID del cliente.
     *
     * @param idClient El nuevo ID del cliente.
     */
    public void setIdClient(int idClient) {
        this.idRegister = idClient;
    }

    /**
     * Obtiene el monto de crédito asociado al cliente.
     *
     * @return El monto de crédito.
     */
    public float getCredit() {
        return credit;
    }

    /**
     * Establece el monto de crédito asociado al cliente.
     *
     * @param credit El nuevo monto de crédito.
     */
    public void setCredit(float credit) {
        this.credit = credit;
    }

    /**
     * Obtiene el monto de débito asociado al cliente.
     *
     * @return El monto de débito.
     */
    public float getDebit() {
        return debit;
    }

    /**
     * Establece el monto de débito asociado al cliente.
     *
     * @param debit El nuevo monto de débito.
     */
    public void setDebit(float debit) {
        this.debit = debit;
    }

    @Override
    public IPrototype prototype() {
        return new Balance(idRegister, credit, debit);
    }
}
