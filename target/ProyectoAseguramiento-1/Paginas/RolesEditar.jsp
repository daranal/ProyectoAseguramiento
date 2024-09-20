<%@page import="Models.Roles"%>
<%@page import="Access.DAORoles"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Roles</title>
        <jsp:include page="/Header.jsp"></jsp:include>
    </head>
    <body>
        <div class="container">
            <br>
            <h1>Roles</h1>
            <br>
            <form id="form-work" class="" name="form-work" action="ControllerRoles" method="get">
                <div class="form-group" >
                    <%
                        DAORoles daoRoles = new DAORoles();
                        int idRol = Integer.parseInt((String) request.getAttribute("idRol"));
                        Roles rol = new Roles();
                        rol = daoRoles.CargarRol(idRol);
                    %> 
                    <input type="hidden" name="codigo" value="<%= rol.getIdRol()%>">
                    <div class="col-md-4">
                        <label class="control-label" for="nombre">Nombre</label>
                        <input name="nombre" class="form-control" placeholder="Nombre" type="text" value ="<%= rol.getNombre()%>">
                    </div>
                    <br><br><br><br>
                    <div class="col-md-4">
                        <label class="control-label" for="apellido">Descripción</label>
                        <input name="descripcion" class="form-control" placeholder="Descripción" type="text" value ="<%= rol.getDescripcion()%>">
                    </div>
                    <br><br><br><br>
                    <div class="col-md-3">
                        <label class="control-label" for="activo">Activo</label>
                        <select name="activo">
                            <option value="Y">Si</option>
                            <option value="N">No</option>
                        </select>
                    </div> 
                    <br><br><br>
                    <div class="col-md-3">
                        <label class="control-label" for="fechaInicio">Fecha</label>
                        <input name="fecha" class="form-control" placeholder="Fecha (dd/mm/aaaa)" type="text">
                    </div>
                    <br><br><br><br>
                    <div class="col-md-3">
                        <button type="submit" name="accion" value="update" class="btn btn-success btn-lg btn-block info">Actualizar</button>
                        <br>
                        <a class="btn btn-success btn-lg btn-block info" href="/ProyectoAseguramiento/Paginas/Roles.jsp"  > Regresar </a>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
