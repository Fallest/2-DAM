package Ejercicio2;

import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 * Realiza una conexión a los siguientes servidores, mostrando, para cada uno,
 * un árbol de los directorios de profundidad 1: - ftp.rediris.es - ftp.uv.es -
 * ftp.freebsd.org - ftp.dit.upm.es
 */
public class ClienteFTP {

    private static String[] types = {"Archivo", "Directorio", "Enlace simbólico"};

    public static void main(String[] args) {
        FTPClient client;
        String[] servers = {
            "ftp.rediris.es",
            "ftp.uv.es",
            "ftp.freebsd.org",
            "ftp.dit.upm.es"
        };
        String usr = "anonymous",
                pw = "anonymous";

        for (String url : servers) {
            System.out.println("Relizando conexión a: " + url);
            client = new FTPClient();

            try {
                // Realizar la conexión a la URL
                client.connect(url);
                client.enterLocalPassiveMode();

                // Realizar la conexión
                if (client.login(usr, pw)) {
                    System.out.println("Sesión iniciada.");
                } else {
                    System.out.println("Credenciales no válidos.");
                    client.disconnect();
                    System.exit(1);
                }

                System.out.println("");
                showTree(client, 2, 2);
                System.out.println("");

                if (client.logout()) {
                    System.out.println("Cerrando sesión...");
                } else {
                    System.out.println("Error cerrando sesión.");
                }

                client.disconnect();
                System.out.println("Desconectado.");
                System.out.println("-------------------------------------------");

            } catch (IOException ex) {
                System.out.println("IOException: " + ex);
            }
        }
    }

    /**
     * Método que muestra el árbol de directorios de un cliente. Es recursivo,
     * se llama a sí mismo para cada directorio que encuentra.
     *
     * @param client El cliente
     * @param depth La profundidad del árbol de directorios (0 based).
     */
    private static void showTree(FTPClient client, int depth, int originalDepth) throws IOException {
        FTPFile[] files;

        if (depth > 0) {
            System.out.println(client.printWorkingDirectory()
                    + " | Directorio\n"
                    + "---- Profundidad " + (originalDepth - depth));
            files = client.listFiles();

            // Iteramos por cada archivo encontrado
            for (FTPFile file : files) {
                String n = file.getName();
                int t = file.getType();

                // Hay que tener cuidado de no caer en un bucle infinito con "." y ".."
                if (!".".equals(n) && !"..".equals(n)) {
                    // Si es un archivo o un enlace simbólico, lo mostramos
                    if (t == 0 || t == 2) {
                        System.out.println(client.printWorkingDirectory()
                                + "/".repeat(depth - originalDepth == 0 ? 0 : 1)
                                + file.getName()
                                + " | " + types[t]);
                    } // Si es un directorio, entramos en él y mostramos sus datos
                    else {
                        // Entramos en el directorio
                        client.changeWorkingDirectory(n);
                        // Mostramos su árbol de directorios (será de una profundidad
                        // de "i" niveles menor a la actual).
                        showTree(client, depth - 1, originalDepth);
                        // Después volvemos al directorio anterior
                        client.changeWorkingDirectory("../");
                    }
                }
            }
        } else {
            System.out.println(client.printWorkingDirectory() + " | Directorio");

        }

    }
}
