package Controller;

import java.sql.*;

/**
 * Clase que comprueba los usuarios y contraseñas. La usará la GUI para
 * acceder a la aplicación.
 */
public class LoginChecker {
    /**
     * Comprueba que una pareja de usuario-contraseña es válida.Devuelve un
     * objeto de tipo User.Para realizar la comprobación, realiza una consulta a
     * la base de datos, y sobre el ResultSet, comprueba si existe la pareja de
     * datos.
     *
     * @param usr
     * @param pass
     * @return
     */
    public static boolean login(String usr, char[] pass) {

        // Primero comprobamos los clientes normales
        String pw = String.valueOf(pass);
        boolean isValid = false;

        try {
            String query = "select nombre, numero from empleado where nombre = ? "
                    + "and numero = ?";
            Connection con = DBConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, usr);
            stmt.setInt(2, Integer.parseInt(pw));
            ResultSet rset = stmt.executeQuery();

            // Sabemos que las columnas van a ser dos:
            while (rset.next()) {
                String nombre = rset.getString(1); 
                int numero = rset.getInt(2);

                if ((nombre.equals("Luna") && numero == 1001)
                        || (nombre.equals("Antonio") && numero == 1002)) {
                    isValid = true;
                }
            }

            DBConnection.close(rset);
            DBConnection.close(stmt);
            DBConnection.close(con);
        } catch (SQLException ex) {
            ExceptionManager.getError(1, "DBManager.getUsers()");
            System.out.println(ex);
        } catch (NumberFormatException ex) { 
            ExceptionManager.getError(6, "");
        }

        return isValid;
    }
}
