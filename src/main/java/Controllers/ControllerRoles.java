package Controllers;

import Access.DAORoles;
import Models.Roles;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ControllerRoles", urlPatterns = {"/ControllerRoles"})
public class ControllerRoles extends HttpServlet {

    String listar = "Paginas/Roles.jsp";
    String add = "Paginas/RolesAgregar.jsp";
    String edit = "Paginas/RolesEditar.jsp";
    String delete = "Paginas/RolesEliminar.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerRoles</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerRoles at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso = "";
        String action = request.getParameter("accion");

        Roles rol = new Roles();
        DAORoles daoRol = new DAORoles();
        HttpSession sessionUser = request.getSession();

        switch (action) {
            case "read":
                acceso = listar;
                break;
            case "add":
                acceso = add;
                break;
            case "create":
                rol.setNombre(request.getParameter("nombre"));
                rol.setDescripcion(request.getParameter("descripcion"));
                rol.setActivo(request.getParameter("activo"));
                
                sessionUser.getAttribute("UsuarioSesion");
                String usuario = sessionUser.getAttribute("UsuarioSesion").toString();
                rol.setUsuario_crea(usuario);
                rol.setUsuario_mod(usuario);
                
                rol.setFecha_crea(request.getParameter("fecha"));
                rol.setFecha_mod(request.getParameter("fecha"));

                daoRol.insertar(rol);
                
                acceso = listar;
                break;
            case "editar":
                request.setAttribute("idRol", request.getParameter("id"));
                acceso = edit;
                break;
            case "update":
                int idRol = Integer.parseInt(request.getParameter("codigo"));
                
                rol.setIdRol(idRol);
                rol.setNombre(request.getParameter("nombre"));
                rol.setDescripcion(request.getParameter("descripcion"));
                rol.setActivo(request.getParameter("activo"));
                
                sessionUser.getAttribute("UsuarioSesion");
                usuario = sessionUser.getAttribute("UsuarioSesion").toString();
                rol.setUsuario_mod(usuario);
                
                rol.setFecha_mod(request.getParameter("fecha"));
                
                daoRol.modificar(rol);
                acceso = listar;
                break;
            case "delete":
                request.setAttribute("idRol", request.getParameter("id"));
                acceso = delete;
                break;
            case "remove":
                idRol = Integer.parseInt(request.getParameter("codigo"));
                
                rol.setIdRol(idRol);

                daoRol.eliminar(rol);
                acceso = listar;
                break;
        }

        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
