package Controller;

import Model.DeliveryPerson;
import View.MainFrame;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

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
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    "ERROR: Exception in Delivery.select().\n"
                    + "Please contact your system adminsitrator.\n"
                    + "Error Message:\n" + ex);
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
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    "ERROR: Exception in DeliveryManager.selectDelCod().\n"
                    + "Please contact your system adminsitrator.\n"
                    + "Error Message:\n" + ex);
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
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    "ERROR: Exception in DeliveryManager.update().\n"
                    + "Please contact your system adminsitrator.\n"
                    + "Error Message:\n" + ex);
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
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    "ERROR: Exception in DeliveryManager.delete().\n"
                    + "Please contact your system adminsitrator.\n"
                    + "Error Message:\n" + ex);
        }
    }

    public static void insert(String values) {
        String query = "insert into delivery values (" + values + ")";

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    "ERROR: Exception in DeliveryManager.insert().\n"
                    + "Please contact your system adminsitrator.\n"
                    + "Error Message:\n" + ex);
        }
    }
    // </editor-fold>
}
