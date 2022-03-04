package Ejercicio3;

import java.net.*;
import java.io.*;

/**
 * Esta es la clase cliente. Va a realizar una conexión al servidor y va a
 * pedirle un fichero, el cual el Server le enviará. Una vez recibido el fichero
 * el cliente creará una copia de este en su sistema.
 *
 * Para ello usaremos dos clases: DataSend y DataReceive. DataSend se usará para
 * realizar la petición del archivo. Data Receive se usará para recibir el
 * archivo junto con sus datos.
 */
public class ClienteFichero {

    public static void main(String[] args) {
        // Declaración de variables
        ObjectInputStream dataIn;               // Flujo de entrada de objetos
        ObjectOutputStream dataOut;             // Flujo de salida de objetos

        MensajeDameFichero askFile;             // Objeto para pedir el archivo
        MensajeTomaFichero receivedFile = null; // Objeto para recibir el archivos

        File file;                              // Objeto File para el archivo
        FileOutputStream writer;                // Flujo de salida de datos

        Socket con = null;                      // Objeto para el Socket.

        // Asignación de variables
        askFile = new MensajeDameFichero("/home/alumno/Escritorio", "fichero.txt");
        try {
            con = new Socket("localhost", 6000);
        } catch (IOException ex) {
            System.out.println("ERROR: Ha ocurrido un error al realizar la conexión.");
            ex.printStackTrace();
        }

        // Envío de la petición
        try {
            // Enviamos la petición del archivo al Server
            dataOut = new ObjectOutputStream(con.getOutputStream());

            // Escribimos los datos en el flujo
            dataOut.writeObject(askFile);

            System.out.println("Petición de archivo enviada.");

        } catch (IOException ex) {
            System.out.println("ERROR: Ha ocurrido un error al enviar la petición.");
            ex.printStackTrace();
        }

        // Recibo del archivo
        try {
            System.out.println("Recibiendo archivo...");
            dataIn = new ObjectInputStream(con.getInputStream());

            // Leemos los datos del flujo
            Object obj = dataIn.readObject();

            // Comprobamos que se ha recibido un MensajeTomaFichero
            if (obj instanceof MensajeTomaFichero) {
                receivedFile = (MensajeTomaFichero) obj;
            } else if (obj instanceof String) {
                // Si no es un MensajeTomaFichero, el archivo no existe,
                // por lo que salimos del programa.
                System.out.println((String) obj);
                System.exit(0);
            }

            System.out.println("Archivo recibido.");
        } catch (IOException ex) {
            System.out.println("ERROR: Ha ocurrido un error al recibir el fichero.");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR: Ha ocurrido un error al convertir el objeto recibido"
                    + " a MensajeTomaFichero.");
            ex.printStackTrace();
        }

        // Creación de la copia del archivo
        file = new File(receivedFile.getPath() + "/" + receivedFile.getFile() + "_copia");
        try {
            file.createNewFile();
        } catch (IOException ex) {
            System.out.println("ERROR: Ha ocurrido un error al crear el fichero copia.");
            ex.printStackTrace();
        }

        // Escritura de datos en el archivo
        try {
            writer = new FileOutputStream(file);

            writer.write(receivedFile.getData());

            System.out.println("Copia finalizada: " + file.getAbsolutePath());

            writer.close();
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: Ha ocurrido un error al encontrar el fichero copia.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("ERROR: Ha ocurrido un error al escribir en el fichero copia.");
            ex.printStackTrace();
        }

        // Cierre de Sockets
        try {
            con.close();
        } catch (IOException ex) {
            System.out.println("ERROR: Ha ocurrido un error al cerrar el Socket.");
            ex.printStackTrace();
        }
    }
}
