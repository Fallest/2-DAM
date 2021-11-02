using System;
using System.IO;
// ReSharper disable All

/**
 * Programa C:
 * Abre un fichero (cuyo nombre introducirá el usuario) y muestra si se trata de
 * un fichero bmp, gif o de otro tipo (mostrará por consola “NombreFichero: Tipo”
 * [En un fichero BMP los dos primeros bytes del fichero corresponden a una letra "B"
 * y una letra "M", respectivamente. En un fichero GIF sus tres primeros bytes son las letras G, I, F].
 */

namespace Actividad3 {
class Program {
    static void Main(string[] args) {
        // Pedimos el nombre del archivo
        string archivo = PedirNombre();
        // Mostramos el tipo de archivo por pantalla
        MostrarTipo(archivo);
    }

    private static void MostrarTipo(string archivo) {
        /**
         * Tenemos que leer los tres primeros Bytes del archivo.
         * Tenemos que convertir esos Bytes a caracteres.
         * Si son BM: es de tipo Bitmap
         * Si son GIF: es de tipo gif
         * En otro caso: son otro tipo
         */
        try {
            // Creamos el flujo de lectura
            using FileStream lector = new FileStream(archivo, FileMode.Open);
            // Leemos los 3 primeros bytes y los convertimos a cadena.
            byte[] bytes = new byte[] {(byte) lector.ReadByte(), (byte) lector.ReadByte(), (byte) lector.ReadByte()};
            string contenido = System.Text.Encoding.UTF8.GetString(bytes);
            //new String(lector.ReadByte().ToString() + lector.ReadByte().ToString() + lector.ReadByte().ToString())

            if (contenido.Substring(0, 2) == "BM")
                Console.WriteLine("El archivo es de tipo Bitmap.");
            else if (contenido == "GIF")
                Console.WriteLine("El archivo es de tipo GIF.");
            else
                Console.WriteLine("El archivo es de un tipo desconocido.");
        }
        catch (FileNotFoundException ex) {
            Console.WriteLine("El archivo no existe.");
        }
        catch (Exception ex) {
            Console.WriteLine("Final del archivo alcanzado. El tamaño del archivo es menor a 3 bytes.");
        }
    }

    private static string PedirNombre() {
        string archivo = "";

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