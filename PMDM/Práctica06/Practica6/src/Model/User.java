package Model;

public class User {

    /**
     * Clase para almacenar los datos referentes a una sesión.
     */
    // Atributos
    private String usrName;
    private boolean isDelivery;

    // Constructor
    public User(String usr_name, boolean is_delivery) {
        this.usrName = usr_name;
        this.isDelivery = is_delivery;
    }

    // Getters
    public String getUsrName() {
        return usrName;
    }

    public boolean isDelivery() {
        return isDelivery;
    }

}
