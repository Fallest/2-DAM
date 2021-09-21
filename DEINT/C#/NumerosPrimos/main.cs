using System;
using System.Linq;

namespace NumerosPrimos {

    class Program {

        static void Main(string[] args) {
            /*
            Este programa tiene que mostrar todos los números primos
            que haya hasta un número límite introducido por el usuario.
            */
            do {
            int numTop = askNum();
            showPrimes(numTop);
            } while(askAgain());
        }

        // Función para pedir el número
        static int askNum() {
            int num;
            
            do {
                Console.WriteLine("Escribe el número que deseas establecer como límite: ");
            } while(!(Int32.TryParse(Console.ReadLine(), out num)));
            /*
            Int32.TryParse(string s, out int n)
            Esta función va a devolver un bool.
            Comprueba si puede hacerse la transformación de string a int.
            Si puede hacerse, devuelve true y almacena en el int n el valor de la conversión.
            Si no puede hacerse, devuelve false, y devuelve n con su valor por defecto.
            Al hacer el bucle while, me aseguro de que a num se le va a dar un valor válido sí o sí,
            por eso no le pongo ningún valor por defecto a num al declarar la variable.
            */
            
            return num;
        }

        static void showPrimes(int numTop) {
            /*
            Esta es la función que va a mostrar todos los números primos desde el 1 hasta el número
            indicado como el límite (numTop).
            Lo hará de la siguiente forma:
                -Entrará en un bucle en el que irá recorriendo todos los números enteros en el rango [1, numTop]
                -Para cada número n en ese rango, comprobará si es divisible (con resto 0) por otro número que no
                    sea 1 ni ese número n.
                -Si el anterior paso se cumple (es divisible solo por 1 y n), lo mostrará por pantalla.
                -Si no se cumple, continuará con el bucle.
            */

            bool prime;
            int count = 0;
            foreach (int n in Enumerable.Range(1, numTop)) {
                prime = true;
                foreach (int i in Enumerable.Range(1, n)) {
                    if ((n%i == 0) && (i != 1) && (i != n)) {
                        prime = false;
                        break;
                    }
                }
                if (prime is true) {
                    count++;
                    Console.WriteLine($"Número primo {count}: {n}");
                } 
            }
        }

        static bool askAgain() {
            Console.WriteLine("¿Quieres consultar otro número (s/n)?: ");
            string ask = Console.ReadLine();

            switch(ask) {
                case "s": {
                    return true;
                    break;
                }
                case "n": {
                    return false;
                    break;
                }
                default: {
                    return askAgain();
                    break;
                }
            }
        }
    }
}
