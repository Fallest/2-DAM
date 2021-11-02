package Boletin1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ejercicio5 {
    public static void main(String[] args) {
        // Directorio que se va a mostrar con el ejercicio 1
        String[] comandos = {"java", "-jar", "Ejercicio1.jar", "\"C:\\Windows\""};
        ProcessBuilder pb = new ProcessBuilder(comandos);
        BufferedReader br;
        Process p;
        String linea;
        
        try {
            pb.directory(new File("C:\\Users\\David\\Documents\\2-DAM\\PSP\\Procesos\\Boletin1\\Jars"));
            
            p = pb.start();
            InputStream is = p.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            
            while ((linea = br.readLine()) != null) {                
                System.out.println(linea);
            }
            
            System.out.println("\n\n\t\tVariables de entorno: ");
            pb.environment().entrySet().forEach(tupla -> {
                System.out.println(tupla.getKey() + " / " + tupla.getValue());
            });
            
            System.out.println("\n\n\t\tArgumentos del comando: ");
            pb.command().forEach(s -> {
                System.out.println(s);
            });
            
            br.close();
        }
        catch(IOException ex) {
            System.out.println("And I oop-");
        }
    }
}
