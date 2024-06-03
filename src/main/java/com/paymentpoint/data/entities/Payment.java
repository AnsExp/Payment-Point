package com.paymentpoint.data.entities;

import java.util.Date;

import com.paymentpoint.data.build.IPrototype;

/**
 * La clase {@code Payment} representa un registro de pago en un sistema.
 * Hereda de la clase `Registrable`, lo que significa que puede ser registrado.
 * 
 * @see Registrable
 */
public final class Payment extends Registrable {

    private int idContract;
    private Date dateMandatory;
    private float amountMandatory;
    private Date datePayment;
    private float amountPayment;
    private Method method;
    private String observation;
    private State state;

    /**
     * Devuelve una representación en cadena de la instancia `Payment`.
     *
     * @return Una cadena que muestra los detalles del pago.
     */
    @Override
    public String toString() {
        return "Payment [idPayment=" + idRegister + ", idContract=" + idContract + ", datePayment=" + datePayment
                + ", amountPayment=" + amountPayment + ", dateMandatory=" + dateMandatory + ", amountMandatory="
                + amountMandatory + ", method=" + method + ", observation=" + observation + ", state=" + state + "]";
    }

    /**
     * Crea una instancia de {@code Payment} con un ID de pago específico y otros
     * detalles.
     *
     * @param idPayment       El ID de pago.
     * @param idContract      El ID del contrato asociado al pago.
     * @param datePayment     La fecha del pago.
     * @param amountPayment   El monto del pago.
     * @param dateMandatory   La fecha obligatoria para el pago.
     * @param amountMandatory El monto obligatorio para el pago.
     * @param method          El método de pago utilizado.
     * @param observation     Observaciones adicionales sobre el pago.
     * @param state           El estado del pago (por ejemplo, completado,
     *                        pendiente, etc.).
     */
    public Payment(int idPayment, int idContract, Date datePayment, float amountPayment, Date dateMandatory,
            float amountMandatory, Method method, String observation, State state) {
        this(idContract, datePayment, amountPayment, dateMandatory, amountMandatory, method, observation, state);
        this.idRegister = idPayment;
    }

    /**
     * Crea una instancia de {@code Payment} con detalles de contrato, fechas,
     * montos, método y estado.
     *
     * @param idContract      El ID del contrato asociado al pago.
     * @param datePayment     La fecha del pago.
     * @param amountPayment   El monto del pago.
     * @param dateMandatory   La fecha obligatoria para el pago.
     * @param amountMandatory El monto obligatorio para el pago.
     * @param method          El método de pago utilizado.
     * @param observation     Observaciones adicionales sobre el pago.
     * @param state           El estado del pago (por ejemplo, completado,
     *                        pendiente, etc.).
     */
    public Payment(int idContract, Date datePayment, float amountPayment, Date dateMandatory, float amountMandatory,
            Method method, String observation, State state) {
        this.idContract = idContract;
        this.datePayment = datePayment;
        this.amountPayment = amountPayment;
        this.dateMandatory = dateMandatory;
        this.amountMandatory = amountMandatory;
        this.method = method;
        this.observation = observation;
        this.state = state;
    }

    /**
     * Crea una instancia de {@code Payment} con un ID de pago específico.
     *
     * @param idPayment El ID de pago.
     */
    public Payment(int idPayment) {
        this.idRegister = idPayment;
    }

    /**
     * Crea una instancia vacía de `Payment`.
     */
    public Payment() {
    }

    /**
     * Obtiene El ID del pago.
     * 
     * @return ID del pago.
     */
    public int getIdPayment() {
        return idRegister;
    }

    /**
     * Establece el ID del Pago.
     * 
     * @param idPayment El nuevo ID del pago.
     */
    public void setIdPayment(int idPayment) {
        this.idRegister = idPayment;
    }

    /**
     * Obtiene el ID del contracto del pago.
     * 
     * @return El ID del contrato del pago.
     */
    public int getIdContract() {
        return idContract;
    }

    /**
     * Establece el ID del contrato del Pago.
     * 
     * @param idContract El nuevo ID del contrato del pago.
     */
    public void setIdContract(int idContract) {
        this.idContract = idContract;
    }

    /**
     * Obtiene la fecha del pago.
     * 
     * @return La fecha del pago.
     */
    public Date getDatePayment() {
        return datePayment;
    }

    /**
     * Establece la fecha del pago.
     * 
     * @param datePayment La nueva fecha del pago.
     */
    public void setDatePayment(Date datePayment) {
        this.datePayment = datePayment;
    }

    /**
     * Obtiene el monto del pago.
     * 
     * @return El monto del pago.
     */
    public float getAmountPayment() {
        return amountPayment;
    }

    /**
     * Establece el monto del pago.
     * 
     * @param amountPayment El nuevo monto del pago.
     */
    public void setAmountPayment(float amountPayment) {
        this.amountPayment = amountPayment;
    }

    /**
     * Obtiene la fecha obligatoria del pago.
     * 
     * @return La fecha obligatoria de pago.
     */
    public Date getDateMandatory() {
        return dateMandatory;
    }

    /**
     * Establece la fecha obligatoria del pago.
     * 
     * @param dateMandatory La nueva fecha obligatoria del pago.
     */
    public void setDateMandatory(Date dateMandatory) {
        this.dateMandatory = dateMandatory;
    }

    /**
     * Obtiene el mont obligatorio del pago.
     * 
     * @return El monto obligatorio del pago.
     */
    public float getAmountMandatory() {
        return amountMandatory;
    }

    /**
     * Establece el monto obligatorio del pago.
     * 
     * @param amountMandatory El nuevo monto obligatorio del pago.
     */
    public void setAmountMandatory(float amountMandatory) {
        this.amountMandatory = amountMandatory;
    }

    /**
     * Obtiene el metodo de pago del pago.
     * 
     * @return El metodo de pago del pago.
     */
    public Method getMethod() {
        return method;
    }

    /**
     * Establece el metodo de pago del pago.
     * 
     * @param method El nuevo metodo de pago del pago.
     */
    public void setMethod(Method method) {
        this.method = method;
    }

    /**
     * Obtiene la observación del pago.
     * 
     * @return La observación del pago.
     */
    public String getObservation() {
        return observation;
    }

    /**
     * Establece la observación del pago.
     * 
     * @param observation La nueva observación del pago.
     */
    public void setObservation(String observation) {
        this.observation = observation;
    }

    /**
     * Obtiene el estado del pago.
     * 
     * @return El estado del pago.
     */
    public State getState() {
        return state;
    }

    /**
     * Establece el estado del pago.
     * 
     * @param state El nuevo estado del pago.
     */
    public void setState(State state) {
        this.state = state;
    }

    @Override
    public IPrototype prototype() {
        return new Payment(idRegister, idContract, datePayment, amountPayment, dateMandatory, amountMandatory, method,
                observation, state);
    }
}
