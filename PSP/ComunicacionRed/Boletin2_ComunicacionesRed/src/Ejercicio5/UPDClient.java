package Ejercicio5;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UPDClient {
    public static void main(String[] args) throws IOException {
        int port = 12345;
        InetAddress address = InetAddress.getByName("192.168.1.45"); // IP de mi portatil
        byte[] msg = ("Enviando un saludo desde PC").getBytes();
        
        DatagramPacket send = new DatagramPacket(msg, msg.length, address, port);
        DatagramSocket socket = new DatagramSocket(34567);
        socket.connect(address, port);
        
        System.out.println("Destino:            " + address.getHostAddress());
        System.out.println("Puerto local:       " + socket.getLocalPort());
        System.out.println("Puerto de destino:  " + socket.getPort());
        
        socket.send(send);
        socket.close();
    }
}
