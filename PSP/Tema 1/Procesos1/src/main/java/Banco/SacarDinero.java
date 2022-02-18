package Banco;


public class SacarDinero extends Thread {
    private Cuenta cuenta;
    
    public SacarDinero(String nombre, Cuenta cuenta) {
        this.setName(nombre);
        this.cuenta = cuenta;
    }
    
    @Override
    public void run() {
        for ( int i = 0; i < 4; i++) {
            cuenta.retirarDinero(10, this.getName());
        }
    }
}
