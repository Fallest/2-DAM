package Ejemplos;

import java.sql.*;

public class ConnectionFactory {
  private static ConnectionFactory ref = new ConnectionFactory();
  private ConnectionFactory() {
    try {
      Class.forName("org.apache.derby.jdbc.ClientDriver");	
    } catch (ClassNotFoundException e) {
      System.out.println("ERROR: exception loading driver class");
    } 
  }

  public static Connection getConnection() throws SQLException {
    String url = "jdbc:derby://localhost:1527/Empresa";
	return DriverManager.getConnection(url,"empresa","empresa");
  } 

  public static void close(ResultSet rs) {
    try {
      rs.close();
    } catch (Exception ignored) {}
  } 

  public static void close(Statement stmt) {
    try {
      stmt.close();
    } catch (Exception ignored) {}
  } 

  public static void close(Connection conn) {
    try {
      conn.close();
    } catch (Exception ignored) {}
  } 
}