package Controller;

import View.MainFrame;
import java.sql.*;
import javax.swing.JOptionPane;

public class DBConnection {

    /**
     * Clase para gestionar la conexión con la base de datos Derby.
     *
     */
    private static final DBConnection ref = new DBConnection();

    public DBConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    "ERROR: Exception in DBConnection's Constructor (ClassNotFound).\n"
                    + "All database connections will be invalid.\n"
                    + "Please contact your system adminsitrator.\n"
                    + "Error Message:\n" + ex);
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:derby://localhost:1527/practica6";
        return DriverManager.getConnection(url, "practica6", "practica6");
    }

    public static void validateCon() {
        try {
            PreparedStatement stmt
                    = getConnection().prepareStatement("select count(*) from clients");
            ResultSet res = stmt.executeQuery();
            res.next();
            if (res.getInt(1) != 0) {
                System.out.println("Validation complete.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    "ERROR: Exception in DBConnection's validation.\n"
                    + "Is the \"clients\" table empty?.\n"
                    + "Please contact your system adminsitrator.\n"
                    + "Error Message:\n" + ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Funciones close()">
    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (Exception ignored) {
        }
    }

    public static void close(Statement stmt) {
        try {
            stmt.close();
        } catch (Exception ignored) {
        }
    }

    public static void close(Connection conn) {
        try {
            conn.close();
        } catch (Exception ignored) {
        }
    }
    // </editor-fold>
}
