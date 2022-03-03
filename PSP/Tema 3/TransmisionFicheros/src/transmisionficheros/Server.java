package transmisionficheros;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Server {

    /**
     * Esta clase va a estar a la escucha, esperando a que se conecte un
     * cliente. Cuando se conecte un cliente, el Server va a esperar a que éste
     * le diga qué fichero quiere recibir. Una vez recibido el objeto
     * DataReceive con el nombre del archivo que quiere recibir, se creará un
     * objeto DataSend con los datos de ese fichero, y se le enviará el objeto
     * DataSend al cliente.
     */
    public static void main(String[] args) {
        int port = 6000;

        try {
            // Declaramos algunas variables y creamos un ServerSocket.
            ServerSocket server = new ServerSocket(port);
            Socket client;
            ObjectOutputStream out;
            ObjectInputStream in;
            DataReceive dataIn;
            DataSend dataOut = null;
            byte[] data;

            // Esperamos a la conexión de un cliente.
            System.out.println("El server está escuchando en el puerto "
                    + server.getLocalPort());
            client = server.accept();

            // Una vez se conecta algún cliente, quedamos a la espera del objeto
            // DataReceive.
            in = new ObjectInputStream(client.getInputStream());
            dataIn = (DataReceive) in.readObject();
            System.out.println("El cliente quiere el fichero "
                    + dataIn.getPath() + dataIn.getFile());

            // Creamos un objeto DataSend con el fichero que quiere el cliente
            // y se lo enviamos
            Path file = Paths.get(dataIn.getPath() + dataIn.getFile());
            if (file.toFile().exists()) {
                // Si existe el archivo creamos un objeto DataSend con los datos
                // del archivo.
                data = Files.readAllBytes(file);

                dataOut = new DataSend(
                        file.toFile().getName(),
                        file.toFile().getPath(),
                        data);

            } else {
                // Si el fichero no existe, enviamos un objeto con un mensaje
                // de error
                data = ("El archivo indicado no existe.").getBytes();

                dataOut = new DataSend(
                        file.toFile().getName(),
                        file.toFile().getPath(),
                        data);
            }

            // Enviamos el objeto
            out = new ObjectOutputStream(client.getOutputStream());
            out.writeObject(dataOut);

            // Esperamos 10 s a que el cliente lea el objeto y cerramos la conexión.
            client.setSoLinger(true, 10);

            // Cierre de Sockets.
            client.close();
            server.close();

        } catch (IOException ex) {
            System.out.println("ERROR: IOException en Server.main()");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR: ClassNotFoundException en Server.main()");
            ex.printStackTrace();
        }
    }
}
