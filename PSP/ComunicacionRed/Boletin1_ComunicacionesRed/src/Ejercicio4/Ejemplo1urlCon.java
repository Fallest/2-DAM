package Ejercicio4;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;

public class Ejemplo1urlCon {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.elaltozano.es");
         
            URLConnection con = url.openConnection();
            InputStream lector = con.getInputStream();
            
            for (byte b : lector.readAllBytes())
                System.out.print((char) b);
            
        } catch (MalformedURLException ex) {
            System.out.println("ERROR - Excepción en la URL.");
        } catch (IOException ex) {
            System.out.println("ERROR - Excepción en el IS.");
        }
    }
}
