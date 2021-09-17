package Ejemplo1;

import java.io.*;

public class Ejemplo1 {
    //Clase de ejemplo para leer y escribir datos en un archivo.
    
    public static void main(String[] args) {
        String archivo = "C:\\Users\\David\\Documents\\2-DAM\\FPIGS\\Flujos\\EjemploFlujos\\src\\main\\java\\Ejemplo1\\textoejemplo.txt";
        
        System.out.println("Vamos a leer los datos almacenados en el archivo:\n");
        leerDatos(archivo);
        
        System.out.println("\nVamos a escribir algunos datos en el archivo:\n");
        escribirDatos(archivo);
        
        System.out.println("\nHas escrito la siguiente frase:");
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

    private static void escribirDatos(String archivo) {
        // Creamos un bloque try-catch para evitar problemas con
        // las IOException.
        try {
            // Creamos un objeto para el archivo .txt
            FileOutputStream txt = new FileOutputStream(archivo);
            DataOutputStream salida = new DataOutputStream(txt);
            
            // Abrimos un flujo para leer lo que escribimos desde el teclado
            InputStreamReader flujoLector = new InputStreamReader(System.in);
            BufferedReader entrada = new BufferedReader(flujoLector);
            
            // Leemos una línea del flujo de lectura
            String line = entrada.readLine();
            
            // Escribimos en el archivo la línea escrita
            salida.writeChars(line);
            
            // No olvidemos cerrar el flujo
            entrada.close();
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
