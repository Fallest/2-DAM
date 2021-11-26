package Semaforos2;

import java.util.concurrent.Semaphore;

public class Hilo1 extends Thread {
    Semaphore sforo;
    
    public Hilo1(Semaphore s) {
        this.sforo = s;
    }
    
    @Override
    public void run() {
        System.out.println("Ejecutando hilo 1");
        for (int i = 0; i < 5; i++)
            System.out.println("Hola");
        
        sforo.release(2);
    }
}
