package Ejercicio1;

import java.io.*;
import java.net.*;

public class Server {
    /**
     * Server TCP
     */
    
    public static void main(String[] args) {
        try {
            int port = 6000;

            ServerSocket server = new ServerSocket(port);
            System.out.println("Server is currently listening for calls at port "
                    + server.getLocalPort());

            Socket client = server.accept();
            
            client.close();
            server.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
