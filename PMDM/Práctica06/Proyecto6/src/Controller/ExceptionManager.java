package Controller;

import View.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import javax.swing.JOptionPane;

public class ExceptionManager {
    private static File logFile = new File("./logFile.txt");
    private static BufferedWriter out;
    
    
    /**
     * Este método va a usar un switch para saber qué error ha ocurrido.
     * Para cada error, mostrará un mensaje por pantalla para avisar al usuario.
     * 
     * Lista de correspondencia entre "n" y el tipo de error:
     * 0: ClassNotFoundExceptions.
     * 1: SQLExceptions.
     * 2: NumberFormatExceptions.
     * 3: Invalid NIF.
     * 4: Invalid Date.
     * 5: Invalid Order Number - Company combination
     * 6: Invalid login credentials.
     * 7: Invalid transaction price.
     * 8: IOExceptions.
     * 9: IndexOutOfBoundsExceptions.
     * @param n
     * @param origin
     */
    public static void getError(int n, String origin) {
        try {
            // Inicialización de los streams.
            out = new BufferedWriter(
                new FileWriter(logFile, true));
            if (!logFile.exists())
                logFile.createNewFile();
            out.write((new Date()).toString());
        } catch (IOException ex) {
            ExceptionManager.getError(8, "ExceptionManager.getError()");
        }
        
        String msg = "";
        switch(n) {
            case 0 -> {
                // ClassNotFoundExceptions
                msg = "ERROR: ClassNotFoundException in DBConnection's Constructor.\n"
                    + "All database connections will be invalid.\n"
                    + "Please contact your system adminsitrator.\n";
            }
            case 1 -> {
                // SQLExceptions
                msg = "ERROR: SQLException in " + origin + ".\n"
                    + "Please contact your system adminsitrator.\n";
            }
            case 2 -> {
                // NumberFormatExceptions
                msg = "ERROR: NumberFormatException in " + origin + ".\n"
                    + "Please contact your system adminsitrator.\n";
            }
            case 3 -> {
                // Invalid NIF
                msg = "ERROR: Invalid NIF.\n"
                    + "Please enter a valid NIF.\n";
            }
            case 4 -> {
                // Invalid Date
                msg = "ERROR: Invalid register date.\n"
                    + "Please enter a valid register date.\n";
            }
            case 5 -> {
                // Invalid Order Number - Company combination
                msg = "ERROR: Invalid Order Number - Company combination.\n"
                    + "Please enter a valid combination.\n";
            }
            case 6 -> {
                // Invalid login credentials
                msg = "ERROR: Invalid login credentials.\n"
                    + "Please try again.\n";
            }
            case 7 -> {
                // Invalid transaction price
                msg = "ERROR: Invalid transaction price.\n"
                    + "Please enter a valid price.\n";
            }
            case 8 -> {
                // IOExceptions
                msg = "ERROR: IOException in " + origin + ".\n"
                    + "Please contact your system administrator.\n";
            }
            case 9 -> {
                // NullPointerExceptions
                msg = "ERROR: IndexOutOfBoundsException in " + origin + ".\n"
                    + "If this problem persists, contact your system adminsitrator.\n";
            }
        }
        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), msg);
        try {
            out.write("\n" + msg + "\n---- END OF ERROR.\n");
        } catch (IOException ex) {
            System.out.println("FATAL ERROR. Could not write to log.");
        }
        
        close();
    }
    
    private static void close() {
        try {
            out.close();
        } catch (IOException ex) {
            System.out.println("FATAL ERROR. Could not close log stream.");
        }
    }
}
