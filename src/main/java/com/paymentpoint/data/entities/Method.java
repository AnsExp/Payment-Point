package com.paymentpoint.data.entities;

/**
 * La enumeración {@code Method} representa los diferentes métodos de pago disponibles.
 * Puede utilizarse para indicar cómo se realiza un pago en un sistema o
 * aplicación.
 */
public enum Method {
    /**
     * Pago en efectivo.
     */
    CASH,
    /**
     * Pago adicional o extra, aparte de los métodos principales.
     */
    EXTRA,
    /**
     * Pago mediante transferencia bancaria.
     */
    TRANSFER,
    /**
     * Otro método de pago no especificado.
     */
    OTHER
}
