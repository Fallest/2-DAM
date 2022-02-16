package Controller;

import Model.DeliveryPerson;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DeliveryManager {

    /**
     * Clase para realizar consultas sobre la tabla "delivery" de la BD.
     */
    // <editor-fold defaultstate="collapsed" desc="Funciones Select, Update, Delete e Insert">
    public static ArrayList<DeliveryPerson> select(String where) {
        ArrayList<DeliveryPerson> res = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery("select * from delivery " + where);

            while (rset.next()) {
                res.add(new DeliveryPerson(
                        rset.getInt(1),
                        rset.getString(2),
                        rset.getString(3),
                        rset.getString(4)
                ));
            }

        } catch (SQLException ex) {
            ExceptionManager.getError(1, "DeliveryManager.select()");
        }

        return res;
    }

    public static String selectDelCod(String where) {
        String res = "";

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            String query = "select del_cod from delivery " + where;
            System.out.println(query);
            ResultSet rset = stmt.executeQuery(query);

            while (rset.next()) {
                res = rset.getString(1);
            }

        } catch (SQLException ex) {
            ExceptionManager.getError(1, "DeliveryManager.selectDelCod()");
        }

        return res;
    }

    public static void update(String what, String where) {
        String query = "update delivery set " + what + " " + where;

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            int rowsAffected = stmt.executeUpdate(query);

            System.out.println(rowsAffected + " rows affected in the update.");
        } catch (SQLException ex) {
            ExceptionManager.getError(1, "DeliveryManager.update()");
        }
    }

    public static void delete(String where) {
        String query = "delete delivery " + where;

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            int rowsAffected = stmt.executeUpdate(query);

            System.out.println(rowsAffected + " rows affected in the delete.");
        } catch (SQLException ex) {
            ExceptionManager.getError(1, "DeliveryManager.delete()");
        }
    }

    public static void insert(String values) {
        String query = "insert into delivery values (" + values + ")";

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            ExceptionManager.getError(1, "DeliveryManager.insert()");
        }
    }
    // </editor-fold>
}
