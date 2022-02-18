package Ejercicio7;

import java.io.*;

public class Serializando implements Serializable {
    
    public static void main(String args[]) {
        // Creamos el archivo si no existe
        File archivo = new File("C:\\Flujo\\Ejercicio7\\empleados.dat");
        
        if (!archivo.exists()) try {
            archivo.createNewFile();
        } catch (IOException ex) {
            System.out.println("Oops.");
        }
        
        Empleado emp1 = new Empleado("Paco Pérez", 1200, 1400);
        
        try {
            // Escribimos el objeto en un fichero.
            ObjectOutputStream escribiendo_fichero = new ObjectOutputStream(
                                new FileOutputStream(archivo));
            escribiendo_fichero.writeObject(emp1);
            escribiendo_fichero.close();
            
            // Leemos un objeto de un fichero.
            ObjectInputStream recupera_fichero = new ObjectInputStream(
                                new FileInputStream(archivo));
            Empleado personal_recuperado = (Empleado) recupera_fichero.readObject();
            recupera_fichero.close();
            
            // Mostramos por pantalla el objeto recuperado.
            System.out.println(personal_recuperado);
        }
        catch(Exception ex) {System.out.println("Oops.");}
    }
}
