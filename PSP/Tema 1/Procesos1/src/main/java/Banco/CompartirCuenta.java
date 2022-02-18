package Banco;

public class CompartirCuenta {
    public static void main(String[] args) {
        Cuenta cuenta = new Cuenta(40);
        
        SacarDinero s1 = new SacarDinero("Hilo 1", cuenta);
        SacarDinero s2 = new SacarDinero("Hilo 2", cuenta);
        
        s1.start();
        s2.start();
    }
}
