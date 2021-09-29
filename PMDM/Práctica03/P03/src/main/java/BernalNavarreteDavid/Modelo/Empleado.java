package BernalNavarreteDavid.Modelo;

import java.util.*;

public abstract class Empleado {
    
    // Atributos
    private String nombre;
    private float sueldo;
    private GregorianCalendar fechaAlta;
    private static float sueldoMaximo;
    
    /*------------------------------------------------------------------------*/
    // Constructores
    Empleado(String nombre, float sueldoMaximo) {
        this.nombre = nombre;
        this.fechaAlta = new GregorianCalendar();
        Empleado.sueldoMaximo = sueldoMaximo;
    }
    
    Empleado(String nombre, float sueldo, GregorianCalendar fechaAlta, float sueldoMaximo) {
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.fechaAlta = fechaAlta;
        Empleado.sueldoMaximo = sueldoMaximo;
    }
    /*------------------------------------------------------------------------*/
    // Setters
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setSueldo(float sueldo) {
        // La comprobación de que el sueldo es válido se hace en las clases hijas.
        this.sueldo = sueldo;
    }
    
    public void setFechaAlta(GregorianCalendar fecha) {
        this.fechaAlta = fecha;
    }
    
    public void setSueldoMaximo(float sueldoMaximo) {
        Empleado.sueldoMaximo = sueldoMaximo;
    }
    /*------------------------------------------------------------------------*/
    // Getters
    
    public String getNombre() {
        return this.nombre;
    }
    
    public float getSueldo() {
        return this.sueldo;
    }
    
    public GregorianCalendar getFechaAlta() {
        return this.fechaAlta;
    }
    
    public float getSueldoMaximo() {
        return Empleado.sueldoMaximo;
    }
    
    public abstract float getDato();
    /*------------------------------------------------------------------------*/
    // Otros métodos
    @Override
    public abstract String toString();
    
    public abstract float calcularSueldo();
}