package Semaforos4;
        
public class ProductorConsumidor {

    public static void main(String[] args) {
        final int PRODUCTOR = 3;
        final int CONSUMIDOR = 10;
        Almacen almacen = new Almacen();
        for (int i = 0; i < PRODUCTOR; i++) {
            new Productor("Productor " + i, almacen).start();
        }
        for (int i = 0; i < CONSUMIDOR; i++) {
            new Consumidor("Consumidor " + i, almacen).start();
        }
    }
}
