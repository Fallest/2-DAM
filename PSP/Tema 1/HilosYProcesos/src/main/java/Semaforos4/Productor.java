package Semaforos4;

public class Productor extends Thread {
    private Almacen almacen;
    
    public Productor(String name, Almacen a) {
        this.setName(name);
        this.almacen = a;
    }
    
    public void run() {
        while (true) {
            almacen.producir(this.getName());
        }
    }
}
