/* Acesso a la base de datos:
   - Con recorrido aleatorio o ResultSet arrastable (ResultSet.TYPE_SCROLL_INSENSITIVE 
                                                     y ResultSet.TYPE_SCROLL_SENSITIVE)
   - Con Statemen de s�lo lectura -> createStatement() (sin p�rametros. Opciones por defecto.
   - Actualizaciones, Borrados, e inserciones directas a la BDs y no al ResulSet.
 */

package accesoBDsV2;

import java.sql.*;
import java.io.*;

public class ScrollingDemo {
  public static void main(String[] args) {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rset = null;

    try {
      conn = ConnectionFactory.getConnection();
      /* Los resultSet puede ser (Primer par�metro de createStatement):
         - Por defecto (lineal o no arrastable / Sólo hacia adelante) -> ResultSet.TYPE_FORWARD_ONLY
         - Insensible a los cambios de la BDs -> ResultSet.TYPE_SCROLL_INSENSITIVE 
         - Sensible a los cambios de la BDs -> Con ResultSet.TYPE_SCROLL_SENSITIVE
         El segundo par�metro de createStatement establece el tipo de concurrencia
       */
      stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                  ResultSet.CONCUR_READ_ONLY);
      String sql = "SELECT * FROM empleado " 
                   + "ORDER BY apellido";
      rset = stmt.executeQuery(sql);

      if (rset.next()) {
        rset.beforeFirst();
        boolean done = false;
        BufferedReader in = 
          new BufferedReader(new InputStreamReader(System.in));
        while (!done) {
          System.out.println("\nOpciones: F (first), N (next), P (previous), " 
                           + "L (last), Q (quit) : ");
          String s = in.readLine();
          boolean result = false;
          if (s.equalsIgnoreCase("F")) {
            result = rset.first();
          } else if (s.equalsIgnoreCase("N")) {
            result = rset.next();
          } else if (s.equalsIgnoreCase("P")) {
            result = rset.previous();
          } else if (s.equalsIgnoreCase("L")) {
            result = rset.last();
          } else if (s.equalsIgnoreCase("Q")) {
            done = true;
          } 
          if (done) {
            break;
          } 
          if (result) {
            System.out.println("CURSOR movido a la fila " + rset.getRow());
            System.out.println("NUMERO: " + rset.getInt(1) 
                               + ", NOMBRE: " + rset.getString(2) 
                               + ", APELLIDO: " + rset.getString(3)
                               + ", FOTO: " + rset.getString(4)
                               + ", SUELDO: " + rset.getFloat(5)
                               + ", SUELDO-MAXIMO: " + rset.getFloat(6)
                               + ", FECHA-ALTA: " + rset.getDate(7)
            );
            
          } else {
            System.out.println("NO se puede mover el cursor");
          } 
        } 
        in.close();
      } else {
        System.out.println("ResultSet sin filas");
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } 
    finally {
      ConnectionFactory.close(rset);
      ConnectionFactory.close(stmt);
      ConnectionFactory.close(conn);
    }
  }
}
