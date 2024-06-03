package com.paymentpoint.controller.report;

/**
 * La interfaz funcional {@code IReporter} define un método {@code generate()}
 * que puede lanzar una excepción. Puede utilizarse para implementar generadores
 * de informes o tareas que requieren procesamiento y generación de datos.
 */
@FunctionalInterface
public interface IReporter {
    /**
     * Genera un informe o realiza una tarea específica.
     *
     * @throws Exception Si ocurre un error durante la generación.
     */
    void generate() throws Exception;
}
