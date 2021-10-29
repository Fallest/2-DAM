package Boletin1;

import java.io.*;

public class Ejercicio2 {
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
            
            br.close();
        }
        catch(IOException ex) {
            System.out.println("And I oop-");
        }

    }
}
