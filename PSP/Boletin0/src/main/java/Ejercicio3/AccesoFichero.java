package Ejercicio3;

/**
 * Clase AccesoFichero:
 * Se encarga de acceder al fichero colocado en el escritorio.
 */

public class AccesoFichero {
    public static void main(String args[]) {
        String archivo = "C:\\Flujo\\Ejercicio3\\fichero.txt";
        LeerFichero lector = new LeerFichero(archivo);
        lector.lee();
    }
}
