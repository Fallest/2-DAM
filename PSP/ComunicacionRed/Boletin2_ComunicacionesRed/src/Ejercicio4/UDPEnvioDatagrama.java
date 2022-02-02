package Ejercicio4;

import java.io.IOException;
import java.net.*;

public class UDPEnvioDatagrama {
    public static void main(String[] args) throws IOException {
        int port = 12345;
        InetAddress address = InetAddress.getLocalHost();
        byte[] msg = ("Enviando un saludo de David").getBytes();
        
        DatagramPacket send = new DatagramPacket(msg, msg.length, address, port);
        DatagramSocket socket = new DatagramSocket(34567);
        socket.send(send);
        socket.close();
    }
}
