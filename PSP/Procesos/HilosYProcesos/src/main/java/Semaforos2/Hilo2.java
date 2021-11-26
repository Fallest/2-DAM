package Semaforos2;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hilo2 extends Thread {

    Semaphore sforo;

    public Hilo2(Semaphore s) {
        this.sforo = s;
    }

    @Override
    public void run() {
        try {

            sforo.acquire(1);
            System.out.println("Ejecutando hilo 2");

            for (int i = 0; i < 5; i++) {
                System.out.println("Adios");
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Hilo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
