package Ejercicio1;

import java.io.IOException;
import java.net.*;

public class TCPServerBasico {
    
    /**
     * En esta clase se crea un Servidor y se abre un socket para escuchar las
     * peticiones de posibles usuarios.
     * Si se conecta algún cliente, se muestran sus datos.
    */

    public static void main(String[] args) {
        try {
            int port = 6000;

            ServerSocket server = new ServerSocket(port);
            System.out.println("Server is currently listening for calls at port "
                    + server.getLocalPort());

            Socket client = server.accept();
            showData(client);
            client.close();

            server.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public static void showData(Socket s) {
        System.out.println("Local Port:     " + s.getLocalPort());
        System.out.println("Remote Port:    " + s.getPort());
        System.out.println("Host/Address:   " + s.getLocalAddress());
        System.out.println("Remote Host:    " + s.getInetAddress().getHostName());
        System.out.println("Remote Address: " + s.getInetAddress().getHostAddress());
    }

}
