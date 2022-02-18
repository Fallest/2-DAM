package Ejercicio1;

/**
 * Clase AccesoFichero:
 * Se encarga de acceder al fichero colocado en el escritorio.
 */

public class AccesoFichero {
    public static void main(String args[]) {
        String archivo = "C:\\Flujo\\Ejercicio1\\fichero.txt";
        LeerFichero lector = new LeerFichero(archivo);
        lector.lee();
    }
}
