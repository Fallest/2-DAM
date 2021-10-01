package Ejercicio2;

import java.io.*;
import java.util.ArrayList;
import static java.util.stream.IntStream.range;
/**
 * Clase EscribirFichero:
 * Se encarga de acceder a un fichero y guardar información en él.
 * Su constructor toma una cadena que será una dirección de un archivo txt.
 */

public class EscribirFichero {
    File fichero;
    
    public EscribirFichero(String archivo) {
        fichero = new File(archivo);
    }
    
    public void escribe() {
        BufferedWriter flujo;
        try {
            flujo = new BufferedWriter(new FileWriter(fichero));
            if (fichero.exists())
            {
                for (int i = 0; i < 10; i++ ) {
                    String cad = "Línea " + (i) + "\n";
                    flujo.append(cad.subSequence(0, cad.length()));
                }
            }
            flujo.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error: El archivo no existe.");
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo.");
        }
    }
}
