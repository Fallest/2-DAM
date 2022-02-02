package Ejercicio2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket server;
        Socket client;
        DataInputStream dataIn;
        DataOutputStream dataOut;
        
        try {
            int port = 6001;

            server = new ServerSocket(port);
            System.out.println("Server de David: Iniciado");
            System.out.println("Escuchando en el puerto "
                    + server.getLocalPort());

            // Esperamos a que un cliente se conecte
            client = server.accept();
            System.out.println("Cliente conectado: " + client.getInetAddress());
            // Antes de decirle nada, vamos a escuchar lo que nos tiene que decir.
            dataIn = new DataInputStream(client.getInputStream());
            System.out.println(dataIn.readUTF());
            
            // Ahora le enviamos un mensaje al cliente
            dataOut = new DataOutputStream(client.getOutputStream());
            dataOut.writeUTF("Servidor: Saludos desde el servidor.");
            dataOut.flush();
            
            dataIn.close();
            dataOut.close();
            client.close();
            server.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
