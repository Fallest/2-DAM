package Semaforos1;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hilo1 extends Thread {
    Semaphore sforo;
    
    public Hilo1(Semaphore s) {
        this.sforo = s;
    }
    
    @Override
    public void run() {
        try {
            sforo.acquire();
            System.out.println("Ejecutando hilo 1");
            for (int i = 0; i < 5; i++)
                System.out.println("Adios");
        } catch (InterruptedException ex) {
            Logger.getLogger(Hilo1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
