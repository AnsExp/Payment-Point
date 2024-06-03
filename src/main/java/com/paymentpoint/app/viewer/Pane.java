package com.paymentpoint.app.viewer;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

public class Pane extends JComponent {

    private JComponent label;
    private JComponent buttonPane;
    private static final int FONT_TITLE_SIZE = 40;
    private static final int FONT_SUBTITLE_SIZE = 20;

    public Pane() {
        initComponents();
    }

    private void initComponents() {
        label = new JLabel();
        buttonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        label.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setLayout(new BorderLayout());
        add(label, BorderLayout.NORTH);
        add(buttonPane, BorderLayout.SOUTH);
        ((JLabel) label).setHorizontalAlignment(SwingConstants.LEFT);
    }

    public void setTitle(String title) {
        ((JLabel) label).setText(
                "<html>" +
                    "<head>" +
                        "<style>" +
                            "h1 { size-font:" + FONT_TITLE_SIZE + "; }" +
                        "</style>" +
                    "</head>" +
                    "<body>" +
                        "<h1>" + title + "</h1>" +
                    "</body>" +
                "</html>");
    }

    public void setTitle(String title, String subtitle) {
        ((JLabel) label).setText(
                "<html>" +
                    "<head>" +
                        "<style>" +
                            "h1 { size-font:" + FONT_TITLE_SIZE + "; margin-bottom: 0px; }" +
                            "h2 { size-font:" + FONT_SUBTITLE_SIZE + "; }" +
                        "</style>" +
                    "</head>" +
                    "<body>" +
                        "<h1>" + title + "</h1>" +
                        "<h2>" + subtitle + "</h2>" +
                    "</body>" +
                "</html>");
    }

    private Component content;

    public void setContent(Component component) {
        add(component, BorderLayout.CENTER);
        this.content = component;
    }

    public void removeContent() {
        if (content == null) return;
        remove(content);
    }

    public void addButton(AbstractButton button) {
        buttonPane.add(button);
    }
}
