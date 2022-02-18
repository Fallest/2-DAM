package IncrementaDecrementa;

import java.util.concurrent.Semaphore;

public class Main {

    static int contador=0;
    
    public static void main(String[] args) {
        int numInc, numDec;
        Semaphore s;
        
        numInc = Leer.datoInt("Indique el numero de incrementos: ");
        numDec = Leer.datoInt("Indique el numero de decremento: ");

        s = new Semaphore(0, true);
        
        if (numDec > numInc)
            System.out.println("ERROR: No se puede decrementar más veces "
                    + "de las que se incrementa.");
        else {
            Hilo inc = new Hilo(numInc, "Incrementar", s);
            Hilo dec = new Hilo(numDec, "Decrementar", s);
        
            inc.start();
            dec.start(); 
        }
    }

    public synchronized static void incrementarContador() {
        Main.contador +=1;
    }

    public synchronized static void decrementarContador() {
        Main.contador -=1;
    }

    public synchronized static int getContador() {
        return contador;
    }
}
