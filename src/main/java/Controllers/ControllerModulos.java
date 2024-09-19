/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Access.DAOModulos;
import Models.Modulos;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author W10
 */
@WebServlet(name = "ControllerModulos", urlPatterns = {"/ControllerModulos"})
public class ControllerModulos extends HttpServlet {
    String listar ="Paginas/Modulos.jsp";
    String add ="Paginas/ModulosOpciones.jsp";
    String edit ="Paginas/ModulosOpciones.jsp";
    
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
            out.println("<title>Servlet ControllerModulos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerModulos at " + request.getContextPath() + "</h1>");
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
        String acceso="";        
        String action = request.getParameter("accion");        
        
        Modulos mod = new Modulos();
        DAOModulos daoMod = new DAOModulos();
        HttpSession sessionUser = request.getSession();
        switch (action){
            case "read":
                acceso = listar;
            break;
            case "delete" :
                request.setAttribute("idModulo", request.getParameter("id"));
                int idMod = Integer.parseInt((String) request.getAttribute("idModulo"));
                daoMod.eliminar(idMod);
                acceso = listar;                
            break;
            case "add" :
                request.setAttribute("idModulo", request.getParameter("id"));
                acceso = add;                
            break;
            case "editar":
                request.setAttribute("idModulo", request.getParameter("id"));
                acceso = edit;
            break;
            case "update" :
                int idModulo = Integer.parseInt(request.getParameter("codigo"));
                mod.setId_modulo(idModulo);
                mod.setNombre(request.getParameter("nombre"));
                mod.setDescripcion(request.getParameter("descripcion"));
                mod.setPath(request.getParameter("path"));
                int nivelModulo = Integer.parseInt(request.getParameter("nivel"));
                mod.setNivel(nivelModulo);
                mod.setOrden(request.getParameter("orden"));
                int ModPadre = Integer.valueOf(request.getParameter("id_moduloPadre"));
                if(ModPadre ==0){
                    ModPadre = daoMod.cargarMaxIDPadre()+1;
                }
                mod.setId_modpadre(ModPadre);
                mod.setActivo(request.getParameter("activo"));
                sessionUser.getAttribute("UsuarioSesion");
                String IDRol = sessionUser.getAttribute("UsuarioSesion").toString();
                mod.setUsuario_crea(IDRol);
                mod.setUsuario_mod(IDRol);
                daoMod.modificar(mod);
                acceso = listar;                
            break;
            case "anadir" :
                idModulo = Integer.parseInt(request.getParameter("codigo"));
                mod.setId_modulo(idModulo);
                mod.setNombre(request.getParameter("nombre"));
                mod.setDescripcion(request.getParameter("descripcion"));
                mod.setPath(request.getParameter("path"));
                nivelModulo = Integer.parseInt(request.getParameter("nivel"));
                mod.setNivel(nivelModulo);
                mod.setOrden(request.getParameter("orden"));
                ModPadre = Integer.valueOf(request.getParameter("id_moduloPadre"));
                if(ModPadre ==0){
                    ModPadre = daoMod.cargarMaxIDPadre()+1;
                }
                mod.setId_modpadre(ModPadre);
                mod.setActivo(request.getParameter("activo"));
                sessionUser.getAttribute("UsuarioSesion");
                IDRol = sessionUser.getAttribute("UsuarioSesion").toString();
                mod.setUsuario_crea(IDRol);
                mod.setUsuario_mod(IDRol);
                daoMod.insertar(mod);
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
