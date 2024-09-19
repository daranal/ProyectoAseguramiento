package Access;

import Conexion.Conexion;
import Models.Roles;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAORoles {

    Roles rol = new Roles();
    String strSql = "";
    Conexion conexion = new Conexion();
    ResultSet rs = null;
    boolean respuesta = false;

    public List listarRoles() throws SQLException, Exception {
        ArrayList<Roles> lstRoles = new ArrayList<>();
        try {
            strSql = "SELECT * FROM ROL";
            conexion.open();
            rs = conexion.executeQuery(strSql);

            while (rs.next()) {
                Roles r = new Roles();
                r.setIdRol(rs.getInt("ID_ROL"));
                r.setNombre(rs.getString("NOMBRE"));
                r.setDescripcion(rs.getString("DESCRIPCION"));
                r.setActivo(rs.getString("ACTIVO"));
                r.setUsuario_crea(rs.getString("USUARIO_CREA"));
                r.setUsuario_mod(rs.getString("USUARIO_MOD"));
                r.setFecha_crea(rs.getString("FECHA_CREA"));
                r.setFecha_mod(rs.getString("FECHA_MOD"));
                lstRoles.add(r);
            }
            rs.close();
            conexion.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAORoles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstRoles;
    }

    public Roles CargarRol(int id) {
        try {
            strSql = "SELECT * FROM ROL WHERE ID_ROL = " + id;
            conexion.open();
            rs = conexion.executeQuery(strSql);

            while (rs.next()) {
                rol.setIdRol(rs.getInt("ID_ROL"));
                rol.setNombre(rs.getString("NOMBRE"));
                rol.setDescripcion(rs.getString("DESCRIPCION"));
                rol.setActivo(rs.getString("ACTIVO"));
                rol.setUsuario_crea(rs.getString("USUARIO_CREA"));
                rol.setUsuario_mod(rs.getString("USUARIO_MOD"));
                rol.setFecha_crea(rs.getString("FECHA_CREA"));
                rol.setFecha_mod(rs.getString("FECHA_MOD"));
            }
            rs.close();
            conexion.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAORoles.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DAORoles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rol;
    }
    
    public boolean insertar(Roles r) {
        strSql = "INSERT INTO ROL VALUES (" 
                + "'" + r.getNombre()
                + "','" + r.getDescripcion()
                + "','" + r.getActivo()
                + "','" + r.getUsuario_crea()
                + "','" + r.getUsuario_mod()
                + "',GETDATE()"
                + ",GETDATE())";
        System.out.println(strSql);
        try {
            conexion.open();
            respuesta = conexion.executeSql(strSql);
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAORoles.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (Exception ex) {
            Logger.getLogger(DAORoles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    public boolean modificar(Roles r) {
        strSql = "UPDATE ROL SET "
                + "NOMBRE = '" + r.getNombre()
                + "', DESCRIPCION = '" + r.getDescripcion()
                + "', FECHA_MOD = '" + r.getFecha_mod()
                + "', USUARIO_MOD = '" + r.getUsuario_mod()
                + "', ACTIVO = '" + r.getActivo()
                + "' WHERE ID_ROL = " + r.getIdRol();
        try {
            conexion.open();
            respuesta = conexion.executeSql(strSql);
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAORoles.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (Exception ex) {
            Logger.getLogger(DAORoles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    public boolean eliminar(Roles r) {
        strSql = "DELETE FROM ROL WHERE ID_ROL = " + r.getIdRol();
        try {
            conexion.open();
            respuesta = conexion.executeSql(strSql);
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAORoles.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (Exception ex) {
            Logger.getLogger(DAORoles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
}
