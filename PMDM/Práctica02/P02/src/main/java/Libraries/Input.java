package Libraries;


/*
    ESTA CLASE NO HACE NADA EN ESTE PROYECTO
    Es una clase para leer datos por consola: NO SE USA EN ESTE PROYECTO.
*/
import Exceptions.Exceptions;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Input {
    /* 
    * Clase para implementar métodos para la lectura de datos desde el
    * teclado. 
    */
    static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    public static String read(String message, String use) {
        /*
        * Este método va a pedir por teclado un dato.
        * Va a estar sobrecargado con otros métodos que leerán otros tipos de
        * datos.
        * Tendrá un parámetro para un mensaje que mostrar por pantalla.
        * Otro para el uso del dato que está pidiendo.
        */        
        boolean exec;
        String str = null;
        
        InputStreamReader stream = new InputStreamReader(System.in);
        BufferedReader keyboard = new BufferedReader(stream);
        
        do {
            try {
                exec = false;
                
                System.out.println("\n\t" + message + ": ");
                str = keyboard.readLine();
                
                switch (str) {
                    case "titulo", "desarrolladora" -> {
                        return str;
                    }
                    case "precio" -> {
                        Float.parseFloat(str);
                    }
                    case "oferta" -> {
                        if (!"s".equals(str) || !"n".equals(str)) {
                            throw new Exceptions("bool");
                        }
                    }
                    default -> {
                        throw new Exceptions("str");
                    }
                }
            }
            catch (IOException e) {
                System.out.println("\n\tError en la entrada de datos.");
                exec = true;
            }
            catch (NumberFormatException e) {
                // Lio esta solo porque quiero usar mi excepción
                Exceptions ex = new Exceptions("float");
                System.gc();
                exec = true;
            }
            catch (Exceptions e)
            {
                exec = true;
            }
        } while (exec);
        
        return str;
    }
    
    public static GregorianCalendar readDate(String message) {
        boolean exec;
        String str;
        GregorianCalendar cal = null;
        
        InputStreamReader stream = new InputStreamReader(System.in);
        BufferedReader keyboard = new BufferedReader(stream);
        
        do {
            try {
                exec = false;
                
                System.out.println("\n\t" + message + " (DD/MM/YYYY): ");
                str = keyboard.readLine();
                
                cal = new GregorianCalendar(
                Integer.parseInt(str.substring(6, 10)),
                Integer.parseInt(str.substring(3, 5)) - 1, // Porque el calendario va de 0 a 11
                Integer.parseInt(str.substring(0, 2))) {
                    @Override
                    public String toString()
                    {
                        Date thisDate = this.getTime();
                        return sdf.format(thisDate);
                    }
                };
            }
            catch (IOException e) {
                System.out.println("\n\tError en la entrada de datos.");
                exec = true;
            }
            catch (NumberFormatException e) {
                System.out.println("\n\tError: No ha introducido números válidos");
                exec = true;
            }
        } while (exec);
        
        return cal;
    }
                    
}
