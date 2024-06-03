package com.paymentpoint.controller.validation;

/**
 * La interfaz funcional {@code IValidator} define un método {@code verify(T o)} para verificar si un objeto cumple con ciertas condiciones.
 * También proporciona un método {@code messageInvalidation()} para obtener un mensaje descriptivo relacionado con la validación.
 *
 * @param <T> El tipo de objeto que se va a verificar.
 */
public interface IValidator<T> {
    /**
     * Verifica si un objeto cumple con ciertas condiciones.
     *
     * @param o El objeto a verificar.
     * @return {@code true} si el objeto cumple con las condiciones, {@code false} en caso contrario.
     */
    boolean verify(T o);

    /**
     * Obtiene un mensaje descriptivo relacionado con la validación.
     *
     * @return El mensaje de validación.
     */
    String messageInvalidation();
}
