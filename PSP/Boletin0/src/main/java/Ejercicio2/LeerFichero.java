package Ejercicio2;

import java.io.*;
/**
 * Clase LeerFichero:
 * Se encarga de acceder a un fichero y rescatar su información.
 * Tiene una función para mostrar por pantalla la información del archivo.
 * Su constructor toma una cadena que será una dirección de un archivo txt.
 */

public class LeerFichero {
    File fichero;
    
    
    public LeerFichero(String archivo) {
        this.fichero = new File(archivo);
        
        if (!this.fichero.exists()) try {
            this.fichero.createNewFile();
        } catch (IOException ex) {
            System.out.println("Oops.");
        }
    }
    
    public LeerFichero(File archivo) {
        this.fichero = archivo;
    }
    
    public void lee() {
        BufferedReader flujo;
        try {
            String cadena;
            flujo = new BufferedReader(new FileReader(fichero));
            if (fichero.exists())
            {
                cadena = flujo.readLine();
                while (cadena != null)
                {
                    if (!cadena.equals("\n"))
                        System.out.println(cadena);
                    
                    cadena = flujo.readLine();
                }
            }
            flujo.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error: El archivo no existe.");
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo.");
        }
    }
    
    
}
