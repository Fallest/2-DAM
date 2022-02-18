package Boletin1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Runtime.getRuntime;

public class Ejercicio4 {
    public static void main(String[] args) {
        Runtime r = getRuntime();
        Process cmd;
        String cadena;
        
        try {
            cmd = r.exec("cmd /c echo Escriba una cadena: ");
            
            // Creamos un lector para leer información
            BufferedReader lector = new BufferedReader(new InputStreamReader(
                    cmd.getInputStream()));
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            
            // Pedimos la cadena desde la entrada estándar:
            System.out.println(lector.readLine());
            cadena = teclado.readLine();
            
            // La visualizamos
            System.out.println("La cadena escrita es: ");
            System.out.println(cadena);
            
            lector.close();
            teclado.close();
        } catch (IOException ex) {
            System.out.println("Oops.");
        }
    }
}
