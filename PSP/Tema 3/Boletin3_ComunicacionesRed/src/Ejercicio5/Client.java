package Ejercicio5;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    /**
     * Cliente TCP
     */
    public static void main(String[] args) throws IOException {
        try {
            Socket localConn = new Socket("DESKTOP-L337VTB", 6000); // portatil
            
            System.out.println("Conexión realizada.");

            // Enviar una cadena
            Scanner reader = new Scanner(System.in);
            while (Server.running) {
                System.out.println("Introduzca una cadena a enviar: ");
                String s = reader.nextLine();
                ObjectOutputStream out = new ObjectOutputStream(localConn.getOutputStream());
                out.writeObject(s);

                // Recibimos datos del server
                ObjectInputStream in = new ObjectInputStream(localConn.getInputStream());
                s = (String) in.readObject();

                System.out.println("Cadena recibida: " + s);
                out.close();
                in.close();
            }
            
            localConn.close();
        } catch (IOException ex) {
            System.out.println("ERROR: Un error IO ha ocurrido en el cliente.");
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR: Un error ClassNotFound ha ocurrido en el cliente.");
        }
    }
}
