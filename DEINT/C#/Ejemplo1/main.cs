using System;

namespace Ejemplo1 {

    public class main {

        public static void Main () {

            Empleado emp1 = new Empleado("Antonio Pérez", 1250, 1600);
            Empleado emp2 = new Empleado("María Antonieta", 1400, 1600);
            
            Empresa empresa = new Empresa(emp1, emp2);
            
            int opc = empresa.menu();
            empresa.ejecOpcion(emp1, opc);
            Console.WriteLine("Empleado 1: " + emp1);
            
            opc = empresa.menu();
            empresa.ejecOpcion(emp2, opc);
            Console.WriteLine("Empleado 2: " + emp2);
        }
    }
}