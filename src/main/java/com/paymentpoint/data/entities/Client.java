package com.paymentpoint.data.entities;

import com.paymentpoint.data.build.IPrototype;

/**
 * La clase {@code Client} representa a un cliente en un sistema.
 * Hereda de la clase {@code Registrable}, lo que significa que puede ser
 * registrado.
 *
 * @see Registrable
 */
public final class Client extends Registrable {

    private String name;
    private String lastname;
    private String phone;
    private String idCard;

    /**
     * Devuelve una representación en cadena de la instancia {@code Client}.
     *
     * @return Una cadena que muestra los detalles del cliente.
     */
    @Override
    public String toString() {
        return "Client [idClient=" + idRegister + ", name=" + name + ", lastname=" + lastname + ", phone=" + phone
                + ", idCard=" + idCard + "]";
    }

    /**
     * Crea una instancia de {@code Client} con un ID de cliente específico y otros
     * detalles.
     *
     * @param idClient El ID de cliente.
     * @param name     El nombre del cliente.
     * @param lastname El apellido del cliente.
     * @param phone    El número de teléfono del cliente.
     * @param idCard   El número de identificación del cliente (por ejemplo, cédula
     *                 o pasaporte).
     */
    public Client(int idClient, String name, String lastname, String phone, String idCard) {
        this(name, lastname, phone, idCard);
        this.idRegister = idClient;
    }

    /**
     * Crea una instancia de {@code Client} con detalles de nombre, apellido,
     * teléfono e
     * identificación.
     *
     * @param name     El nombre del cliente.
     * @param lastname El apellido del cliente.
     * @param phone    El número de teléfono del cliente.
     * @param idCard   El número de identificación del cliente (por ejemplo, cédula
     *                 o pasaporte).
     */
    public Client(String name, String lastname, String phone, String idCard) {
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.idCard = idCard;
    }

    /**
     * Crea una instancia de {@code Client} con un ID de cliente específico.
     *
     * @param idClient El ID de cliente.
     */
    public Client(int idClient) {
        this.idRegister = idClient;
    }

    /**
     * Crea una instancia vacía de {@code Client}.
     */
    public Client() {
    }

    /**
     * Obtiene el nombre del cliente.
     *
     * @return El nombre del cliente.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del cliente.
     *
     * @param name El nuevo nombre del cliente.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el apellido del cliente.
     *
     * @return El apellido del cliente.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Establece el apellido del cliente.
     *
     * @param lastname El nuevo apellido del cliente.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Obtiene el número de teléfono del cliente.
     *
     * @return El número de teléfono del cliente.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Establece el número de teléfono del cliente.
     *
     * @param phone El nuevo número de teléfono del cliente.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Obtiene el número de identificación del cliente.
     *
     * @return El número de identificación del cliente.
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * Establece el número de identificación del cliente.
     *
     * @param idCard El nuevo número de identificación del cliente.
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /**
     * Obtiene el ID del cliente;
     *
     * @return el ID del client;
     */
    public int getIdClient() {
        return idRegister;
    }

    /**
     * Establece el ID del cliente.
     *
     * @param idClient El nuevo ID del cliente.
     */
    public void setIdClient(int idClient) {
        this.idRegister = idClient;
    }

    @Override
    public IPrototype prototype() {
        return new Client(idRegister, name, lastname, phone, idCard);
    }
}
