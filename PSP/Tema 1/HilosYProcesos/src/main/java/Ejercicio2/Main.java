package Ejercicio2;

public class Main {
    public static void main(String[] args) {
        Contador cont = new Contador(100);
        
        HiloA hA = new HiloA("HiloA", cont);
        HiloB hB = new HiloB("HiloB", cont);
        
        hA.start();
        hB.start();
    }
}
