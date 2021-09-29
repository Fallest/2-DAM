package BernalNavarreteDavid.Modelo;

import java.util.GregorianCalendar;
import BernalNavarreteDavid.Controlador.*;

/**
* Clase hija de Empleado.
* Tiene que implementar todos los métodos abstractas (toString(), getDato()).
* Declarada "final" para que no se pueda heredar de ella.
*/ 

public final class Temporal extends Empleado {
    
    // Atributos
        // remunHora es lo que cobra el temporal por hora.
    private float horasTrabajadas; // Este es el atributo "Dato".
    private float eurosHora; 
    
    /*------------------------------------------------------------------------*/
    // Constructores
    public Temporal(String nombre, float horasTrabajadas, float eurosHora, float sueldoMaximo) {
        super(nombre, 0f, new GregorianCalendar(), sueldoMaximo);
        this.horasTrabajadas = horasTrabajadas;
        this.eurosHora = eurosHora;
        // Tenemos que asignar el sueldo después de llamar a super().
        this.setSueldo(calcularSueldo());
    }
    
    public Temporal(String nombre, float horasTrabajadas, float eurosHora, float sueldoMaximo, String fechaAlta) {
        // Como la fecha la recibimos en forma de cadena, la parseamos.
        super(nombre, 0f, DateParser.parseDate(fechaAlta), sueldoMaximo);
        this.horasTrabajadas = horasTrabajadas;
        this.eurosHora = eurosHora;
        // Tenemos que asignar el sueldo después de llamar a super().
        this.setSueldo(calcularSueldo());
    }
    
    /*------------------------------------------------------------------------*/
    // Setters
    public void setHorasTrabajadas(float horasTrabajadas) {
        /* Hay que comprobar que al actualizar las horas trabajadas no superamos
        el sueldo máximo establecido.
        */
        if (horasTrabajadas * this.eurosHora > super.getSueldoMaximo()
                || horasTrabajadas * this.eurosHora <= 0f)
            System.out.println("Horas trabajadas no válidas. Se supera el sueldo máximo.");
        else {
            this.horasTrabajadas = horasTrabajadas;
            // Hay que actualizar el sueldo cada vez que se actualicen las horas.
            this.setSueldo(calcularSueldo());
        }
    }
    
    public void setEurosHora(float eurosHora) {
        /* Hay que comprobar que al actualizar los euros por hora no superamos
        el sueldo máximo establecido.
        */
        if (this.horasTrabajadas * eurosHora > super.getSueldoMaximo()
                || this.horasTrabajadas * eurosHora <= 0f)
            System.out.println("Euros por hora no válidos. Se supera el sueldo máximo.");
        else {
            this.eurosHora = eurosHora;
            // Hay que actualizar el sueldo cada vez que se actualice los euros por hora.
            this.setSueldo(calcularSueldo());
        }   
    }
    
    /*------------------------------------------------------------------------*/
    // Getters
    public float getEurosHora() {
        return this.eurosHora;
    }
    
    @Override
    public float getDato() {
        return this.horasTrabajadas;
    }
    /*------------------------------------------------------------------------*/
    // Otros métodos  
    @Override
    public float calcularSueldo() {
        // Cálculo simple del sueldo bruto (horas * eurosHora)
        return eurosHora*horasTrabajadas;
    }
    
    @Override
    public String toString() {
        // Devolvemos el objeto en forma de cadena con el formato "N | F | S ..."
        return    this.getNombre() + " | "
                + DateParser.parseDate(this.getFechaAlta()) + " | "
                + this.getSueldo() + " | "
                + this.getSueldoMaximo()+ " | "
                + this.horasTrabajadas + " | "
                + this.eurosHora;
    }
}
