using System;
using System.Collections.Generic;

namespace GestorClub.Objetos {
class Fondo {
    // Array principal, que al inicio tiene nada más que 10 posiciones.
    private Ejemplar[] _fondo;
    private Socio[] _socios;

    /*-------------------------------------------------------------------------------*/
    // Constructor
    public Fondo(Socio[] socios) {
        _fondo = new Ejemplar[10];
        this._socios = socios;
    }
    
    /*-------------------------------------------------------------------------------*/
    // Getter
    public Socio[] GetSocios() {
        return _socios;
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
        if (_fondo.Length - Count() == 0) {
            Incrementar();
        }

        int cont = 0;
        foreach (Ejemplar item in _fondo) {
            // Si item.Titulo es anterior a e.Titulo en orden:
            if (item != null 
                && String.Compare(item.GetTitulo(), e.GetTitulo(), StringComparison.Ordinal) < 0 ) 
                cont++;
            
            // Si item.Titulo es posterior o igual a e.Titulo en orden:
            else {
                // Desplazamos todos los registros siguientes y añadimos el ejemplar.
                Desplazar(cont);
                _fondo[cont] = e;
                Console.WriteLine("\tSystem: Elemento añadido.");
                break;
            }
        }

    }
    
    /*-------------------------------------*/
    public void Remove(int id) {
        /*
         * Busca el ejemplar dado su ID.
         * Una vez encontrado el índice del ejemplar, se desplazan hacia abajo todos los
         * ejemplares a partir de ese índice, para machacar el dato.
         */
        int cont = 0;
        bool eliminado = false;

        foreach (Ejemplar e in _fondo) {
            if (e != null && e.GetId() == id) {
                // Desplazamos hacia abajo todos los datos.
                eliminado = true;
                for (int i = cont; _fondo[i] != null; i++)
                    // Hacemos solo un cambio: el siguiente al actual
                    _fondo[i] = _fondo[i + 1];
            }

            cont++;
        }
        
        if (eliminado)
            Console.WriteLine("\tSystem: Elemento eliminado.");
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
            if (item == null)
                break;
            aux[cont] = item;
            cont++;
        }

        _fondo = aux;
        
        Console.WriteLine("\tSystem: Array incrementado en tamaño");
    }
    
    /*-------------------------------------------------------------------------------*/
    // Métodos para mostrar por pantalla los datos.

    public void Mostrar() {
        // Esta función muestra todos los datos
        foreach (Ejemplar e in _fondo)
            if (e != null)
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

        HashSet<Ejemplar>
            auxiliar1 = null, // Filtro por tipo
            auxiliar2 = null, // Filtro por título
            auxiliar3 = null, // Filtro por género
            auxiliar4 = null, // Filtro por disponibilidad
            auxiliar5 = null, // Filtro por ID de socio
            auxiliar6 = null, // Filtro por fecha de estreno (películas)
            auxiliar7 = null, // Filtro por plataforma (videojuegos)
            resultado = null; // Set para el resultado
        
        List<Ejemplar> copiaFondo = new List<Ejemplar>(); // Copia tipo List de _fondo

        // Creamos una copia tipo List<Ejemplar> de _fondo.
        foreach (Ejemplar e in _fondo)
            if (e != null)
                copiaFondo.Add(e);

        Console.Write("\n\nSystem: Filtro avanzado: ");
        foreach (Tuple<int, string> tupla in lista) {
            switch (tupla.Item1) {
                case 1:
                    // Filtrar por tipo.
                    auxiliar1 = new HashSet<Ejemplar>();
                    if (tupla.Item2.Equals("pelicula") || tupla.Item2.Equals("película")) {
                        foreach (Ejemplar e in copiaFondo)
                            if (e is Pelicula)
                                auxiliar1.Add(e);
                    }

                    else if (tupla.Item2.Equals("videojuego")) {
                        foreach (Ejemplar e in copiaFondo)
                            if (e is Videojuego)
                                auxiliar1.Add(e);
                    }

                    Console.Write(" - Tipo \"" + tupla.Item2 + "\"");
                    break;
                case 2:
                    // Filtrar por título
                    auxiliar2 = new HashSet<Ejemplar>();
                    foreach (Ejemplar e in copiaFondo) 
                        if (e.GetTitulo().ToLower().Trim().Equals(tupla.Item2.ToLower().Trim()))
                            auxiliar2.Add(e);
                    Console.Write(" - Titulo \"" + tupla.Item2 + "\"");
                    break;
                case 3:
                    // Filtro por género (usa Contains, no Equals, porque puede tener varios géneros)
                    auxiliar3 = new HashSet<Ejemplar>();
                    foreach (Ejemplar e in copiaFondo) 
                        if (e.GetGenero().ToLower().Trim().Contains(tupla.Item2.ToLower().Trim()))
                            auxiliar3.Add(e);
                    Console.Write(" - Género \"" + tupla.Item2 + "\"");
                    break;
                case 4:
                    // Filtro por disponibilidad
                    /*
                     * Las dos posibilidades (s o n) están en bloques separadas para mejorar
                     * los tiempos de ejecución (si estuvieran los ifs dentro del foreach tendrían
                     * que evaluarse en cada iteración, cuando así solo se tiene que evaluar
                     * una vez al principio).
                     */
                    auxiliar4= new HashSet<Ejemplar>();

                    if (tupla.Item2.Equals("s")) {
                        foreach (Ejemplar e in copiaFondo)
                            if (e.GetDisponible())
                                auxiliar4.Add(e);
                    }
                    else if (tupla.Item2.Equals("n")) {
                        foreach (Ejemplar ej in copiaFondo)
                            if (!ej.GetDisponible())
                                auxiliar4.Add(ej);
                    }
                    Console.Write(" - Disponibilidad \"" + tupla.Item2 + "\"");
                    break;
                case 5:
                    // Filtro por ID de socio
                    /*
                     * Uso un Parse porque en la función que llama a esta ya se ha comprobado
                     * que la cadena añadida se puede parsear a un int.
                     */
                    auxiliar5 = new HashSet<Ejemplar>();
                    foreach (Ejemplar e in copiaFondo) 
                        if (e.GetSocioId() == Int32.Parse(tupla.Item2))
                            auxiliar5.Add(e);
                    Console.Write(" - ID Socio \"" + tupla.Item2 + "\"");
                    break;
                case 6:
                    // Filtro por fecha de estreno
                    // Comparamos las versiones string de las fechas.
                    auxiliar6 = new HashSet<Ejemplar>();
                    foreach (Ejemplar e in copiaFondo) 
                        if (e is Pelicula p)
                            if (p.GetFecha().ToString().Equals(tupla.Item2))
                                auxiliar6.Add(e);
                    Console.Write(" - Fecha Estreno \"" + tupla.Item2 + "\"");
                    break;
                case 7:
                    // Filtro por plataforma
                    auxiliar7 = new HashSet<Ejemplar>();
                    foreach (Ejemplar e in copiaFondo) 
                        if (e is Videojuego v)
                            if (v.GetPlataforma().ToLower().Trim().Contains(tupla.Item2))
                                auxiliar7.Add(e);
                    Console.Write(" - Plataforma \"" + tupla.Item2 + "\"");
                    break;
            }
            // Ahora tenemos que unir los resultados en una lista
            /*
             * NO se pueden realizar intersecciones a palo seco, porque si una resulta
             * ser null, la intersección será NADA.
             * Entonces, hay que comprobar todo el rato si el Set al que se está intentando hacer la
             * intersección es null.
             * No solo eso, sino que puede que auxiliar 1 sea null, y puede que auxiliar 1 y 2 sean null,
             * etc, por lo que también hay que comprobar en cada caso si el auxiliar en el que se está
             * haciendo la intersección es null o no.
             */
            // Seguro que hay una forma más bonita de hacer esto, pero...
            int i = -1;
            if (auxiliar1 != null)
                i = 1;
            else if (auxiliar2 != null)
                i = 2;
            else if (auxiliar3 != null)
                i = 3;
            else if (auxiliar4 != null)
                i = 4;
            else if (auxiliar5 != null)
                i = 5;
            else if (auxiliar6 != null)
                i = 6;
            else if (auxiliar7 != null)
                i = 7;

            switch (i) {
                case -1:
                    Console.WriteLine("\nNo hay datos que coincidan con tu búsqueda.");
                    break;
                case 1:
                    resultado = auxiliar1;
                    break;
                case 2:
                    resultado = auxiliar2;
                    break;
                case 3:
                    resultado = auxiliar3;
                    break;
                case 4:
                    resultado = auxiliar4;
                    break;
                case 5:
                    resultado = auxiliar5;
                    break;
                case 6:
                    resultado = auxiliar6;
                    break;
                case 7:
                    resultado = auxiliar7;
                    break;
            }
            // Soy consciente de que se hará un intersect consigo mismo en algún momento
            if (auxiliar1 != null) resultado.IntersectWith(auxiliar1);
            if (auxiliar2 != null) resultado.IntersectWith(auxiliar2); 
            if (auxiliar3 != null) resultado.IntersectWith(auxiliar3);
            if (auxiliar4 != null) resultado.IntersectWith(auxiliar4);
            if (auxiliar5 != null) resultado.IntersectWith(auxiliar5);
            if (auxiliar6 != null) resultado.IntersectWith(auxiliar6);
            if (auxiliar7 != null) resultado.IntersectWith(auxiliar7);

            
        }
        Console.WriteLine("\n");
        // Queda mostrar por pantalla los elementos
        if (resultado.Count != 0)
            foreach (Ejemplar e in resultado)
                Console.WriteLine(e);
        else {
            
            Console.WriteLine("\t\tSystem: No hay datos para el filtro aplicado.");
        }
    }
    
    /*-------------------------------------------------------------------------------*/
    // Métodos para prestar y devolver ejemplares.
    public void Devolver(int id) {
        CambiarDisponibilidad(id, true);
    }

    /*-------------------------------------*/
    public void Prestar(int id) {
        CambiarDisponibilidad(id, false);
    }

    /*-------------------------------------*/
    private void CambiarDisponibilidad(int id, bool disp) {
        /*
         * Método para cambiar la disponibilidad de un ejemplar.
         * Lo utilizar los métodos Prestar y Devolver para ahorrar código
         * y facilitar el uso de los métodos desde el exterior.
         * 
         * Primero busca el ejemplar indicado con el tipo y el título,
         * y luego modifica su campo Disponible a lo que indique disp. 
         */
        bool encontrado = false;
        foreach (Ejemplar item in _fondo)
            if (item != null && item.GetId() == id) {
                encontrado = true;
                item.SetDisponible(disp);
                if(disp)
                    Console.WriteLine("\t\tSystem: El ejemplar con ID \"" + id + "\" está ahora disponible.\n\n");
                else
                    Console.WriteLine("\t\tSystem: El ejemplar con ID \"" + id + "\" ya no está disponible.\n\n");
            }
        if (!encontrado)
            Console.WriteLine("\t\tSystem: Ejemplar no encontrado.");
    }
    /*-------------------------------------------------------------------------------*/
    // Método Count
    public int Count() {
        int cont = 0;
        
        foreach (Ejemplar e in _fondo) {
            if (e != null) cont++;
        }
        
        return cont;
    }
}
}