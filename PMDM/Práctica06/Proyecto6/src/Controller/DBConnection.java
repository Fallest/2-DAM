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
            System.out.println("ERROR: Exception in DBConnection's Constructor "
                    + "(ClassNotFound).");
            System.out.println(ex.toString());
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:derby://localhost:1527/practica6";
        return DriverManager.getConnection(url, "practica6", "practica6");
    }
    
    public void validateCon(String table) throws SQLException {
        PreparedStatement stmt = 
                getConnection().prepareStatement("select count(*) from clients");
        ResultSet res = stmt.executeQuery();
        res.next();
        if (res.getInt(1) != 0)
            System.out.println("Validation complete.");
        else
            System.out.println("ERROR: An error ocurred in the validation. Is the \"clients\" table empty?");
    }

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
}
