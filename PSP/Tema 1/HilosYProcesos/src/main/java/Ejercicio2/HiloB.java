package Ejercicio2;

import static java.lang.Thread.sleep;

public class HiloB extends Thread {
    private Contador contador;
    
    public HiloB(String name, Contador c) {
        setName(name);
        contador = c;
    }
    
    @Override
    public void run() {
        
        for(int i = 0; i < 300; i++) {
            contador.decrementa();
            try {
                sleep(10);
            } catch(InterruptedException ex) {
                System.out.println("Interrumpido");
            }
        }
        
        System.out.println("HiloB > " + contador.get());
    }
}