package Controlador;

import Modelo.Empleado;
import java.sql.*;
import java.util.ArrayList;

public class Gestor {

    /**
     * Clase para controlar la conexión a la base de datos, realizar consltas y
     * demás.
     */
    
    public static ArrayList<Empleado> execute(String sql){
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
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
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
            e.printStackTrace();
        } finally {
            Conexion.close(rset);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return list;
    }
}
