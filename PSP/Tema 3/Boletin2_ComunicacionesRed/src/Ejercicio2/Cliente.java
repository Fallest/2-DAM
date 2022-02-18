package Ejercicio2;

import java.io.*;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        Socket localConn;
        DataInputStream dataIn;
        DataOutputStream dataOut;
        
        try {
            // localConn = new Socket("DESKTOP-L337VTB", 6000); // portatil
            localConn = new Socket("DESKTOP-A2NJM1I", 6000);    // PC de casa
            System.out.println("Cliente: Conectado.");
            
            // Saludamos al servidor al conectarnos.
            dataOut = new DataOutputStream(localConn.getOutputStream());
            dataOut.writeUTF("Cliente: Saludos desde el cliente.");
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
