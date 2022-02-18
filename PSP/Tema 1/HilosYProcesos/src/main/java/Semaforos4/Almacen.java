package Semaforos4;

import java.util.concurrent.Semaphore;

public class Almacen {

    private final int MAX_LIMITE = 20;
    private int producto = 0;
    private Semaphore productor = new Semaphore(MAX_LIMITE);
    private Semaphore consumidor = new Semaphore(0);
    private Semaphore mutex = new Semaphore(1);

    public synchronized void producir(String nombreProductor) {
        System.out.println(nombreProductor + " intentando almacenar un producto");
        try {
            mutex.acquire();
            productor.acquire();
            System.out.println(nombreProductor + " almacena un producto. "
                    + "Almacén con " + producto + (producto > 1 ? " productos." : " producto."));

            Thread.sleep(500);
            consumidor.release();
            mutex.release();
        } catch (InterruptedException ex) {
        } finally {
        }
    }

    public synchronized void consumir(String nombreConsumidor) {
        System.out.println(nombreConsumidor + " intentando retirar un producto");
        try {
            mutex.acquire();
            consumidor.acquire();
            System.out.println(nombreConsumidor + " retira un producto. "
                    + "Almacén con " + producto + (producto > 1 ? " productos." : " producto."));

            Thread.sleep(500);
            productor.release();
            mutex.release();
        } catch (InterruptedException ex) {
        } finally {
        }
    }
}
