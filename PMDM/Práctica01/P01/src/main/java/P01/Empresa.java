package P01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Empresa {
    // Atributos
    private Empleado emp1;
    private Empleado emp2;
    
    /*------------------------------------------------------------------------*/
    // Constructor: 1/1
    Empresa(Empleado ...empleados) {
        emp1 = empleados[0];
        emp2 = empleados[1];
    }
    
    /*------------------------------------------------------------------------*/
    // Métodos para el menú: 0/5
    public int menu() {
        int opcion = 0;
        
        // Menú
        System.out.println("\n1. Consultar sueldo");
        System.out.println("2. Consultar nombre");
        System.out.println("3. Cambiar nombre");
        System.out.println("4. Establecer sueldo");
        System.out.println("5. Listado de empleados");
        
        // Preguntar por la opción escogida
        /*
        Es un bloque try-catch dentro de un bucle do-while, para pedir el dato
        hasta que se introduzca correctamente.
        */
        boolean ejec;
        
        InputStreamReader stream = new InputStreamReader(System.in);
        BufferedReader teclado = new BufferedReader(stream);
        
        do {
            try {
                ejec = false;

                System.out.println("\n\tIntroduzca una opción:");
                opcion = Integer.parseInt(teclado.readLine());

                // Comprobar que el dato introducido es válido.
                if ((opcion <= 0) || (opcion > 5)) {
                    System.out.println("\nOpción no válida.\n");
                    ejec = true;
                }
            }
            catch (IOException e) {
                System.out.println("\nError en la entrada de datos.");
                ejec = true;
            }
        } while (ejec);
        
        return opcion;
    }
    
    public void ejecOpcion(Empleado emp, int opc) {
        switch(opc) {
            case 1 -> {
                System.out.println("Sueldo: " + emp.getSueldo());
            }
            case 2 ->  {
                System.out.println("Nombre: " + emp.getNombre());
            }
            case 3 ->  {
                emp.setNombre();
            }
            case 4 ->  {
                emp.setSueldo();
            }
            case 5 ->  {
                mostrarEmpleados();
            }
        }
    }
    
    public void mostrarEmpleados() {
        System.out.println(emp1);
        System.out.println(emp2);
    }
}
