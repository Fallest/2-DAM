package Controller;

import View.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import javax.swing.JOptionPane;

public class ExceptionManager {
    private static final File LOGFILE = new File("./logFile.txt");
    private static BufferedWriter out;
    
    
    /**
     * Este método va a usar un switch para saber qué error ha ocurrido.
     * Para cada error, mostrará un mensaje por pantalla para avisar al usuario.
     * 
     * Lista de correspondencia entre "n" y el tipo de error:
     * 0: ClassNotFoundExceptions.
     * 1: SQLExceptions.
     * 2: NumberFormatExceptions.
     * 3: 
     * 4: Invalid Date.
     * 5: 
     * 6: Invalid login credentials.
     * 7: 
     * 8: IOExceptions.
     * 9: IndexOutOfBoundsExceptions.
     * 10: Sueldo real mayor que el sueldo máximo.
     * @param n
     * @param origin
     */
    public static void getError(int n, String origin) {
        try {
            // Inicialización de los streams.
            out = new BufferedWriter(
                new FileWriter(LOGFILE, true));
            if (!LOGFILE.exists())
                LOGFILE.createNewFile();
            out.write((new Date()).toString());
        } catch (IOException ex) {
            ExceptionManager.getError(8, "ExceptionManager.getError()");
        }
        
        String msg = "";
        switch(n) {
            case 0: {
                // ClassNotFoundExceptions
                msg = "ERROR: ClassNotFoundException en " + origin + ".\n"
                    + "Todos los accesos a la base de datos serán inválidos.\n"
                    + "Por favor, contacte al administrador de su sistema.\n";
                break;
            }
            case 1: {
                // SQLExceptions
                msg = "ERROR: SQLException en " + origin + ".\n"
                    + "Por favor, contacte al administrador de su sistema.\n";
                break;
            }
            case 2: {
                // NumberFormatExceptions
                msg = "ERROR: NumberFormatException en " + origin + ".\n"
                    + "Por favor, contacte al administrador de su sistema.\n";
                break;
            }
            case 4: {
                // Invalid Date
                msg = "ERROR: Fecha no válida.\n"
                    + "Por favor introduzca una fecha válida.\n";
                break;
            }
            case 6: {
                // Invalid login credentials
                msg = "ERROR: Credenciales no válidos.\n"
                    + "Vuelva a intentarlo.\n";
                break;
            }
            case 8: {
                // IOExceptions
                msg = "ERROR: IOException en " + origin + ".\n"
                    + "Por favor, contacta con el administrador de tu sistema.\n";
                break;
            }
            case 9: {
                // NullPointerExceptions
                msg = "ERROR: IndexOutOfBoundsException en " + origin + ".\n"
                    + "Si el problema continúa, contacta con el administrador de tu sistema.\n";
                break;
            }
            case 10: {
                // Sueldo real no válido
                msg = "ERROR: El sueldo real no puede ser superior al sueldo máximo.\n"
                    + "El sueldo máximo será el asignado como real.\n";
                break;
            }
        }
        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), msg);
        try {
            out.write("\n" + msg + "\n---- END OF ERROR.\n");
        } catch (IOException ex) {
            System.out.println("FATAL ERROR. No se pudo escribir en el registro de errores.");
        }
        
        close();
    }
    
    private static void close() {
        try {
            out.close();
        } catch (IOException ex) {
            System.out.println("FATAL ERROR. No se pudo cerrar el stream del registro de errores.");
        }
    }
}
