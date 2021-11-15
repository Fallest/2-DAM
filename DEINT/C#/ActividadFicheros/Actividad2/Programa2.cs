using System;
using System.IO;

/**
 * Programa B:
 * Haz un programa que duplique un fichero, copiando todo su contenido a un nuevo
 * fichero, byte a byte. El nombre del fichero de salida se tomará del nombre del
 * fichero de entrada, al que se añadirá ".out".
 */

namespace Actividad2 {
class Program {
    static void Main(string[] args) {
        // Pedir el nombre del archivo a copiar
        String archivoOriginal = PedirNombre();
        // Generamos la copia
        CrearCopia(archivoOriginal);
    }

    private static void CrearCopia(string archivoOriginal) {
    /*
    Cambiar la creación del archivocopia.
    
    */
    
        /**
         * 1º: Va a crear el archivo para la copia.
         * 2º: Va a crear dos streams: uno para leer del original, otro para escribir en la copia
         * 3º: En un bucle, va a copiar los bytes del original a la copia.
         */

        // Creamos la cadena para el archivo copia.
        int posicionUltimoPunto = archivoOriginal.LastIndexOf(".");

        String archivoCopia = archivoOriginal.Substring(0, posicionUltimoPunto) + ".out";

        // Creamos una variable para almacenar el byte que leemos
        int b;

        try {
            // Creamos los flujos para leer y escribir, con los modos adecuados para cada función
            using FileStream original = new FileStream(archivoOriginal, FileMode.Open);
            using FileStream copia = new FileStream(archivoCopia, FileMode.Create);

            // Bucle para leer Byte a Byte del archivo original y escribir en la copia
            do {
                // Leemos byte a byte, y lo escribimos en copia siempre que no sea -1
                if ((b = original.ReadByte()) != -1)
                    // Tenemos que hacer un casting a byte porque para poder leer el -1 tenemos que usar un int.
                    copia.WriteByte((byte) b);
            } while (b != -1);

            Console.WriteLine("\n\nProceso terminado con éxito.");
        }
        catch (Exception e) {
            Console.WriteLine("Error: " + e.Message);
        }
    }

    private static String PedirNombre() {
        String archivo = "";

        try {
            Console.Write("Introduzca la ruta al archivo: ");
            archivo = Console.ReadLine();
        }
        catch (IOException ex) {
            Console.WriteLine("Se han introducido caracteres no válidos. \nSaliendo del programa...");
            System.Environment.Exit(-1);
        }

        return archivo;
    }
}
}