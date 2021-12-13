package Controlador;

import Modelo.Empleado;
import java.sql.*;
import java.util.ArrayList;

public class Gestor {

    /**
     * Clase para controlar la conexión a la base de datos, realizar consltas y
     * demás.
     */
    
    public static void execute(String sql) {
        try {
            Connection con;
            Statement stmt;
            ResultSet rset;
            
            con = Conexion.getConnection();
            stmt = con.createStatement();
            rset = stmt.executeQuery(sql);
            
            while (rset.next())
                System.out.print(
                  rset.getString(1) + "; " +
                  rset.getString(2) + "; " +
                  rset.getString(3) + "; " +
                  rset.getString(4) + "; " +
                  rset.getString(5) + "; " +
                  rset.getString(6) + "; " +
                  rset.getString(7));
            
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error en Gestor.execute()");
        }
    }
    
    public static ArrayList<String> extractString(String sql) {
        /**
         * Extrae los datos de un ResultSet originado de una sentencia SQL y lo
         * devuelve en forma de un array de cadenas
         */
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        ArrayList<String> list = new ArrayList<>();
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.createStatement();
            rset = stmt.executeQuery(sql);

            while (rset.next()) {
                list.add(
                        rset.getInt(1) + " ; " +
                        rset.getString(2) + " ; " +
                        rset.getString(3) + " ; " +
                        rset.getString(4) + " ; " +
                        rset.getFloat(5) + " ; " +
                        rset.getFloat(6) + " ; " +
                        rset.getDate(7)
                );
            }
        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error en Gestor.extractString()");
        } finally {
            Conexion.close(rset);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return list;
    }
    
    public static ArrayList<Empleado> extract(String sql){
        /**
         * Extrae los datos de un ResultSet originado de una sentencia SQL
         * y lo devuelve en forma de ArrayList.
         */
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        ArrayList<Empleado> list = new ArrayList<Empleado>();
        
        try {
            conn = Conexion.getConnection();
            /* Los resultSet puede ser (Primer par�metro de createStatement):
            - Por defecto (lineal o no arrastable / Sólo hacia adelante) -> ResultSet.TYPE_FORWARD_ONLY
            - Insensible a los cambios de la BDs -> ResultSet.TYPE_SCROLL_INSENSITIVE 
            - Sensible a los cambios de la BDs -> Con ResultSet.TYPE_SCROLL_SENSITIVE
            El segundo par�metro de createStatement establece el tipo de concurrencia
             */
            stmt = conn.createStatement();
            rset = stmt.executeQuery(sql);

            while (rset.next()) {
                list.add(new Empleado(
                        rset.getInt(1),
                        rset.getString(2),
                        rset.getString(3),
                        rset.getString(4),
                        rset.getFloat(5),
                        rset.getFloat(6),
                        rset.getDate(7)
                ));
            }
        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error en Gestor.extract()");
        } finally {
            Conexion.close(rset);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return list;
    }
    
    public static void consult(String sql) {
        /**
         * Ejecuta una sentencia SQL y permite ir viendo los datos uno a uno.
         */
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        ArrayList<Empleado> list = new ArrayList<>();
        
        try {
            conn = Conexion.getConnection();
            /* Los resultSet puede ser (Primer par�metro de createStatement):
            - Por defecto (lineal o no arrastable / Sólo hacia adelante) -> ResultSet.TYPE_FORWARD_ONLY
            - Insensible a los cambios de la BDs -> ResultSet.TYPE_SCROLL_INSENSITIVE 
            - Sensible a los cambios de la BDs -> Con ResultSet.TYPE_SCROLL_SENSITIVE
            El segundo par�metro de createStatement establece el tipo de concurrencia
             */
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                        ResultSet.CONCUR_READ_ONLY);
            rset = stmt.executeQuery(sql);

            while (rset.next()) {
                continue;
            }
        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error en Gestor.consult()");
        } finally {
            Conexion.close(rset);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }
}
