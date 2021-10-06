package Ejercicio6;

import java.io.*;

public class MezclarFicheros {
    
    public static void main(String args[]) {
        
        InputStreamReader flujo = new InputStreamReader(System.in);
        BufferedReader lector = new BufferedReader(flujo);
        
        String rutaArchivo1 = "";
        String rutaArchivo2 = "";
        String rutaMezcla = "";
        
        try {
            System.out.println("Introduzca la ruta al primer archivo.");
            rutaArchivo1 = lector.readLine();
            System.out.println("Introduzca la ruta al segundo archivo.");
            rutaArchivo2 = lector.readLine();
            System.out.println("Introduzca la ruta al archivo donde se mezclarán los dos anteriores.");
            rutaMezcla = lector.readLine();
        }
        catch(IOException e) {
            System.out.println("Oops.");
        }
        
        mezclar(rutaArchivo1, rutaArchivo2, rutaMezcla);
    }

    private static void mezclar(String rutaArchivo1, String rutaArchivo2, String rutaMezcla) {
        File    archivo1 = new File(rutaArchivo1),
                archivo2 = new File(rutaArchivo2),
                archMezcla = new File(rutaMezcla);
        
        try {
            // Si no existe el archivo para mezclar, lo creamos
            if (!archMezcla.exists()) archMezcla.createNewFile();
            
            // Inicialización de flujos
            BufferedReader  lector1 = new BufferedReader(new FileReader(archivo1)),
                            lector2 = new BufferedReader(new FileReader(archivo2));
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archMezcla));
            
            // Inicialización de variables
            String cad1 = lector1.readLine();
            String cad2 = lector2.readLine();
            boolean flag = true;
            
            /**
             * Bucle el cual se repetirá mientras alguno de los archivos tenga
             * datos por leer.
             * Funciona tal que leerá datos de forma alterna, entre los dos 
             * archivos dados por consola.
             */
            while (cad1 != null || cad2 != null) {
                if (flag) {
                    if (cad1 != null) {
                        escritor.append(cad1 + "\n");
                        cad1 = lector1.readLine();
                    }
                    flag = false;
                }
                else {
                    if (cad2 != null) {
                        escritor.append(cad2 + "\n");
                        cad2 = lector2.readLine();
                    }
                    flag = true;
                }
            }
            
            // Cierre de flujos
            lector1.close();
            lector2.close();
            escritor.close();
        }
        catch (IOException e) {
            System.out.println("Oops.");
        }
        
        System.out.println("Mezcla terminada.");    
    }
}
