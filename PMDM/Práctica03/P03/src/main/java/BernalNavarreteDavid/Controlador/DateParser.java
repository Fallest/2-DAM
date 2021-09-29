package BernalNavarreteDavid.Controlador;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Esta clase se va a usar para las conversiones de las fechas de String a 
 * GregorianCalendar y viceversa.
 * Extiende a GregorianCalendar para sobreescribir el toString() de Calendar sin
 * tener que crear una instancia de GregorianCalendar y sobreescribir el toString()
 * usando una clase anónima (en resumen, así solo tengo que añadir una función simple
 * al final para convertir de GregorianCalendar a String, sin tener que hacer un relío
 * más difícil de entender).
 * 
 * La clase tiene tres métodos:
 *  1- Uno para pasar de String a GregorianCalendar.
 *  2- Otro para pasar de GregorianCalendar a String.
 *  3- El método sobreescrito toString (es un auxiliar para el segundo método).
 * 
 * Los dos métodos principales van a hacer uso del polimorfismo (van a tener el 
 * mismo nombre y lo único que va a cambiar es el tipo de parámetro que reciben 
 * y el tipo de dato que devuelven).
 */
public class DateParser extends GregorianCalendar {
    /** 
     * Es un objeto de tipo SimpleDateFormat, es para ayudarnos a convertir de
     * GregorianCalendar a String.
     */
    static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    // Primer método: de String a GregorianCalendar.
    public static GregorianCalendar parseDate(String fecha) {
        /*
        * Convierte una cadena con formato dd/mm/yyyy a un objeto GregorianCalendar.
        */
        GregorianCalendar cal = null;
        
        try {
            cal = new GregorianCalendar(
            Integer.parseInt(fecha.substring(6, 10)),
            Integer.parseInt(fecha.substring(3, 5)) - 1, // Porque el calendario va de 0 a 11
            Integer.parseInt(fecha.substring(0, 2)));
        }
        catch (NumberFormatException e) {
            System.out.println("\n\tError: Formato incorrecto.");
        }
        catch (Exception e) {
            System.out.println("\n\tError: Valores fuera de rango.");
        }
        return cal;
    }
     
    // Segundo método: de GregorianCalendar a String.
    public static String parseDate(GregorianCalendar cal) {
        /*
        * Convierte un objeto GregorianCalendar a una cadena con formato
        * dd/mm/yyyy.
        */
        return cal.toString();
    }
    
    // Método sobreescrito auxiliar toString.
    @Override
    public String toString()
    {
        try {
            // Sobreescribe el método toString de java.util.Calendar.
            Date thisDate = this.getTime();
            return sdf.format(thisDate);
        }
        catch(Exception e) {
            // Si la fecha no se puede formatear a una cadena
            return null;
        }
    }
}
