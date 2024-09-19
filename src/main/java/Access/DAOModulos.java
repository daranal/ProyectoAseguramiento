/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Conexion.Conexion;
import Models.Modulos;
import Models.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author W10
 */
public class DAOModulos {
    Modulos modulo = new Modulos();
    Usuario user = new Usuario();
    String strSql =  "";
    Conexion conexion = new Conexion();
    ResultSet rs = null;
    boolean respuesta = false;
    
    //@Override
    public List listarModulos() throws SQLException, Exception {

        ArrayList<Modulos>lstModulos = new ArrayList<>();
         try {
            strSql = "SELECT * FROM MODULO;";
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {
                Modulos mod = new Modulos();
                mod.setId_modulo(rs.getInt("ID_MODULO"));
                mod.setNombre(rs.getString("NOMBRE"));
                mod.setDescripcion(rs.getString("DESCRIPCION"));
                mod.setPath(rs.getString("PATH"));
                mod.setNivel(rs.getInt("NIVEL"));
                mod.setOrden(rs.getString("ORDEN"));
                mod.setId_modpadre(rs.getInt("ID_MODULO_PADRE"));
                mod.setFecha_crea(rs.getString("FECHA_CREA"));
                mod.setFecha_mod(rs.getString("FECHA_MOD"));
                mod.setUsuario_crea(rs.getString("USUARIO_CREA"));
                mod.setUsuario_mod(rs.getString("USUARIO_MOD"));
                mod.setActivo(rs.getString("ACTIVO"));
                lstModulos.add(mod);
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Modulos.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return lstModulos;
    }
    
    public Modulos CargarModulo(int id) {
        try {            
            strSql = "SELECT * FROM MODULO WHERE ID_MODULO = " + id;
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {                
                modulo.setId_modulo(rs.getInt("ID_MODULO"));
                modulo.setNombre(rs.getString("NOMBRE"));
                modulo.setDescripcion(rs.getString("DESCRIPCION"));
                modulo.setPath(rs.getString("PATH"));
                modulo.setNivel(rs.getInt("NIVEL"));
                modulo.setOrden(rs.getString("ORDEN"));
                modulo.setId_modpadre(rs.getInt("ID_MODULO_PADRE"));
                modulo.setFecha_crea(rs.getString("FECHA_CREA"));
                modulo.setFecha_mod(rs.getString("FECHA_MOD"));
                modulo.setUsuario_crea(rs.getString("USUARIO_CREA"));
                modulo.setUsuario_mod(rs.getString("USUARIO_MOD"));
                modulo.setActivo(rs.getString("ACTIVO"));                
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOModulos.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DAOModulos.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return modulo;
    }
    
    public boolean eliminar(int IDModulo) {
         //Se prepara la sentencia SQL a ejecutar en la BD
        strSql = "DELETE MODULO WHERE ID_MODULO = " + IDModulo;
        
        try {
            //se abre una conexión hacia la BD
            conexion.open();
            //Se ejecuta la instrucción y retorna si la ejecución fue satisfactoria
            respuesta = conexion.executeSql(strSql);
            //Se cierra la conexión hacia la BD
            conexion.close();
             
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Modulos.class.getName()).log(Level.SEVERE, null, ex);     
            return false;
        } catch(Exception ex){
            Logger.getLogger(Modulos.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }
    
    public boolean insertar(Modulos mod) {
         //Se prepara la sentencia SQL a ejecutar en la BD
        strSql = "INSERT INTO MODULO (NOMBRE, DESCRIPCION, PATH, NIVEL, ORDEN, ID_MODULO_PADRE, FECHA_CREA, FECHA_MOD, USUARIO_CREA, USUARIO_MOD, ACTIVO) VALUES ('"+mod.getNombre()
                +"','"+mod.getDescripcion()
                +"','"+mod.getPath()
                +"',"+mod.getNivel()
                +",'"+mod.getOrden()
                +"','"+mod.getId_modpadre()
                +"',GETDATE()"
                +",GETDATE()"
                +",'"+ mod.getUsuario_crea()
                +"','"+mod.getUsuario_mod()
                +"','"+mod.getActivo()+"');";

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
        } catch(Exception ex){
            Logger.getLogger(DAOModulos.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }
    
    public boolean modificar(Modulos modulo) {
         //Se prepara la sentencia SQL a ejecutar en la BD
         
        strSql = "UPDATE MODULO SET "
                + "NOMBRE = '"+modulo.getNombre()
                +"', DESCRIPCION = '"+modulo.getDescripcion()
                +"', PATH = '"+modulo.getPath()
                +"', NIVEL = "+modulo.getNivel()
                +", ORDEN = '"+modulo.getOrden()
                +"', ID_MODULO_PADRE = '"+modulo.getId_modpadre()
                +"', FECHA_MOD = GETDATE()"
                +", USUARIO_MOD = '"+ modulo.getUsuario_mod()
                +"', ACTIVO = '"+modulo.getActivo()
                +"' WHERE ID_MODULO = "+modulo.getId_modulo()+";";
        
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
        } catch(Exception ex){
            Logger.getLogger(DAOModulos.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return respuesta;
    }
    
    public List listarModulosPadres() throws SQLException, Exception {
        //jdbcTransaction();
        ArrayList<Modulos>lstModulos = new ArrayList<>();
         try {
            strSql = "select * from MODULO where nivel = 1";
            conexion.open();
            rs = conexion.executeQuery(strSql);                             

            while (rs.next()) {
                Modulos mod = new Modulos();
                    mod.setId_modulo(rs.getInt("ID_MODULO"));
                    mod.setNombre(rs.getString("NOMBRE"));
                    mod.setDescripcion(rs.getString("DESCRIPCION"));
                    mod.setPath(rs.getString("PATH"));
                    mod.setNivel(rs.getInt("NIVEL"));
                    mod.setOrden(rs.getString("ORDEN"));
                    mod.setId_modpadre(rs.getInt("ID_MODULO_PADRE"));
                    mod.setFecha_crea(rs.getString("FECHA_CREA"));
                    mod.setFecha_mod(rs.getString("FECHA_MOD"));
                    mod.setUsuario_crea(rs.getString("USUARIO_CREA"));
                    mod.setUsuario_mod(rs.getString("USUARIO_MOD"));
                    mod.setActivo(rs.getString("ACTIVO"));
                    lstModulos.add(mod);  

            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Modulos.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return lstModulos;
    }
    
    public int cargarMaxIDPadre () {
        int getMaxID =0;
        try {            
            strSql = "select MAX(ID_MODULO_PADRE) from MODULO where nivel = 1 ";
            conexion.open();
            rs = conexion.executeQuery(strSql);                             
            
            while (rs.next()) {                
                getMaxID = rs.getInt(1);
            }
            rs.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOModulos.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(Exception ex){
            Logger.getLogger(DAOModulos.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
         return getMaxID;
    }
    
    
}
