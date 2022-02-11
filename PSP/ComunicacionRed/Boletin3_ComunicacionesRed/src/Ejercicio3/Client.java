package Ejercicio3;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int sendPort = 12345;
        int myPort = 34567;
        InetAddress address = InetAddress.getByName("192.168.1.45"); // IP de mi portatil
        
        // Creamos el objeto y extraemos el array de bytes
        Persona p = new Persona("Maribel", 24);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(p);
        byte[] data = bos.toByteArray();
        out.close();
        
        // Creamos el paquete a enviar
        DatagramPacket pack = new DatagramPacket(data, data.length, address, sendPort);
        DatagramSocket socket = new DatagramSocket(myPort);
        socket.connect(address, sendPort);
        
        // Enviamos el paquete
        System.out.println("Enviando datos: " + p);
        socket.send(pack);
        
        // Recibimos el paquete y extraemos los datos
        socket.receive(pack);
        ByteArrayInputStream bis = new ByteArrayInputStream(pack.getData());
        ObjectInputStream in = new ObjectInputStream(bis);
        p = (Persona) in.readObject();
        in.close();
        System.out.println("Datos recibidos: " + p);
        
        // Cerramos el socket
        socket.close();
    }
}
