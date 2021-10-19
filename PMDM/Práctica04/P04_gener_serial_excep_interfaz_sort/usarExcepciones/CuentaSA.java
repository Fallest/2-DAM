package usarExcepciones;

/**
 * Esta clase representa estructuras de tipo
 * cuenta bancaria.
 *
 * Cada objeto de esta clase contendrá las
 * siguientes variables:
 * 	- Nombre del titular (tipo String)
 *	- Cantidad DEBE (tipo entero)
 *	- Cantidad HABER (tipo entero)
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

   public CuentaSA(String titular, int imposicionInicial)  {
      titularCuenta = titular;
      debeCuenta    = 0;
      haberCuenta   = imposicionInicial;
   }

   public int getDebe () {
      return debeCuenta;
   }

   public int getHaber () {
      return haberCuenta;
   }

      /** Devuelve el saldo */
   public int getSaldo () {
      return haberCuenta - debeCuenta;
   }

      /** Método toString para la     *
        * impresión del contenido de  *
	* de una cuenta               */
   public String toString() {
      String imprime = "   #>CUENTA: " +
			titularCuenta;
      imprime += "\n\tDebe : " + getDebe();
      imprime += "\n\tHaber: " + getHaber();
      imprime += "\n\tSaldo: " + getSaldo();
      return imprime;
   }


      /* Método Imposición */
   public boolean Imposicion(int cantidad) {
	haberCuenta += cantidad;
	return true;
   }

      /* Método Reintegro */
   public void Reintegro(int cantidad) throws ESaldoNoValido{
	if (getSaldo() >= cantidad) {
		debeCuenta += cantidad;
	} else
		throw new ESaldoNoValido("Error: saldo Negativo");
   }

}

/******** Fin de CuentaSA.java ***************/
