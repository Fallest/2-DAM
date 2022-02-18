package Ejercicio5;

import java.io.*;

public class TabChanger {
    
    public static void eliminarTabs(String ruta, String archivo, String changeChar) throws Exception {
        File file = new File(ruta + "\\" + archivo);
        File copiaFile = new File(ruta + "\\" + 
                archivo.substring(0, archivo.length() - 4) + "-copia.txt");
        
        if(!copiaFile.exists()) copiaFile.createNewFile();
        
        BufferedReader lector = new BufferedReader(new FileReader(file));
        BufferedWriter escritor = new BufferedWriter(new FileWriter(copiaFile));
        
        // Tenemos que leer de file mientras escribimos en copiaFile.
        String cad = lector.readLine();
        while (cad != null) {
            System.out.println(cad);
            // Sustituimos los caracteres.
            // Escribimos la línea en el otro archivo.
            escritor.append(cad.replace("\t", changeChar) + "\n");
            // Pasamos a la siguiente línea.
            cad = lector.readLine();
        }
        escritor.close();
        lector.close();
        
    }
    
    public static void main(String args[]) {
        String changeChar = "  "; // Dos espacios
        String ruta = "C:\\Flujo\\Ejercicio5";
        String archivo = "tabulador.txt";
        
        try {
            eliminarTabs(ruta, archivo, changeChar);
        }
        catch (Exception e) {
            System.out.println("Oops.");
        }
    }
}
