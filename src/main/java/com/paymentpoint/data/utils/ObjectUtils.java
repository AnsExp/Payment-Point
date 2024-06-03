package com.paymentpoint.data.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public final class ObjectUtils {

    private ObjectUtils() { }

    public static void exportObject(Object obj, String path) {
        try {
            OutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public static Object inportObject(String path) {
        try {
            InputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            var object = ois.readObject();
            ois.close();
            fis.close();
            return object;
        } catch (IOException | ClassNotFoundException _) {
            return null;
        }
    }
}
