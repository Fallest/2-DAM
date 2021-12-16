package Controlador;

import Modelo.Cliente;
import java.sql.*;

public class GestorValidacion {
    
    // Atributos
    private static Connection con;
    private static PreparedStatement pstmt;
    private static ResultSet rset;
    private static Cliente cliente;
    private static String sql;
    
    // Constructor
    public GestorValidacion(String usr, String pwd) {
        
    }
    
    public static boolean comprobarUsuario(String usr, String pwd) {
        try {
            con = Conexion.getConnection();
            sql = "select * from cliente where nombre = ? and contrasena = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, usr);
            pstmt.setString(2, pwd);
            rset = pstmt.executeQuery();
            
            if (rset.next())
                cliente = new Cliente(
                        rset.getInt(1),
                        rset.getString(2),
                        rset.getString(3),
                        rset.getString(5)
                );
            
            return true;
        } catch (SQLException ex) {
            System.out.println("Error en GestorValidacion.comprobarUsuario()");
        } finally {
            Conexion.close(rset);
            Conexion.close(pstmt);
            Conexion.close(con);
        }
        return false;
    }
    
    // Para recuperar los datos del cliente.
    public static Cliente getCliente() {
        return GestorValidacion.cliente;
    }
}
