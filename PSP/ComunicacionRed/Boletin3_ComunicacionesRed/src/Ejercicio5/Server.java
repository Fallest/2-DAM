package Ejercicio5;

import java.io.*;
import java.net.*;

public class Server {
    /**
     * Server TCP
     */
    public static boolean running = true;
    
    public static void main(String[] args) throws ClassNotFoundException {
        try {
            // Bucle infinito para aceptar conexiones.
            ServerSocket socket = new ServerSocket(6000);
            
            
            while (running) {
                Socket cli = socket.accept();
                HiloServidor atender = (new HiloServidor(cli));
                atender.start();
            }
            
            System.out.println("Cerrando server...");
            socket.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
