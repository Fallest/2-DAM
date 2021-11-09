using System;
using System.ComponentModel.Design;
using GestorClub.Objetos;

namespace GestorClub {
class GestorClub {
    private static Fondo _fondo = new Fondo();
    private static Socio s1 = new Socio("1", "Andrés Pineda");
    private static Socio s2 = new Socio("2", "María Antonieta");
    private static Socio s3 = new Socio("3", "David Kaye");
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
        
        _fondo.add(new Pelicula("Interstellar", "Ciencia ficción/Aventura", "n",
            "0", "07/11/2014"));
        _fondo.add(new Pelicula("Eternals", "Aventura/Acción", "s", 
            s2.GetId().ToString(), "05/11/2021"));
        _fondo.add(new Pelicula("El Lobo de Wall Street", "Drama/Comedia", "s",
            s3.GetId().ToString(), "17/01/2014"));
        _fondo.add(new Videojuego("Dying Light", "Acción/Horror/Supervivencia", "s",
            s3.GetId().ToString(), "Windows/PlayStation/XBox"));
        _fondo.add(new Videojuego("Ford vs. Chevy", "Carreras", "n", 
            "0", "PlayStation 2/XBox"));
        _fondo.add(new Videojuego("Dragon Age: Inquisition", "Rol/Acción", "s",
            s1.GetId().ToString(), "Windows/PlayStation/XBox"));
    }
    private static void IniciarPrograma() {
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
        
        // Pedir la situación del ejemplar
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
                    (id != s1.GetId() && id != s2.GetId() && id != s3.GetId())) {
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
            _fondo.add(new Pelicula(titulo, genero, prestamo, id, fechaEstreno));
        else
            _fondo.add(new Videojuego(titulo, genero, prestamo, id, plataforma));
    }
    
    /*-------------------------------------*/
    private static void EliminarEjemplar() {
        Console.WriteLine("-------------------------\n" + 
                          "    ELIMINAR EJEMPLAR    \n" + 
                          "-------------------------\n");
        bool exec = false;
        
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
        
        // Una vez tenemos los datos, lo añadimos a la estructura.
        _fondo.remove(titulo, tipo);
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

    private static void ListadoMostrarTodo() {
        throw new NotImplementedException();
    }

    private static void ListadoMostrarPrestados() {
        throw new NotImplementedException();
    }

    private static void ListadoAvanzado() {
        throw new NotImplementedException();
    }
    /*-------------------------------------*/

    private static void PrestarEjemplar() {
        
    }
    
    /*-------------------------------------*/
    private static void DevolverEjemplar() {
        throw new NotImplementedException();
    }
    
    /*-------------------------------------------------------------------------------*/
    

    
}
}