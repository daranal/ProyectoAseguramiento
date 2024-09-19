/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Access;

import Conexion.Conexion;
import Models.Usuario;
import java.sql.ResultSet;

/**
 *
 * @author ivanp
 */
public class DAOLogin {

    static Conexion conexion = new Conexion();

    static public boolean Login(String User, String Password) throws ClassNotFoundException {
        String strSql = "";
        try {
            strSql = "SELECT * FROM USUARIOS WHERE (USUARIO='" + User + "' AND PASSWORD=HASHBYTES('SHA2_512','"+ Password +"'))";
            conexion.open();
            Usuario loggeduser = new Usuario();
            loggeduser.setUsuario(User);
            ResultSet rs = null;
            rs = conexion.executeQuery(strSql);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }

}
