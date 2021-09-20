using System;

namespace Ejemplo1 {
    class Empresa {
        // Atributos
        private Empleado emp1;
        private Empleado emp2;
        
        /*------------------------------------------------------------------------*/
        // Constructor: 1/1
        public Empresa(params Empleado[] empleados) {
            emp1 = empleados[0];
            emp2 = empleados[1];
        }
        
        /*------------------------------------------------------------------------*/
        // Métodos para el menú: 0/5
        public int menu() {
            var opcion = 0;
            
            // Menú
            Console.WriteLine("\n1. Consultar sueldo");
            Console.WriteLine("2. Consultar nombre");
            Console.WriteLine("3. Cambiar nombre");
            Console.WriteLine("4. Establecer sueldo");
            Console.WriteLine("5. Listado de empleados");
            
            // Preguntar por la opción escogida
            /*
            Es un bloque try-catch dentro de un bucle do-while, para pedir el dato
            hasta que se introduzca correctamente.
            */
            bool ejec;
            
            do {
                try {
                    ejec = false;

                    Console.WriteLine("\n\tIntroduzca una opción:");
                    opcion = Int32.Parse(Console.ReadLine());

                    // Comprobar que el dato introducido es válido.
                    if ((opcion <= 0) || (opcion > 5)) {
                        Console.WriteLine("\nOpción no válida.\n");
                        ejec = true;
                    }
                }
                catch (System.IO.IOException e) {
                    Console.WriteLine("\nError en la entrada de datos.");
                    ejec = true;
                }
            } while (ejec);
            
            return opcion;
        }
        
        public void ejecOpcion(Empleado emp, int opc) {
            switch(opc) {
                case 1: {
                    Console.WriteLine("Sueldo: " + emp.getSueldo());
                    break;
                }
                case 2: {
                    Console.WriteLine("Nombre: " + emp.getNombre());
                    break;
                }
                case 3: {
                    emp.setNombre();
                    break;
                }
                case 4: {
                    emp.setSueldo();
                    break;
                }
                case 5: {
                    mostrarEmpleados();
                    break;
                }
            }
        }
        
        public void mostrarEmpleados() {
            Console.WriteLine(emp1);
            Console.WriteLine(emp2);
        }
    }    
}