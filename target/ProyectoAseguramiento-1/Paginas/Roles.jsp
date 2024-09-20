<%@page import="Models.Roles"%>
<%@page import="Access.DAORoles"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Roles</title>
        <jsp:include page="/Header.jsp"></jsp:include>

        </head>
        <body>
        <center>
            <h1>Roles</h1>
            <a class="btn btn-success" href="/ProyectoAseguramiento/ControllerRoles?accion=add"  > Añadir rol </a>
            <br><br>
        </center>
        <div class="container">            
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th class="text-center">Acciones</th>
                        <th class="text-center">ID Rol</th>
                        <th class="text-center">Nombre</th>
                        <th class="text-center">Descripcion</th>
                        <th class="text-center">Fecha Creacion</th>
                        <th class="text-center">Fecha Modificacion</th>
                        <th class="text-center">Creacion Usuario</th>
                        <th class="text-center">Modificacion Usuario</th>
                        <th class="text-center">Activo</th>
                    </tr>
                </thead>
            <%
                DAORoles daoRoles = new DAORoles();
                List<Roles> lstRoles = daoRoles.listarRoles();
                Iterator<Roles> iteratorRoles = lstRoles.iterator();
                Roles rol = null;
                while (iteratorRoles.hasNext()) {
                    rol = iteratorRoles.next();
            %>                     
            <tbody>
                <tr>
                    <td class="text-center">                                
                        <a href="/ProyectoAseguramiento/ControllerRoles?accion=editar&id=<%= rol.getIdRol()%>">Editar</a>
                        <a href="/ProyectoAseguramiento/ControllerRoles?accion=delete&id=<%= rol.getIdRol()%>">Eliminar</a>
                    </td>
                    <td class="text-center"><%= rol.getIdRol()%></td>
                    <td class="text-center"><%= rol.getNombre()%></td>
                    <td class="text-center"><%= rol.getDescripcion()%></td>
                    <td class="text-center"><%= rol.getFecha_crea()%></td> 
                    <td class="text-center"><%= rol.getFecha_mod()%></td> 
                    <td class="text-center"><%= rol.getUsuario_crea()%></td> 
                    <td class="text-center"><%= rol.getUsuario_mod()%></td>
                    <td class="text-center"><%= rol.getActivo()%></td>
                    </td>
                </tr>
                <%}%>
            </tbody>
        </table>                   
    </div>
</body>
</html>