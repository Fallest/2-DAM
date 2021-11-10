using System;
using System.Collections.Generic;
using GestorClub.Objetos;

namespace GestorClub {
class GestorClub {
    // Inicialización del Fondo y de 3 socios.
    private static Fondo _fondo = new Fondo();
    private static Socio _s1 = new Socio("1", "Andrés Pineda");
    private static Socio _s2 = new Socio("2", "María Antonieta");
    private static Socio _s3 = new Socio("3", "David Kaye");
    static void Main(string[] args) {
        // TODO: Inicializamos la GUI.
        
        InicializarDatos();

        IniciarPrograma();
    }

    /*-------------------------------------------------------------------------------*/
    private static void InicializarDatos() {
        /*
         * Insertar 6 ejemplares.
         */
        
        _fondo.Add(new Pelicula("Interstellar", "Ciencia ficción/Aventura", "n",
            "0", "07/11/2014"));
        _fondo.Add(new Pelicula("Eternals", "Aventura/Acción", "s", 
            _s2.GetId().ToString(), "05/11/2021"));
        _fondo.Add(new Pelicula("El Lobo de Wall Street", "Drama/Comedia", "s",
            _s3.GetId().ToString(), "17/01/2014"));
        _fondo.Add(new Videojuego("Dying Light", "Acción/Horror/Supervivencia", "s",
            _s3.GetId().ToString(), "Windows/PlayStation/XBox"));
        _fondo.Add(new Videojuego("Ford vs. Chevy", "Carreras", "n", 
            "0", "PlayStation 2/XBox"));
        _fondo.Add(new Videojuego("Dragon Age: Inquisition", "Rol/Acción", "s",
            _s1.GetId().ToString(), "Windows/PlayStation/XBox"));
    }
    
    /*-------------------------------------*/
    private static void IniciarPrograma() {
        // Bucle de ejecución
        int opc;
        do {
            opc = Menu();
            if (opc < 0 || opc > 6)
                Console.WriteLine("Opción no válida.\n\n\n");
            EjectuarOpcion(opc);
        } while (opc != 0);
    }

    /*-------------------------------------------------------------------------------*/
    // Menú y control de opciones
    private static int Menu() {
        Console.WriteLine("----------------------------------\n" +
                          "---------------MENU---------------\n" +
                          "----------------------------------\n" +
                          "|    1 - Añadir ejemplar         |\n" +
                          "|    2 - Eliminar ejemplar       |\n" +
                          "|    3 - Listado                 |\n" +
                          "|    4 - Prestar ejemplar        |\n" +
                          "|    5 - Devolver ejemplar       |\n" +
                          "|                                |\n" +
                          "|    0 - Salir                   |\n" +
                          "----------------------------------\n" +
                          "\n");
        
        bool exec;
        int opc;
        
        do {
            Console.Write("Elección    > ");
            exec = Int32.TryParse(Console.ReadLine(), out opc);
        } while (opc < 0 || opc > 5 || !exec);

        return opc;
    }
    
    /*-------------------------------------*/
    private static void EjectuarOpcion(int opc) {
        switch (opc) {
            case 0:
                Console.WriteLine("\n\nSaliendo del programa...");
                break;
            case 1:
                AñadirEjemplar();
                break;
            case 2:
                EliminarEjemplar();
                break;
            case 3:
                Listado(MenuListado());
                break;
            case 4:
                PrestarEjemplar();
                break;
            case 5:
                DevolverEjemplar();
                break;
        }
    }

    /*-------------------------------------------------------------------------------*/
    // Opciones del menú
    private static void AñadirEjemplar() {
        /*
         * Añade un ejemplar a Fondo.
         * Primero pide todos los datos necesarios, luego crea un nuevo
         * objeto del tipo escogido y lo añade a Fondo.
         */
        Console.WriteLine("-------------------------\n" + 
                          "   DATOS DEL EJEMPLAR    \n" + 
                          "-------------------------\n");
        bool exec; 
        
        string tipo;
        string titulo;
        string genero;
        string aux;
        bool prestamo;
        int id = 0;
        Fecha fechaEstreno = null;
        string plataforma = null;
        
        // Pedir el tipo
        do {
            exec = false;
            Console.WriteLine("Introduzca el tipo de ejemplar (Película/Videojuego): ");
            tipo = Console.ReadLine()?.Trim().ToLower();
            if (tipo == null || !tipo.Equals("pelicula") || !tipo.Equals("película") || !tipo.Equals("videojuego")) {
                exec = true;
                Console.WriteLine("----Error----Tipo no válido.\nIntroduzca un tipo válido.\n\n");
            }
        } while (exec);
        
        // Pedir el título
        do {
            exec = false;
            Console.WriteLine("Introduzca el título del ejemplar (máx. 30 caracteres): ");
            titulo = Console.ReadLine()?.Trim().ToLower();
            if (titulo == null || titulo.Length > 30) {
                exec = true;
                Console.WriteLine("----Error----Longitud de cadena no válida.\nIntroduzca una cadena válida.\n\n");
            }
        } while (exec);
        
        // Pedir el género
        do {
            exec = false;
            Console.WriteLine("Introduzca el género del ejemplar (máx. 30 caracteres): ");
            genero = Console.ReadLine()?.Trim().ToLower();
            if (genero == null || genero.Length > 30) {
                exec = true;
                Console.WriteLine("----Error----Longitud de cadena no válida.\nIntroduzca una cadena válida.\n\n");
            }
        } while (exec);
        
        // Pedir la disponibilidad del ejemplar
        do {
            exec = false;
            Console.WriteLine("Introduzca la situación de préstamo del ejemplar (s/n): ");
            aux = Console.ReadLine()?.Trim().ToLower();
            if (aux == null || !aux.Equals("s") || !aux.Equals("n")) {
                exec = true;
                Console.WriteLine("----Error----Carácter no válido.\nIntroduzca un carácter válido.\n\n");
            }
        } while (exec);

        prestamo = aux.Equals("s");
        
        // Pedir el ID del socio si está prestado
        if (prestamo)
            do {
                exec = false;
                Console.WriteLine("Introduzca el ID del socio que tiene el ejemplar: ");
                aux = Console.ReadLine()?.Trim().ToLower();
                if (aux == null || !Int32.TryParse(aux, out id) || 
                    (id != _s1.GetId() && id != _s2.GetId() && id != _s3.GetId())) {
                    exec = true;
                    Console.WriteLine("----Error----ID no válido.\nIntroduzca un ID válido.\n\n");
                }
            } while (exec);
        else 
            id = 0;
        
        // Pedir el atributo dependiente de cada ejemplar
        if (tipo.Equals("pelicula") || tipo.Equals("película")) {
            do {
                exec = false;
                Console.WriteLine("Introduzca la fecha de estreno de la película (DD/MM/YYYY): ");
                aux = Console.ReadLine()?.Trim();
                if (aux == null || !Fecha.TryParse(aux, out fechaEstreno)) {
                    exec = true;
                    Console.WriteLine("----Error----Fecha no válida.\nIntroduzca una fecha válida.\n\n");
                }
            } while (exec);
        }
        else {
            do {
                exec = false;
                Console.WriteLine("Introduzca la(s) plataforma(s) del videojuego (máx. 30 caracteres): ");
                plataforma = Console.ReadLine()?.Trim();
                if (plataforma == null || plataforma.Length > 30) {
                    exec = true;
                    Console.WriteLine("----Error----Longitud de cadena no válida.\nIntroduzca una cadena válida.\n\n");
                }
            } while (exec);
        }
        
        // Una vez tenemos los datos, lo añadimos a la estructura.
        
        if (tipo.Equals("pelicula") || tipo.Equals("película"))
            _fondo.Add(new Pelicula(titulo, genero, prestamo, id, fechaEstreno));
        else
            _fondo.Add(new Videojuego(titulo, genero, prestamo, id, plataforma));
    }
    
    /*-------------------------------------*/
    private static void EliminarEjemplar() {
        Console.WriteLine("-------------------------\n" + 
                          "    ELIMINAR EJEMPLAR    \n" + 
                          "-------------------------\n");
        bool exec;
        
        string tipo;
        string titulo;
        
        // Pedir el tipo
        do {
            exec = false;
            Console.WriteLine("Introduzca el tipo de ejemplar a eliminar (Película/Videojuego): ");
            tipo = Console.ReadLine()?.Trim().ToLower();
            if (tipo == null || !tipo.Equals("pelicula") || !tipo.Equals("película") || !tipo.Equals("videojuego")) {
                exec = true;
                Console.WriteLine("----Error----Tipo no válido.\nIntroduzca un tipo válido.\n\n");
            }
        } while (exec);
        
        // Pedir el título
        do {
            exec = false;
            Console.WriteLine("Introduzca el título del ejemplar a eliminar: ");
            titulo = Console.ReadLine()?.Trim().ToLower();
            if (titulo == null || titulo.Length > 30) {
                exec = true;
                Console.WriteLine("----Error----Longitud de cadena no válida.\nIntroduzca una cadena válida.\n\n");
            }
        } while (exec);
        
        // Una vez tenemos los datos, lo eliminamos de la estructura.
        _fondo.Remove(titulo, tipo);
    }

    /*-------------------------------------*/
    private static int MenuListado() {
        Console.WriteLine("----------------------------------\n" +
                          "--------------LISTADO-------------\n" +
                          "|    1 - Mostrar todo            |\n" +
                          "|    2 - Ejemplares prestados    |\n" +
                          "|    3 - Búsqueda avanzada       |\n" +
                          "|                                |\n" +
                          "|    0 - Cancelar                   |\n" +
                          "----------------------------------\n" +
                          "\n");
        
        bool exec;
        int opc;
        
        do {
            Console.Write("Elección    > ");
            exec = Int32.TryParse(Console.ReadLine(), out opc);
        } while (opc < 0 || opc > 3 || !exec);

        return opc;
    }
    
    /*-------------------------------------*/
    private static void Listado(int opc) {
        switch (opc) {
            case 0:
                break;
            case 1:
                ListadoMostrarTodo();
                break;
            case 2:
                ListadoMostrarPrestados();
                break;
            case 3:
                ListadoAvanzado();
                break;
        }
    }

    /*-------------------------------------*/
    private static void ListadoMostrarTodo() {
        // Muestra todos los elementos guardados en el array de Fondo.
        Console.WriteLine("----------------------------------\n" +
                          "----------LISTADO COMPLETO--------");
        _fondo.Mostrar();

        Console.WriteLine("----------------------------------\n");
    }

    /*-------------------------------------*/
    private static void ListadoMostrarPrestados() {
        // Muestra todos los elementos prestados del array de Fondo.
        Console.WriteLine("----------------------------------\n" +
                          "---------LISTADO PRESTADOS--------");
        // Se le da una lista de tuplas como parámetros, que se usarán para el filtro
        List<Tuple<int, string>> lista = new List<Tuple<int, string>>();
        lista.Add(new Tuple<int, string>(4, "s"));
        _fondo.Mostrar(lista);

        Console.WriteLine("----------------------------------\n");
    }

    /*-------------------------------------*/
    private static void ListadoAvanzado() {
        // Muestra los elementos según una serie de filtros
        Console.WriteLine("--------------------------------------\n" +
                          "-----------LISTADO AVANZADO-----------");
        /* Hay que preguntar al usuario por qué quiere filtrar:
         *  -Por tipo.
         *  -Por título.
         *  -Por género.
         *  -Por disponibilidad.
         *  -Por ID de socio.
         *  -Por fecha de estreno (automáticamente filtra por tipo).
         *  -Por plataforma (automáticamente filtra por tipo).
         */
        Console.WriteLine("|                                    |\n" +
                          "|       OPCIONES DE BÚSQUEDA         |\n" +
                          "|    1 - Por tipo                    |\n" +
                          "|    2 - Por título                  |\n" +
                          "|    3 - Por género                  |\n" +
                          "|    4 - Por disponibilidad          |\n" +
                          "|    5 - Por ID de socio             |\n" +
                          "|    6 - Por fecha de estreno (pelis)|\n" +
                          "|    7 - Por plataforma (juegos)     |\n" +
                          "|                                    |\n" +
                          "|    0 - Cancelar   / 'A' - Aceptar  |\n" +
                          "--------------------------------------\n" +
                          "\n");
        
        List<Tuple<int, string>> lista = new List<Tuple<int, string>>();

        string opc, respuesta;
        List<int> filtro = new List<int>();
        int i = 0;
        bool exec;
        /*
         * Este bucle va a pedir opciones hasta que:
         *  -Se escoja 'A'.
         *  -Se escoja '0'.
         *  -Se escojan todas las opciones disponibles.
         * Hay que tener en cuenta que la 6 y la 7 no se pueden seleccionar a la vez,
         * y que si en tipo se especifica "película" o "videojuego", solo se permitirá
         * elegir la 6 o la 7 dependiendo del tipo escogido.
         */
        do {
            exec = false;
            Console.Write("Elección    > ");
            opc = Console.ReadLine();

            if (opc != null && opc.ToLower().Trim().Equals("a"))
                // Si se ha escrito 'A', salimos
                break;
            if (opc == null || !Int32.TryParse(opc, out i)) {
                // Si se ha escrito un carácter no válido, se vuelve a pedir.
                Console.WriteLine("\n----Error----Opción no válida.\n Introduzca una opción válida.\n");
                exec = true;
            }
            else {
                // Si se ha introducido un carácter válido (se puede parsear a entero)
                /*
                 * Si la opción escogida es 0, se sale del bucle.
                 * Hay que comprobar si está en el array.
                 *  -Si está en el array, se ignora y se pide otra.
                 *  -Si no está en el array, hay que realizar otras comprobaciones:
                 *      -Si es la opción 6 o la 7, hay que comprobar que la otra no está en el array.
                 *      -Si es la opción 1, hay que comprobar si la respuesta leída coincide con la
                 *          existencia o no de un valor 6 o 7 en el array.
                 */
                if (i == 0) 
                    break;
                if (filtro.IndexOf(i) == -1) {
                    if ((i == 6 && filtro.IndexOf(7) != -1) || (i == 7 && filtro.IndexOf(6) != -1)) {
                        Console.WriteLine("\n----Error----No se permite la combinación de opciones 6 y 7.\n" +
                                          "Introduzca una opción válida.\n");
                        exec = true;
                    }
                    else {
                        respuesta = PreguntaAvanzada(i);
                        if ((i == 1 && (respuesta.Equals("pelicula") || respuesta.Equals("película"))
                                && filtro.IndexOf(7) != -1)
                            || (i == 1 && respuesta.Equals("videojuego") && filtro.IndexOf(6) != -1)) {
                            Console.WriteLine("\n----Error----Combinación errónea de tipos con otras opciones.\n" +
                                              "Introduzca una opción válida.\n");
                            exec = true;
                        }
                        else {
                            filtro.Add(i);
                            lista.Add(new Tuple<int, string>(i, respuesta));
                        }
                    }
                }
                else {
                    // Si el número ya está en el array volvemos a pedir otra opción.
                    Console.WriteLine("\n----Error----Opción ya escogida.\n Introduzca una opción válida.\n");
                    exec = true;
                }
            }
        } while (exec);
    
        // Si hemos escogido la opción 0 no se mostrará nada. Así que preguntamos si NO se ha
        // escogido la opción 0.
        if (i != 0)
            _fondo.Mostrar(lista);

        Console.WriteLine("----------------------------------\n");
    }

    /*-------------------------------------*/
    public static string PreguntaAvanzada(int i) {
        /*
         * Para pedir datos para el filtro. Usa un switch con i para saber qué dato está pidiendo.
         * 1: Pide el tipo.
         * 2: Pide el título.
         * 3: Pide el género.
         * 4: Pide la disponibilidad.
         * 5: Pide la ID del socio.
         * 6: Pide la fecha de estreno (solo pelis).
         * 7: Pide la plataforma (solo juegos).
         * 
         * Cada opción tiene una limitación:
         *  -El tipo solo puede ser "pelicula", "película" o "videojuego".
         *  -El título, el género y la plataforma tienen un límite de longitud de 30.
         *  -La disponibilidad tiene que ser "s" o "n".
         *  -La ID del socio debe existir en uno de los 3 socios que hay.
         *  -La fecha de estreno debe ser válida.
         */
        bool exec;
        string res;
        
        switch (i) {
            // Pedir el tipo
            case 1:
                do {
                    exec = false;
                    Console.WriteLine("Introduzca el tipo de ejemplar (Película/Videojuego): ");
                    res = Console.ReadLine()?.Trim().ToLower();
                    if (res == null || !res.Equals("pelicula") || !res.Equals("película") || !res.Equals("videojuego")) {
                        exec = true;
                        Console.WriteLine("----Error----Tipo no válido.\nIntroduzca un tipo válido.\n\n");
                    }
                } while (exec);
                
                return res;
            // Pedir el título
            case 2:
                do {
                    exec = false;
                    Console.WriteLine("Introduzca el título del ejemplar (máx. 30 caracteres): ");
                    res = Console.ReadLine()?.Trim().ToLower();
                    if (res == null || res.Length > 30) {
                        exec = true;
                        Console.WriteLine("----Error----Longitud de cadena no válida.\nIntroduzca una cadena válida.\n\n");
                    }
                } while (exec);
                break;
            // Pedir el género
            case 3:
                do {
                    exec = false;
                    Console.WriteLine("Introduzca el género del ejemplar (máx. 30 caracteres): ");
                    res = Console.ReadLine()?.Trim().ToLower();
                    if (res == null || res.Length > 30) {
                        exec = true;
                        Console.WriteLine("----Error----Longitud de cadena no válida.\nIntroduzca una cadena válida.\n\n");
                    }
                } while (exec);
                
                return res;
            // Pedir la disponibilidad
            case 4:
                do {
                    exec = false;
                    Console.WriteLine("Introduzca la situación de préstamo del ejemplar (s/n): ");
                    res = Console.ReadLine()?.Trim().ToLower();
                    if (res == null || !res.Equals("s") || !res.Equals("n")) {
                        exec = true;
                        Console.WriteLine("----Error----Carácter no válido.\nIntroduzca un carácter válido.\n\n");
                    }
                } while (exec);

                return res;
            // Pedir la ID del socio
            case 5:
                int id = 0;
                do {
                    exec = false;
                    Console.WriteLine("Introduzca el ID del socio que tiene el ejemplar: ");
                    res = Console.ReadLine()?.Trim().ToLower();
                    if (res == null || !Int32.TryParse(res, out id) || 
                        (id != _s1.GetId() && id != _s2.GetId() && id != _s3.GetId())) {
                        exec = true;
                        Console.WriteLine("----Error----ID no válido.\nIntroduzca un ID válido.\n\n");
                    }
                } while (exec);

                return id.ToString();
            // Pedir la fecha de estreno
            case 6:
                Fecha fechaEstreno = null;
                do {
                    exec = false;
                    Console.WriteLine("Introduzca la fecha de estreno de la película (DD/MM/YYYY): ");
                    res = Console.ReadLine()?.Trim();
                    if (res == null || !Fecha.TryParse(res, out fechaEstreno)) {
                        exec = true;
                        Console.WriteLine("----Error----Fecha no válida.\nIntroduzca una fecha válida.\n\n");
                    }
                } while (exec);

                return fechaEstreno.ToString();
            // Pedir la plataforma
            case 7:
                do {
                    exec = false;
                    Console.WriteLine("Introduzca la(s) plataforma(s) del videojuego (máx. 30 caracteres): ");
                    res = Console.ReadLine()?.Trim();
                    if (res == null || res.Length > 30) {
                        exec = true;
                        Console.WriteLine("----Error----Longitud de cadena no válida.\nIntroduzca una cadena válida.\n\n");
                    }
                } while (exec);

                return res;
        }

        return null;
    }
    /*-------------------------------------*/

    private static void PrestarEjemplar() {
        /*
         * Hay que pedir el tipo de ejemplar y el título.
         */
        string tipo, titulo;
        bool exec;
        
        // Pedimos el tipo
        do {
            exec = false;
            Console.WriteLine("Introduzca el tipo de ejemplar a prestar (Película/Videojuego): ");
            tipo = Console.ReadLine()?.Trim().ToLower();
            if (tipo == null || !tipo.Equals("pelicula") || !tipo.Equals("película") || !tipo.Equals("videojuego")) {
                exec = true;
                Console.WriteLine("----Error----Tipo no válido.\nIntroduzca un tipo válido.\n\n");
            }
        } while (exec);
        
        // Pedimos el título
        do {
            exec = false;
            Console.WriteLine("Introduzca el título del ejemplar a prestar (máx. 30 caracteres): ");
            titulo = Console.ReadLine()?.Trim().ToLower();
            if (titulo == null || titulo.Length > 30) {
                exec = true;
                Console.WriteLine("----Error----Longitud de cadena no válida.\nIntroduzca una cadena válida.\n\n");
            }
        } while (exec);

        _fondo.Prestar(tipo, titulo);
    }
    
    /*-------------------------------------*/
    private static void DevolverEjemplar() {
        /*
         * Hay que pedir el tipo de ejemplar y el título.
         */
        string tipo, titulo;
        bool exec;
        
        // Pedimos el tipo
        do {
            exec = false;
            Console.WriteLine("Introduzca el tipo de ejemplar a devolver (Película/Videojuego): ");
            tipo = Console.ReadLine()?.Trim().ToLower();
            if (tipo == null || !tipo.Equals("pelicula") || !tipo.Equals("película") || !tipo.Equals("videojuego")) {
                exec = true;
                Console.WriteLine("----Error----Tipo no válido.\nIntroduzca un tipo válido.\n\n");
            }
        } while (exec);
        
        // Pedimos el título
        do {
            exec = false;
            Console.WriteLine("Introduzca el título del ejemplar a devolver (máx. 30 caracteres): ");
            titulo = Console.ReadLine()?.Trim().ToLower();
            if (titulo == null || titulo.Length > 30) {
                exec = true;
                Console.WriteLine("----Error----Longitud de cadena no válida.\nIntroduzca una cadena válida.\n\n");
            }
        } while (exec);

        _fondo.Devolver(tipo, titulo);
    }
    
    /*-------------------------------------------------------------------------------*/
}
}