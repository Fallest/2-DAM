package Semaforos3;

public class Semaforo {

    private int permisos;

    public Semaforo(int p) {
        this.permisos = p;
    }

    public synchronized void acquire() throws InterruptedException {
        acquire(1);
    }

    public synchronized void acquire(int n) throws InterruptedException {
        while (n > permisos) {
            wait();
        }
        permisos -= n;
    }

    public synchronized void release() throws InterruptedException {
        release(1);
    }

    public synchronized void release(int n) throws InterruptedException {
        permisos += n;
        notifyAll();
    }
}
