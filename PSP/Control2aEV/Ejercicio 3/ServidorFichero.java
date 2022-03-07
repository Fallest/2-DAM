package Ejercicio3;

import java.net.*;
import java.io.*;

/**
 * Esta es la clase Server. Va a recibir una petición de un cliente para que le
 * envíe un archivo. Va a leer los datos de este archivo, los va a meter en un
 * objeto MensajeTomaFichero, y se lo va a enviar al cliente.
 */
public class ServidorFichero {

    public static void main(String[] args) {
        // Declaración de variables
        ObjectInputStream dataIn;               // Flujo de entrada de objetos
        ObjectOutputStream dataOut;             // Flujo de salida de objetos

        MensajeDameFichero askFile = null;      // Objeto para recibir la petición
        MensajeTomaFichero sendFile;            // Objeto para enviar el archivo

        File file;                              // Objeto File para el archivo
        FileInputStream reader;                 // Flujo de entrada de datos

        Socket client = null;                   // Objeto para el socket del cliente
        ServerSocket server = null;             // Objeto para el socket del server

        // Asignación de variables
        try {
            server = new ServerSocket(6000);
        } catch (IOException ex) {
            System.out.println("ERROR: Ha ocurrido un error al crear el socket para el servidor.");
            ex.printStackTrace();
        }

        // Esperamos a que un cliente realice una conexión
        try {
            System.out.println("Esperando a un cliente...");

            client = server.accept();

            System.out.println("Cliente conectado.");
        } catch (IOException ex) {
            System.out.println("ERROR: Ha ocurrido un error al realizar la conexión con el cliente.");
            ex.printStackTrace();
        }

        // Recibimos el MensajeDameFichero del cliente
        try {
            dataIn = new ObjectInputStream(client.getInputStream());

            // Extraemos el mensaje
            askFile = (MensajeDameFichero) dataIn.readObject();

            System.out.println("Petición de archivo recibida: "
                    + askFile.getPath() + "/" + askFile.getFile());
        } catch (IOException ex) {
            System.out.println("ERROR: Ha ocurrido un error al leer datos del cliente.");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR: Ha ocurrido un error al convertir Object a MensajeDameFichero.");
            ex.printStackTrace();
        }

        // Buscamos el fichero: Si existe extraemos los datos, si no, enviamos un
        // mensaje de error al cliente.
        file = new File(askFile.getPath() + "/" + askFile.getFile());
        if (file.exists()) {
            // Si existe creamos el objeto MensajeTomaFichero y lo enviamos
            try {
                dataOut = new ObjectOutputStream(client.getOutputStream());

                // Extraemos los datos del archivo y creamos el MensajeTomaFichero
                reader = new FileInputStream(file);
                sendFile = new MensajeTomaFichero(
                        askFile.getPath(),
                        askFile.getFile(),
                        reader.readAllBytes());

                // Enviamos el MensajeTomaFichero
                dataOut.writeObject(sendFile);

                System.out.println("Fichero enviado.");

            } catch (IOException ex) {
                System.out.println("ERROR: Ha ocurrido un error al enviar el Mensaje al cliente.");
                ex.printStackTrace();
            }
        } else {
            // Si no existe enviamos un mensaje de error

            try {
                dataOut = new ObjectOutputStream(client.getOutputStream());

                dataOut.writeObject("El fichero no existe.");

                System.out.println("El fichero que el cliente ha pedido no existe.");

            } catch (IOException ex) {
                System.out.println("ERROR: Ha ocurrido un error al enviar datos al cliente.");
                ex.printStackTrace();
            }
        }

        // Apagar el ServidorFichero
        System.out.println("Apagando servidor...");
        try {
            server.close();
            client.close();
        } catch (IOException ex) {
            System.out.println("ERROR: Ha ocurrido un error al cerrar los Sockets.");
            ex.printStackTrace();
        }
    }
}
