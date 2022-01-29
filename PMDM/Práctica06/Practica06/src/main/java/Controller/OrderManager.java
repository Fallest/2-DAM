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

}
