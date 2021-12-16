package Controlador;

/**
 * Clase para gestionar el JList con los movimientos.
 */
import Modelo.Movimiento;
import java.sql.*;
import java.util.ArrayList;

public class GestorListado {

    // Atributos
    private static Connection con;
    private static PreparedStatement pstmt;
    private static ResultSet rset;
    private static String sql;

    // Constructor
    public GestorListado() {
        prepararStmt();
    }
    
    // Para preparar el stmt
    public static void prepararStmt() {
        try {
            con = Conexion.getConnection();
            sql = "select * from banco.movimiento "
                    + "where cueNumero in ("
                    + "select numero from banco.cuenta"
                    + "where cliCodigo = ?"
                    + ")";
            pstmt = con.prepareStatement(sql);
        } catch (SQLException ex) {
            System.out.println("Error en GestorListado.GestorListado()");
        }
    }

    // Ejecutar el PreparedStatement
    public ArrayList<Movimiento> execute(int codCli) {
        ArrayList<Movimiento> list = new ArrayList<>();

        try {
            pstmt.setInt(1, codCli);
            rset = pstmt.executeQuery();

            while (rset.next()) {
                list.add(new Movimiento(
                        rset.getInt(1),
                        rset.getInt(2),
                        rset.getDate(3),
                        rset.getFloat(4),
                        rset.getString(5)
                ));
            }

        } catch (SQLException ex) {
            System.out.println("Error en GestorListado.execute()");
        }

        return list;
    }
    
    // Para cerrar el Prepared Statement
    public static void finalizar() {
        Conexion.close(rset);
        Conexion.close(pstmt);
        Conexion.close(con);
    }
}
