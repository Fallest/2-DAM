using System;

namespace NumerosPrimos {
    static class Extensiones {
        // this string <- es para que el primer parámetro sea ¡, literalmente, el objeto de la clase.
        // por ejemplo: cadena.ConvertirEntero(3);
        // donde cadena es "this string cad" y 3 es "int porDefecto"
        public static int ConvertirEntero(this string cad, int porDefecto) {
            int n = 0;
            if (int.TryParse(cad, out n))
                return n;
            else return porDefecto;
        }
    }

}