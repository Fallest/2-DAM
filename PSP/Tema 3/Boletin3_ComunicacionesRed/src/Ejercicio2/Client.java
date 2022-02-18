package Ejercicio2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    /**
     * Cliente TCP
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try {
            // Socket localConn = new Socket("DESKTOP-L337VTB", 6000); // portatil
            Socket localConn = new Socket("DESKTOP-A2NJM1I", 6000);    // PC de casa
            System.out.println("Conexión realizada.");
            
            Scanner reader = new Scanner(System.in);
            ObjectInputStream in;
            ObjectOutputStream out;
            
            int n;
            do {
                System.out.println("Introduzca un número: ");
                n = reader.nextInt();
                
                // Creamos el objeto y lo enviamos al servidor
                Numeros num = new Numeros(n);
                System.out.println("Objeto enviado: " + num);
                out = new ObjectOutputStream(localConn.getOutputStream());
                out.writeObject(num);
                
                // Leemos el objeto que nos envía
                in = new ObjectInputStream(localConn.getInputStream());
                num = (Numeros) in.readObject();
                System.out.println("Objeto recibido: " + num);
                
            } while (n > 0);
            
            System.out.println("Terminando conexión.");
            out.close();
            in.close();
            localConn.close();
        } catch (IOException ex) {
            System.out.println("ERROR: Un error IO ha ocurrido en el cliente.");
        }
    }
}
