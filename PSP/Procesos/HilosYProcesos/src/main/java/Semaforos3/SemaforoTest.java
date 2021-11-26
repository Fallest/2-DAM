package Semaforos3;

public class SemaforoTest {

    private static final int CNT = 100;
    private int x = 0;

    public static void main(String[] args) throws InterruptedException {
        //En el main se declara un objeto SemaforoTest y se llama al método iniciar()
        SemaforoTest semTest = new SemaforoTest();
        semTest.iniciar();
    }

    private void iniciar() throws InterruptedException {
        Semaforo s = new Semaforo(1);

        Hilo t1 = new Hilo(s);
        Hilo t2 = new Hilo(s);
        Hilo t3 = new Hilo(s);
        
        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        if (x == 3 * CNT) // Si la variable X ha llegado a 3000 indica que cada hilo ha realizado bien su tarea, por lo que se imprime
        {
            System.out.println("OK");
        } else {
            //Si el valor de X no es 3000 el semáforo no ha realizado su función , se ha incrementado x de forma anormal
            System.out.println("CONDICIÓN DE CARRERA!");
        }

    }

    public class Hilo extends Thread {

        private final Semaforo semaforo;

        public Hilo(Semaforo s) {
            this.semaforo = s;
        }
        // Método run() que debe de ejecutar cada hilo, consistirá en incrementar la variable x un total de
        // CNT veces, solicitando permiso y devolviendo el permiso.@Override

        @Override
        public void run() {
            try {
                for (int i = 0; i < CNT; i++) {
                    semaforo.acquire();
                    x += 1;
                    semaforo.release();
                }
            } catch (InterruptedException ex) {
                System.out.println("Algo ha salido mal.");
            }
        }

    }
}
