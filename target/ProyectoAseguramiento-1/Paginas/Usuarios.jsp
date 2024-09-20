<%-- 
    Document   : Usuarios
    Created on : Apr 25, 2022, 5:06:21 PM
    Author     : W10
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Models.Usuario"%>
<%@page import="Access.DAOUsuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuarios</title>

        <jsp:include page="/Header.jsp"></jsp:include>
        </head>
        <body>
        <center>
            
                <h1>Usuarios</h1>
                <a class="btn btn-success" href="/ProyectoAseguramiento/ControllerUsuarios?accion=add&id=0"  > Agregar Usuario </a>
                <br>
                <br>
        <div class="container" style="">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>OPCIONES</th>
                            <th>ID_USUARIO</th>
                            <th>NOMBRE</th>
                            <th>APELLIDO</th>
                            <th>USUARIO</th>
                            <th>PASSWORD</th>
                            <th>ID_ROL</th>
                            <th>FECHA CREA</th>
                            <th>ACTIVO</th>
                            <th>FECHA MOD</th>
                        </tr>
                    </thead>
                <%
                    DAOUsuarios daou = new DAOUsuarios();
                    List<Usuario> lstUsuario = daou.listar();
                    Iterator<Usuario> iteratorU = lstUsuario.iterator();
                    Usuario u = null;
                    while (iteratorU.hasNext()) {
                        u = iteratorU.next();
                %>   
                <tbody>
                    <tr>
                        <td>                                
                            <a href="/ProyectoAseguramiento/ControllerUsuarios?accion=editar&id=<%= u.getIdUsuario()%>">Editar</a>
                            <a href="/ProyectoAseguramiento/ControllerUsuarios?accion=delete&id=<%= u.getIdUsuario()%>">Eliminar</a>
                        </td>
                        <td><%= u.getIdUsuario()%> </td>
                        <td><%= u.getNombre()%> </td>
                        <td><%= u.getApellido()%> </td>
                        <td><%= u.getUsuario()%> </td>
                        <td>*****</td>
                        <td><%= u.getIdRol()%> </td>
                        <td><%= u.getFechacrea()%> </td>
                        <td><%= u.getActivo()%> </td>
                        <td><%= u.getFechamod()%> </td>               
                    </tr>
                    <%}%>
                </tbody>
            </table>
        </div>
    </center>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  
</body>
</html>
