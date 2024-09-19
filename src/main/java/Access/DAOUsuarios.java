/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Conexion.Conexion;
import Models.Modulos;
import Models.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author richi
 */
public class DAOUsuarios {

    Usuario u = new Usuario();
    String strSql = "";
    Conexion conexion = new Conexion();
    ResultSet rs = null;
    boolean respuesta = false;

    public List listar() throws SQLException, Exception {
        List<Usuario> lista = new ArrayList();
        try {
            strSql = "SELECT * FROM USUARIOS;";
            conexion.open();
            rs = conexion.executeQuery(strSql);
            while (rs.next()) {
                Usuario l = new Usuario();
                l.setIdUsuario(rs.getInt("ID_USUARIO"));
                l.setNombre(rs.getString("NOMBRE"));
                l.setApellido(rs.getString("APELLIDO"));
                l.setUsuario(rs.getString("USUARIO"));
                l.setPass(rs.getString("PASSWORD"));
                l.setIdRol(rs.getInt("ID_ROL"));
                l.setFechacrea(rs.getString("FECHA_CREA"));
                l.setActivo(rs.getString("ACTIVO"));
                l.setFechamod(rs.getString("FECHA_MOD"));

                lista.add(l);
            }
            rs.close();
            conexion.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public Usuario CargarUsuario(int id) {
        try {
            strSql = "SELECT * FROM USUARIOS WHERE ID_USUARIO = " + id;
            conexion.open();
            rs = conexion.executeQuery(strSql);

            while (rs.next()) {
                u.setIdUsuario(rs.getInt("ID_USUARIO"));
                u.setNombre(rs.getString("NOMBRE"));
                u.setApellido(rs.getString("APELLIDO"));
                u.setUsuario(rs.getString("USUARIO"));
                u.setPass(rs.getString("PASSWORD"));
                u.setIdRol(rs.getInt("ID_ROL"));
                u.setFechacrea(rs.getString("FECHA_CREA"));
                u.setActivo(rs.getString("ACTIVO"));
                u.setFechamod(rs.getString("FECHA_MOD"));

            }
            rs.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DAOUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }

        return u;
    }
    
        public boolean eliminar(int IdUsuario) {
         //Se prepara la sentencia SQL a ejecutar en la BD
        strSql = "DELETE USUARIOS WHERE ID_USUARIO = " + IdUsuario;
        
        try {
            //se abre una conexión hacia la BD
            conexion.open();
            //Se ejecuta la instrucción y retorna si la ejecución fue satisfactoria
            respuesta = conexion.executeSql(strSql);
            //Se cierra la conexión hacia la BD
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }

    public boolean insertar(Usuario u) {
        //Se prepara la sentencia SQL a ejecutar en la BD
        strSql = "INSERT INTO USUARIOS (NOMBRE, APELLIDO, USUARIO, PASSWORD, ID_ROL, FECHA_CREA,ACTIVO, FECHA_MOD)"
                + "VALUES ('" + u.getNombre() + "','" + u.getApellido() + "', '" + u.getUsuario() + "', HASHBYTES('SHA2_512','" + u.getPass() + "'), " + u.getIdRol() + ", GETDATE(),'" + u.getActivo() + "', GETDATE())";

        try {
            //se abre una conexión hacia la BD
            conexion.open();
            //Se ejecuta la instrucción y retorna si la ejecución fue satisfactoria
            respuesta = conexion.executeSql(strSql);
            //Se cierra la conexión hacia la BD
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOModulos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (Exception ex) {
            Logger.getLogger(DAOModulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
       public boolean modificar(Usuario u) {
         //Se prepara la sentencia SQL a ejecutar en la BD
         
        strSql = "UPDATE USUARIOS SET NOMBRE='"+u.getNombre()+"', APELLIDO='"+u.getApellido()+"', USUARIO='"+u.getUsuario()+"',"
                + "PASSWORD=HASHBYTES('SHA2_512','"+u.getPass()+"'), ID_ROL="+u.getIdRol()+", FECHA_CREA=GETDATE(),ACTIVO='"+u.getActivo()+"',"
                + "FECHA_MOD=GETDATE() WHERE ID_USUARIO="+u.getIdUsuario()+";";
        
        try {
            //se abre una conexión hacia la BD
            conexion.open();
            //Se ejecuta la instrucción y retorna si la ejecución fue satisfactoria
            respuesta = conexion.executeSql(strSql);
            //Se cierra la conexión hacia la BD
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOUsuarios.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DAOUsuarios.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }
}
