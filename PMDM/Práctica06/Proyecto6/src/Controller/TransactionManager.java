package Controller;

import Model.ShopTransaction;
import View.MainFrame;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.derby.iapi.sql.PreparedStatement;

public class TransactionManager {

    /**
     * Clase para realizar consultas sobre la tabla "shops" de la BD.
     */
    // <editor-fold defaultstate="collapsed" desc="Funciones Select, Update, Delete e Insert">
    public static ArrayList<ShopTransaction> select(String where) {
        ArrayList<ShopTransaction> res = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery("select * from shops " + where);

            while (rset.next()) {
                res.add(new ShopTransaction(
                        rset.getInt(1),
                        rset.getInt(2),
                        rset.getInt(3),
                        rset.getString(4),
                        rset.getFloat(5)
                ));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    "ERROR: Exception in TransactionManager.select().\n"
                    + "Please contact your system adminsitrator.\n"
                    + "Error Message:\n" + ex);
        }

        return res;
    }
    
    public static ArrayList<ShopTransaction> selectWithLoc(String loc) {
        ArrayList<ShopTransaction> res = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            java.sql.PreparedStatement stmt = con.prepareStatement("select * from shops where loc = ?");
            stmt.setInt(1, Integer.parseInt(loc));
            ResultSet rset = stmt.executeQuery();

            while (rset.next()) {
                res.add(new ShopTransaction(
                        rset.getInt(1),
                        rset.getInt(2),
                        rset.getInt(3),
                        rset.getString(4),
                        rset.getFloat(5)
                ));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    "ERROR: Exception in TransactionManager.select().\n"
                    + "Please contact your system adminsitrator.\n"
                    + "Error Message:\n" + ex);
        }

        return res;
    }
    
    public static ArrayList<String> selectShops() {
        ArrayList<String> res = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery("select distinct shop_name from shops");

            while (rset.next()) {
                res.add(rset.getString(1));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    "ERROR: Exception in TransactionManager.selectShops().\n"
                    + "Please contact your system adminsitrator.\n"
                    + "Error Message:\n" + ex);
        }

        return res;
    }

    public static void update(String what, String where) {
        String query = "update shops set " + what + " " + where;

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            int rowsAffected = stmt.executeUpdate(query);

            System.out.println(rowsAffected + " rows affected in the update.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    "ERROR: Exception in TransactionManager.update().\n"
                    + "Please contact your system adminsitrator.\n"
                    + "Error Message:\n" + ex);
        }
    }

    public static void delete(String where) {
        String query = "delete shops " + where;

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            int rowsAffected = stmt.executeUpdate(query);

            System.out.println(rowsAffected + " rows affected in the delete.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    "ERROR: Exception in TransactionManager.delete().\n"
                    + "Please contact your system adminsitrator.\n"
                    + "Error Message:\n" + ex);
        }
    }

    public static void insert(String values) {
        String query = "insert into shops values (" + values + ")";

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    "ERROR: Exception in TransactionManager.insert().\n"
                    + "Please contact your system adminsitrator.\n"
                    + "Error Message:\n" + ex);
        }
    }
    // </editor-fold>
}
