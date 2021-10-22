package Ejercicio1;

import java.io.*;
import static java.lang.Runtime.getRuntime;

public class Ejercicio2 {
    public static void main(String[] args) {
        if (args.length != 0) {
            // Dirección del lugar a mostrar dada mediante argumentos de consola
            String directorio = args[0];
            
            // Dirección del jar del ejercicio1
            String localizacionJar = "C:\\Users\\David\\Documents\\2-DAM\\PSP\\Procesos\\Boletin1";
            
            ejecutarComando("cmd /c java -jar " + localizacionJar + "\\ejercicio1.jar " + directorio);
        }
        
    }

    private static void ejecutarComando(String command) {
        // Crea una consola
        Runtime r = getRuntime();
        
        // Objeto tipo Proceso.
        Process p;
        
        try {
            // Almacenamos en el proceso la ejecución del comando en consola.
            p = r.exec(command);
            
            // Tenemos que crear un InputStream, luego usar ese flujo para 
            // leerlo con un InputStreamReader, y luego leer del lector con un 
            // buffered reader para poder leer línea a línea.
            InputStream is = p.getInputStream();
            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(is));
            
            String linea;
            
            while((linea = br.readLine()) != null) 
                System.out.println(linea);
            
            br.close();
        }
        catch(Exception ex) {
            System.out.println("Oops-.");
            ex.printStackTrace();
        }
    }
}
