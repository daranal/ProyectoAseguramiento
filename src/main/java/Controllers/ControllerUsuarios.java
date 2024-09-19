/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Access.DAOUsuarios;
import Models.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author richi
 */
@WebServlet(name = "ControllerUsuarios", urlPatterns = {"/ControllerUsuarios"})
public class ControllerUsuarios extends HttpServlet {

    String listar = "Paginas/Usuarios.jsp";
    String add = "Paginas/AgregarUsuario.jsp";
    String edit = "Paginas/AgregarUsuario.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerUsuarios</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerUsuarios at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso = "";
        String action = request.getParameter("accion");

        Usuario u = new Usuario();
        DAOUsuarios daou = new DAOUsuarios();
        HttpSession sessionUser = request.getSession();
        int idUser;

        switch (action) {
            case "read":
                acceso = listar;
                break;
            case "delete":
                request.setAttribute("idUser", request.getParameter("id"));
                int idUse = Integer.parseInt((String) request.getAttribute("idUser"));
                daou.eliminar(idUse);
                acceso = listar;
                break;
            case "add":
                request.setAttribute("idUser", request.getParameter("id"));
                acceso = add;
                break;
            case "editar":
                request.setAttribute("idUser", request.getParameter("id"));
                acceso = edit;
                break;

            case "update":
                idUser = Integer.parseInt(request.getParameter("codigo"));
                u.setIdUsuario(idUser);
                u.setNombre(request.getParameter("nombre"));
                u.setApellido(request.getParameter("apellido"));
                u.setUsuario(request.getParameter("usuario"));
                u.setPass(request.getParameter("pass"));
                u.setIdRol(Integer.valueOf(request.getParameter("idrol")));
                u.setActivo(request.getParameter("activo"));
                daou.modificar(u);
                acceso = listar;
                break;
            case "anadir":
                idUser = Integer.parseInt(request.getParameter("codigo"));
                u.setIdUsuario(idUser);
                u.setNombre(request.getParameter("nombre"));
                u.setApellido(request.getParameter("apellido"));
                u.setUsuario(request.getParameter("usuario"));
                u.setPass(request.getParameter("pass"));
                u.setIdRol(Integer.valueOf(request.getParameter("idrol")));
                u.setActivo(request.getParameter("activo"));
                daou.insertar(u);
                acceso = listar;
                break;

        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso); //invoca de modo directo un recurso web
        vista.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
