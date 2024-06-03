package com.paymentpoint.data.entities;

import com.paymentpoint.data.build.IPrototype;

import java.util.Date;

/**
 * La clase {@code Contract} representa un contrato en un sistema.
 * Hereda de la clase {@code Registrable}, lo que significa que puede ser
 * registrado.
 *
 * @see Registrable
 */
public final class Contract extends Registrable {

    private State state = null;
    private int idClient = 0, idPlan = 0;
    private Date dateStart = null, dateEnd = null;
    private float amountPaid = 0, amountTotal = 0;

    /**
     * Gets a string representation of the client.
     *
     * @return String representation of the client.
     */
    @Override
    public String toString() {
        return "Contract [idClient=" + idClient + ", idPlan=" + idPlan + ", dateStart=" + dateStart + ", dateEnd="
                + dateEnd + ", amountPaid=" + amountPaid + ", amountTotal=" + amountTotal + ", state=" + state + "]";
    }

    /**
     * Create a new instance of {@code Contract} with all fields initialized.
     *
     * @param idContract  The ID of contract.
     * @param idClient    The client ID associate with the contract.
     * @param idPlan      The plan ID associated with the contract.
     * @param dateStart   The start date of the contract.
     * @param dateEnd     The end date of the contract.
     * @param amountPaid  The amount paid with the contract.
     * @param amountTotal The amount total with the contract.
     * @param state       The state with the contract (for example: enabled,
     *                    completed, etc.).
     * @see State All posibles states.
     */
    public Contract(int idContract, int idClient, int idPlan, Date dateStart, Date dateEnd, float amountPaid,
            float amountTotal, State state) {
        this(idClient, idPlan, dateStart, dateEnd, amountPaid, amountTotal, state);
        this.idRegister = idContract;
    }

    /**
     * Create a new instance of {@code Contract} with all fields initialized.
     *
     * @param idClient    The client ID associate with the contract.
     * @param idPlan      The plan ID associated with the contract.
     * @param dateStart   The start date of the contract.
     * @param dateEnd     The end date of the contract.
     * @param amountPaid  The amount paid with the contract.
     * @param amountTotal The amount total with the contract.
     * @param state       The state with the contract (for example: enabled,
     *                    completed, etc.).
     * @see State All posibles states.
     */
    public Contract(int idClient, int idPlan, Date dateStart, Date dateEnd, float amountPaid, float amountTotal,
            State state) {
        this.state = state;
        this.idPlan = idPlan;
        this.dateEnd = dateEnd;
        this.idClient = idClient;
        this.dateStart = dateStart;
        this.amountPaid = amountPaid;
        this.amountTotal = amountTotal;
    }

    /**
     * Crea una instancia de {@code Contract} con un ID de contrato específico.
     *
     * @param idContract El ID del contrato.
     */
    public Contract(int idContract) {
        this.idRegister = idContract;
    }

    /**
     * Crea una instancia vacía de {@code Contract}.
     */
    public Contract() {
    }

    /**
     * Obtiene el ID del cliente del contrato.
     *
     * @return El ID del cliente del contrato.
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * Establece el ID del cliente del contrato.
     *
     * @param idClient EL nuevo ID del cliente del contrato.
     */
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    /**
     * Obtiene el ID del plan del contrato.
     *
     * @return El ID del plan del contrato.
     */
    public int getIdPlan() {
        return idPlan;
    }

    /**
     * Establece el ID del plan del contrato.
     *
     * @param idPlan El nuevo ID del contrato.
     */
    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    /**
     * Obtiene la fecha de inicio del contrato.
     *
     * @return La fecha de inicio del contrato.
     */
    public Date getDateStart() {
        return dateStart;
    }

    /**
     * Establece la fecha de inicio del contrato.
     *
     * @param dateStart La nueva fecha de inicio del contrato.
     */
    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    /**
     * Obtiene la fecha de fin del contrato.
     *
     * @return La fecha de fin del contrato.
     */
    public Date getDateEnd() {
        return dateEnd;
    }

    /**
     * Establece la fecha de fin del contrato.
     *
     * @param dateEnd La nueva fecha de fin del contrato.
     */
    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    /**
     * Obtiene el monto pagado del contrato.
     *
     * @return El monto pagado del contrato.
     */
    public float getAmountPaid() {
        return amountPaid;
    }

    /**
     * Establece el monto pagado del contrato.
     *
     * @param amountPaid El nuevo monto pagado.
     */
    public void setAmountPaid(float amountPaid) {
        this.amountPaid = amountPaid;
    }

    /**
     * Obtiene el monto total del contrato.
     *
     * @return El monto total del contrato.
     */
    public float getAmountTotal() {
        return amountTotal;
    }

    /**
     * Establece el monto total del contrato.
     *
     * @param amountTotal El nuevo monto total del contrato.
     */
    public void setAmountTotal(float amountTotal) {
        this.amountTotal = amountTotal;
    }

    /**
     * Obtiene el estado del contrato.
     *
     * @return El estado del contrato.
     */
    public State getState() {
        return state;
    }

    /**
     * Establece el estado del contrato.
     *
     * @param state El nuevo estado contrato.
     */
    public void setState(State state) {
        this.state = state;
    }

    public void setIdContract(int idContract) {
        this.idRegister = idContract;
    }

    public int getIdContract() {
        return idRegister;
    }

    @Override
    public IPrototype prototype() {
        return new Contract(idRegister, idClient, idPlan, dateStart, dateEnd, amountPaid, amountTotal, state);
    }
}
