package Controller;

import Model.User;
import java.util.HashMap;
import java.util.Map.Entry;

public class LoginChecker {
    /**
     * Clase que comprueba los usuarios y contraseñas. La usará la GUI para
     * acceder a la aplicación.
     */
    
    public static User login(String usr, char[] pass) {
        /**
         * Comprueba que una pareja de usuario-contraseña es válida.
         * 
         * Devuelve un objeto de tipo User.
         * 
         * Para realizar la comprobación, realiza una consulta a la base de datos,
         * y sobre el ResultSet, comprueba si existe la pareja de datos.
         */        
        // Primero comprobamos los clientes normales
        HashMap<String, String> clients = DBManager.getUsers(true);
        String pw = String.valueOf(pass);
        
        for (Entry<String, String> cli : clients.entrySet()) {
            if (cli.getKey().equals(usr) && cli.getValue().equals(pw))
                return new User(usr, false);
        }
        
        // Luego comprobamos los repartidores
        HashMap<String, String> delUsers = DBManager.getUsers(false);
        
        for (Entry<String, String> del : delUsers.entrySet()) {
            if (del.getKey().equals(usr) && del.getValue().equals(pw))
                return new User(usr, true);
        }
        
        // Si no se encuentra en ninguno de los conjuntos, se devuelve null.
        return null;
    }
}
