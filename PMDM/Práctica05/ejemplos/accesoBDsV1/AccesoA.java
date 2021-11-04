/* Acesso a la base de datos:
 * - Con recorrido lineal -> nex()
 * - Con Statemen de s�lo lectura -> createStatement() (sin p�rametros. Opciones por defecto.
 * - Actualizaciones, Borrados, e inserciones directas a la BDs y no al ResulSet.
 */

package accesoBDsV1;
import java.sql.*;

public class AccesoA {
  public static void main (String args[]) {
    try {
      Class.forName("org.apache.derby.jdbc.ClientDriver");
      //Class.forName("oracle.jdbc.driver.OracleDriver");
      //Class.forName("com.inet.csv.CsvDriver");
    }
    catch (Exception e) {
        System.out.println("JDBC driver falied to load.");
        return;
    }

    try {
      // en Servidor remoto Connection con = DriverManager.getConnection("jdbc:oracle:thin:@server:1521:infor","banquero","banquero");
      Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/empresa","empresa","empresa");
      //Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","banquero","banquero");
      //Connection con = DriverManager.getConnection("jdbc:odbc:DB","","");
      System.out.println("Conectado");
      
      /* 
         Con esta versi�n, sin par�metros, del m�todo createStatement(), creamos un objeto Statement
         que producur� un ResultSet que s�lo se puede recorrer hacia delante con next()
       */
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM empleado WHERE apellido = 'GARCIA'");
      ResultSetMetaData rsmd = rs.getMetaData();
      int numCols = rsmd.getColumnCount();

      while (rs.next()) {
        for (int i = 1; i <= numCols; i++) {
          System.out.print(rs.getString(i) + " ");
        }
        System.out.println("");
      }
        

      /*int count = stmt.executeUpdate("INSERT INTO  " +
                                            "VALUES (1031,'Andrés','PEREZ','default.jpg',2500,3000,'10/17/2021')");
      System.out.print("Insetadas: " + count + "filas");*/
      
      rs.close();
      stmt.close();
      con.close();

    }
    catch (Exception e) {
      System.out.println(e);
    }
  }
}