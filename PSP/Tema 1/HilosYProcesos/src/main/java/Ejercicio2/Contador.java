package Ejercicio2;

public class Contador {
    private int c = 0;
    
    Contador (int c) {
        this.c = c;
    }
    
    public synchronized void incrementa() {c++;}
    public synchronized void decrementa() {c--;}
    public int get() {return c;}
}
