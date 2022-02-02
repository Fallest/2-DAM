package Ejercicio6;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UPDClient {

    public static void main(String[] args) throws IOException {
        int port = 12345;
        InetAddress address = InetAddress.getByName("192.168.1.45"); // IP de mi portatil

        System.out.println("Introduce un mensaje para enviarlo al servidor:\n");
        byte[] msg
                = (new BufferedReader(new InputStreamReader(System.in)))
                        .readLine()
                        .getBytes();

        DatagramPacket send = new DatagramPacket(msg, msg.length, address, port);
        DatagramSocket socket = new DatagramSocket(34567);

        socket.send(send);
        socket.close();
    }
}
