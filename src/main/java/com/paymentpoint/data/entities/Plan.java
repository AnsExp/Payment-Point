package com.paymentpoint.data.entities;

import com.paymentpoint.data.build.IPrototype;

/**
 * La clase {@code Plan} representa un plan o programa en un sistema.
 * Hereda de la clase {@code Registrable}, lo que significa que puede ser
 * registrado.
 *
 * @see Registrable
 */
public final class Plan extends Registrable {

    private String name;
    private int steps;
    private float amount;
    private float tax;
    private float payment;
    private State state;

    /**
     * Devuelve una representación en cadena de la instancia {@code Plan}.
     *
     * @return Una cadena que muestra los detalles del plan.
     */
    @Override
    public String toString() {
        return "Plan [name=" + name + ", steps=" + steps + ", amount=" + amount + ", tax=" + tax + ", amountPayment="
                + payment + ", state=" + state + "]";
    }

    /**
     * Crea una instancia de {@code Plan} con un ID de plan específico y otros
     * detalles.
     *
     * @param idPlan        El ID del plan.
     * @param name          El nombre del plan.
     * @param steps         El número de pasos o etapas en el plan.
     * @param amount        El monto total del plan.
     * @param tax           El impuesto asociado al plan.
     * @param amountPayment El monto de pago requerido para el plan.
     */
    public Plan(int idPlan, String name, int steps, float amount, float tax, float amountPayment, State state) {
        this(name, steps, amount, tax, amountPayment, state);
        this.idRegister = idPlan;
    }

    /**
     * Crea una instancia de {@code Plan} con detalles de nombre, pasos, monto,
     * impuesto y
     * pago.
     *
     * @param name          El nombre del plan.
     * @param steps         El número de pasos o etapas en el plan.
     * @param amount        El monto total del plan.
     * @param tax           El impuesto asociado al plan.
     * @param amountPayment El monto de pago requerido para el plan.
     */
    public Plan(String name, int steps, float amount, float tax, float amountPayment, State state) {
        this.tax = tax;
        this.name = name;
        this.state = state;
        this.steps = steps;
        this.amount = amount;
        this.payment = amountPayment;
    }

    /**
     * Crea una instancia de {@code Plan} con un ID de plan específico.
     *
     * @param idPlan El ID del plan.
     */
    public Plan(int idPlan) {
        this.idRegister = idPlan;
    }

    /**
     * Crea una instancia vacía de {@code Plan}.
     */
    public Plan() {
    }

    /**
     * Obtiene el nombre del plan.
     *
     * @return El nombre del plan.
     */
    public String getName() {
        return name;
    }

    /**
     * Estable el nombre del plan
     *
     * @param name El nuevo nombre del plan.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene los pasos del plan.
     *
     * @return Los pasos del plan.
     */
    public int getSteps() {
        return steps;
    }

    /**
     * Establece los pasos del plan.
     *
     * @param steps Los nuevos pasos del plan.
     */
    public void setSteps(int steps) {
        this.steps = steps;
    }

    /**
     * Obtiene el monte del plan.
     *
     * @return El monto del plan.
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Establece el monto del plan.
     *
     * @param amount El nuevo monto del plan.
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }

    /**
     * Obtiene el interés del plan.
     *
     * @return El interés del plan.
     */
    public float getTax() {
        return tax;
    }

    /**
     * Establece el interés del plan.
     *
     * @param tax el nuevo interés del plan.
     */
    public void setTax(float tax) {
        this.tax = tax;
    }

    /**
     * Obtiene el monto del pago.
     *
     * @return El monto del pago.
     */
    public float getPayment() {
        return payment;
    }

    /**
     * Establece el monto del pago.
     *
     * @param payment El nuevo monto del pago.
     */
    public void setPayment(float payment) {
        this.payment = payment;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getIdPlan() {
        return idRegister;
    }

    public void setIdPlan(int idPlan) {
        this.idRegister = idPlan;
    }

    @Override
    public IPrototype prototype() {
        return new Plan(idRegister, name, steps, amount, tax, payment, state);
    }
}
