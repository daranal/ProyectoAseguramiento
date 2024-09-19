/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Access.DAOMain;
import Models.Permisos;
import Models.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ivanp
 */

@WebServlet(name = "ControllerMain", urlPatterns = {"/ControllerMain"})

public class ControllerMain extends HttpServlet {

    protected void listPermissions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (session.getAttribute("UsuarioSesion") == null) {
            System.out.println("User not logged in");
            RequestDispatcher vista = request.getRequestDispatcher("/Login.jsp"); 
            vista.forward(request, response);
        } else {
            DAOMain permisos = new DAOMain();
            request.getAttribute("user");
            request.getAttribute("password");
            List<Permisos> listapermisos = new ArrayList<Permisos>();
            
            HttpSession sessionUser = request.getSession();
            sessionUser.getAttribute("UsuarioSesion");
            String IDRol = sessionUser.getAttribute("UsuarioSesion").toString();
            
            try {
                listapermisos = permisos.listpermisos(permisos.SelectRoles(IDRol));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(IDRol);
            request.setAttribute("permisos", listapermisos);
            int max = Integer.MIN_VALUE;
            int count = 1;
            for (int i = 0; i < listapermisos.size(); i++) {
                if (listapermisos.get(i).getNivel() > max) {
                    max = listapermisos.get(i).getNivel();
                    count = 1;
                } else if (listapermisos.get(i).getNivel() == max) {
                    count++;
                }
            }
        }
    }
}
