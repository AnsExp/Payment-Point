package com.paymentpoint.controller.command;

import com.paymentpoint.controller.log.ILogger;

/**
 * La clase {@code ControlService} proporciona un servicio para ejecutar una
 * acción de control. Utiliza una implementación de la interfaz {@code IControl}
 * y un registro de eventos a través de la interfaz {@code ILogger}.
 */
public final class ControlService {

    private final IControl control;
    private final ILogger logger;

    /**
     * Crea una instancia de {@code ControlService} con una implementación de
     * {@code IControl} y un registro de eventos.
     *
     * @param control La implementación de {@code IControl} para ejecutar la acción
     *                de
     *                control.
     * @param logger  El registro de eventos a través de la interfaz
     *                {@code ILogger}.
     */
    public ControlService(IControl control, ILogger logger) {
        this.control = control;
        this.logger = logger;
    }

    /**
     * Ejecuta la acción de control y registra eventos según el resultado.
     *
     * @return {@code true} si la acción se completó correctamente, {@code false} si
     *         ocurrió un error.
     */
    public boolean controlService() {
        try {
            logger.warning("Control Executing");
            control.execute();
            logger.info("Control Completed");
            return true;
        } catch (Exception e) {
            logger.error("Control Error", e);
            return false;
        }
    }
}
