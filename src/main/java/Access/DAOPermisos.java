/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Conexion.Conexion;
import Models.Permisos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author W10
 */
public class DAOPermisos {
    Permisos permiso = new Permisos();
    String strSql =  "";
    Conexion conexion = new Conexion();
    ResultSet rs = null;
    boolean respuesta = false;
    
    public boolean insertar(Permisos perm) {
         //Se prepara la sentencia SQL a ejecutar en la BD
         System.out.println(perm.getId_Modulo());
         int IDMOd=perm.getId_Modulo();
        strSql = " insert into permiso (ID_Modulo,ID_ROL, FECHA_CREA, FECHA_MOD, USUARIO_CREA, USUARIO_MOD, ACTIVO) values("+IDMOd
                +","+perm.getId_rol()
                +",GETDATE()"
                +",GETDATE()"
                +",'"+perm.getUsuario_crea()
                +"','"+perm.getUsuario_mod()
                +"','"+perm.getActivo()+"');";
        try {
            //se abre una conexión hacia la BD
            conexion.open();
            //Se ejecuta la instrucción y retorna si la ejecución fue satisfactoria
            respuesta = conexion.executeSql(strSql);
            //Se cierra la conexión hacia la BD
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOPermisos.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(DAOPermisos.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }
    
        public boolean eliminar(int IDPermiso) {
         //Se prepara la sentencia SQL a ejecutar en la BD
        strSql = "delete from permiso where ID_PERMISO = " + IDPermiso;
        
        try {
            //se abre una conexión hacia la BD
            conexion.open();
            //Se ejecuta la instrucción y retorna si la ejecución fue satisfactoria
            respuesta = conexion.executeSql(strSql);
            //Se cierra la conexión hacia la BD
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Permisos.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(Permisos.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }
        
    public List listarPermisos() throws SQLException, Exception {

        ArrayList<Permisos>lstPermisos = new ArrayList<>();
         try {
            strSql = "SELECT * FROM PERMISO;";
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {
                Permisos perm = new Permisos();
                perm.setId_Permiso(rs.getInt("ID_PERMISO"));
                perm.setId_modulo(rs.getInt("ID_MODULO"));
                perm.setId_rol(rs.getInt("ID_ROL"));
                perm.setFecha_crea(rs.getString("FECHA_CREA"));
                perm.setFecha_mod(rs.getString("FECHA_MOD"));
                perm.setUsuario_crea(rs.getString("USUARIO_CREA"));
                perm.setUsuario_mod(rs.getString("USUARIO_MOD"));
                perm.setActivo(rs.getString("ACTIVO"));
                lstPermisos.add(perm);
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Permisos.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return lstPermisos;
    }
    
}
