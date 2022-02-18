package Ejercicio2;

import java.io.*;
import java.net.*;

public class Server {

    /**
     * Server TCP
     */
    
    public static void main(String[] args) throws ClassNotFoundException {
        try {            
            int port = 6000, n;
            
            ServerSocket server = new ServerSocket(port);
            Socket client;
            ObjectOutputStream out;
            ObjectInputStream in;
            Numeros num;
            
            System.out.println("El server está escuchando en el puerto "
                        + server.getLocalPort());
                client = server.accept();
            
            do {
                // Una vez hemos conectado con algún cliente, recibimos el objeto Numero
                in = new ObjectInputStream(client.getInputStream());
                num = (Numeros) in.readObject();
                System.out.println("Objeto recibido: " + num);
                
                System.out.println("Calculando datos...");
                n = num.getNumero();
                num.setCuadrado(n * n);
                num.setCubo(n * n * n);
                
                out = new ObjectOutputStream(client.getOutputStream());
                out.writeObject(num);
                System.out.println("Objeto enviado.");
            } while (n > 0);
            
            System.out.println("Cerrando servidor.");
            
            in.close();
            out.close();
            client.close();
            server.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
