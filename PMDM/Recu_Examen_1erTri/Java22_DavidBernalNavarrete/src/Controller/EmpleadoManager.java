package Controller;

import Model.*;
import java.sql.*;
import java.util.*;

/**
 * Clase para realizar consultas y modificaciones sobre la tabla "empleado" de
 * la BD.
 */
public class EmpleadoManager {
    private static ResultSet empleados;
    
    public static void start() {
        // Inicializa el ResultSet de empleados, para recorrerlo desde el navegador
        // de empleados.
        System.out.println("EmpleadoManager started");
        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            empleados = stmt.executeQuery("select * from empleado");
        } catch (SQLException ex) {
            ExceptionManager.getError(1, "EmpleadoManager.start()");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Funciones para recorrer el ResultSet">
    public static boolean isFirst() {
        try {
            return empleados.isFirst();
        } catch (SQLException ex) {
            ExceptionManager.getError(1, "EmpleadoManager.isFirst()");
        }
        return false;
    }

    public static boolean isLast() {
        try {
            return empleados.isLast();
        } catch (SQLException ex) {
            ExceptionManager.getError(1, "EmpleadoManager.isLast()");
        }
        return false;
    }

    public static Empleado first() {
        try {
            if (empleados.first()) {
                return new Empleado(
                        empleados.getInt(1),
                        empleados.getString(2),
                        empleados.getString(3),
                        empleados.getString(4),
                        empleados.getFloat(5),
                        empleados.getFloat(6),
                        empleados.getFloat(7),
                        empleados.getFloat(8),
                        empleados.getInt(9)
                );
            }
        } catch (SQLException ex) {
            ExceptionManager.getError(1, "EmpleadoManager.first()");
        }
        return null;
    }

    public static Empleado last() {
        try {
            if (empleados.last()) {
                return new Empleado(
                        empleados.getInt(1),
                        empleados.getString(2),
                        empleados.getString(3),
                        empleados.getString(4),
                        empleados.getFloat(5),
                        empleados.getFloat(6),
                        empleados.getFloat(7),
                        empleados.getFloat(8),
                        empleados.getInt(9)
                );
            }
        } catch (SQLException ex) {
            ExceptionManager.getError(1, "EmpleadoManager.last()");
        }
        return null;

    }

    public static Empleado next() {
        try {
            if (empleados.next()) {
                return new Empleado(
                        empleados.getInt(1),
                        empleados.getString(2),
                        empleados.getString(3),
                        empleados.getString(4),
                        empleados.getFloat(5),
                        empleados.getFloat(6),
                        empleados.getFloat(7),
                        empleados.getFloat(8),
                        empleados.getInt(9)
                );
            }
        } catch (SQLException ex) {
            ExceptionManager.getError(1, "EmpleadoManager.next()");
        }
        return null;
    }

    public static Empleado prev() {
        try {
            if (empleados.previous()) {
                return new Empleado(
                        empleados.getInt(1),
                        empleados.getString(2),
                        empleados.getString(3),
                        empleados.getString(4),
                        empleados.getFloat(5),
                        empleados.getFloat(6),
                        empleados.getFloat(7),
                        empleados.getFloat(8),
                        empleados.getInt(9)
                );
            }
        } catch (SQLException ex) {
            ExceptionManager.getError(1, "EmpleadoManager.prec()");
        }
        return null;
    }
    
    public static Empleado get() {
        try {
            return new Empleado(
                    empleados.getInt(1),
                    empleados.getString(2),
                    empleados.getString(3),
                    empleados.getString(4),
                    empleados.getFloat(5),
                    empleados.getFloat(6),
                    empleados.getFloat(7),
                    empleados.getFloat(8),
                    empleados.getInt(9)
            );
        } catch (SQLException ex) {
            ExceptionManager.getError(1, "EmpleadoManager.prec()");
        }
        return null;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Funciones Select, Update, Delete e Insert">
    public static ArrayList<Empleado> select(String where) {
        ArrayList<Empleado> res = new ArrayList<>();
        
        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery("select * from empleado " + where);
            
            while (rset.next()) {
                res.add(new Empleado(
                        rset.getInt(1),
                        rset.getString(2),
                        rset.getString(3),
                        rset.getString(4),
                        rset.getFloat(5),
                        rset.getFloat(6),
                        rset.getFloat(7),
                        rset.getFloat(8),
                        rset.getInt(9)
                ));
            }
            
            con.close();
            stmt.close();
            rset.close();
        } catch (SQLException ex) {
            ExceptionManager.getError(1, "EmpleadoManager.select()");
        }
        
        return res;
    }
    
    public static ArrayList<Trabajo> getTrabajos(int numero) {
        ArrayList<Trabajo> res = new ArrayList<>();
        
        try {
            String query = "select * from trabajo where empNumero = ?";
            
            Connection con = DBConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, numero);
            ResultSet rset = stmt.executeQuery();
            
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
            ExceptionManager.getError(1, "EmpleadoManager.getTrabajos()");
        }
        
        return res;
    }
    
    public static void update(String what, String where) {
        String query = "update empleado set " + what + " " + where;
        
        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            int rowsAffected = stmt.executeUpdate(query);
            
            System.out.println(rowsAffected + " rows affected in the update.");
            
            con.close();
            stmt.close();
        } catch (SQLException ex) {
            ExceptionManager.getError(1, "EmpleadoManager.update()");
        }
    }
    
    public static void delete(String where) {
        String query = "delete empleado " + where;
        
        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            int rowsAffected = stmt.executeUpdate(query);
            
            System.out.println(rowsAffected + " rows affected in the delete.");
            
            con.close();
            stmt.close();
        } catch (SQLException ex) {
            ExceptionManager.getError(1, "EmpleadoManager.delete()");
        }
    }
    
    public static void insert(String values) {
        String query = "insert into empleado values (" + values + ")";
        
        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            
            con.close();
            stmt.close();
        } catch (SQLException ex) {
            ExceptionManager.getError(1, "EmpleadoManager.insert()");
        }
    }
    // </editor-fold>
}
