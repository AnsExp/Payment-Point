package com.paymentpoint.controller.log;

/**
 * La interfaz {@code ILogger} define métodos para registrar información,
 * advertencias y errores en un sistema.
 * Puede utilizarse para implementar un mecanismo de registro o seguimiento de
 * eventos.
 */
public interface ILogger {
    /**
     * Registra un mensaje de información.
     *
     * @param info El mensaje de información a registrar.
     */
    void info(String info);

    /**
     * Registra una advertencia.
     *
     * @param warning La advertencia a registrar.
     */
    void warning(String warning);

    /**
     * Registra un mensaje de error junto con una excepción.
     *
     * @param msg El mensaje de error.
     * @param e   La excepción asociada al error.
     */
    void error(String msg, Exception e);
}
