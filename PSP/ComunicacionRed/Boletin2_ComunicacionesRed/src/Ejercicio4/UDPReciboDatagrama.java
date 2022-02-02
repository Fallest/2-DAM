package Ejercicio4;

import java.io.IOException;
import java.net.*;

public class UDPReciboDatagrama {
    public static void main(String[] args) throws IOException {
        int port = 12345;
        InetAddress address = InetAddress.getLocalHost();
        byte[] buffer = new byte[2048];
        
        DatagramPacket receive = new DatagramPacket(buffer, buffer.length);
        System.out.println("Esperando un datagrama...");
        
        DatagramSocket socket = new DatagramSocket(12345);
        socket.receive(receive);
        
        int recByte = receive.getLength();
        String data = new String(receive.getData());
        
        System.out.println("Bytes:              " + recByte);
        System.out.println("Contenido:          " + data.trim());
        System.out.println("Puerto de origen:   " + receive.getPort());
        System.out.println("IP de origen:       " + receive.getAddress().getHostAddress());
        System.out.println("Puerto de destino:  " + socket.getLocalPort());
        
        socket.close();
    }
}
