package Ejercicio9;

import java.io.*;

public class CopiarArchivos {
    public static void main(String args[]) {
        InputStreamReader flujo = new InputStreamReader(System.in);
        BufferedReader lector = new BufferedReader(flujo);
        
        try {
            System.out.println("Introduce la ruta origen: ");
            String rutaOrigen = lector.readLine();
            System.out.println("Introduce la ruta destino: ");
            String rutaDestino = lector.readLine();
            
            copiarRama(rutaOrigen, rutaDestino);
            
            lector.close();
            flujo.close();
        }
        catch(IOException ex) {System.out.println("Oops. (main)");}
    }
    
    /**
     * Función coparArchivos(ruta de origen, ruta de destino):
     * Va a copiar todo lo que haya en la ruta origen a la ruta de destino.
     * Se usará una función auxiliar para rescatar los datos que haya en cada
     * directorio.
     */
    public static void copiarRama(String rutaOrigen, String rutaDestino) {
        File    origen = new File(rutaOrigen),
                destino = new File(rutaDestino),
                actual = new File(rutaOrigen);
        
        // Comprobamos si existe la ruta de destino. Si no, la creamos.
        if (!destino.exists()) {
            try {destino.createNewFile();}
            catch(Exception e) {System.out.println("Oops. (copiarrama, crear destino)");}
        }
        
        // Usamos la función auxiliar para copiar lo que haya donde estemos ahora
        // mismo al destino.
        CopiarArchivos.copiarDirectorio(actual, destino);
        
    }

    private static void copiarDirectorio(File actual, File destino) {
        for(File f: actual.listFiles()) {
            if(f.isDirectory()) {
                File des = new File(destino.getPath() + "\\" + f.getName());
                if(!des.exists()) {
                    try {des.mkdir();}
                    catch(Exception ex) {System.out.println("Oops.");}
                }
                
                copiarDirectorio(f, des);
            }
            if(f.isFile())
                copiarArchivo(f, destino);
        }
    }

    private static void copiarArchivo(File f, File destino) {
        File copia = new File(destino.getPath() + "\\" + f.getName());
        
        // Si no existe la copia, la creamos
        if (!copia.exists()) {
            try {
                copia.createNewFile();
            }
            catch(Exception e) {System.out.println("Oops. (copiararchivo, crear archivos)");}
        }
        
        // Leemos todos los bytes de f y los escribimos en copia.
        try {
            FileInputStream lector = new FileInputStream(f);
            FileOutputStream escritor = new FileOutputStream(copia);
            
            escritor.write(lector.readAllBytes());
            
            lector.close();
            escritor.close();
        }
        
        catch (Exception ex) {System.out.println("Oops (copiar archivo, lectura).");}
    }
}
