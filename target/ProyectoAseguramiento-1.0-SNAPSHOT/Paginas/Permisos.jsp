<%-- 
    Document   : Permisos
    Created on : Apr 25, 2022, 5:06:41 PM
    Author     : W10
--%>

<%@page import="Access.DAOPermisos"%>
<%@page import="Models.Permisos"%>
<%@page import="Models.Modulos"%>
<%@page import="Models.Modulos"%>
<%@page import="Access.DAOModulos"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Models.Usuario"%>
<%@page import="Access.DAOUsuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Header.jsp"></jsp:include>
        <title>JSP Page</title>
    </head>
    <body>
        <center>
            <h1>Permisos</h1>
            <br>
            <br>
        </center>
  
                
        <div class="container" style="">
            <h3>Asignar Permisos</h3>
            <form id="form-work" class="" name="form-work" action="/ProyectoFaseI/ControllerPermisos" method="get">
            <label class="control-label" for="nombre">Seleccione Usuario</label>
            <select name="user">
                <option value="null"> </option>
                <%
                    DAOUsuarios daou = new DAOUsuarios();
                    List<Usuario> lstUsuario = daou.listar();
                    Iterator<Usuario> iteratorU = lstUsuario.iterator();
                    Usuario u = null;
                    while (iteratorU.hasNext()) {
                        u = iteratorU.next();
                %>   
                    <option value="<%= u.getUsuario()%>"><%= u.getUsuario()+": " + u.getNombre() +" " + u.getApellido()%> </option>
                <%}%>
            </select>
            <br>
            <label class="control-label" for="nombre">Seleccione Modulo</label>
            <select name="modulo">
                <option value="null"> </option>
                <%
                    DAOModulos daoModulo = new DAOModulos();
                    List<Modulos> lstModulo = daoModulo.listarModulos();
                    Iterator<Modulos> iteratorMod = lstModulo.iterator();
                    Modulos mod = null;
                    while (iteratorMod.hasNext()) {
                        mod = iteratorMod.next();
                %> 
                    <option value="<%= mod.getId_modulo()%>"><%= mod.getNombre()%> </option>

                <%}%>
            </select>
            <br>
            <label class="control-label" for="permiso">Asignar Permiso</label>
            <select name = "rol">
                <option value="null"> </option>
                <option value="1">Super Administrador: Puede eliminar otros administradores </option>
                <option value="2">Administrador: Permiso de todo, excepto eliminar administradores </option>
                <option value="3">Usuario: Puede ver notas </option>
            </select>
            <br>
            <label class="control-label" for="estado">Estado</label>
            <select name = "activo">
                <option value="Y">Activo </option>
                <option value="N">Inactivo </option>
            </select>
            <br>
            <button type="submit" name="accion" value="update" class="btn btn-success">Actualizar</button>
            </form>
        </div>
            <br>
        <div class="container" style="">
            <h3>Eliminar Permisos</h3>
             <table class="table table-bordered">
                <thead>
                    <tr>
                        <th class="text-center">Acciones</th>
                        <th class="text-center">ID Modulo</th>
                        <th class="text-center">ID Rol</th>
                        <th class="text-center">Fecha Creacion</th>
                        <th class="text-center">Fecha Modificacion</th>
                        <th class="text-center">Creacion Usuario</th>
                        <th class="text-center">Modificacion Usuario</th>
                        <th class="text-center">Activo</th>
                    </tr>
                </thead>
            <%
                DAOPermisos daoPermiso = new DAOPermisos();
                List<Permisos> lstPermiso = daoPermiso.listarPermisos();
                Iterator<Permisos> iteratorPerm = lstPermiso.iterator();
                Permisos perm = null;
                while (iteratorPerm.hasNext()) {
                    perm = iteratorPerm.next();
            %>                     
            <tbody>
                <tr>
                    <td class="text-center">                                
                        <a href="/ProyectoFaseI/ControllerPermisos?accion=delete&id=<%= perm.getId_Permiso()%>">Eliminar</a>
                    </td>
                    <td class="text-center"><%= perm.getId_modulo()%></td>
                    <td class="text-center"><%= perm.getId_rol()%></td>
                    <td class="text-center"><%= perm.getFecha_crea()%></td> 
                    <td class="text-center"><%= perm.getFecha_mod()%></td> 
                    <td class="text-center"><%= perm.getUsuario_crea()%></td> 
                    <td class="text-center"><%= perm.getUsuario_mod()%></td>
                    <td class="text-center"><%= perm.getActivo()%></td>
                    </td>
                </tr>
                <%}%>
            </tbody>
        </table>         
        </div>
    </body>
</html>
