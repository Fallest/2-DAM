package BernalNavarreteDavid.Modelo;

import BernalNavarreteDavid.Controlador.DateParser;
import java.util.*;

/**
 * Clase "padre": a ella no vamos a acceder directamente, accederemos a ella mediante
 * sus "hijos".
 * Es abstracta porque tiene métodos abstractos (toString, getDato, calcularSueldo).
 * Por si no recordáis lo que era un método abstracto, aquí están mis apuntes del
 * año pasado:
 * Una clase abstracta es una clase que no puede definirse lo que hace hasta que se hereda.
 * Es una clase con una definición "vaga" o muy general, que solo se define al usarse en otra
 * clase que la hereda.
 *	-Si la clase es abstracta, deberá declararse como como abstract class:
 *	abstract class Nombre.
 *	-Al declararse un método abstracto, se debe declarar como si fuera un prototipo, sin llaves.
 *	-Además, se pueden diferenciar entre métodos abstract y métodos no abstractos.
 *	-Los métodos no abstractos pueden tener código.
 *	-Al sobrescribir el método abstracto en una clase derivada, no hay que poner abstract.
 *	-En la clase derivada se definen los métodos.
 *	-Cuando la clase es abstracta no se puede hacer new (no se puede instanciar la clase).
 *	-Cuando una clase hereda una clase abstracta, está obligada a impletentar
 *	todos los métodos abstractos que tenga dicha clase abstracta.
 *	-Cuando una clase derivada no puede usar un método abstracto (porque no tiene sentido
 *	usarlo para dicha clase derivada), se tiene que implementar el método, vacío.
 * 
 * Atributos de un empleado:
 * -Un nombre.
 * -Un sueldo.
 * -Una fecha de alta.
 * -El sueldo máximo que puede tener cualqier empleado.
 * 
 * Tiene dos constructores.
 * -Uno que solo toma su nombre y el sueldo máximo (asume la fecha de alta como la actual).
 * -Otro que toma valores para los 4 atributos, y los asigna.
 * 
 * Tiene sus setters y getters para cada atributo.
 * Por si no sabéis lo que son los getters y los setter y cuáles son sus propósitos:
 * Véis que los atributos son "private"? Eso significa que, efectivamente, son
 * privados y a ellos solo puede acceder directamente esta clase (ni sus hijos pueden).
 * Entonces necesitamos una forma de modificarlos, cómo? Pues con funciones que se
 * llaman getters y setters. Son, básicamente, "obtenedores" y "asignadores", y 
 * van a controlar la asignación y extracción de estos atributos privados.
 * 
 * Por último están los tres métodos abstractos, getDato(), toString(), y calcularSueldo().
 * Que no tienen mucho chiste, solo se ponen ahí con "abstract", y palante.
 */

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
    
    public int getDiaFecha() {
        return Integer.parseInt(
                DateParser
                        .parseDate(this.fechaAlta)
                        .substring(0,2));
    }
    
    public int getMesFecha() {
        return Integer.parseInt(
                DateParser
                        .parseDate(this.fechaAlta)
                        .substring(3,5));
    }
    
    public int getAñoFecha() {
        return Integer.parseInt(
                DateParser
                        .parseDate(this.fechaAlta)
                        .substring(6,10));
    }    
    
    public abstract float getDato();
    /*------------------------------------------------------------------------*/
    // Otros métodos
    @Override
    public abstract String toString();
    
    public abstract float calcularSueldo();
}