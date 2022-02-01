package Controller;

import Model.Order;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
            orders = stmt.executeQuery("select * from orders where nif_cli = " + nif);
        } catch (SQLException ex) {
            System.out.println("ERROR: An exception ocurred at OrderManager.start().");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Funciones para recorrer el ResultSet">
    public static boolean isFirst() {
        try {
            return orders.isFirst();
        } catch (SQLException ex) {
            System.out.println("ERROR: An exception ocurred at OrderManager.isFirst().");
        }
        return false;
    }
    
    public static boolean isLast() {
        try {
            return orders.isLast();
        } catch (SQLException ex) {
            System.out.println("ERROR: An exception ocurred at OrderManager.isLast().");
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
            System.out.println("ERROR: An exception ocurred at OrderManager.first().");
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
            System.out.println("ERROR: An exception ocurred at OrderManager.last+().");
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
            System.out.println("ERROR: An exception ocurred at OrderManager.next().");
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
            System.out.println("ERROR: An exception ocurred at OrderManager.prev().");
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
            System.out.println("ERROR: An exception ocurred at OrderManager.select().");
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
            System.out.println("ERROR: An exception ocurred at OrderManager.selectLocs().");
        }

        return res;
    }

    public static void update(String what, String where) {
        String query = "udpate orders set = " + what + " " + where;

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            int rowsAffected = stmt.executeUpdate(query);

            System.out.println(rowsAffected + " rows affected in the update.");
        } catch (SQLException ex) {
            System.out.println("ERROR: An exception ocurred at OrderManager.update()");
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
            System.out.println("ERROR: An exception ocurred at OrderManager.delete()");
        }
    }

    public static void insert(String values) {
        String query = "insert into orders values (" + values + ")";

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("ERROR: An exception ocurred at OrderManager.insert()");
        }
    }
    // </editor-fold>
}
