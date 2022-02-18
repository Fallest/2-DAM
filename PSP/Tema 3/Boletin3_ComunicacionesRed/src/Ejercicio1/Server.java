package Ejercicio1;

import java.io.*;
import java.net.*;

public class Server {
    /**
     * Server TCP
     */
    
    public static void main(String[] args) throws ClassNotFoundException {
        try {
            // Creamos un objeto Persona y lo mostramos por pantalla
            Persona p = new Persona("Maribel", 24);
            System.out.println("Objeto a enviar: " + p);
            
            int port = 6000;

            ServerSocket server = new ServerSocket(port);
            System.out.println("El server está escuchando en el puerto "
                    + server.getLocalPort());

            Socket client = server.accept();
            
            // Una vez hemos conectado con algún cliente, le enviamos la persona
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            out.writeObject(p);
            
            System.out.println("Objeto enviado.");
            
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            p = (Persona) in.readObject();
            System.out.println("Objeto recibido: " + p);
            
            in.close();
            out.close();
            client.close();
            server.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
