package Controlador;

import java.sql.*;
import Modelo.Empleado;

public class Scroller {
    /**
     * Clase para mostrar uno a uno los elementos de la base de datos.
     */
    
    // Atributos
    Connection con;
    Statement stmt;
    ResultSet rset;

    /*------------------------------------------------------------------------*/
    // Constructor
    public Scroller (String sql) {
        // Inicia la conexión, el statement, y ejecuta la búsqueda.
        
        try {
            con = Conexion.getConnection();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rset = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error en Scroller.Scroller()");
        }
        
    }
    
    /*------------------------------------------------------------------------*/
    // Métodos para moverse por el ResultSet.
    public Empleado getData() {
        // Rescata el dato actual, convertido a un objeto de tipo Empleado.
        try {
            return new Empleado(
                    rset.getInt(1),
                    rset.getString(2),
                    rset.getString(3),
                    rset.getString(4),
                    rset.getFloat(5),
                    rset.getFloat(6),
                    rset.getDate(7)
            );
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error en Scroller.getData()");
        }
        return null;
    }
    
    public boolean next() {
        try {
            return rset.next();
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error en Scroller.next()");
        }
        return false;
    }
    
    public boolean previous() {
        try {
            return rset.previous();
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error en Scroller.previous()");
        }
        return false;
    }
    
    public boolean first() {
        try {
            return rset.first();
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error en Scroller.first()");
        }
        return false;
    }
    
    public boolean last() {
        try {
            return rset.last();
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error en Scroller.last()");
        }
        return false;
    }
    
    public void close() {
        // Cierra todos los elementos abiertos
        Conexion.close(rset);
        Conexion.close(stmt);
        Conexion.close(con);
    }
    
}
