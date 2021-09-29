package BernalNavarreteDavid.Controlador;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateParser extends GregorianCalendar {
    /*
    * Esta clase se va a usar para convertir objetos GregorianCalendar a cadenas
    * y cadenas con cierto formato a objetos GregorianCalendar.
    */
    static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
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
     
    public static String parseDate(GregorianCalendar cal) {
        /*
        * Convierte un objeto GregorianCalendar a una cadena con formato
        * dd/mm/yyyy.
        */
        return cal.toString();
    }
    
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
