using System;
using System.IO;
using System.Text;
using GestorGUI.Objetos;

namespace GestorGUI {
public class DataManager {
    /*
     * Esta clase se va a encargar de escribir y leer datos de un archivo .dat.
     * Los datos que se van a leer y escribir son registros, con distinto tamaño.
     * El tamaño de los registros es concretamente de 230 bytes en el caso de los videojuegos
     * y de 190 bytes en el caso de las películas.
     *
     * Para escribir, se pasarán los ejemplares de una lista a un array de bytes, y se escribirán
     * en un archivo .dat.
     * 
     * Se extraerán estos datos en arrays con su tamaño, y se añadirán a una lista dada
     * como Ejemplares, usando Ejemplar.Parse(byte[]).
     */
    
    public static void LeerDatos(Fondo fondo) {
        /*
         * Los datos van a estar almacenados por líneas, separados por un salto de línea.
         * Vamos a almacenar todos los datos del archivo en un array de bytes, lo vamos a convertir
         * a una cadena, y la vamos a convertir en una lista, dividiendo por saltos de línea.
         * Cada elemento de la lista será un ejemplar. Podemos llamar al método Parse de Ejemplar
         * para convertir la string en un Ejemplar.
         */
        try {
            // Archivo del que se va a leer
            using FileStream lector = new FileStream(
                "C:\\Users\\David\\Documents\\2-DAM\\DEINT\\C#\\GestorClub\\GestorGUI\\Datos\\archivo.dat",
                FileMode.Open);

            byte[] dataB = new byte[lector.Length];
            lector.Read(dataB);
            string data = Encoding.UTF8.GetString(dataB);

            foreach (var linea in data.Split(Environment.NewLine, StringSplitOptions.RemoveEmptyEntries)) {
                Console.WriteLine(linea);
                fondo.Add(Ejemplar.Parse(linea));
            }
        }
        catch (FileNotFoundException) {
            // En el caso de que no exista el archivo
            Console.WriteLine("\t\tSystem: Error al cargar los archivos. La lista estará vacía.");
        }
    }

    public static void EscribirDatos(Fondo datos) {
        try {
            // Archivo al que se va a escribir.
            using FileStream escritor = new FileStream(
                "C:\\Users\\David\\Documents\\2-DAM\\DEINT\\C#\\GestorClub\\GestorGUI\\Datos\\archivo.dat",
                FileMode.Create);
            
                foreach (Ejemplar e in datos.GetEjemplares()) {
                    // Si e no es null lo escribimos en el archivo
                    if (e != null) {
                        if (e is Pelicula p) {
                            escritor.Write(p.ToByteArray());
                            escritor.Write(Encoding.UTF8.GetBytes(Environment.NewLine));                            
                        }

                        else if (e is Videojuego v) {
                            escritor.Write(v.ToByteArray());
                            escritor.Write(Encoding.UTF8.GetBytes(Environment.NewLine));
                        }
                    }
                }
        }
        catch (FileNotFoundException) {
            // En el caso de que no exista el archivo
            Console.WriteLine("\t\tSystem: Error al escribir los archivos. La lista estará vacía.");
        }
    }
    
}
}