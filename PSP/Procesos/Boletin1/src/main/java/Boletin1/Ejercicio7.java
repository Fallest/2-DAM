package Boletin1;

import java.io.File;
import java.io.IOException;

public class Ejercicio7 {
    public static void main(String[] args) {
        ejecutarComandos();
    }

    private static void ejecutarComandos() {
        
        
        try {
            String[] comandos = {"cmd", "/c", "entrada.bat"};
            ProcessBuilder pb = new ProcessBuilder(comandos);
            Process p;
            
            File entrada = new File("C:\\Users\\David\\Documents\\2-DAM\\PSP\\Procesos\\Boletin1\\Jars\\entrada.bat");
            File salida = new File("C:\\Users\\David\\Documents\\2-DAM\\PSP\\Procesos\\Boletin1\\Jars\\salida.txt");
            File error = new File("C:\\Users\\David\\Documents\\2-DAM\\PSP\\Procesos\\Boletin1\\Jars\\error.txt");
            
            if (!entrada.exists()) throw new IOException();
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
