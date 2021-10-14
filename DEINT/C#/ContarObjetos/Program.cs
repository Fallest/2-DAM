using System;
using System.Threading;

namespace ContarObjetos {
    public class Persona {
        public static int count = 0;
        private string nombre;
        private int edad;
         public Persona(string nombre, int edad) {
             Interlocked.Increment(ref count);
             this.nombre = nombre;
             this.edad = edad;
         }
         ~Persona() {
             Interlocked.Decrement(ref count);
         }
    }

    class Program {
        static void Main(string[] args) {
            GC.Collect();
            Thread.Sleep(1000);
            Console.WriteLine(ContarObjetos.Persona.count);
        }
        static void crear() {
            
            Persona p1 = new Persona("José Pérez", 23);
            Persona p2 = new Persona("María Antonieta", 42);
        }
    }
}
