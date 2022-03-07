package Ejercicio1;

import java.net.*;
import java.io.*;
import java.util.Scanner;


/**
 * Esta clase va a realizar una conexión mediante UDP a un servidor.
 * A continuación se ejecutará un bucle:
 *  Mientras el número introducido por teclado sea mayor que 0:
 *      -Se crea un objeto Números.
 *      -Se envía el objeto al servidor.
 *      -Se recibe un objeto del servidor.
 *      -Se muestra por pantalla los datos del objeto recibido.
 * 
 * Controlar errores:
 *      -Si el servidor no está disponible.
 *      -Errores en la entrada de datos.
 *      -Errores en el envío y recibo de datos.
 */

public class EnvioNumeroUDP {
    public static void main(String[] args) {
        try {
            // Declaración e inicialización de variables
            byte[] data = new byte[2048];
            int port = 6000;
            InetAddress address = InetAddress.getByName("localhost");
            int n = 1;
            Scanner reader = new Scanner(System.in);
            Numeros num;
            
            // Datagrama y servidor
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            DatagramSocket socket = new DatagramSocket(6001);
            
            // Para saber si el servidor está abierto o no, vamos a intentar
            // crear un DatagramSocket en el mismo puerto que el server.
            // Si no podemos conectarnos, está encendido y podemos enviar y recibir
            // objetos.
            // Si no, terminamos el proceso.
            try {
                DatagramSocket s = new DatagramSocket(6000);
                System.out.println("El servidor está apagado.");
            } catch(SocketException ex) {
                // Si se encuentra una excepción significa que el server está abierto.
                while (true) {
                    // Leemos el número
                    System.out.println("Introduzca un número: ");
                    n = reader.nextInt();


                    // Creamos el objeto Numeros.
                    num = new Numeros(n);
                    // Convertimos el objeto a un byte array
                    ByteArrayOutputStream baos = new ByteArrayOutputStream(data.length);
                    // Creamos un Object output stream para convertir el objeto a bytes.
                    ObjectOutputStream oos = new ObjectOutputStream(baos);
                    // Escribimos el objeto
                    oos.writeObject(num);
                    // Creamos un flujo de lectura para leer los bytes que hay en el flujo de salida
                    data = baos.toByteArray();

                    // Asignamos esos datos al paquete y lo enviamos
                    packet.setData(data, 0, data.length);
                    socket.send(packet);

                    // Comprobamos que no es menor o igual que 0
                    if (n <= 0)
                        break;

                    // Ahora recibimos el paquete de vuelta
                    socket.receive(packet);
                    // Extraemos los datos con la ayuda de flujos de bytes y objects.
                    ByteArrayInputStream bais = new ByteArrayInputStream(data);
                    num = (Numeros) (new ObjectInputStream(bais)).readObject();
                    // Mostramos los datos del objeto recibido
                    System.out.println(num.toString());
                    System.out.println("---------------------");

                }
            }
            
            System.out.println("Cliente apagado.");
            
            socket.close();
        } catch (UnknownHostException ex) {
            System.out.println("ERROR: La dirección Inet indicada no existe.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("ERROR: Ha ocurrido un error IOException en el Cliente.");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR: Ha ocurrido un error al convertir Object a un Numeros.");
            ex.printStackTrace();
        }
        
    }
}
