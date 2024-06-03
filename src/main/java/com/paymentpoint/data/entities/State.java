package com.paymentpoint.data.entities;

/**
 * La enumeración {@code State} representa diferentes estados o situaciones.
 * Puede utilizarse para indicar el estado de un objeto o proceso en un sistema.
 */
public enum State {
    /**
     * Estado habilitado o activo.
     */
    ENABLED,
    /**
     * Estado obsoleto o en desuso.
     */
    DEPRECATED,
    /**
     * Estado completado o finalizado.
     */
    COMPLETED,
    /**
     * En curso o en progreso.
     */
    ON_COURSE,
    /**
     * Pendiente o a la espera de acción.
     */
    PENDING,
    /**
     * En tiempo o puntual.
     */
    ON_TIME,
    /**
     * Retrasado o fuera de tiempo.
     */
    LATE,
    SANCTION
}
