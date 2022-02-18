package Ejercicio4;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    /**
     * Cliente UDP
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int sendPort = 12345;
        int myPort = 34567;
        InetAddress address = InetAddress.getByName("192.168.1.45"); // IP de mi portatil
        Scanner reader = new Scanner(System.in);
        Numeros num;
        int n;

        DatagramPacket pack;
        DatagramSocket socket = new DatagramSocket(myPort);
        socket.connect(address, sendPort);

        ByteArrayOutputStream bos;
        ObjectOutputStream out;
        ByteArrayInputStream bis;
        ObjectInputStream in;

        // Bucle
        do {
            System.out.println("Introduzca un número: ");
            n = reader.nextInt();

            // Creamos el objeto, el datagrama, y lo enviamos
            num = new Numeros(n);

            bos = new ByteArrayOutputStream();
            out = new ObjectOutputStream(bos);
            out.writeObject(num);
            byte[] data = bos.toByteArray();
            out.close();
            bos.close();

            pack = new DatagramPacket(data, data.length, address, sendPort);
            System.out.println("Enviando datos: " + num);
            socket.send(pack);

            // Recibimos el paquete y extraemos los datos
            socket.receive(pack);
            bis = new ByteArrayInputStream(pack.getData());
            in = new ObjectInputStream(bis);
            num = (Numeros) in.readObject();
            System.out.println("Datos recibidos: " + num);
            in.close();
            bis.close();

        } while (n > 0);

        System.out.println("Terminado el programa...");

        // Cerramos el socket
        socket.close();
    }
}
