package Semaforos4;

public class Consumidor extends Thread {
    private Almacen almacen;
    
    public Consumidor(String name, Almacen a) {
        this.setName(name);
        this.almacen = a;
    }
    
    public void run() {
        while (true) {
            almacen.consumir(this.getName());
        }
    }
}
