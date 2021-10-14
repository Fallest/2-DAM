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
        /** s debe tener el formato (DD/MM/YYYY)
         * Vamos a crear un nuevo calendario usando el constructor que toma la 
         * siguiente forma:
         * GregorianCalendar(int año, int mes, int dia)
         * Para extraer los números de una cadena, tenemos que estraer cada número
         * independiente en forma de cadena, y luego convertirlo a entero, y LUEGO
         * se lo podemos pasar al constructor.
         * Para el primer paso, usamos s.substring(n, m), y va a extraer la cadena
         * que haya entre las posiciones n y n+m (hay que tener en cuenta que si
         * tenemos "hola" y hacemos "hola".substring(2, 1) vamos a extraer "a", 
         * es decir, la primera posición es 0).
         * Para convertir la subcadena en un número, lo hacemos con
         * Integer.parseInt(cadena).
         * Hay una nota en la posición del entero del mes, esto es porque los meses
         * los pido (en el formato) de 1 a 12, y GregorianCalendar los toma de 0 a 11,
         * lo que significa que al dato que yo pida hay que quitarle 1.
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
            // Por si, aunque tenga el formato correcto, los números
            // se pasan de un dia/mes/año válido.
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
        return toString(cal);
    }
    
    // Método sobreescrito auxiliar toString.
    public static String toString(GregorianCalendar cal)
    {
        /**
         * Tenemos que sobreescribir el método toString
         * de GregorianCalendar (que a su vez es el método de la superclase Calendar, 
         * de la cual hereda GregorianCalendar) y así poder convertir de GregorianCalendar
         * a String sin tener que hacer mucho más lío que esto.
         * No es necesario comprender el código dentro del método toString, pero
         * lo voy a explicar porque puedo.
         * 
         * Básicamente va a crear un objeto de tipo Date (la versión más facil de 
         * usar de GregorianCalendar), y va a pasar de usar un GregorianCalendar
         * a un Date, y lo va a convertir, usando el formato declarado en los 
         * atributos, a una cadena.
         */
        try {
            Date thisDate = cal.getTime();
            return sdf.format(thisDate);
        }
        catch(Exception e) {
            // Si la fecha no se puede formatear a una cadena
            return null;
        }
    }
}
