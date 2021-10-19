#nullable enable
using System;
using System.IO;

/**
 * Programa A:
 * Un programa que pregunte un nombre de fichero y muestre en pantalla el contenido de ese fichero,
 * haciendo una pausa después de cada 25 líneas, para que dé tiempo a leerlo.
 * Cuando el usuario pulse la tecla Intro, se mostrarán las siguientes 25 líneas,
 * y así hasta que termine el fichero.
 */

// ReSharper disable once CheckNamespace
namespace ActividadFicheros {
class Programa1 {
    static void Main(string[] args) {
        // Pedir el nombre del archivo
        String? archivo = PedirNombre();
        // Leer el contenido del archivo
        LeerArchivo(archivo);
    }

    private static String? PedirNombre() {
        String? archivo = null;

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

    private static void LeerArchivo(String? archivo) {
        if (archivo != null) {
            using StreamReader lector = new StreamReader(archivo);
            String? linea = lector.ReadLine();

            /**
         * Las variables longitud y pag son para mostrar el número de línea en la que se encuentra el
         * programa en cada momento.
         * Además, modificando la variable longitud, podemos modificar la cantidad de líneas a mostrar
         * en cada página.
         */
            int cont = 0, longitud = 25, pag = 0;

            while (linea != null) {
                // Código para mostrar la línea
                Console.WriteLine(((pag * longitud) + (cont + 1)) + "    " + linea);
                cont++;

                if (cont == longitud) {
                    Console.Write("\nPulse espacio para continuar...");
                    Console.ReadKey();
                    Console.WriteLine("");
                    cont = 0;
                    pag++;
                }

                linea = lector.ReadLine();
            }
        }
    }
}
}