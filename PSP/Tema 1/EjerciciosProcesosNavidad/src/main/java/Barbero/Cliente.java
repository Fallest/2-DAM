package Barbero;

import static java.lang.Thread.sleep;

class Cliente extends Thread {
    private Barbero bar;
    private int numCliente;
    
    public Cliente(int nCli, Barbero b) {
        this.numCliente = nCli;
        this.bar = b;
    }

    @Override
    public void run() {
        try {
            bar.inicioCorte(numCliente);
            sleep(800);
            bar.finCorte(numCliente);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}