using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;

namespace GestorClub.Objetos {
class Fondo {
    // Array principal, que al inicio tiene nada más que 10 posiciones.
    private Ejemplar[] _fondo;

    /*-------------------------------------------------------------------------------*/
    // Constructor
    public Fondo() {
        _fondo = new Ejemplar[10];
    }
    
    /*-------------------------------------------------------------------------------*/
    // Operaciones añadir y eliminar.
    public void Add(Ejemplar e) {
        /*
         * Añade un elemento al array de forma ordenada.
         * Hay que tener en cuenta lo siguiente:
         *  -Puede que se supere el tamaño actual del array al añadir un nuevo dato.
         * En dicho caso se aumentará de 5 en 5 el tamaño del array, creando una copia
         * en cada aumento.
         */
        if (_fondo.Length - _fondo.Count() == 0) {
            Incrementar();
        }

        int cont = 0;
        foreach (Ejemplar item in _fondo) {
            // Si item.Titulo es anterior a e.Titulo en orden:
            if (item.GetTitulo().CompareTo(e) < 0 ) {
                cont++;
            }
            // Si item.Titulo es posterior o igual a e.Titulo en orden:
            else {
                // Desplazamos todos los registros siguientes y añadimos el ejemplar.
                Desplazar(cont);
                _fondo[cont] = e;
            }
        }

    }
    
    /*-------------------------------------*/
    public void Remove(string titulo, string tipo) {
        throw new NotImplementedException();
    }
    
    /*-------------------------------------------------------------------------------*/
    // Métodos para aumentar el tamaño de la lista y desplazar elementos.
    public void Desplazar(int num) {
        /*
         * Vamos a recorrer el array de forma inversa, para así poder ir desplazando los datos hacia arriba.
         * Nunca nos vamos a encontrar con a situación de que el array esté lleno, porque esa
         * situación ya se contempla en Add(Ejemplar e).
         *
         * Vamos a recorrer el array desde su longitud -1 (en el caso de que tenga tamaño 10, empezaremos
         * desde la 9 hasta num). Pararemos cuando i sea igual a num, lo que significa que ya se podrá
         * machacar la información que hubiera en _fondo[num] (que es lo que necesita Add, tener ese
         * hueco disponible).
         */
        for (int i = _fondo.Length - 1; i >= num; i--) {
            // Puesto que nunca se va a dar la situación en la que el array está lleno (10/10 por ejemplo)
            // podemos ignorar la primera posición.
            if (i != _fondo.Length - 1) {
                // Asignamos al "siguiente" (en orden ascendente) el "anterior" (que es el actual).
                _fondo[i + 1] = _fondo[i];
            }
        }
    }

    /*-------------------------------------*/
    private void Incrementar() {
        // Incrementa el tamaño del array en 5, y copia el anterior al nuevo.
        Ejemplar[] aux = new Ejemplar[_fondo.Length + 5];
        
        int cont = 0;
        foreach (Ejemplar item in _fondo) {
            aux[cont] = item;
            cont++;
        }

        _fondo = aux;
    }
    
    /*-------------------------------------------------------------------------------*/
    // Métodos para mostrar por pantalla los datos.

    public void Mostrar() {
        // Esta función muestra todos los datos
        foreach (Ejemplar e in _fondo)
            Console.WriteLine(e);
    }
    
    /*-------------------------------------*/
    public void Mostrar(List<Tuple<int, string>> lista) {
        /*
         * Esta función va a mostrar los datos siguiendo una serie de condiciones
         * indicadas en la Lista de tupladas dada por parámetros.
         * Las tuplas de la lista tienen la siguiente estructura:
         *  -Un int, que indica internamente cuál es la opción de filtro escogida.
         *  -Una string, que indica qué valor usar para el filtro.
         *
         * La lista de opciones es la siguiente:
         *  1 - Por tipo.
         *  2 - Por título.
         *  3 - Por género.
         *  4 - Por disponibilidad.
         *  5 - Por ID de socio.
         *  6 - Por fecha de estreno (automáticamente filtra por tipo).
         *  7 - Por plataforma (automáticamente filtra por tipo).
         *
         * Las restricciones ya están aplicadas en las funciones que llaman a esta función.
         *
         * La forma en la que el filtro va a funcionar va a ser la siguiente:
         * Se va a recorrer la lista de tuplas, y por cada filtro que se encuentre, se va a aplicar
         * en cascada junto con los demás sobre una List que servirá de auxiliar.
         * Los registros restantes que queden en la List tras completar la iteración de la lista de
         * tuplas se mostrarán por pantalla, puesto que serán los registros que han pasado el filtro.
         *
         * Se creará una lista auxiliar para cada filtro, y al final se hará una intersección de todas
         * las auxiliares.
         */

        List<Ejemplar>
            auxiliar1, // Filtro por tipo
            auxiliar2, // Filtro por título
            auxiliar3, // Filtro por género
            auxiliar4, // Filtro por disponibilidad
            auxiliar5, // Filtro por ID de socio
            auxiliar6, // Filtro por fecha de estreno (películas)
            auxiliar7, // Filtro por plataforma (videojuegos)
            copiaFondo = new List<Ejemplar>(), // Copia tipo List de _fondo
            resultado; // Lista para el resultado

        // Creamos una copia tipo List<Ejemplar> de _fondo.
        foreach (Ejemplar e in _fondo)
            copiaFondo.Add(e);

        foreach (Tuple<int, string> tupla in lista) {
            switch (tupla.Item1) {
                case 1:
                    // Filtrar por tipo.
                    auxiliar1 = new List<Ejemplar>();
                    if ( tupla.Item2.Equals("pelicula") || tupla.Item2.Equals("película") )
                        foreach (Ejemplar e in copiaFondo) 
                            if (e is Pelicula)
                                auxiliar1.Add(e);
                    if ( tupla.Item2.Equals("videojuego") )
                        foreach (Ejemplar e in copiaFondo) 
                            if (e is Videojuego)
                                auxiliar1.Add(e);
                    break;
                case 2:
                    // Filtrar por título
                    auxiliar2 = new List<Ejemplar>();
                    foreach (Ejemplar e in copiaFondo) 
                        if (e.GetTitulo().ToLower().Trim().Equals(tupla.Item2.ToLower().Trim()))
                            auxiliar2.Add(e);
                    break;
                case 3:
                    // Filtro por género (usa Contains, no Equals, porque puede tener varios géneros)
                    auxiliar3 = new List<Ejemplar>();
                    foreach (Ejemplar e in copiaFondo) 
                        if (e.GetGenero().ToLower().Trim().Contains(tupla.Item2.ToLower().Trim()))
                            auxiliar3.Add(e);
                    break;
                case 4:
                    // Filtro por disponibilidad
                    /*
                     * Las dos posibilidades (s o n) están en bloques separadas para mejorar
                     * los tiempos de ejecución (si estuvieran los ifs dentro del foreach tendrían
                     * que evaluarse en cada iteración, cuando así solo se tiene que evaluar
                     * una vez al principio).
                     */
                    auxiliar4= new List<Ejemplar>();
                    
                    if (tupla.Item2.Equals("s")) 
                        foreach (Ejemplar e in copiaFondo) 
                            if (e.GetDisponible())
                                auxiliar4.Add(e);
                    
                    if (tupla.Item2.Equals("n")) 
                        foreach (Ejemplar e in copiaFondo) 
                            if (!e.GetDisponible())
                                auxiliar4.Add(e);
                    break;
                case 5:
                    // Filtro por ID de socio
                    /*
                     * Uso un Parse porque en la función que llama a esta ya se ha comprobado
                     * que la cadena añadida se puede parsear a un int.
                     */
                    auxiliar5 = new List<Ejemplar>();
                    foreach (Ejemplar e in copiaFondo) 
                        if (e.GetSocioId() == Int32.Parse(tupla.Item2))
                            auxiliar5.Add(e);
                    break;
                case 6:
                    // Filtro por fecha de estreno
                    break;
                case 7:
                    // Filtro por plataforma
                    break;
            }
        }



    }
    
    /*-------------------------------------------------------------------------------*/
    // Métodos para prestar y devolver ejemplares.
    public void Devolver(string tipo, string titulo) {
        CambiarDisponibilidad(tipo, titulo, true);
    }

    /*-------------------------------------*/
    public void Prestar(string tipo, string titulo) {
        CambiarDisponibilidad(tipo, titulo, false);
    }

    /*-------------------------------------*/
    private void CambiarDisponibilidad(string tipo, string titulo, bool disp) {
        /*
         * Método para cambiar la disponibilidad de un ejemplar.
         * Lo utilizar los métodos Prestar y Devolver para ahorrar código
         * y facilitar el uso de los métodos desde el exterior.
         * 
         * Primero busca el ejemplar indicado con el tipo y el título,
         * y luego modifica su campo Disponible a lo que indique disp. 
         */
        tipo = tipo.ToLower().Trim();
        titulo = titulo.ToLower().Trim();
        
        if ( tipo.Equals("pelicula") || tipo.Equals("película")) 
            foreach (Ejemplar item in _fondo) 
                if (item is Pelicula && item.GetTitulo().ToLower().Trim() == titulo)
                    item.SetDisponible(disp);

        if ( tipo.Equals("videojuego") ) 
            foreach (Ejemplar item in _fondo) 
                if (item is Videojuego && item.GetTitulo().ToLower().Trim() == titulo)
                    item.SetDisponible(disp);
    }
}
}