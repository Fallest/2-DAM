using System;
using System.IO;
using System.Linq;
using System.Text;
using GestorClub.Objetos;

namespace GestorClub {
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
    
    public static void LeerDatos(Fondo datos) {
        /*
         * Los datos de los ejemplares van a estar juntos unos detrás de otros.
         * Sabemos que los 10 primeros bytes de cada registro nos van a decir qué
         * tipo de ejemplar son.
         * Sabiendo qué ejempalr es, sabemos qué longitud tiene el registro:
         *  -190 en el caso de que los 10 primeros bytes sean "Pelicula  ".
         *  -230 en el caso de que sean "videojuego".
         */
        try {
            using FileStream lector = new FileStream(
                "C:\\Users\\David\\Documents\\2-DAM\\DEINT\\C#\\GestorClub\\GestorClub\\GestorClub\\Datos\\archivo.dat",
                FileMode.Open);

            bool exec = true;
            byte[] ejemplarB;

            byte[] data = new byte[lector.Length];
            
            lector.Read(data);
            string todo = Encoding.UTF8.GetString(data);
            Console.WriteLine(todo);

            foreach (var linea in todo.Split('\n')) {
                if (Encoding.UTF8.GetString(tipoB).Trim().ToLower().Equals("pelicula")) {
                    // Tenemos que leer los siguientes 180 bytes, convertirlo a un ejemplar
                    // y escribirlo en nuestros datos.
                    Console.WriteLine();
                    ejemplarB = new byte[95];
                    tipoB.CopyTo(ejemplarB, 0);
                     
                    try {
                        lector.Read(peliculaB);
                        peliculaB.CopyTo(ejemplarB, 10);
                    }
                    catch (Exception e) {
                        exec = false;
                    }
                    datos.Add(Ejemplar.Parse(ejemplarB));
                }
            }
            
            //while (exec) {
                /*
                // Leemos 10 bytes y clasificamos la info según lo que leamos
                lector.Read(tipoB);
                
                if (Encoding.UTF8.GetString(tipoB).Trim().ToLower().Equals("pelicula")) {
                     // Tenemos que leer los siguientes 180 bytes, convertirlo a un ejemplar
                     // y escribirlo en nuestros datos.
                     Console.WriteLine();
                     ejemplarB = new byte[95];
                     tipoB.CopyTo(ejemplarB, 0);
                     
                     try {
                         lector.Read(peliculaB);
                         peliculaB.CopyTo(ejemplarB, 10);
                     }
                     catch (Exception e) {
                         exec = false;
                     }
                     datos.Add(Ejemplar.Parse(ejemplarB));
                }
                else if (Encoding.UTF8.GetString(tipoB).Trim().ToLower().Equals("videojuego")) {
                    // Tenemos que leer los siguientes 220 bytes, convertirlo a un ejemplar
                    // y escribirlo en nuestros datos.
                    ejemplarB = new byte[115];
                    tipoB.CopyTo(ejemplarB, 0);
                     
                    try {
                        lector.Read(videojuegoB);
                        videojuegoB.CopyTo(ejemplarB, 10);
                    }
                    catch (Exception e) {
                        exec = false;
                    }
                    datos.Add(Ejemplar.Parse(ejemplarB));
                }
                else {
                    exec = false;
                }
                */
                
            //}
        }
        catch (FileNotFoundException ex) {
            // En el caso de que no exista el archivo
            Console.WriteLine("\t\tSystem: Error al cargar los archivos. La lista estará vacía.");
        }
    }

    public static void EscribirDatos(Fondo datos) {
        try {
            using FileStream escritor = new FileStream(
                "C:\\Users\\David\\Documents\\2-DAM\\DEINT\\C#\\GestorClub\\GestorClub\\GestorClub\\Datos\\archivo.dat",
                FileMode.Create);
            
                foreach (Ejemplar e in datos.GetEjemplares()) {
                    // Si e no es null lo escribimos en el archivo
                    if (e != null) {
                        if (e is Pelicula p) {
                            escritor.Write(p.ToByteArray());
                            escritor.Write(BitConverter.GetBytes('\n'));                            
                        }

                        if (e is Videojuego v) {
                            escritor.Write(v.ToByteArray());
                            escritor.Write(BitConverter.GetBytes('\n'));
                        }
                    }
                }
        }
        catch (FileNotFoundException ex) {
            // En el caso de que no exista el archivo
            Console.WriteLine("\t\tSystem: Error al escribir los archivos. La lista estará vacía.");
        }
    }
    
    public void LeerDatos(out Fondo datos) {
        datos = null;
    }
}
}