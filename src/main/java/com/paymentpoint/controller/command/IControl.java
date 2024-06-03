package com.paymentpoint.controller.command;

/**
 * La interfaz funcional {@code IControl} define un método {@code execute()} que
 * puede lanzar una excepción. Puede utilizarse para implementar acciones o
 * tareas específicas que requieren ejecución.
 */
@FunctionalInterface
public interface IControl {
    /**
     * Ejecuta una acción o tarea específica.
     *
     * @throws Exception Si ocurre un error durante la ejecución.
     */
    void execute() throws Exception;
}
