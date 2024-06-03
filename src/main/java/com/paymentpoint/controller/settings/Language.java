package com.paymentpoint.controller.settings;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * La clase {@code Language} proporciona una interfaz para gestionar textos y diccionarios.
 * Puede utilizarse para obtener textos localizados o personalizados según un diccionario específico.
 */
public class Language {

    private static Map<String, String> dictionary;
    private static final String URL_FILE = "src/main/resources/lang.csv";

    /**
     * Crea una instancia de la clase {@code Language}.
     */
    public Language() {
        initComponents();
    }

    private void initComponents() {
        if (dictionary == null) {
            dictionary = new HashMap<>();
            setDictionary(Settings.getInstance().getPropertyInteger("language-system"));
        }
    }

    /**
     * Obtiene el texto asociado a una clave específica.
     *
     * @param key La clave del texto a obtener.
     * @return El texto correspondiente a la clave.
     */
    public String getText(String key) {
        return dictionary.get(key);
    }

    /**
     * Establece el diccionario de textos según un índice específico.
     *
     * @param index El índice del diccionario a establecer.
     */
    public void setDictionary(int index) {

        dictionary.clear();

        char separator = ',';

        Reader fr;
        try {

            fr = new InputStreamReader(new FileInputStream(URL_FILE), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(fr);

            String line;
            String[] parts;

            while ((line = br.readLine()) != null) {
                parts = line.split(String.valueOf(separator));
                dictionary.put(parts[0], parts[index]);
            }

            br.close();
            fr.close();

        } catch (IOException _) {
            setDictionary(1);
        }
    }
}
