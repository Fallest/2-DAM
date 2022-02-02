package Ejercicio7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MCServer {
    // Envía datos al grupo multicast.

    public static void main(String[] args) throws IOException {
        // Variables
        int port = 12345;
        InetAddress mcGroup = InetAddress.getByName("225.0.0.1");
        DatagramPacket send = new DatagramPacket(new byte[0], 0, mcGroup, port);
        MulticastSocket socket = new MulticastSocket();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        String aux;
        byte[] msg;

        do {
            System.out.println("Introduce un mensaje para enviarlo al servidor:\n");
            aux = reader.readLine();
            msg = aux.getBytes();

            // Comprobamos si se ha introducido "*"
            if ("*".equals(aux)) {
                break;
            }

            send.setData(msg, 0, msg.length);
            socket.send(send);
        } while (true);

        socket.close();

    }

}
