package Controller;

import java.sql.*;
import java.util.HashMap;

public class DBManager {
    /**
     * Clase para ejecutar consultas y sentencias generales sobre la BD.
     */
    
    public static HashMap<String, String> getUsers(boolean clients) {
        /*
        Genera un HashMap de pares usuario-contraseña del tipo indicado (
        repartidores (clients es false) o clientes).
        */
        HashMap<String, String> dict = new HashMap<>();
        
        try {
            String query = clients ? "select nif, pw from clients" : 
                    "select del_cod, pw from delivery";
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(query);
            
            // Sabemos que las columnas van a ser dos:
            while(rset.next())
                dict.put(String.valueOf(rset.getInt(1)), rset.getString(2));
            
            DBConnection.close(rset);
            DBConnection.close(stmt);
            DBConnection.close(con);
        } catch (SQLException ex) {
            System.out.println("ERROR: An exception ocurred at DBManager.getUsers().");
        }
        return dict;    
    }
}
