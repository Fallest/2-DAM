package Ejercicio4;

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

            Numeros num;
            int n;

            DatagramPacket pack;
            DatagramSocket socket = new DatagramSocket(myPort);

            ByteArrayOutputStream bos;
            ObjectOutputStream out;
            ByteArrayInputStream bis;
            ObjectInputStream in;

            // Bucle
            do {
                // Recibimos el paquete
                pack = new DatagramPacket(data, data.length);
                System.out.println("Esperando datos...");

                socket.receive(pack);

                // Extraemos los datos del paquete
                bis = new ByteArrayInputStream(pack.getData());
                in = new ObjectInputStream(bis);
                num = (Numeros) in.readObject();
                n = num.getNumero();
                in.close();
                bis.close();
                System.out.println("Datos recibidos: " + num);

                // Modificamos los datos
                num.setCuadrado(n * n);
                num.setCubo(n * n * n);
                System.out.println("Datos a enviar: " + num);
                bos = new ByteArrayOutputStream();
                out = new ObjectOutputStream(bos);
                out.writeObject(num);
                data = bos.toByteArray();
                out.close();
                bos.close();

                // Enviamos el paquete
                // Tenemos que usar la dirección del paquete recibido, para enviarlo de vuelta
                socket.connect(pack.getAddress(), sendPort);
                pack = new DatagramPacket(data, data.length, pack.getAddress(), sendPort);
                socket.send(pack);

            } while (n > 0);

            System.out.println("Terminado el programa...");

            socket.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
