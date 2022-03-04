package Ejercicio2;

import java.io.IOException;
import java.net.Socket;

/**
 * Esta clase debe mostrar los puertos abiertos de localhost en el rango
 * [1-1024]. Si creamos un Socket en un puerto cerrado, lanzará la excepción
 * IOException. Si no, se conectará.
 *
 * Si se conecta, el puerto está abierto.
 */
public class ScannerPuertos {

    public static void main(String[] args) {
        Socket s;

        System.out.println("Iniciando escaneo de puertos...\n");
        System.out.println("| PUERTO | ESTADO  |");
        System.out.println("|________|_________|");
        for (int i = 1; i <= 1024; i++) {
            try {

                s = new Socket("localhost", i);
                String aux = String.valueOf(i);
                switch (aux.length()) {
                    case 1: {
                        System.out.println("|   " + aux + "    | ABIERTO |");
                        break;
                    }
                    case 2: {
                        System.out.println("|  " + aux + "    | ABIERTO |");
                        break;
                    }
                    case 3: {
                        System.out.println("|  " + aux + "   | ABIERTO |");
                        break;
                    }
                }
                s.close();
            } catch (IOException ignored) {
            }
        }
        System.out.println("|********|*********|");
        System.out.println("\nEscaneo de puertos terminado.");
    }
}
