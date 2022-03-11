package Ejercicio5;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HiloServidor extends Thread {

    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket client;
    private String cad;
    private boolean run = true;

    public HiloServidor(Socket s) throws IOException {
        client = s;
        in = new ObjectInputStream(client.getInputStream());
        out = new ObjectOutputStream(client.getOutputStream());
    }

    @Override
    public void run() {
        try {
            System.out.println("Conexión: " + client.toString());
            // El cliente recibe la cadena
            leerCadena();

            // Modificamos la cadena y la enviamos
            enviarCadena();

            System.out.println("Finalizado: " + client.toString());
            in.close();
            out.close();
            client.close();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void leerCadena() throws IOException, ClassNotFoundException {
        cad = (String) in.readObject();
        if (cad.equals("*")) {
            run = false;
        }
    }

    public synchronized void enviarCadena() throws IOException {
        out.writeObject(cad.toLowerCase());
    }
}
