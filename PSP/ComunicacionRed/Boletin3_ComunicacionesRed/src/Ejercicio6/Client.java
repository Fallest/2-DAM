package Ejercicio6;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    /**
     * Cliente TCP
     */
    public static void main(String[] args) throws IOException {
        try {
            // Socket localConn = new Socket("DESKTOP-L337VTB", 6000); // portatil
            Socket localConn = new Socket("DESKTOP-A2NJM1I", 6000);    // PC de casa
            System.out.println("Conexión realizada.");

            // Recibimos datos del server
            ObjectInputStream in = new ObjectInputStream(localConn.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(localConn.getOutputStream());
            System.out.println(in.readUTF());

            // Bucle de ejecución
            Scanner reader = new Scanner(System.in);

            while (!Server.acierto) {
                // Enviar una cadena
                System.out.println("Introduzca un número: ");
                int n = reader.nextInt();

                out.writeObject(n);
                
                // Recibimos datos
                System.out.println((String) in.readObject());
            }

            if (in.available() != 0)
                System.out.println((String) in.readObject());
            
            System.out.println("Cerrando conexión...");
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
