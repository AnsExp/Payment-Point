package com.paymentpoint.controller.settings;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * La clase {@code Settings} proporciona una interfaz para gestionar preferencias y propiedades del sistema.
 * Puede utilizarse para cargar, almacenar y acceder a valores de configuración.
 */
public class Settings {

    private static Settings instance;
    private static JsonObject jsonObject = null;
    private static final String PATH = "src/main/resources/init.json";

    private Settings() {
        try {
            loadPreferenceFile();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    /**
     * Carga el archivo de preferencias.
     *
     * @throws IOException Si ocurre un error durante la carga.
     */
    private void loadPreferenceFile() throws IOException {
        jsonObject = JsonParser
                .parseReader(new FileReader(PATH))
                .getAsJsonObject();
    }

    /**
     * Almacena el archivo de preferencias.
     *
     * @throws IOException Si ocurre un error durante el almacenamiento.
     */
    public void storePreferenceFile() throws IOException {
        FileWriter writer = new FileWriter(PATH);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(jsonObject);

        writer.write(jsonString);
        writer.close();
    }

    /**
     * Obtiene una instancia única de la clase `Settings`.
     *
     * @return La instancia única de `Settings`.
     */
    public static Settings getInstance() {

        if (instance == null)
            instance = new Settings();

        return instance;
    }

    /**
     * Obtiene el valor de una propiedad como un número de punto flotante.
     *
     * @param key La clave de la propiedad.
     * @return El valor de la propiedad como un número de punto flotante.
     */
    public Float getPropertyFloat(String key) {
        return jsonObject.get(key).getAsFloat();
    }

    /**
     * Obtiene el valor de una propiedad como una cadena de texto.
     *
     * @param key La clave de la propiedad.
     * @return El valor de la propiedad como una cadena de texto.
     */
    public String getPropertyString(String key) {
        return jsonObject.get(key).getAsString();
    }

    /**
     * Obtiene el valor de una propiedad como un número entero.
     *
     * @param key La clave de la propiedad.
     * @return El valor de la propiedad como un número entero.
     */
    public Integer getPropertyInteger(String key) {
        return jsonObject.get(key).getAsInt();
    }

    /**
     * Obtiene el valor de una propiedad como un valor booleano.
     *
     * @param key La clave de la propiedad.
     * @return El valor de la propiedad como un valor booleano.
     */
    public Boolean getPropertyBoolean(String key) {
        return jsonObject.get(key).getAsBoolean();
    }

    /**
     * Establece el valor de una propiedad como un número de punto flotante.
     *
     * @param key   La clave de la propiedad.
     * @param value El valor a establecer.
     */
    public void setProperty(String key, Float value) {
        jsonObject.addProperty(key, value);
    }

    /**
     * Establece el valor de una propiedad como una cadena de texto.
     *
     * @param key   La clave de la propiedad.
     * @param value El valor a establecer.
     */
    public void setProperty(String key, String value) {
        jsonObject.addProperty(key, value);
    }

    /**
     * Establece el valor de una propiedad como un número entero.
     *
     * @param key   La clave de la propiedad.
     * @param value El valor a establecer.
     */
    public void setProperty(String key, Integer value) {
        jsonObject.addProperty(key, value);
    }

    /**
     * Establece el valor de una propiedad como un valor booleano.
     *
     * @param key   La clave de la propiedad.
     * @param value El valor a establecer.
     */
    public void setProperty(String key, Boolean value) {
        jsonObject.addProperty(key, value);
    }
}
