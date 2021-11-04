package Boletin1;

import java.io.File;
import java.io.IOException;

public class Ejercicio6 {
    public static void main(String[] args) {
        String[] comandos = {"cmd", "/c", "dir"};
        ProcessBuilder pb = new ProcessBuilder(comandos);
        Process p;
        
        try {
            
            File salida = new File("C:\\Users\\David\\Documents\\2-DAM\\PSP\\Procesos\\Boletin1\\Jars\\salida.txt");
            File error = new File("C:\\Users\\David\\Documents\\2-DAM\\PSP\\Procesos\\Boletin1\\Jars\\error.txt");
            
            if (!salida.exists()) salida.createNewFile();
            if (!error.exists()) error.createNewFile();
            
            pb = pb.redirectOutput(salida);
            pb = pb.redirectError(error);
            pb.directory(new File("C:\\Users\\David\\Documents\\2-DAM\\PSP\\Procesos\\Boletin1\\Jars"));
            p = pb.start();
        }
        catch (IOException ex) {
            System.out.println("Oops.");
        }
    }
}
