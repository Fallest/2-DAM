package Ejercicio6;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HiloServidor extends Thread {

    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket client;
    private int intentos = 5, n;
    private boolean acierto = false, mayor;

    public HiloServidor(Socket s) throws IOException {
        client = s;
    }

    @Override
    public void run() {
        try {
            in = new ObjectInputStream(client.getInputStream());
            out = new ObjectOutputStream(client.getOutputStream());
            System.out.println("Conexión: " + client.toString());
            // Le comunicamos al cliente todos sus datos
            enviarDatos();
            while (intentos > 0) {
                // El servidor recibe el intento de adivinar
                leerIntento();
                comprobarIntento();
                if (Server.acierto) {
                    enviarCadena("El jugador " + Server.ganador + " ha ganado.");
                    break;
                } else if (acierto) {
                    Server.acierto(this.getName());
                    enviarCadena("Has ganado!");
                    break;
                } else if (mayor) {
                    enviarCadena("Tu intento ha sido mayor que el número a adivinar.");
                } else if (!mayor) {
                    enviarCadena("Tu intento ha sido menor que el número a adivinar.");
                }
            }
            
            if (intentos == 0)
                enviarCadena("Has usado todos tus intentos!");

            System.out.println("Finalizado: " + client.toString());
            in.close();
            out.close();
            client.close();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void leerIntento() throws IOException, ClassNotFoundException {
        System.out.println("Intentos restantes: " + intentos);
        n = (int) in.readObject();
        intentos--;
    }

    public synchronized void enviarCadena(String cad) throws IOException {
        out.writeObject(cad);
    }

    private void enviarDatos() throws IOException {
        out.writeUTF("Bienvenido a Adivina el número: El juego está en progreso."
                + "\nTu identificador: " + this.getName());
    }

    private synchronized void comprobarIntento() {
        // Comprueba si el intento era correcto o no
        if (n > Server.getNumeroAdivinar()) {
            mayor = true;
        } else if (n < Server.getNumeroAdivinar()) {
            mayor = false;
        } else if (n == Server.getNumeroAdivinar()) {
            acierto = true;
        }
    }
}
