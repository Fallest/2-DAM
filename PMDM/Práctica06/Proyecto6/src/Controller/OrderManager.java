package Controller;

import Model.Order;
import View.MainFrame;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class OrderManager {

    /**
     * Clase para realizar consultas sobre la tabla "orders" de la BD.
     */
    private static ResultSet orders;

    public static void start(String nif) {
        // Inicializa el ResultSet de pedidos, para recorrerlo desde el navegador
        // de pedidos.
        System.out.println("OrderManager started");
        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            if (nif.equals("admin")) {
                orders = stmt.executeQuery("select * from orders");
            } else {
                orders = stmt.executeQuery("select * from orders where nif_cli = " + nif);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    "ERROR: Exception in OrderManager.start().\n"
                    + "Please contact your system adminsitrator.\n"
                    + "Error Message:\n" + ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Funciones para recorrer el ResultSet">
    public static boolean isFirst() {
        try {
            return orders.isFirst();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    "ERROR: Exception in OrderManager.isFirst().\n"
                    + "Please contact your system adminsitrator.\n"
                    + "Error Message:\n" + ex);
        }
        return false;
    }

    public static boolean isLast() {
        try {
            return orders.isLast();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    "ERROR: Exception in OrderManager.isLast().\n"
                    + "Please contact your system adminsitrator.\n"
                    + "Error Message:\n" + ex);
        }
        return false;
    }

    public static Order first() {
        try {
            if (orders.first()) {
                return new Order(
                        orders.getInt(1),
                        orders.getInt(2),
                        orders.getFloat(3)
                );
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    "ERROR: Exception in OrderManager.first().\n"
                    + "Please contact your system adminsitrator.\n"
                    + "Error Message:\n" + ex);
        }
        return null;
    }

    public static Order last() {
        try {
            if (orders.last()) {
                return new Order(
                        orders.getInt(1),
                        orders.getInt(2),
                        orders.getFloat(3)
                );
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    "ERROR: Exception in OrderManager.last().\n"
                    + "Please contact your system adminsitrator.\n"
                    + "Error Message:\n" + ex);
        }
        return null;

    }

    public static Order next() {
        try {
            if (orders.next()) {
                return new Order(
                        orders.getInt(1),
                        orders.getInt(2),
                        orders.getFloat(3)
                );
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    "ERROR: Exception in OrderManager.next().\n"
                    + "Please contact your system adminsitrator.\n"
                    + "Error Message:\n" + ex);
        }
        return null;
    }

    public static Order prev() {
        try {
            if (orders.previous()) {
                return new Order(
                        orders.getInt(1),
                        orders.getInt(2),
                        orders.getFloat(3)
                );
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    "ERROR: Exception in OrderManager.prev().\n"
                    + "Please contact your system adminsitrator.\n"
                    + "Error Message:\n" + ex);
        }
        return null;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Funciones Select, Update, Delete e Insert">
    public static ArrayList<Order> select(String where) {
        ArrayList<Order> res = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery("select * from orders " + where);

            while (rset.next()) {
                res.add(new Order(
                        rset.getInt(1),
                        rset.getInt(2),
                        rset.getFloat(3)
                ));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    "ERROR: Exception in OrderManager.select().\n"
                    + "Please contact your system adminsitrator.\n"
                    + "Error Message:\n" + ex);
        }

        return res;
    }

    public static ArrayList<String> selectLocs(String nif) {
        ArrayList<String> res = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery("select loc from orders where nif_cli = " + nif);

            while (rset.next()) {
                res.add(String.valueOf(rset.getInt(1)));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    "ERROR: Exception in OrderManager.selectLocs().\n"
                    + "Please contact your system adminsitrator.\n"
                    + "Error Message:\n" + ex);
        }

        return res;
    }

    public static void update(String what, String where) {
        String query = "update orders set " + what + " " + where;

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            int rowsAffected = stmt.executeUpdate(query);

            System.out.println(rowsAffected + " rows affected in the update.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    "ERROR: Exception in OrderManager.update().\n"
                    + "Please contact your system adminsitrator.\n"
                    + "Error Message:\n" + ex);
        }
    }

    public static void delete(String where) {
        String query = "delete orders " + where;

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            int rowsAffected = stmt.executeUpdate(query);

            System.out.println(rowsAffected + " rows affected in the delete.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    "ERROR: Exception in OrderManager.delete().\n"
                    + "Please contact your system adminsitrator.\n"
                    + "Error Message:\n" + ex);
        }
    }

    public static void insert(String values) {
        String query = "insert into orders values (" + values + ")";

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),
                    "ERROR: Exception in OrderManager.insert().\n"
                    + "Please contact your system adminsitrator.\n"
                    + "Error Message:\n" + ex);
        }
    }
    // </editor-fold>
}
