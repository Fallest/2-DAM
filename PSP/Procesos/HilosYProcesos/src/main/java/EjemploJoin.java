public class EjemploJoin {

    public static void main(String args[]) {
        HiloJoin 
                t1 = new HiloJoin("Hilo 1", 2),
                t2 = new HiloJoin("Hilo 2", 5),
                t3 = new HiloJoin("Hilo 3", 7);
        
        /*
        try {
            t1.start();
            t1.join();
            t2.start();
            t2.join();
            t3.start();
            t3.join();
        } catch(InterruptedException ex) {
            System.out.println("Fin del programa.");
        }
        */
        
        t1.start();
        t2.start();
        t3.start();
        
        
        try {
            t2.join();
            t3.join();
            t1.join();      
        } catch(InterruptedException ex) {
            System.out.println("Fin del programa.");

        }
    }
}
