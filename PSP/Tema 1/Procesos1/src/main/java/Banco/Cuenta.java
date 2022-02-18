package Banco;

import static java.lang.Thread.sleep;

public class Cuenta {
    private float saldo;
    
    public Cuenta(float saldo) {
        this.saldo = saldo;
        try {sleep(50);}
        catch(InterruptedException e) {
            System.out.println("Interrumpido.");
        }
    }
    
    public float getSaldo() {
        return saldo;
    }
    
    public void restar(float menos) {
        saldo -= menos;
    }
    
    public synchronized void retirarDinero(float cantidad, String hilo) {
        if ( saldo >= cantidad ) {
            restar(cantidad);
            System.out.println(cantidad + " euros retirados por " + hilo + ".");
            System.out.println("Quedan " + saldo + " euros.");
        }
        else {
            System.out.println("ERROR - No hay suficiente saldo para " + hilo + ".");
        }
    }
}
