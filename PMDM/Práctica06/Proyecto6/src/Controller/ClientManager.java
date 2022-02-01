package Controller;

import Model.Client;
import View.MainFrame;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

public class ClientManager {

    /**
     * Clase para realizar consultas sobre la tabla "clients" de la BD.
     */
    // <editor-fold defaultstate="collapsed" desc="Funciones Select, Update, Delete e Insert">
    public static ArrayList<Client> select(String where) {
        ArrayList<Client> res = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery("select * from clients " + where);

            while (rset.next()) {
                res.add(new Client(
                        rset.getInt(1),
                        rset.getString(2),
                        rset.getDate(3),
                        rset.getString(4)
                ));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    "ERROR: Exception in ClientManager.select().\n"
                    + "Please contact your system adminsitrator.\n"
                    + "Error Message:\n" + ex);
        }

        return res;
    }

    public static void update(String what, String where) {
        String query = "udpate clients set = " + what + " " + where;

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            int rowsAffected = stmt.executeUpdate(query);

            System.out.println(rowsAffected + " rows affected in the update.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    "ERROR: Exception in ClientManager.update().\n"
                    + "Please contact your system adminsitrator.\n"
                    + "Error Message:\n" + ex);
        }
    }

    public static void delete(String where) {
        String query = "delete clients " + where;

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            int rowsAffected = stmt.executeUpdate(query);

            System.out.println(rowsAffected + " rows affected in the delete.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    "ERROR: Exception in ClientManager.delete().\n"
                    + "Please contact your system adminsitrator.\n"
                    + "Error Message:\n" + ex);
        }
    }

    public static void insert(String values) {
        String query = "insert into clients values (" + values + ")";

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    "ERROR: Exception in ClientManager.insert().\n"
                    + "Please contact your system adminsitrator.\n"
                    + "Error Message:\n" + ex);
        }
    }
    // </editor-fold>
}
