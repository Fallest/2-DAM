package Ejercicio6;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UPDServer {

    public static void main(String[] args) {
        try {
            int port = 12345;
            InetAddress address = InetAddress.getLocalHost();
            byte[] buffer = new byte[2048];

            DatagramPacket receive = new DatagramPacket(buffer, buffer.length);
            System.out.println("Esperando un datagrama...");

            DatagramSocket socket = new DatagramSocket(12345);
            socket.receive(receive);

            String data = new String(receive.getData());

            // Contar las veces que aparece la letra "a"
            int times = 0;
            for (char c : data.toCharArray()) {
                if (c == 'a') {
                    times++;
                }
            }

            System.out.println("Cantidad de letras \"a\": " + times);

            socket.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
