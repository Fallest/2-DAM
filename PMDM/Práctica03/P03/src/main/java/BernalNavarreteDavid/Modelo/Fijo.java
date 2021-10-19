package BernalNavarreteDavid.Modelo;

import BernalNavarreteDavid.Controlador.*;

/**
* Clase Fijo, extiende a Empleado.
* Tiene que implementar todos los métodos abstractas (toString(), getDato(), calcularSueldo()).
* Los dos atributos extras que tiene son: horasMes (el atributo que va a devolver
* getDato()), y departamento.
* 
* Va a tener dos constructores:
* -Uno que toma el nombre, el sueldoMaximo, las horas al mes y la fecha de alta.
* -Otro que toma todos los atributos existentes, y crea el sueldo a partir del 
* departamento y de las horas al mes.
* En ambos casos se llama al constructor de Empleado con super().
* 
* Los fijos trabajan todos la misma cantidad de horas, lo que varía su sueldo
* es el departamento en el que se encuentran.
* 
* De nuevo, van a tener setters y getter para cada uno de los nuevos atributos,
* realizando las comprobaciones necesarias tanto en las horas al mes (para que
* no sobrepase el salario tope) y el departamento en el que se encuentra (comprobando
* que el departamento existe).
* 
* Al final tenemos las implementaciones de los métodos abstractos:
* 
* getDato() devuelve el atributo horasMes.
* 
* calcularSueldo() tiene dos implementaciones:
* -Una para calcular el sueldo cuando tenemos tanto las horasMes como el departamento.
* -Otro para cuando queremos comprobar que unas horas que nos han dado no sobrepasan
* el sueldo máximo.
* 
* toString() para convertir de Fijo a String.
*/ 

public class Fijo extends Empleado {
    
    // Atributos
        // Los fijos van a trabajar todos la misma cantidad de horas.
    private static float horasMes = 0; // Este es el atributo "Dato".
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
    
    public Fijo(String nombre, float sueldoMaximo, float horasMes, String departamento, String fechaAlta) {
        // Como la fecha la recibimos en forma de cadena, la parseamos.
        super(nombre, 0f, DateParser.parseDate(fechaAlta), sueldoMaximo);
        setDepart(departamento);
        Fijo.horasMes = horasMes;
    }
    
    public Fijo(String nombre, float horasMes, String departamento, String fechaAlta) {
        // Como la fecha la recibimos en forma de cadena, la parseamos.
        super(nombre, 0f, DateParser.parseDate(fechaAlta));
        setDepart(departamento);
        Fijo.horasMes = horasMes;
    }
    
    public Fijo(String nombre, String Departameto, String fechaAlta) {
        // Como la fecha la recibimos en forma de cadena, la parseamos.
        super(nombre, 0f, DateParser.parseDate(fechaAlta));
        setDepart(departamento);
    }
    
    /*------------------------------------------------------------------------*/
    // Setters
    public void setHorasMes(float horasMes) {
        /** Hay que comprobar que al actualizar las horas al mes no superamos
        * el sueldo máximo establecido.
        */
        if (Empleado.getSueldoMaximo() == 0f) {
            Fijo.horasMes = horasMes;
            this.setSueldo(calcularSueldo());
        }
        else {
            if (calcularSueldo(horasMes) > Empleado.getSueldoMaximo()
                || calcularSueldo(horasMes) <= 0f)
                System.out.println("Euros por hora no válidos. Se supera el sueldo máximo.");
            else {
                Fijo.horasMes = horasMes;
                // Hay que actualizar el sueldo cada vez que se actualice las horas al mes.
                this.setSueldo(calcularSueldo());
            }   
        }
    }
    
    public void setDepart(String depart) {
        // Tenemos que comprobar que el departamento existe.
        try {
            if(!depart.equals("RRHH") && !depart.equals("Admisiones") && !depart.equals("Restauración"))
                throw new Exceptions("dep");
            else this.departamento = depart;
            }
        catch (Exceptions ex) {// No hacemos nada   
        }
    }
    
    /*------------------------------------------------------------------------*/
    // Getters
    @Override
    public float getDato() {
        return Fijo.horasMes;
    }
   
    public static float getHorasMes() {
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
                + Empleado.getSueldoMaximo()+ " | "
                + Fijo.horasMes + " | "
                + this.departamento;
    }
}
