package Ejercicio2;

/**
 * Clase AccesoFichero:
 * Se encarga de acceder al fichero colocado en el escritorio.
 */

public class AccesoFichero {
    public static void main(String args[]) {
        String ruta = "C:\\Flujo\\Ejercicio2\\Desktop";
        String archivo = "fichero.txt";
        
        CopiaFicheros copiar = new CopiaFicheros(ruta, archivo);
    }
}