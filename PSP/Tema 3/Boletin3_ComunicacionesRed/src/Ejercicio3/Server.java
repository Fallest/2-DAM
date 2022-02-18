package Ejercicio3;

import java.io.*;
import java.net.*;

public class Server {
    /**
     * Server UDP
     */
    public static void main(String[] args) throws ClassNotFoundException {
        try {
            int sendPort = 34567;
            int myPort = 12345;
            byte[] data = new byte[1024];
            
            // Recibimos el paquete
            DatagramPacket pack = new DatagramPacket(data, data.length);
            System.out.println("Esperando datos...");

            DatagramSocket socket = new DatagramSocket(myPort);
            socket.receive(pack);

            // Extraemos los datos del paquete
            ByteArrayInputStream bis = new ByteArrayInputStream(pack.getData());
            ObjectInputStream in = new ObjectInputStream(bis);
            Persona p = (Persona) in.readObject();
            in.close();
            System.out.println("Datos recibidos: " + p);
            
            // Modificamos los datos
            p.setEdad(25);
            System.out.println("Datos a enviar: " + p);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(p);
            data = bos.toByteArray();
            out.close();
            
            // Enviamos el paquete
            // Tenemos que usar la dirección del paquete recibido, para enviarlo de vuelta
            socket.connect(pack.getAddress(), sendPort);
            pack = new DatagramPacket(data, data.length, pack.getAddress(), sendPort);
            socket.send(pack);

            socket.close();
        } catch(IOException ex) {
            System.out.println(ex);
        }
    }
}
