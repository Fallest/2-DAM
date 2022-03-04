package tcpchat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            // Primero realizamos la conexión al server
            Socket localConn = new Socket("localhost", 6000);    // PC de casa
            System.out.println("Conexión realizada.");
            
            ObjectOutputStream out = new ObjectOutputStream(localConn.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(localConn.getInputStream());
            Scanner reader = new Scanner(System.in);
            String s;

            // Bucle de envío y recibo de datos entre el chat y el cliente.
            while (Server.running) {
                // Recibimos datos
                while (in.available() > 0) {
                    s = (String) in.readObject();
                    System.out.println(s);
                }
                
                // Enviamos datos
                System.out.println("Enviar: ");
                s = reader.nextLine();
                out.writeObject(s);
            }
            
            out.close();
            in.close();
            localConn.close();
        } catch (IOException ex) {
            System.out.println("ERROR: Un error IO ha ocurrido en el cliente.");
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR: Un error ClassNotFound ha ocurrido en el cliente.");
        }
    }
}
