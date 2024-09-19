<%-- 
    Document   : Modulo
    Created on : Apr 25, 2022, 4:56:59 PM
    Author     : W10
--%>

<%@page import="Models.Modulos"%>
<%@page import="Access.DAOModulos"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modulos</title>
        
        <jsp:include page="/Header.jsp"></jsp:include>
        </head>
        <body>
        <center>
            <h1>Modulos</h1>
            <a class="btn btn-success" href="/ProyectoFaseI/ControllerModulos?accion=add&id=0"  > Agregar modulo </a>
            <br>
            <br>
        </center>
        <div class="container">            
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th class="text-center">Acciones</th>
                        <th class="text-center">ID Modulo</th>
                        <th class="text-center">Nombre</th>
                        <th class="text-center">Descripcion</th>
                        <th class="text-center">Path</th>
                        <th class="text-center">Nivel</th>
                        <th class="text-center">Orden</th>
                        <th class="text-center">ID Modulo Padre</th>
                        <th class="text-center">Fecha Creacion</th>
                        <th class="text-center">Fecha Modificacion</th>
                        <th class="text-center">Creacion Usuario</th>
                        <th class="text-center">Modificacion Usuario</th>
                        <th class="text-center">Activo</th>
                    </tr>
                </thead>
            <%
                DAOModulos daoModulo = new DAOModulos();
                List<Modulos> lstModulo = daoModulo.listarModulos();
                Iterator<Modulos> iteratorMod = lstModulo.iterator();
                Modulos mod = null;
                while (iteratorMod.hasNext()) {
                    mod = iteratorMod.next();
            %>                     
            <tbody>
                <tr>
                    <td class="text-center">                                
                        <a href="/ProyectoFaseI/ControllerModulos?accion=editar&id=<%= mod.getId_modulo()%>">Editar</a>
                        <a href="/ProyectoFaseI/ControllerModulos?accion=delete&id=<%= mod.getId_modulo()%>">Eliminar</a>
                    </td>
                    <td class="text-center"><%= mod.getId_modulo()%></td>
                    <td class="text-center"><%= mod.getNombre()%></td>
                    <td class="text-center"><%= mod.getDescripcion()%></td>
                    <td class="text-center"><%= mod.getPath()%></td>
                    <td class="text-center"><%= mod.getNivel()%></td>
                    <td class="text-center"><%= mod.getOrden()%></td>
                    <td class="text-center"><%= mod.getId_modpadre()%></td> 
                    <td class="text-center"><%= mod.getFecha_crea()%></td> 
                    <td class="text-center"><%= mod.getFecha_mod()%></td> 
                    <td class="text-center"><%= mod.getUsuario_crea()%></td> 
                    <td class="text-center"><%= mod.getUsuario_mod()%></td>
                    <td class="text-center"><%= mod.getActivo()%></td>
                    </td>
                </tr>
                <%}%>
            </tbody>
        </table>                   
    </div>
</body>
</html>
