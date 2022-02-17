package Controller;

import java.sql.*;

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
            ExceptionManager.getError(0, "DBConnection.DBConnection()");
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:derby://localhost:1527/java22";
        return DriverManager.getConnection(url, "java22", "java22");
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
