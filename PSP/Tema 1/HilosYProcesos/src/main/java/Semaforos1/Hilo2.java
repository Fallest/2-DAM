package Semaforos1;

import java.util.concurrent.Semaphore;

public class Hilo2 extends Thread {
    Semaphore sforo;
    
    public Hilo2(Semaphore s) {
        this.sforo = s;
    }
    
    @Override
    public void run() {
        System.out.println("Ejecutando hilo 2");

        for (int i = 0; i < 5; i++)
            System.out.println("Hola");
        
        sforo.release(1);
    }
}
