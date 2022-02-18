package Ejercicio7;

import java.io.Serializable;

public class Empleado implements Serializable {
    
    // Atributos
    private String nombre;
    private int sueldo;
    private static int sueldoMaximo;
    
    /*------------------------------------------------------------------------*/
    // Construtctores:    
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
    
    public void setSueldo(int sueldo) {
        if (sueldo < Empleado.sueldoMaximo)
            this.sueldo = sueldo;
        else System.out.println("El sueldo no puede ser superior al sueldo máximo.");
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