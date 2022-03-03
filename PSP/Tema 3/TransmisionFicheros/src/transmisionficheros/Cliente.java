package transmisionficheros;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {
    /**
     * El cliente le pide un fichero al server al que se conecta.
     * Recbie el fichero del Server y lo almacena usando la misma
     * ruta en la que etaba almacenado el fichero original.
     * Le añade "_copia" al nombre del archivo original.
     * 
     * Se crearán dos clases para el envío y recibo de ficheros.
     * DataReceive es el equivalente a la clase MensajeDameFichero.
     * DataSend es el equivalente a la clase MensajeTomaFichero.
     */
    public static void main(String[] args) {
        int port = 6000;
        
        try {
            // Declaración de variables.
            ObjectOutputStream out;
            ObjectInputStream in;
            DataReceive dataOut;
            DataSend dataIn;
            byte[] data;
            
            // Primero realizamos la conexión al Server
            Socket con = new Socket("localhost", port);
            System.out.println("Conexión establecida.");
            
            // Creamos y enviamos el objeto DataReceive
            dataOut = new DataReceive(
                    "fichero.txt",
                    "C:\\Users\\David\\Documents\\2-DAM\\PSP\\Tema 3\\TransmisionFicheros\\");
            out = new ObjectOutputStream(con.getOutputStream());
            out.writeObject(dataOut);
            
            
            System.out.println("Fichero " + dataOut.getPath() + dataOut.getFile() 
                    + " pedido.");
            
            // Ahora esperamos a que el server nos envíe el objeto DataSend con
            // los datos del fichero.
            in = new ObjectInputStream(con.getInputStream());
            dataIn = (DataSend) in.readObject();
            
            // Creamos el nuevo fichero con los datos recibidos
            File file = new File(dataIn.getPath() + "_copia");
            file.createNewFile();
            // FOS para escribir los datos en el archivo.
            FileOutputStream fos = new FileOutputStream(file); 
            fos.write(dataIn.getData());
            fos.close();
            System.out.println("Archivo creado.");
            
            // Cierre de los Sockets
            con.close();
        } catch (IOException ex) {
            System.out.println("ERROR: IOException en Cliente.main()");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR: ClassNotFoundException en Cliente.main()");
            ex.printStackTrace();
        }
    }
}
