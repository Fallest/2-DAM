package BernalNavarreteDavid.Modelo;

import BernalNavarreteDavid.Controlador.*;

/**
* Clase hija de Empleado.
* Tiene que implementar todos los métodos abstractas (toString(), getDato()).
*/ 

public class Fijo extends Empleado {
    
    // Atributos
        // Los fijos van a trabajar todos la misma cantidad de horas.
    private static float horasMes; // Este es el atributo "Dato".
    private String departamento;
    
    /*------------------------------------------------------------------------*/
    // Constructores
    public Fijo(String nombre, float sueldoMaximo, float horasMes, String fechaAlta) {
        super(nombre, sueldoMaximo);
        // Como la fecha la recibimos en forma de cadena, la parseamos.
        this.setFechaAlta(DateParser.parseDate(fechaAlta));
        // Referencia a horasMes con la clase (horasMes es static).
        Fijo.horasMes = horasMes;
    }
    
    public Fijo(String nombre, float sueldoMaximo, float horasMes, float eurosHora, String fechaAlta) {
        // Como la fecha la recibimos en forma de cadena, la parseamos.
        super(nombre, 0f, DateParser.parseDate(fechaAlta), sueldoMaximo);
        Fijo.horasMes = horasMes;
    }
    
    /*------------------------------------------------------------------------*/
    // Setters
    public void setHorasMes(float horasMes) {
        /* Hay que comprobar que al actualizar las horas al mes no superamos
        el sueldo máximo establecido.
        */
        if (calcularSueldo(horasMes) > super.getSueldoMaximo()
                || calcularSueldo(horasMes) <= 0f)
            System.out.println("Euros por hora no válidos. Se supera el sueldo máximo.");
        else {
            Fijo.horasMes = horasMes;
            // Hay que actualizar el sueldo cada vez que se actualice las horas al mes.
            this.setSueldo(calcularSueldo());
        }   
    }
    
    public void setDepart(String depart) {
        try {
        if(!depart.equals("RRHH") || !depart.equals("Admisiones") || !depart.equals("Restauración"))
            throw new Exceptions("dep");
        }
        catch (Exceptions ex) {}
    }
    
    /*------------------------------------------------------------------------*/
    // Getters
    @Override
    public float getDato() {
        return Fijo.horasMes;
    }
    
    public String getDepartamento() {
        return this.departamento;
    }
    
    /*------------------------------------------------------------------------*/
    // Otros métodos
    @Override
    public final float calcularSueldo() {
        try {
            switch (departamento) {
                case "RRHH" -> {return horasMes * 7f;}
                case "Admisiones" -> {return horasMes * 6f;}
                case "Restauración" -> {return horasMes * 5f;}
                default -> {throw new Exceptions("dep");}
            }
        }
        catch (Exceptions e) {return 0f;}
    }
    
    public final float calcularSueldo(float horasMes) {
        // Para poder hacer comprobaciones de sueldo.
        try {
            switch (departamento) {
                case "RRHH" -> {return horasMes * 7f;}
                case "Admisiones" -> {return horasMes * 6f;}
                case "Restauración" -> {return horasMes * 5f;}
                default -> {throw new Exceptions("dep");}
            }
        }
        catch (Exceptions e) {return 0f;}
    }
    
    @Override
    public String toString() {
        // Devolvemos el objeto en forma de cadena con el formato "N | F | S ..."
        return    this.getNombre() + " | "
                + DateParser.parseDate(this.getFechaAlta()) + " | "
                + this.getSueldo() + " | "
                + this.getSueldoMaximo()+ " | "
                + Fijo.horasMes + " | "
                + this.departamento;
    }
}
