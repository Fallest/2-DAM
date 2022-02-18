public class Mesa {

    static Object tenedor1 = new Object();
    static Object tenedor2 = new Object();
    static Object tenedor3 = new Object();
    static Object tenedor4 = new Object();
    static Object tenedor5 = new Object();
    

    public static void main(String[] args) {
        
        Filosofo f1 = new Filosofo("Filosofo 1", tenedor1, tenedor2, 1);
        Filosofo f2 = new Filosofo("Filosofo 2", tenedor2, tenedor3, 1);
        Filosofo f3 = new Filosofo("Filosofo 3", tenedor3, tenedor4, 1);
        Filosofo f4 = new Filosofo("Filosofo 4", tenedor4, tenedor5, 1);
        Filosofo f5 = new Filosofo("Filosofo 5", tenedor5, tenedor1, 2);
        
        Thread hilo1 = new Thread(f1);
        Thread hilo2 = new Thread(f2);
        Thread hilo3 = new Thread(f3);
        Thread hilo4 = new Thread(f4);
        Thread hilo5 = new Thread(f5);
        
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
    }
}