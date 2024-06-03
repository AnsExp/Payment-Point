package com.paymentpoint.controller.report;

import com.paymentpoint.controller.log.ILogger;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * La clase {@code ReporterService} proporciona un servicio para generar
 * informes. Utiliza una implementación de la interfaz {@code IReporter} para
 * generar el informe y registra eventos a través de la interfaz
 * {@code ILogger}.
 */
public final class ReporterService {

    private final IReporter reporter;
    private final ILogger logger;

    /**
     * Crea una instancia de {@code ReporterService} con una implementación de
     * {@code IReporter} y un registro de eventos.
     *
     * @param reporter La implementación de {@code IReporter} para generar el
     *                 informe.
     * @param logger   El registro de eventos a través de la interfaz
     *                 {@code ILogger}.
     */
    public ReporterService(IReporter reporter, ILogger logger) {
        this.reporter = reporter;
        this.logger = logger;
    }

    /**
     * Genera el informe y registra eventos según el resultado.
     */
    public void generateReport() {
        try {
            logger.warning("Control Executing");
            reporter.generate();
            logger.info("Control Completed");
        } catch (Exception e) {
            logger.error("Control Error", e);
        }
    }

    /**
     * Elige el archivo en donde se guardará el reporte.
     *
     * @return El archivo del reporte.
     */
    public static File selectFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Document PDF", "pdf"));
        int result = fileChooser.showSaveDialog(null);
        if (result != JFileChooser.APPROVE_OPTION)
            return null;
        else
            return fileChooser.getSelectedFile();
    }
}
