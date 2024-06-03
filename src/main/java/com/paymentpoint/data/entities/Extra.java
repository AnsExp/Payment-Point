package com.paymentpoint.data.entities;

import com.paymentpoint.data.build.IPrototype;

/**
 * La clase {@code Extra} representa un registro adicional o extra en un
 * sistema. Hereda de la clase {@code Registrable}, lo que significa que puede
 * ser registrado.
 */
public final class Extra extends Registrable {

    private float amountExtra;

    /**
     * Devuelve una representación en cadena de la instancia `Extra`.
     *
     * @return Una cadena que muestra los detalles del registro adicional.
     */
    @Override
    public String toString() {
        return "Extra[idClient=" + idRegister + ", amountExtra=" + amountExtra + "]";
    }

    /**
     * Crea una instancia de {@code Extra} con un ID de cliente específico y el
     * monto adicional.
     *
     * @param idClient    El ID de cliente asociado al registro adicional.
     * @param amountExtra El monto adicional.
     */
    public Extra(int idClient, float amountExtra) {
        this(amountExtra);
        this.amountExtra = amountExtra;
    }

    /**
     * Crea una instancia de {@code Extra} con el monto adicional.
     *
     * @param amountExtra El monto adicional.
     */
    public Extra(float amountExtra) {
        this.amountExtra = amountExtra;
    }

    /**
     * Crea una instancia de {@code Extra} con un ID de cliente específico.
     *
     * @param idClient El ID de cliente asociado al registro adicional.
     */
    public Extra(int idClient) {
        this.idRegister = idClient;
    }

    /**
     * Crea una instancia vacía de {@code Extra}.
     */
    public Extra() {
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
     * Obtiene el monto adicional.
     *
     * @return El monto adicional.
     */
    public float getAmountExtra() {
        return amountExtra;
    }

    /**
     * Establece el monto adicional.
     *
     * @param amountExtra El nuevo monto adicional.
     */
    public void setAmountExtra(float amountExtra) {
        this.amountExtra = amountExtra;
    }

    @Override
    public IPrototype prototype() {
        return new Extra(idRegister, amountExtra);
    }
}
