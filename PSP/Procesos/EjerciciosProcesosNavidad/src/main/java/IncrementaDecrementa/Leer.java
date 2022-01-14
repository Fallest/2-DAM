package IncrementaDecrementa;

import java.io.*;

public class Leer {

    public static void imprimeMensaje(String mensaje) {
        System.out.println(mensaje + " ");
    }

    public static String dato() {
        String sdato = "";
        try {
            // Definir un flujo de caracteres de entrada: flujoE
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader flujoE = new BufferedReader(isr);
            // Leer. La entrada finaliza al pulsar la tecla Entrar
            sdato = flujoE.readLine();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return sdato; // devolver el dato tecleado
    }

    public static int datoInt(String mensaje) {
        while (true) {
            imprimeMensaje(mensaje);
            try {
                return Integer.parseInt(dato());
            } catch (NumberFormatException e) {
                System.out.println("\n\nERROR: Debes introducir un valor numerico");
            }
        }

    }
}
