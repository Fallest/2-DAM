package Modelo;

public class Excepciones extends Exception {
    
    private String mensaje = "1";

    Excepciones(String err) {
        if (err.equals("saldo"))
            this.mensaje = 
                    "ERROR\n"
                    + "El saldo no puede ser menor que el saldo \n"
                    + "mínimo establecido para la cuenta.";
    }
    
    public String getMensaje() {
        return this.mensaje;
    }
    
}
