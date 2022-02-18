package Ejercicio6;

import java.io.*;
import java.net.*;
import java.util.Random;

public class Server {
    /**
     * Server TCP
     */
    public static boolean running = true;
    private static int numeroAdivinar;
    public static boolean acierto = false;
    public static String ganador;
    
    public static void main(String[] args) throws ClassNotFoundException {
        try {
            // Bucle infinito para aceptar conexiones.
            ServerSocket socket = new ServerSocket(6000);
            
            numeroAdivinar = (new Random()).nextInt(101);
            
            while (running) {
                System.out.println("El servidor está escuchando en el puerto " + 6000);
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
    
    public static int getNumeroAdivinar() {
        return Server.numeroAdivinar;
    }

    static void acierto(String name) {
        System.out.println("El jugador " + name + " ha acertado.");
        Server.ganador = name;
        Server.acierto = true;
        Server.running = false;
    }
}
