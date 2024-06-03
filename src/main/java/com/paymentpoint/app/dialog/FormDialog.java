package com.paymentpoint.app.dialog;

import com.paymentpoint.app.component.CommandButton;
import com.paymentpoint.controller.settings.Language;

import java.awt.Window;
import java.util.function.Consumer;

public abstract class FormDialog extends ParentDialog implements Consumer<Integer> {

    protected CommandButton submitButton;
    protected CommandButton cancelButton;

    protected int idForm = -1;

    public FormDialog(Window parent) {
        super(parent);

        submitButton = new CommandButton();
        cancelButton = new CommandButton();

        contentPane.addButton(submitButton);
        contentPane.addButton(cancelButton);

        cancelButton.setCommand(this::dispose);

        Language lang = new Language();

        submitButton.setText(lang.getText("submit"));
        cancelButton.setText(lang.getText("cancel"));
    }

    public void setSubmitEvent(Runnable runnable) {
        submitButton.setCommand(runnable);
    }

    public void setCancelEvent(Runnable runnable) {
        cancelButton.setCommand(runnable);
    }
}
