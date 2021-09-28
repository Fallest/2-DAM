package P01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Empleado {
    
    // Atributos
    private String nombre;
    private int sueldo;
    private static int sueldoMaximo;
    
    /*------------------------------------------------------------------------*/
    // Construtctores: 2/2
    Empleado(String nombre, int sueldoMaximo) {
        this.nombre = nombre;
        Empleado.sueldoMaximo = sueldoMaximo;
    }
    
    Empleado(String nombre, int sueldo, int sueldoMaximo) {
        this.nombre = nombre;
        this.sueldo = sueldo;
        Empleado.sueldoMaximo = sueldoMaximo;
    }
    /*------------------------------------------------------------------------*/
    // Setters: 5/5
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setNombre() {
        boolean ejec;
        
        InputStreamReader stream = new InputStreamReader(System.in);
        BufferedReader teclado = new BufferedReader(stream);
        
        do {
            try {
                ejec = false;

                System.out.println("\n\tIntroduzca nuevo nombre:");
                this.nombre = teclado.readLine();
            }
            catch (IOException e) {
                System.out.println("\nError en la entrada de datos.");
                ejec = true;
            }
        } while (ejec);
    }
    
    public void setSueldo(int sueldo) {
        if (sueldo < Empleado.sueldoMaximo)
            this.sueldo = sueldo;
        else System.out.println("El sueldo no puede ser superior al sueldo máximo.");
    }
    
    public void setSueldo() {
        boolean ejec;
        
        InputStreamReader stream = new InputStreamReader(System.in);
        BufferedReader teclado = new BufferedReader(stream);
        
        do {
            try {
                ejec = false;
                
                System.out.println("\n\tIntroduzca el sueldo del empleado");
                sueldo = Integer.parseInt(teclado.readLine());

                // Comprobar que el dato introducido es válido.
                if ((sueldo > sueldoMaximo) || (sueldo < 0)) {
                    System.out.println("Error: El sueldo introducido no es válido");
                    ejec = true;
                }
            }
            catch (IOException e) {
                System.out.println("\nError en la entrada de datos.");
                ejec = true;
            }
        } while (ejec);
    }
    
    public void setSueldoMaximo(int sueldoMaximo) {
        Empleado.sueldoMaximo = sueldoMaximo;
    }
    /*------------------------------------------------------------------------*/
    // Getters: 3/3
    
    public String getNombre() {
        return this.nombre;
    }
    
    public int getSueldo() {
        return this.sueldo;
    }
    
    public int getSueldoMaximo() {
        return Empleado.sueldoMaximo;
    }
    /*------------------------------------------------------------------------*/
    // Otros métodos: 1/1
    @Override
    public String toString() {
        return this.nombre + "*" + this.sueldo + "*" + Empleado.sueldoMaximo;
    }
}