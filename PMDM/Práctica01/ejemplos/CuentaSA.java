/*********************************************
 * Jose F. Quesada                           *
 *                Curso de Programación Java *
 * Capitulo03/CuentaSA.java                  *
 *********************************************/

 /**
 * Esta clase representa estructuras de tipo
 * cuenta bancaria.
 *
 * Cada objeto de esta clase contendrá las
 * siguientes variables:
 * 	- Nombre del titular (tipo String)
 *	- Cantidad DEBE (tipo entero)
 *	- Cantidad HABER (tipo entero)
 *
 * Los métodos soportados por esta clase son:
 *   * Constructores
 *	- CuentaSA (titular)
 *	- CuentaSA (titular,haberInicial)
 *   * Acceso a campos
 *	- Debe
 *	- Haber
 *	- Saldo
 *   * Operaciones sobre la cuenta
 *	- Reintegro
 *	- Imposicion
 *
 * @version 1.00
 * @author Jose F. Quesada
 */

class CuentaSA {
   private String titularCuenta;
   private int    debeCuenta;
   private int    haberCuenta;

      /** Constructor básico          *
        *  - Requiere necesariamente  *
        *    un titular               */
   public CuentaSA(String titular) {
      titularCuenta = titular;
      debeCuenta    = 0;
      haberCuenta   = 0;
   }

      /** Constructor auxiliar        *
        *  - Permite la asignacion    *
        *    de un haber inicial      */

      /** Constructor general con argumentos para
	* la inicialización del objeto */
   public CuentaSA(String titular,
                   int imposicionInicial)  {
      titularCuenta = titular;
      debeCuenta    = 0;
      haberCuenta   = imposicionInicial;
   }

      /** Funciones de acceso a los   *
        * campos privados de la clase */

      /** Devuelve el valor del debe  */
   public int Debe () {
      return debeCuenta;
   }

      /** Devuelve el valor del haber  */
   public int Haber () {
      return haberCuenta;
   }

      /** Devuelve el saldo */
   public int Saldo () {
      return haberCuenta - debeCuenta;
   }

      /** Método toString para la     *
        * impresión del contenido de  *
	* de una cuenta               */
   public String toString() {
      String imprime = "   #>CUENTA: " +
			titularCuenta;
      imprime += "\n\tDebe : " + Debe();
      imprime += "\n\tHaber: " + Haber();
      imprime += "\n\tSaldo: " + Saldo();
      return imprime;
   }

    public void descripcionCompleta() {
        System.out.println("Descripcion completa: ");
        System.out.println("   Titular : " + titularCuenta);
        System.out.println("   Debe : " + debeCuenta);
        System.out.println("   Haber : " + haberCuenta);
        System.out.println("   Saldo : " + Saldo() );
    }

      /* Método Imposición */
   public boolean Imposicion(int cantidad) {
	haberCuenta += cantidad;
	return true;
   }

      /* Método Reintegro */
   public boolean Reintegro(int cantidad) {
	if (Saldo() >= cantidad) {
		debeCuenta += cantidad;
		return true;
	} else
		return false;
   }

}

/******** Fin de CuentaSA.java ***************/
