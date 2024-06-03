package com.paymentpoint.app.component;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * Esta clase representa un botón personalizado con funcionalidad adicional.
 * Extiende la clase JButton.
 */
public class CommandButton extends JButton {

    /**
     * Constructor que inicializa el botón.
     */
    public CommandButton() {
        initComponents();
    }

    /**
     * Inicializa los componentes del botón.
     */
    private void initComponents() {

        Color font = getForeground();
        Color back = getBackground();

        setBackground(null);

        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(font);
                setForeground(back);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(null);
                setForeground(font);
            }
        });
    }

    /**
     * Establece el comando a ejecutar cuando se hace clic en el botón.
     *
     * @param runnable El comando a asignar.
     */
    public void setCommand(Runnable runnable) {

        for (ActionListener listener : getActionListeners())
            removeActionListener(listener);

        addActionListener(_ -> runnable.run());
    }
}
