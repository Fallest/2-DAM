package Semaforos2;

import java.util.concurrent.Semaphore;

public class Main {
    
    protected static Semaphore sforo = new Semaphore(0, true);
    
    public static void main(String[] args) {
        Hilo1 h1 = new Hilo1(sforo);
        h1.start();
        Hilo2 h2 = new Hilo2(sforo);
        h2.start();
        Hilo3 h3 = new Hilo3(sforo);
        h3.start();
    }
}
