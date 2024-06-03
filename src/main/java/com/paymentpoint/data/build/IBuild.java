package com.paymentpoint.data.build;

/**
 * La interfaz funcional {@code IBuild} define un método {@code build()} que permite
 * construir un objeto de tipo genérico.
 * Puede utilizarse para crear instancias personalizadas de clases o interfaces.
 *
 * @param <T> El tipo de objeto que se va a construir.
 */
@FunctionalInterface
public interface IBuild<T> {
    /**
     * Construye un objeto de tipo genérico.
     *
     * @return El objeto construido.
     */
    T build();
}
