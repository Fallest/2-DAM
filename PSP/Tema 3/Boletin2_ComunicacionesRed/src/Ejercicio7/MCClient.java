package Ejercicio7;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MCClient {

    public static void main(String[] args) {
        try {
            int port = 12345;
            InetAddress mcGroup = InetAddress.getByName("225.0.0.1");
            MulticastSocket socket = new MulticastSocket(port);
            byte[] buffer = new byte[2048];

            // Nos unimos al grupo
            socket.joinGroup(mcGroup);

            while (true) {
                DatagramPacket receive = new DatagramPacket(buffer, buffer.length);

                // Recibir y mostrar el mensaje
                socket.receive(receive);
                String data = new String(receive.getData());
                if ("*".equals(data)) {
                    break;
                }
                System.out.println("Multicast: " + data);
            }
            
            // Salimos del grupo y cerramos el socket
            socket.leaveGroup(mcGroup);
            socket.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
