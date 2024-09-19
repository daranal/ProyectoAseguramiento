package Conexion;

/**
 *
 * @author ivanp
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

       static private PreparedStatement preparar = null;
    static private Connection coneccion = null;
    static private ResultSet resultado = null;
    static String stringConnectionUrl = "jdbc:sqlserver://proyectosazure.database.windows.net:1433;" +
                                "databaseName=ProyectoAseguramiento;";
    static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static Connection con = null;
    static private boolean respuesta = false;

    static public Connection open() throws ClassNotFoundException {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(stringConnectionUrl, "adminSergio", "Umg$2024");
        } catch (SQLException e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Excepción: " + e.getMessage());
        }
        return con;
    }

    static public void close() throws Exception {
        try {
            if (con != null) {
                con.clearWarnings();
                con.close();
            }
        } catch (SQLException e) {
            con = null;
            throw new Exception(e.getMessage());
        }
    }

    public boolean executeSql(String strSQL) throws Exception {
        if (strSQL != null) {
            try {
                this.preparar = this.con.prepareStatement(strSQL);
                this.preparar.executeUpdate();
                respuesta = true;
            } catch (SQLException e) {
                throw new Exception(e.getMessage());
            }
        } else {
            throw new Exception("El comando a ejecutar es nulo!");
        }
        return respuesta;
    }

    public ResultSet executeQuery(String strSQL) {
        if (strSQL != null) {
            try {
                preparar = con.prepareStatement(strSQL);
                resultado = preparar.executeQuery();
            } catch (SQLException e) {
                System.out.println("Error al ejecutar el query en Clase: Conexion: " + e.toString());
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return resultado;
    }
}
