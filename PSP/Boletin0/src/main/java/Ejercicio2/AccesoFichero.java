package Ejercicio2;

/**
 * Clase AccesoFichero:
 * Se encarga de acceder al fichero colocado en el escritorio.
 */

public class AccesoFichero {
    public static void main(String args[]) {
        String archivo = "C:\\Users\\David\\Desktop\\fichero.txt";
        EscribirFichero lector = new EscribirFichero(archivo);
        
        lector.escribe();
    }
}