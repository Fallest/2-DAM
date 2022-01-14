package Barbero;

import static java.lang.Thread.sleep;

public class BarberoDurmiente {

    public static void main(String[] args) {
        Barbero barbero = new Barbero(false);
        for (int i = 0; i < 4; i++) {
            Cliente c = new Cliente(i + 1, barbero);
            c.start();
        }
    }
}