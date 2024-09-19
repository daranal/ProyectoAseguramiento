/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Conexion.Conexion;
import static Access.DAOLogin.conexion;
import Models.Permisos;
import Models.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author ivanp
 */
public class DAOMain {

    static Conexion conexion = new Conexion();

    static public List<Permisos> listpermisos(int id_rol) {
        List<Permisos> listapermisos = new ArrayList<Permisos>();
        try {
            conexion.open();
            ResultSet rs = null;
            String strSql = "SELECT * FROM PERMISO inner join MODULO on PERMISO.ID_MODULO = MODULO.ID_MODULO Where PERMISO.ID_ROL = " + id_rol + "";
            rs = conexion.executeQuery(strSql);
            while (rs.next()) {
                Permisos permiso = new Permisos();
                permiso.setId_Permiso(rs.getInt(1));
                permiso.setId_Modulo(rs.getInt(2));
                permiso.setNombre(rs.getString(10));
                permiso.setNivel(rs.getInt(13));
                permiso.setPath(rs.getString(12));
                permiso.setId_modpadre(rs.getInt(15));
                listapermisos.add(permiso);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return listapermisos;
    }
    static public Usuario user()
    {
        
        return null;
    }
    
    public static int SelectRoles(String User) throws ClassNotFoundException {
        String strSql = "";
        int rol = 0;
        try {
            strSql = "SELECT ID_ROL FROM USUARIOS WHERE USUARIO='" + User +"'";
            conexion.open();
            Usuario loggeduser = new Usuario();
            ResultSet rs = null;
            rs = conexion.executeQuery(strSql);
            
            
            if (rs.next()) {
                rol = rs.getInt("ID_ROL");
            } else {
                rol= 0;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return rol;
    }
}
