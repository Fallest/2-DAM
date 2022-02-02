package Ejercicio3;

import java.io.*;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        Socket localConn;
        DataInputStream dataIn;
        DataOutputStream dataOut;
        
        try {
            localConn = new Socket("DESKTOP-L337VTB", 6000); // Mi portatil
            System.out.println("Cliente de David: Conectado.");
            
            // Saludamos al servidor al conectarnos.
            dataOut = new DataOutputStream(localConn.getOutputStream());
            dataOut.writeUTF("Cliente-David: Saludos desde el cliente.");
            dataOut.flush();
            
            // Escuchamos lo que el servidor tiene que decirnos
            dataIn = new DataInputStream(localConn.getInputStream());
            System.out.println(dataIn.readUTF());
        
            dataIn.close();
            dataOut.close();
            localConn.close();
        } catch(IOException ex) {
            System.out.println(ex);
        }
    }
}
