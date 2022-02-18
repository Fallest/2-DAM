package Semaforos2;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hilo3 extends Thread {
    Semaphore sforo;
    
    public Hilo3(Semaphore s) {
        this.sforo = s;
    }
    
    @Override
    public void run() {
        try {
            
            sforo.acquire(1);
            System.out.println("Ejecutando hilo 3");
            
            for (int i = 0; i < 5; i++)
                System.out.println("Bye");
        } catch (InterruptedException ex) {
            Logger.getLogger(Hilo3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
