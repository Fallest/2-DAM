package Ejercicio3;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;

public class Ejemplo2URL {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.elaltozano.es");
            InputStream lector = url.openStream();

            for (byte b : lector.readAllBytes())
                System.out.print((char) b);
        } catch (MalformedURLException ex) {
            System.out.println("ERROR - Excepción en la URL.");
        } catch (IOException ex) {
            System.out.println("ERROR - Excepción en la lectura de datos.");
        }
    }
}
