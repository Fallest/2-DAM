package BernalNavarreteDavid.Modelo;

import java.util.GregorianCalendar;
import BernalNavarreteDavid.Controlador.*;

/**
* Clase Temporal, hereda de Empleado.
* Declarada "final" para que no se pueda heredar de ella.
* Tiene que implementar todos los métodos abstractas (toString(), getDato(), calcularSueldo()).
* Los dos atributos extras que tiene son: horasTrabajadas (el atributo que va a devolver
* getDato()), y eurosHora.
* 
* Va a tener dos constructores:
* -Uno que toma el nombre, las horas trabajadas, los euros por hora y el sueldo máximo.
* -Otro que toma todos los atributos existentes, y crea el sueldo a partir de las 
* horas trabajadas y los euros por hora.
* En ambos casos se llama al constructor de Empleado con super().
* 
* De nuevo, van a tener setters y getter para cada uno de los nuevos atributos,
* realizando las comprobaciones necesarias tanto en las horas trabajadas (para que
* no sobrepase el salario tope) y los euros por hora (comprobando que el sueldo
* no sobrepasa el salario tope).
* 
* Al final tenemos las implementaciones de los métodos abstractos:
* 
* getDato() devuelve el atributo horasTrabajadas.
* 
* calcularSueldo() calcula el sueldo con las horas trabajadas y los euros por hora
* 
* toString() para convertir de Temporal a String.
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
        this.setHorasTrabajadas(horasTrabajadas);
        this.setEurosHora(eurosHora);
        // Tenemos que asignar el sueldo después de llamar a super().
        this.setSueldo(calcularSueldo());
    }
    
    public Temporal(String nombre, float horasTrabajadas, float eurosHora, float sueldoMaximo, String fechaAlta) {
        // Como la fecha la recibimos en forma de cadena, la parseamos.
        super(nombre, 0f, DateParser.parseDate(fechaAlta), sueldoMaximo);
        this.setHorasTrabajadas(horasTrabajadas);
        this.setEurosHora(eurosHora);
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
