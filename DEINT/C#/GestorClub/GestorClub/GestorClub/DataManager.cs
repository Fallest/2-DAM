using System.IO;
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

    public static void EscribirDatos(Fondo datos) {
        using FileStream escritor = new FileStream("archivo.dat", FileMode.Open);

        foreach (Ejemplar e in datos.GetEjemplares()) {
            // Si e no es null lo escribimos en el archivo
            if (e != null) {
                if (e is Pelicula p)
                    escritor.Write(p.ToByteArray());
                if (e is Videojuego v)
                    escritor.Write(v.ToByteArray());
            }
        }
    }
    
    public void LeerDatos(out Fondo datos) {
        datos = null;
    }
}
}