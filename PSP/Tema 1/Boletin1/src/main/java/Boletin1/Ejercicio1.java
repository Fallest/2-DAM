package Boletin1;

import java.io.*;

import static java.lang.Runtime.getRuntime;
public class Ejercicio1 {
    public static void main(String[] args) {
        // Crea una consola
        Runtime r = getRuntime();
        
        // Cadena para los comandos que se van a ejecutar
        String command = "cmd /c dir";
        // Objeto tipo Proceso.
        Process p;
        
        try {
            // Almacenamos en el proceso la ejecucion del comando en consola.
            if (args.length == 1)
                p = r.exec(command + " " + args[0]);
            else
                p = r.exec(command);
            
            // Tenemos que crear un InputStream, luego usar ese flujo para 
            // leerlo con un InputStreamReader, y luego leer del lector con un 
            // buffered reader para poder leer linea a linea.
            InputStream is = p.getInputStream();
            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(is));
            
            String linea;
            
            while((linea = br.readLine()) != null) 
                System.out.println(linea);
            
            br.close();
        }
        catch(IOException ex) {
            System.out.println("Oops-.");
        }
    }
}
