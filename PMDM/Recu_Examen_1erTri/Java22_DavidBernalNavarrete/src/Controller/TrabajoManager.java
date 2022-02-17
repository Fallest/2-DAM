package Controller;

import Model.Trabajo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Clase para realizar consultas sobre la tabla "shops" de la BD.
 */
public class TrabajoManager {

    // <editor-fold defaultstate="collapsed" desc="Funciones Select, Update, Delete e Insert">
    public static ArrayList<Trabajo> select(String where) {
        ArrayList<Trabajo> res = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery("select * from trabajo " + where);

            while (rset.next()) {
                res.add(new Trabajo(
                        rset.getInt(1),
                        rset.getString(2),
                        rset.getFloat(3),
                        rset.getDate(4),
                        rset.getInt(5)
                ));
            }

            con.close();
            stmt.close();
            rset.close();
        } catch (SQLException ex) {
            ExceptionManager.getError(1, "TrabajoManager.select()");
        }

        return res;
    }

    public static void update(String what, String where) {
        String query = "update trabajo set " + what + " " + where;

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            int rowsAffected = stmt.executeUpdate(query);

            System.out.println(rowsAffected + " rows affected in the update.");
            
            con.close();
            stmt.close();
        } catch (SQLException ex) {
            ExceptionManager.getError(1, "TrabajoManager.update()");
        }
    }

    public static void delete(String where) {
        String query = "delete trabajo " + where;

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            int rowsAffected = stmt.executeUpdate(query);

            System.out.println(rowsAffected + " rows affected in the delete.");
            
            con.close();
            stmt.close();
        } catch (SQLException ex) {
            ExceptionManager.getError(1, "TrabajoManager.delete()");
        }
    }

    public static void insert(String values) {
        String query = "insert into trabajo values (" + values + ")";

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            
            con.close();
            stmt.close();
        } catch (SQLException ex) {
            ExceptionManager.getError(1, "TrabajoManager.insert()");
        }
    }
    // </editor-fold>
}
