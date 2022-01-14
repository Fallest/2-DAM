package IncrementaDecrementa;

import java.util.concurrent.Semaphore;

public class Hilo extends Thread {
    int num;
    String accion;
    Semaphore sem;
    
    Hilo(int numInc, String accion, Semaphore s) {
        this.num=numInc;
        this.accion=accion;
        this.sem = s;
    }
    
    @Override
    public synchronized void run (){
        for (int i = this.num; i > 0; i--) {
            
            switch(this.accion) {
                case "Incrementar" -> {
                    // Incrementamos el contador
                    Main.incrementarContador();
                    
                    System.out.println("Contador incrementado: " + Main.getContador());
                    
                    this.sem.release(1);
                }
                case "Decrementar" -> { 
                    try {
                        this.sem.acquire(1);

                        Main.decrementarContador();
                        
                        System.out.println("Contador decrementado: " + Main.getContador());
                    } catch (InterruptedException ignored) {}
                }
            }
            
        }        
    }

}
