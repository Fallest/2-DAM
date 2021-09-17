package Ejemplo1;

import java.io.*;

public class Ejemplo1 {
    //Clase de ejemplo para leer y escribir datos en un archivo.
    
    public static void main(String[] args) {
        String archivo = "textoejemplo.txt";
        
        leerDatos(archivo);
    }
    
    public static void leerDatos(String archivo) {
        // Creamos un bloque try-catch para evitar problemas con
        // las IOException.
        try {
            // Creamos un objeto para el archivo .txt
            FileReader txt = new FileReader(archivo);
            
            // Abrimos un flujo para leer desde el lector
            BufferedReader reader = new BufferedReader(txt);
            
            // Leemos una línea del flujo del objeto del archivo
            String line = reader.readLine();
            
            // Creamos un bucle while() en el que vamos a seguir leyendo
            // línea a línea hasta que lleguemos al final del archivo
            while (line != null) {
                // Mostramos los datos leídos por pantalla
                System.out.println(line);
                
                // Pasamos a la siguiente línea
                line = reader.readLine();
            }
            
            // No olvidemos cerrar el flujo
            reader.close();
        }
        catch (FileNotFoundException f) {
            System.out.println("Archivo no encontrado");
            System.exit(2);
        }
        catch (IOException e) {
            System.out.println("Ha ocurrido un error en la lectura de datos");
            System.exit(2);
        }
    }
}
