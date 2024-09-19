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

        <br>
        <h1>Roles</h1>
        <br>
    </center>
    <div class="container">
        <center>
            <div class="col-md-3">
                <form id="form-work" class="" name="form-work" action="ControllerRoles" method="get">
                    <div class="form-group" >  
                            <label class="control-label" for="nombre">Nombre</label>
                            <input name="nombre" class="form-control" placeholder="Nombre" type="text">
                        <br>
                        <label class="control-label" for="apellido">Descripción</label>
                        <input name="descripcion" class="form-control" placeholder="Descripción" type="text">
                        <br>
                        <label class="control-label" for="fechaInicio">Fecha</label>
                        <input name="fecha" class="form-control" placeholder="Fecha (dd/mm/aaaa)" type="text">
                        <br>
                        <label class="control-label" for="activo">Activo</label>
                         <br>
                        <select name="activo">
                            <option value="Y">Si</option>
                            <option value="N">No</option>
                        </select>
                        <br> <br>
                        <button type="submit" name="accion" value="create" class="btn btn-success btn-lg btn-block info">Guardar</button>
                        <br> <br>
                        <a class="btn btn-success btn-lg btn-block info" href="/ProyectoFaseI/Paginas/Roles.jsp"  > Regresar </a>
                    </div>
                </form>
            </div>
    </div>
</body>
</html>
