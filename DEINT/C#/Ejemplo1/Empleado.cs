using System;

namespace Ejemplo1
{
    class Empleado
    {
        // Atributos
        private string nombre;
        private int sueldo;
        private static int sueldoMaximo;
        
        /*------------------------------------------------------------------------*/
        // Construtctores: 2/2
        public Empleado(string nombre, int sueldoMaximo) {
            this.nombre = nombre;
            Empleado.sueldoMaximo = sueldoMaximo;
        }
        
        public Empleado(string nombre, int sueldo, int sueldoMaximo) {
            this.nombre = nombre;
            this.sueldo = sueldo;
            Empleado.sueldoMaximo = sueldoMaximo;
        }
        /*------------------------------------------------------------------------*/
        // Setters: 5/5
        
        public void setNombre(string nombre) {
            this.nombre = nombre;
        }
        
        public void setNombre() {
            bool ejec;
            
            do {
                try {
                    ejec = false;

                    Console.WriteLine("\n\tIntroduzca nuevo nombre:");
                    this.nombre = Console.ReadLine();
                }
                catch (System.IO.IOException e) {
                    Console.WriteLine("\nError en la entrada de datos.");
                    ejec = true;
                }
            } while (ejec);
        }
        
        public void setSueldo(int sueldo) {
            if (sueldo < Empleado.sueldoMaximo)
                this.sueldo = sueldo;
            else Console.WriteLine("El sueldo no puede ser superior al sueldo máximo.");
        }
        
        public void setSueldo() {
            bool ejec;
            int sueldo;
            
            do {
                try {
                    ejec = false;
                    
                    Console.WriteLine("\n\tIntroduzca el sueldo del empleado");
                    sueldo = Int32.Parse(Console.ReadLine());

                    // Comprobar que el dato introducido es válido.
                    if ((sueldo > sueldoMaximo) || (sueldo < 0)) {
                        Console.WriteLine("Error: El sueldo introducido no es válido");
                        ejec = true;
                    }
                }
                catch (System.IO.IOException e) {
                    Console.WriteLine("\nError en la entrada de datos.");
                    ejec = true;
                }
            } while (ejec);
        }
        
        public void setSueldoMaximo(int sueldoMaximo) {
            Empleado.sueldoMaximo = sueldoMaximo;
        }
        /*------------------------------------------------------------------------*/
        // Getters: 3/3
        
        public string getNombre() {
            return this.nombre;
        }
        
        public int getSueldo() {
            return this.sueldo;
        }
        
        public int getSueldoMaximo() {
            return Empleado.sueldoMaximo;
        }
        /*------------------------------------------------------------------------*/
        // Otros métodos: 1/1
        override
        public string ToString() {
            return this.nombre + "*" + this.sueldo + "*" + Empleado.sueldoMaximo;
        }
    }
}