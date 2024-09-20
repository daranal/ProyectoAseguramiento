<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Proyecto Aseguramiento</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        
        
        <jsp:include page="/ControllerMain" />
        </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="/ProyectoAseguramiento/Paginas/Principal.jsp">Inicio</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto">
                        <c:forEach items="${permisos}" var="permiso">
                            <c:choose>
                                <c:when test="${permiso.nivel=='1'}"> 
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                            ${permiso.nombre}
                                        </a>                                        
                                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                            <c:forEach items="${permisos}" var="permiso2" varStatus="loop">
                                                <c:choose>
                                                    <c:when test="${permiso.id_modpadre == permiso2.id_modpadre}">
                                                        <c:choose>
                                                            <c:when test="${permiso.nombre == permiso2.nombre}">                                                            
                                                            </c:when>
                                                            <c:otherwise>
                                                                <li><a class="dropdown-item" href="/ProyectoAseguramiento/Paginas/${permiso2.path}"> ${permiso2.nombre}</a></li>
                                                                </c:otherwise> 
                                                            </c:choose>
                                                        </c:when>
                                                    </c:choose>
                                                </c:forEach>
                                        </ul>
                                    </li>
                                </c:when>    
                            </c:choose>
                        </c:forEach></ul>
                                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                                        <a class="nav-link dropdown-toggle" href="" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                            ${UsuarioSesion}
                                        </a> 
                                        <a class="btn btn-secondary" href="/ProyectoAseguramiento/ControllerLogin?accion=logout"  >Log Out</a></ul>
                        

                    
                </div>
            </div>
        </nav>
    </body>
</html>

