package Ejercicio1;

import java.net.*;
import java.io.*;

/**
 * Esta clase va a recibir una conexión de un cliente UDP. A continuación se
 * ejecutará un bucle: Mientras el número recibido sea mayor que 0: -Recibir un
 * número. -Calcular el cuadrado y el factorial del número. -Modificar el
 * objeto. -Enviar el objeto de vuelta al cliente de donde provino.
 *
 * Controlar errores: -Errores en el envío y recibo de datos.
 */
public class ReciboNumeroUDP {

    public static void main(String[] args) {
        ByteArrayOutputStream baos;
        ByteArrayInputStream bais;

        try {
            // Vamos a crear el Socket UDP y un Datagrama para extraer los datos.
            byte[] buffer = new byte[2048];                     // Array de bytes como búffer.
            DatagramSocket server = new DatagramSocket(6000);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            int n = 1;
            Numeros num = null;
            
            do {
                // Recibimos el paquete
                System.out.println("Esperando un paquete...");
                server.receive(packet);
                System.out.println("Paquete recibido.");
                // Extraemos los datos con la ayuda de flujos de bytes y objects.
                bais = new ByteArrayInputStream(buffer);
                num = (Numeros) (new ObjectInputStream(bais)).readObject();
                n = num.getNumero();
                // Si se ha enviado un número menor o igual que 0 se sale del bucle.
                if (n <= 0) {
                    break;
                }
                System.out.println("Realizando modificaciones al Número...");
                // Calculamos el cuadrado
                num.setCuadrado(n * n);
                // Calculamos el factorial
                num.setfactorial(getFactorialOf(n));
                System.out.println(num);
                
                // Reenviamos el paquete de vuelta
                System.out.println("Reenviando el paquete...");
                // Primero convertimos el objeto de vuelta a un array de bytes
                // Creamos un flujo de salida de array de bytes con la longitud del búffer.
                baos = new ByteArrayOutputStream(buffer.length);
                // Creamos un Object output stream para convertir el objeto a bytes.
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                // Escribimos el objeto
                oos.writeObject(num);
                // Creamos un flujo de lectura para leer los bytes que hay en el flujo de salida
                buffer = baos.toByteArray();
                // Enviamos el paquete con los nuevos datos
                packet.setData(buffer, 0, buffer.length);
                packet.setAddress(InetAddress.getByName("localhost"));
                packet.setPort(6001);
                server.send(packet);
                System.out.println("Paquete reenviado.");
                System.out.println("-------------------");
            } while (true);
            
            System.out.println("Servidor apagado.");
            server.close();
        } catch (SocketException ex) {
            System.out.println("ERROR: Ha ocurrido un error al inicializar el DatagramSocket.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("ERROR: Ha ocurrido un error al recibir el DatagramPacket.");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR: Ha ocurrido un error al convertir Object a un Numeros.");
            ex.printStackTrace();
        }
    }

    /**
     * Método para calcular el factorial de un número usando un bucle.
     */
    public static long getFactorialOf(int n) {
        long fact = n;

        for (int i = n - 1; i > 0; i--) {
            fact *= i;
        }

        return fact;
    }
}
