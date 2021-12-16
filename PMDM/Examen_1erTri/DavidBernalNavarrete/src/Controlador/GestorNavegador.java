package Controlador;

import Modelo.Cuenta;
import java.sql.*;

/**
 * Clase para gestionar el visionade de cuentas de una en una.
 */

public class GestorNavegador {
    // Atributos
    private static Connection con;
    private static Statement stmt;
    private static String sql;
    private static ResultSet rset;
    
    // Métodos
    public static void iniciar(int codCli) {
        try {
            sql = "select * from cuenta where codCli = " 
                    + String.valueOf(codCli);
            con = Conexion.getConnection();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rset = stmt.executeQuery(sql);
            rset.next();
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error en GestorNavegador.iniciar()");
        }
    }
    
    public static boolean avanzar() {
        try {
            rset.next();
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error en GestorNavegador.avanzar()");
        }
        return false;
    }
    
    public static boolean retroceder() {
        try {
            rset.previous();
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error en GestorNavegador.retroceder()");
        }
        return false;
    }
    
    public static boolean primero() {
        try {
            rset.first();
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error en GestorNavegador.primero()");
        }
        return false;
    }
    
    public static boolean ultimo() {
        try {
            rset.last();
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error en GestorNavegador.ultimo()");
        }
        return false;
    }
    
    public static boolean esPrimero() {
        try {
            rset.isFirst();
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error en GestorNavegador.esPrimero()");
        }
        return false;
    }
    
    public static boolean esUltimo() {
        try {
            rset.isLast();
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error en GestorNavegador.esUltimo()");
        }
        return false;
    }
    
    public static Cuenta leer() {
        try {
            return new Cuenta(
                    rset.getInt(1),
                    rset.getDate(2),
                    rset.getFloat(3),
                    rset.getFloat(4),
                    rset.getFloat(5),
                    rset.getInt(6)
            );
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error en GestorNavegador.leer()");
        }
        return null;
    }
    
    public static void finalizar() {
        Conexion.close(rset);
        Conexion.close(stmt);
        Conexion.close(con);
    }
}
