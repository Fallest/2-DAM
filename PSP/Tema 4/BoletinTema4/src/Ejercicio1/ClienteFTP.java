package Ejercicio1;

import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 * Cliente FTP para conectarnos a ftp.rediris.es, usando un acceso anónimo.
 * Usuario y clave = anonymous. Mostrar la lista de ficheros del directorio
 * actual. (método listFiles()).
 */
public class ClienteFTP {

    public static void main(String[] args) {
        FTPClient cliente = new FTPClient();
        String url = "ftp.rediris.es",
                usuario = "anonymous",
                clave = "anonymous";

        System.out.println("Relizando conexión a: " + url);

        try {
            // Realizar la conexión a la URL
            cliente.connect(url);
            cliente.enterLocalPassiveMode();

            // Realizar la conexión
            if (cliente.login(usuario, clave)) {
                System.out.println("Sesión iniciada.");
            } else {
                System.out.println("Credenciales no válidos.");
                cliente.disconnect();
                System.exit(1);
            }
            // Mostrar el directorio actual
            System.out.println("Directorio: " + cliente.printWorkingDirectory());
            // Extraer la lista de archivos
            FTPFile[] archivos = cliente.listFiles();
            String[] tipos = {"Archivo", "Directorio", "Enlace simbólico"};

            for (FTPFile f : archivos) {
                System.out.println("---- " + f.getName()
                        + " | " + tipos[f.getType()]);
            }

            if (cliente.logout()) {
                System.out.println("Cerrando sesión...");
            } else {
                System.out.println("Error cerrando sesión.");
            }

            cliente.disconnect();
            System.out.println("Desconectado.");

        } catch (IOException ex) {
            System.out.println("IOException: " + ex);
        }
    }
}
