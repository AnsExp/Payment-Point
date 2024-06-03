package com.paymentpoint.data.entities;

import com.paymentpoint.data.build.IPrototype;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public abstract class Registrable implements Serializable, IPrototype {

    protected int idRegister;

    @Contract(pure = true)
    protected Registrable() {
    }

    @Contract(pure = true)
    protected Registrable(int idRegister) {
        this.idRegister = idRegister;
    }

    public int getIdRegister() {
        return idRegister;
    }

    public void setIdRegister(int idRegister) {
        this.idRegister = idRegister;
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull Registrable getInstance(int id) {
        return new Registrable(id) {
            @Override
            public IPrototype prototype() {
                return null;
            }
        };
    }
}
