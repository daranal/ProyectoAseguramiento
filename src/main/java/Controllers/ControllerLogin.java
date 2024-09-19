/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Access.DAOLogin;
import Models.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ivanp
 */
@WebServlet(name = "ControllerLogin", urlPatterns = {"/ControllerLogin"})

public class ControllerLogin extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("accion");
        switch (action) {
            case "Login":
                boolean loggedin = false;
                 {
                    try {
                        String User = request.getParameter("user");
                        String Password = request.getParameter("password");
                        loggedin = DAOLogin.Login(User, Password);
                        if (loggedin == true) {
                            HttpSession session = request.getSession();
                            session.setAttribute("UsuarioSesion", User);
                            response.sendRedirect("Paginas/Principal.jsp");
                            Usuario user = new Usuario();
                            user.setUsuario(User);
                            //System.out.println(user.getUsuario());
                        } else {
                            response.sendRedirect("Login.jsp");
                        }
                    } catch (ClassNotFoundException ex) {
                        System.out.println(ex.toString());
                    }
                }
                break;
            case "cambio":
                response.sendRedirect("www.google.com");
            break;
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("accion");
        switch (action) {
            case "Login":
                boolean loggedin = false;
                 {
                    try {
                        String User = request.getParameter("user");
                        String Password = request.getParameter("password");
                        loggedin = DAOLogin.Login(User, Password);
                        if (loggedin == true) {
                            HttpSession session = request.getSession();
                            session.setAttribute("UsuarioSesion", User);
                            response.sendRedirect("Paginas/Principal.jsp");
                            Usuario user = new Usuario();
                            user.setUsuario(User);
                            //System.out.println(user.getUsuario());
                        } else {
                            response.sendRedirect("Login.jsp");
                        }
                    } catch (ClassNotFoundException ex) {
                        System.out.println(ex.toString());
                    }
                }
                break;
            case "cambio":
                response.sendRedirect("www.google.com");
            break;
            case "logout":
                HttpSession session = request.getSession();
                session.invalidate();
                RequestDispatcher vista = request.getRequestDispatcher("/Login.jsp"); 
                vista.forward(request, response);
                break;
        }
    } 
    
    
}
