<%-- 
    Document   : home.jsp
    Created on : May 5, 2022, 9:51:27 PM
    Author     : ivanp
--%>

<%@page import="Access.DAOFase2"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/ControladorAlquiler" />



<html> 

    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script> 
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>  
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.2/font/bootstrap-icons.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
    <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
    <head> 
        <title> 
            Alquileres 
        </title> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Clientes</title>
        <jsp:include page="/Header.jsp"></jsp:include>
            <meta charset="utf-8">
        </head> 

        <body> 
        <center> 
            <h1> 
                Ventas 
            </h1> 
            <div class="container" style="">
                <h2>Tipo de cambio Q<%= request.getAttribute("TipoCambio")%></h2>
        </div>
        <!-- Nav tabs -->
        <ul class="nav nav-tabs" id="NuevaTab" role="tablist">
            <li class="nav-item">
                <a class="nav-link active" id="agregar-tab" data-toggle="tab" href="#facturas" role="tab" aria-controls="profile" aria-selected="true">Facturas</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="resumen-tab" data-toggle="tab" href="#clientes" role="tab" aria-controls="resumen" aria-selected="false">Clientes</a>
            </li>            
            <li class="nav-item">
                <a class="nav-link" id="animales-tab" data-toggle="tab" href="#Vehiculos" role="tab" aria-controls="animales" aria-selected="false">Vehiculos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="modificar-tab" data-toggle="tab" href="#reportes" role="tab" aria-controls="eliminar" aria-selected="false" >Reportes</a>
            </li> 
       

        </ul>            

        <div class="tab-content">
            <div class="tab-pane active" id="facturas" role="tabpanel" aria-labelledby="agregar-tab"><div class="container">
                    <br><br>
                    <div class="container">
                        <form action="ControladorAlquiler" method="post" id="NewServlet" onSubmit="if (!confirm('¿Seguro que desea agregar esta compra?')) {
                                    return false;
                                }">
                            <div class="row">
                                <div class="col-4">
                                    <label for="idorden" class="text-info">ID Factura: </label><br><label class"text-info"><%= request.getAttribute("IDRenta")%></label>
                                </div>    
                                <div class="col-4">
                                    <label for="idcliente" class="text-info">ID cliente </label><br>
                                    <input id="txtIDCliente" name="txtIDCliente"  type="number" class="form-control" readonly required="required">
                                </div>   
                                <div class="col-4">
                                    <label for="nitcliente" class="text-info">Nit Cliente</label><br>
                                    <select id="ddcliente" name="ddcliente" class="form-control" onchange="changeFunc();">
                                        <c:forEach items="${TablaClientes}" var="cliente"> 
                                            <option value="${cliente.idCliente}?${cliente.nit}?${cliente.nombre}?${cliente.apellido}?${cliente.direccion}?${cliente.telefono}"><c:out value="${cliente.nit} ${cliente.nombre} ${cliente.apellido}"></c:out></option>   
                                        </c:forEach>                     
                                    </select>
                                </div>                        
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-8">
                                    <label for="txtnombre" class="text-info">Nombre Cliente</label>
                                    <input id="txtNombre" name="txtNombre"  type="text" class="form-control" readonly required="required">                                                
                                </div>                            
                                <div class="col-4">
                                    <label for="txtnit" class="text-info">Telefono</label>
                                    <input id="txtTelefono" name="txtTelefono"  type="number" class="form-control" readonly required="required">
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-8">
                                    <label for="txtdireccion" class="text-info">Dirección Cliente</label>
                                    <input id="txtDireccion" name="txtDireccion" type="text" class="form-control" readonly required="required"></div>
                                <div class="col-4">
                                    <label for="stipodepago" class="text-info">Tipo de Pago</label>
                                    <select id="stipopago" name="stipopago" class="form-control" required="required">
                                        <option value="1" >Efectivo</option>
                                        <option value="2">Tarjeta</option>
                                    </select>    
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-4">
                                    <label for="textarea" class="text-info">Observaciones</label> 
                                    <textarea id="txt_observacion" name="txt_observacion" cols="40" rows="5" class="form-control"></textarea>
                                </div>
                                <div class="col-4">
                                    <label for="lblMarcaVehiculo" class="text-info">Marca Vehiculo</label><br>
                                    <select id="ddMarca" name="ddMarca" class="form-control" onchange="">
                                        <c:forEach items="${Vehiculos}" var="vehiculo"> 
                                            <option value="${vehiculo}"><c:out value="${vehiculo}"></c:out></option>   
                                        </c:forEach>                     
                                    </select>
                                </div>  
                                <div class="col-4">
                                    <label for="lblMarcaVehiculo" class="text-info">Tipo Vehiculo</label><br>
                                    <select id="ddTipoVehiculo" name="ddTipoVehiculo" class="form-control" onchange="">

                                    </select>
                                </div> 
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-6">
                                    <label for="lbldatepickerinicio" class="text-info">Fecha inicio</label>
                                    <div class='input-group' id='divdatepickerinicio' data-td-target-input='nearest' data-td-target-toggle='nearest'>
                                        <input class="form-control" name="datepickerinicio" id="datepickerinicio"/>
                                        <span class="input-group-text" data-td-target="#divdatepickerinicio" data-td-toggle="datetimepicker">
                                            <span class='bi bi-calendar'></span>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <label for="lbldatepickerfin" class="text-info">Fecha fin</label>
                                    <div class='input-group' id='divdatepickerfin' data-td-target-input='nearest' data-td-target-toggle='nearest'>
                                        <input id="datepickerfin" name="datepickerfin" type="text" class="form-control" data-td-target="#datepickerfin"/>
                                        <span class='input-group-text' data-td-target='#divdatepickerfin' data-td-toggle='datetimepicker'>
                                            <span class='bi bi-calendar'></span>
                                        </span>
                                    </div>
                                </div>

                            </div>
                            <br>
                            <div class="row">                                            
                                <div class="col-4">
                                    <br>
                                    <label for="lbltxtDolares" class="text-info">Total Dólares</label>
                                    <input value="" id="txttdolares" name="txttdolares" type="text" class="form-control" readonly>
                                </div>
                                <div class="col-4">
                                    <br>
                                    <label for="lbltxtQuetz" class="text-info">Total Quetzales</label>
                                    <input value="" id="txttquetz" name="txttquetz" type="text" class="form-control" readonly >
                                </div>   
                                <div class="col-4">
                                    <br>
                                    <label for="lblTotalDias" class="text-info">Total Dias</label>
                                    <input id="txttotaldias" name="txttquetz" type="text" class="form-control" readonly >
                                </div>  
                            </div>
                            <br>
                            <br>                                
                            <div class="col-12"><button name="submit" type="submit" value="AgregarRenta" class="btn btn-primary">Agregar Renta</button></div>
                            <br><br>
                            </div>
                        </form>
                    </div>
                    <div class="container">
                        <table class="table table-bordered table-hover">
                            <br>
                            <h2> Vehiculos en Renta</h2>
                            <td><b>ID Renta</b></td>
                            <td><b>Serie</b></td>
                            <td><b>Nombre Cliente</b></td>
                            <td><b>Tipo Pago</b></td>  
                            <td><b>Total</b></td>  
                            <td><b>Acciones</b></td>  
                            <c:forEach items="${TablaRentas}" var="renta">
                                <tr>
                                    <td>${renta.idRenta}</td>
                                    <td>${renta.serie}</td>
                                    <td>${renta.nombre} ${renta.apellido} </td>
                                    <td>${renta.tipoPago}</td>
                                    <td>Q${renta.totalCalculated}</td>
                                    <td><a href="" data-bs-toggle="modal" data-bs-target="#modaldetalles"
                                           onclick="detalleRenta('${renta.idRenta}', '${renta.serie}', '${renta.nombre}', '${renta.apellido}', '${renta.tipoPago}', '${renta.fechaRentaSelected}'
                                                           , '${renta.fechaDevolucionSelected}', '${renta.idVehiculoSelected}', '${renta.marca}', '${renta.tipo}')">Detalles</a> 
                                                           
                                        <a href="" data-bs-toggle="modal" data-bs-target="#modaldevolucion"
                                           onclick="devolucionRenta('${renta.idRenta}', '${renta.idVehiculoSelected}', '${renta.nombre}', '${renta.apellido}','${renta.mora}')">Devolucion</a></td>

                                </tr>
                            </c:forEach>
                        </table>
                        <br><br>
                    </div>
                </div>
                <div class="tab-pane" id="Vehiculos" role="tabpanel" aria-labelledby="resumen-tab"><div class="container">                          
                        <br>
                        <table class="table table-bordered table-hover">
                            <br>
                            <td><b>ID Vehiculo</b></td>
                            <td><b>Marca</b></td>
                            <td><b>Tipo</b></td>
                            <td><b>Modelo</b></td>
                            <td><b>Estado</b></td>
                            <td><b>Acciones</b></td>
                            <c:forEach items="${TablaVehiculos}" var="Vehiculos">
                                <c:choose>
                                    <c:when test = "${Vehiculos.estadoDescripcion == 'En renta'}">
                                        <tr>
                                            <td>${Vehiculos.idVehiculo}</td>
                                            <td>${Vehiculos.marcaDescripcion}</td>
                                            <td>${Vehiculos.tipoDescripcion}</td>
                                            <td>${Vehiculos.modelo}</td>
                                            <td>${Vehiculos.estadoDescripcion}</td> 
                                            <td></td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td>${Vehiculos.idVehiculo}</td>
                                            <td>${Vehiculos.marcaDescripcion}</td>
                                            <td>${Vehiculos.tipoDescripcion}</td>
                                            <td>${Vehiculos.modelo}</td>
                                            <td>${Vehiculos.estadoDescripcion}</td> 
                                            <td><a href="" id="btn_editar_vehiculo" data-bs-toggle="modal" data-bs-target="#modaleditarprod"
                                                   onclick="editarVehiculo('${Vehiculos.idVehiculo}', '${Vehiculos.marcaDescripcion}', '${Vehiculos.tipoDescripcion}', '${Vehiculos.modelo}', '${Vehiculos.idVehiculoEstado}')">Editar</a> 
                                                <a href="eliminarprod?id=<c:out value='${Vehiculos.idVehiculo}' />" data-confirm="¿Deseas elimar el vehiculo?">Eliminar</a></td>
                                        </tr>    
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </table>
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalagregarvehiculo">
                            Agregar Vehiculo
                        </button>
                        <br>
                        <table class="table table-bordered table-hover"><br>
                            <td><b>ID Marca</b></td>
                            <td><b>Descripcion</b></td>
                            <td><b>Acciones</b></td>
                            <c:forEach items="${TablaMarcas}" var="marcas">
                                <tr>
                                    <td>${marcas.idmarca}</td>
                                    <td>${marcas.descripcion}</td>
                                    <td>
                                        <a href="eliminarMarca?id=<c:out value='${marcas.idmarca}' />" data-confirm="¿Deseas elimar el vehiculo?">Eliminar</a></td>
                                </tr>
                            </c:forEach></table>
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalagregarmarca">
                            Agregar Marca
                        </button>
                        <br>
                        <table class="table table-bordered table-hover"><br>
                            <td><b>ID Tipo</b></td>
                            <td><b>Descripcion</b></td>
                            <td><b>Acciones</b></td>
                            <c:forEach items="${TablaTipoVehiculos}" var="vehiculos">
                                <tr>
                                    <td>${vehiculos.idTipoVehiculo}</td>
                                    <td>${vehiculos.descripcion}</td>
                                    <td>
                                        <a href="eliminarTipo?id=<c:out value='${vehiculos.idTipoVehiculo}' />" data-confirm="¿Deseas elimar el vehiculo?">Eliminar</a>
                                    </td>
                                </tr>
                            </c:forEach></table>
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalagregartipo">
                            Agregar Tipo
                        </button>
                        <br><br>
                    </div>
                </div>
                <div class="tab-pane" id="clientes" role="tabpanel" aria-labelledby="resumen-tab"><div class="container">    
                        <br>
                        <br>
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalagregarcliente">
                            Agregar cliente
                        </button>
                        <br>
                        <br>
                        <br>
                        <table class="table table-bordered table-hover">
                            <td><b>ID Cliente</b></td>
                            <td><b>Nombre</b></td>
                            <td><b>Apellido</b></td>
                            <td><b>NIT</b></td>
                            <td><b>Telefono</b></td>
                            <td><b>Direccion</b></td>
                            <td><b>Acciones</b></td>
                            <c:forEach items="${TablaClientes}" var="cliente">
                                <tr>
                                    <td>${cliente.idCliente}</td>
                                    <td>${cliente.nombre}</td>
                                    <td>${cliente.apellido}</td>
                                    <td>${cliente.nit}</td>
                                    <td>${cliente.telefono}</td>     
                                    <td>${cliente.direccion}</td>    
                                    <td> <a href="" data-bs-toggle="modal" data-bs-target="#modaleditarcliente"
                                            onclick="editarcliente('${cliente.idCliente}', '${cliente.nombre}', '${cliente.apellido}', '${cliente.nit}', '${cliente.telefono}', '${cliente.direccion}')">Editar</a> <a href="eliminarcliente?id=<c:out value='${cliente.idCliente}' />" data-confirm="¿Deseas elimar el registro?">Eliminar</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
                <div class="tab-pane" id="reportes" role="tabpanel" aria-labelledby="resumen-tab"><div class="container">                          
                        <br>
                        <h2>Rentas completadas</h2>
                        <table class="table table-bordered table-hover">

                            <td><b>ID Renta</b></td>
                            <td><b>Nombre cliente</b></td>
                            <td><b>Estado</b></td>  
                            <td><b>Fecha actual de devolucion</b></td>  
                            <td><b>Mora</b></td> 
                            <td><b>Dias de atraso</b></td> 
                            <td><b>Total</b></td> 
                            <td><b>Acciones</b></td>  
                            <c:forEach items="${TablaTodasRentas}" var="renta">
                                <tr>
                                    <td>${renta.idRenta}</td>
                                    <td>${renta.nombre} ${renta.apellido}</td>
                                    <td>${renta.estado} </td>
                                    <td>${renta.fechaDevolucion}</td>
                                    <td>${renta.mora}</td>
                                    <td>${renta.diasAtraso}</td>
                                    <td>${renta.totalCalculated}</td>
                                    <td><a href="" data-bs-toggle="modal" data-bs-target="#modaldetalles"
                                           onclick="detalleRenta('${renta.idRenta}', '${renta.serie}', '${renta.nombre}', '${renta.apellido}', '${renta.tipoPago}', '${renta.fechaRentaSelected}'
                                                           , '${renta.fechaDevolucionSelected}', '${renta.idVehiculoSelected}', '${renta.marca}', '${renta.tipo}')">Detalles</a></td>

                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <div class="container">
                        <table class="table table-bordered table-hover">
                            <br>
                            <h2> Vehiculos en Renta</h2>
                            <td><b>ID Renta</b></td>
                            <td><b>Serie</b></td>
                            <td><b>Nombre Cliente</b></td>
                            <td><b>Tipo Pago</b></td>  
                            <td><b>Total</b></td>  
                            <td><b>Acciones</b></td>

                            <c:forEach items="${TablaRentas}" var="renta">
                                <tr>
                                    <td>${renta.idRenta}</td>
                                    <td>${renta.serie}</td>
                                    <td>${renta.nombre} ${renta.apellido} </td>
                                    <td>${renta.tipoPago}</td>
                                    <td>Q${renta.totalCalculated}</td>
                                    <td><a href="" data-bs-toggle="modal" data-bs-target="#modaldetalles"
                                           onclick="detalleRenta('${renta.idRenta}', '${renta.serie}', '${renta.nombre}', '${renta.apellido}', '${renta.tipoPago}', '${renta.fechaRentaSelected}'
                                                           , '${renta.fechaDevolucionSelected}', '${renta.idVehiculoSelected}', '${renta.marca}', '${renta.tipo}')">Detalles</a>
                                        <a href="" data-bs-toggle="modal" data-bs-target="#modaldevolucion"
                                           onclick="devolucionRenta('${renta.idRenta}', '${renta.idVehiculoSelected}', '${renta.nombre}', '${renta.apellido}', '${renta.mora}')">Devolucion</a></td>

                                </tr>
                            </c:forEach>
                        </table>

                        <br><br>
                    </div>
                </div>
            </div>
            <div class="modal fade"  data-bs-backdrop="static" id="modaleditarprod" tabindex="-1" aria-labelledby="modaleditarprod" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="modaleditarprodlabel">Editar Vehiculo</h5>
                            <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>

                        </div>
                        <form action="ControladorAlquiler" method="post" id="test" onSubmit="if (!confirm('¿Seguro que desea agregar esta compra?')) {
                                    return false;
                                }">
                            <div class="modal-body">
                                <div class="row">
                                    <div class="container">                                        
                                        <label for="txtidvehiculoeditar" class="text-info">ID Vehiculo</label>
                                        <input id="txtidvehiculoeditar" name="txtidvehiculoeditar" type="text" class="form-control" readonly>
                                        <br>
                                        <label for="ddEditarVehiculoMarca" class="text-info">Marca</label>
                                        <select id="ddEditarVehiculoMarca" name="ddEditarVehiculoMarca" class="form-control">
                                            <c:forEach items="${TablaMarcas}" var="marca"> 
                                                <option value="${marca.idmarca}"><c:out value="${marca.descripcion}"></c:out></option>   
                                            </c:forEach>                     
                                        </select>
                                        <br>
                                        <label for="ddEditarVehiculoTipo" class="text-info">Tipo</label>
                                        <select id="ddEditarVehiculoTipo" name="ddEditarVehiculoTipo" class="form-control">
                                            <c:forEach items="${TablaTipoVehiculos}" var="tipo"> 
                                                <option value="${tipo.idTipoVehiculo}"><c:out value="${tipo.descripcion}"></c:out></option>   
                                            </c:forEach>                     
                                        </select>
                                        <br>
                                        <label for="txtModeloEditar" class="text-info">Modelo</label>
                                        <input id="txtModeloEditar" name="txtModeloEditar" type="number" class="form-control">
                                        <br>
                                        <label for="ddEditarVehiculoEstado" class="text-info">Estado</label>
                                        <select id="ddEditarVehiculoEstado" name="ddEditarVehiculoEstado" class="form-control">
                                            <option value="0">En taller</option> 
                                            <option value="1">Disponible</option>   
                                        </select>
                                    </div></div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                <div class="row">
                                    <div class="col-12"><button name="submit" type="submit" value="GuardarCambiosVehiculo" class="btn btn-primary">Guardar Cambios</button></div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <div class="modal fade"  data-bs-backdrop="static" id="modalagregarvehiculo" tabindex="-1" aria-labelledby="modalagregarvehiculo" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="modaleditarprodlabel">Agregar Vehiculo</h5>
                            <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>

                        </div>
                        <form action="ControladorAlquiler" method="post" id="test" onSubmit="if (!confirm('¿Seguro que desea agregar esta compra?')) {
                                    return false;
                                }">
                            <div class="modal-body">
                                <div class="row">
                                    <div class="container">                                        
                                        <label for="txtidvehiculoagregar" class="text-info">ID Vehiculo</label>
                                        <input id="txtidvehiculoagregar" name="txtidvehiculoagregar" type="text" class="form-control" readonly>
                                        <br>
                                        <label for="ddAgregarVehiculoMarca" class="text-info">Marca</label>
                                        <select id="ddAgregarVehiculoMarca" name="ddAgregarVehiculoMarca" class="form-control">
                                            <c:forEach items="${TablaMarcas}" var="marca"> 
                                                <option value="${marca.idmarca}"><c:out value="${marca.descripcion}"></c:out></option>   
                                            </c:forEach>                     
                                        </select>
                                        <br>
                                        <label for="ddAgregarVehiculoTipo" class="text-info">Tipo</label>
                                        <select id="ddAgregarVehiculoTipo" name="ddAgregarVehiculoTipo" class="form-control">
                                            <c:forEach items="${TablaTipoVehiculos}" var="tipo"> 
                                                <option value="${tipo.idTipoVehiculo}"><c:out value="${tipo.descripcion}"></c:out></option>   
                                            </c:forEach>                     
                                        </select>
                                        <br>
                                        <label for="txtAgregarModelo" class="text-info">Modelo</label>
                                        <input id="txtAgregarModelo" name="txtAgregarModelo" type="number" class="form-control">
                                        <br>
                                        <label for="ddAgregarVehiculoEstado" class="text-info">Estado</label>
                                        <select id="ddAgregarVehiculoEstado" name="ddAgregarVehiculoEstado" class="form-control">
                                            <option value="0">En taller</option> 
                                            <option value="1">Disponible</option>   
                                        </select>
                                    </div></div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                <div class="row">
                                    <div class="col-12"><button name="submit" type="submit" value="AgregarVehiculo" class="btn btn-primary">Guardar Cambios</button></div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal fade"  data-bs-backdrop="static" id="modalagregarmarca" tabindex="-1" aria-labelledby="modalagregarmarca" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="modaleditarprodlabel">Agregar marca</h5>
                            <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>

                        </div>
                        <form action="ControladorAlquiler" method="post" id="test" onSubmit="if (!confirm('¿Seguro que desea agregar esta marca?')) {
                                    return false;
                                }">
                            <div class="modal-body">
                                <div class="row">
                                    <div class="container">
                                        <label for="txtAgregarIdMarca" class="text-info">ID Marca</label>
                                        <input id="txtAgregarIdMarca" name="txtAgregarIdMarca" type="text" class="form-control" readonly>
                                        <br>
                                        <label for="txtAgregarMarcaDescripcion" class="text-info">Descripcion</label>
                                        <input id="txtAgregarMarcaDescripcion" name="txtAgregarMarcaDescripcion" type="text" class="form-control">
                                        <br>
                                    </div></div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                <div class="row">
                                    <div class="col-12"><button name="submit" type="submit" value="AgregarMarca" class="btn btn-primary">Agregar</button></div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal fade"  data-bs-backdrop="static" id="modalagregartipo" tabindex="-1" aria-labelledby="modalagregartipo" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="modaleditarprodlabel">Agregar tipo</h5>
                            <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>

                        </div>
                        <form action="ControladorAlquiler" method="post" id="test" onSubmit="if (!confirm('¿Seguro que desea agregar este tipo de vehiculo?')) {
                                    return false;
                                }">
                            <div class="modal-body">
                                <div class="row">
                                    <div class="container">
                                        <label for="txtAgregarIdTipo" class="text-info">ID Tipo</label>
                                        <input id="txtAgregarIdTipo" name="txtAgregarIdTipo" type="text" class="form-control" readonly>
                                        <br>
                                        <label for="nombreClienteAgregar" class="text-info">Descripcion</label>
                                        <input id="txtAgregarDescripcionTipo" name="txtAgregarDescripcionTipo" type="text" class="form-control">
                                        <br>
                                    </div></div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                <div class="row">
                                    <div class="col-12"><button name="submit" type="submit" value="AgregarTipoVehiculo" class="btn btn-primary">Agregar</button></div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal fade"  data-bs-backdrop="static" id="modaleditarcliente" tabindex="-1" aria-labelledby="modaleditarcliente" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="modaleditarprodlabel">Editar cliente</h5>
                            <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>

                        </div>
                        <form action="ControladorAlquiler" method="post" id="test" onSubmit="if (!confirm('¿Seguro que desea guardar cambios?')) {
                                    return false;
                                }">
                            <div class="modal-body">
                                <div class="row">
                                    <div class="container">

                                        <label for="idClienteAgregar" class="text-info">ID Cliente</label>
                                        <input id="txt_idCliente_editar" name="txt_idCliente_editar" type="text" class="form-control" readonly>
                                        <br>
                                        <label for="nombreClienteAgregar" class="text-info">Nombre</label>
                                        <input id="txt_nombre_editar" name="txt_nombre_editar" type="text" class="form-control">
                                        <br>
                                        <label for="apellidoClienteAgregar" class="text-info">Apellido</label>
                                        <input id="txt_apellido_editar" name="txt_apellido_editar" type="text" class="form-control">
                                        <br>
                                        <label for="NITclienteAgregar" class="text-info">NIT</label>
                                        <input id="txt_NIT_editar" name="txt_NIT_editar" type="number" class="form-control">
                                        <br>
                                        <label for="telefonoClienteAgregar" class="text-info">Telefono</label>
                                        <input id="txt_telefono_editar" name="txt_telefono_editar" type="number" class="form-control">
                                        <br>
                                        <label for="direccionClienteAgregar" class="text-info">Direccion</label>
                                        <input id="txt_direccion_editar" name="txt_direccion_editar" type="text" class="form-control">

                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                <div class="row">
                                    <div class="col-12"><button name="submit" type="submit" value="GuardarCambiosCliente" class="btn btn-primary">Guardar Cambios</button></div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal fade"  data-bs-backdrop="static" id="modalagregarcliente" tabindex="-1" aria-labelledby="modalagregarcliente" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="modaleditarprodlabel">Agregar cliente</h5>
                            <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>

                        </div>
                        <form action="ControladorAlquiler" method="post" id="test" onSubmit="if (!confirm('¿Seguro que desea agregar este cliente?')) {
                                    return false;
                                }">
                            <div class="modal-body">
                                <div class="row">
                                    <div class="container">
                                        <label for="txt_id_agregar" class="text-info">ID Cliente</label>
                                        <input id="txt_id_agregar" name="txt_id_agregar" type="text" class="form-control" readonly>
                                        <br>
                                        <label for="txt_nombre_agregar" class="text-info">Nombre</label>
                                        <input id="txt_nombre_agregar" name="txt_nombre_agregar" type="text" class="form-control">
                                        <br>
                                        <label for="txt_apellido_agregar" class="text-info">Apellido</label>
                                        <input id="txt_apellido_agregar" name="txt_apellido_agregar" type="text" class="form-control">
                                        <br>
                                        <label for="txt_NIT_agregar" class="text-info">NIT</label>
                                        <input id="txt_NIT_agregar" name="txt_NIT_agregar" type="number" class="form-control">
                                        <br>
                                        <label for="txt_telefono_agregar" class="text-info">Telefono</label>
                                        <input id="txt_telefono_agregar" name="txt_telefono_agregar" type="number" class="form-control">
                                        <br>
                                        <label for="txt_direccion_agregar" class="text-info">Direccion</label>
                                        <input id="txt_direccion_agregar" name="txt_direccion_agregar" type="text" class="form-control">

                                    </div></div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                <div class="row">
                                    <div class="col-12"><button name="submit" type="submit" value="AgregarCliente" class="btn btn-primary">Guardar Cambios</button></div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>      
            <div class="modal fade"  data-bs-backdrop="static" id="modaldetalles" tabindex="-1" aria-labelledby="modalagregarcliente" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="modaleditarprodlabel">Detalles Renta</h5>
                            <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>

                        </div>
                        <form action="ControladorAlquiler" method="post" id="test" onSubmit="if (!confirm('¿Seguro que desea guardar cambios?')) {
                                    return false;
                                }">
                            <div class="modal-body">
                                <div class="row">
                                    <div class="container-fluid">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label for="txt_idCliente_detalle_renta" class="text-info">ID Cliente</label>
                                                <input id="txt_idCliente_detalle_renta" name="txt_idCliente_detalle_renta" type="text" class="form-control" readonly>
                                                <br>
                                                <label for="txt_serie_detalle_renta" class="text-info">Serie</label>
                                                <input id="txt_serie_detalle_renta" name="txt_serie_detalle_renta" type="text" class="form-control" readonly>
                                                <br>
                                                <label for="txt_nombre_detalle_renta" class="text-info">Nombre</label>
                                                <input id="txt_nombre_detalle_renta" name="txt_nombre_detalle_renta" type="text" class="form-control" readonly>
                                                <br>
                                                <label for="txt_apellido_detalle_renta" class="text-info">Apellido</label>
                                                <input id="txt_apellido_detalle_renta" name="txt_apellido_detalle_renta" type="text" class="form-control" readonly>
                                                <br>
                                                <label for="txt_pago_detalle_renta" class="text-info">Pago</label>
                                                <input id="txt_pago_detalle_renta" name="txt_pago_detalle_renta" type="text" class="form-control" readonly>
                                            </div>
                                            <div class="col-md-6 ms-auto">
                                                <label for="txt_fechaR_detalle_renta" class="text-info">Fecha renta</label>
                                                <input id="txt_fechaR_detalle_renta" name="txt_fechaR_detalle_renta" type="text" class="form-control" readonly>
                                                <br>
                                                <label for="txt_fechaD_detalle_renta" class="text-info">Fecha devolucion esperada</label>
                                                <input id="txt_fechaD_detalle_renta" name="txt_fechaD_detalle_renta" type="text" class="form-control" readonly>
                                                <br>
                                                <label for="txt_idVehiculo_detalle_renta" class="text-info">ID Vehiculo</label>
                                                <input id="txt_idVehiculo_detalle_renta" name="txt_idVehiculo_detalle_renta" type="text" class="form-control" readonly>
                                                <br>
                                                <label for="txt_marca_detalle_renta" class="text-info">Marca</label>
                                                <input id="txt_marca_detalle_renta" name="txt_marca_detalle_renta" type="text" class="form-control" readonly>
                                                <br>
                                                <label for="txt_tipo_detalle_renta" class="text-info">Tipo</label>
                                                <input id="txt_tipo_detalle_renta" name="txt_tipo_detalle_renta" type="text" class="form-control" readonly>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>                                
                            </div>
                        </form>
                    </div>
                </div>
            </div>   
            <div class="modal fade"  data-bs-backdrop="static" id="modaldevolucion" tabindex="-1" aria-labelledby="modaldevolucion" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="modaleditarprodlabel">Devolver vehiculo</h5>
                            <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>

                        </div>
                        <form action="ControladorAlquiler" method="post" id="test" onSubmit="if (!confirm('¿Seguro que desea guardar cambios?')) {
                                    return false;
                                }">
                            <div class="modal-body">
                                <div class="row">
                                    <div class="container">
                                        <label for="txt_idRenta_devolucion" class="text-info">ID Renta</label>
                                        <input id="txt_idRenta_devolucion" name="txt_idRenta_devolucion" type="text" class="form-control" readonly>
                                        <br>
                                        <label for="txt_idVehiculo_devolucion" class="text-info">ID Vehiculo</label> 
                                        <input id="txt_idVehiculo_devolucion" name="txt_idVehiculo_devolucion" class="form-control" readonly>
                                        <br>
                                        <label for="txt_nombre_devolucion" class="text-info">Nombre</label>
                                        <input id="txt_nombre_devolucion" name="txt_nombre_editar" type="text" class="form-control" readonly>
                                        <br>
                                        <label for="txt_apellido_devolucion" class="text-info">Apellido</label>
                                        <input id="txt_apellido_devolucion" name="txt_apellido_editar" type="text" class="form-control" readonly>
                                        <br>
                                        <label for="txt_mora" class="text-info">Mora</label>
                                        <input id="txt_mora" name="txt_apellido_editar" type="text" class="form-control" readonly>
                                        <br>
                                        <label for="txt_observacion_devolucion" class="text-info">Observaciones</label> 
                                        <textarea id="txt_observacion_devolucion" name="txt_observacion" cols="40" rows="5" class="form-control"></textarea>
                                        <label for="txt_fecha_hora_devolucion" class="text-info">Fecha y Hora</label> 
                                        <input id="txt_fecha_hora_devolucion" name="txt_fecha_hora_devolucion" class="form-control" readonly>
                                    </div></div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                <div class="row">
                                    <div class="col-12"><button name="submit" type="submit" value="GuardarDevolucion" class="btn btn-primary">Devolver</button></div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>  
            <script> //Para las fechas
            </script>
            <script>
                var date1 = new flatpickr("#datepickerinicio", {
                    minDate: "today",
                    minuteIncrement: 30,
                    position: "auto center",
                    enableTime: true,
                    minTime: "8:00",
                    maxTime: "20:00",
                });
                var date2 = new flatpickr("#datepickerfin", {
                    minDate: "today",
                    minuteIncrement: 30,
                    position: "auto center",
                    enableTime: true,
                    minTime: "8:00",
                    maxTime: "20:00",
                });
                $("#datepickerfin").change(function () {
                    date1 = new flatpickr("#datepickerinicio", {
                        maxDate: document.getElementById("datepickerfin").value,
                        minDate: "today",
                        minuteIncrement: 30,
                        position: "auto center",
                        enableTime: true,
                        minTime: "8:00",
                        maxTime: "20:00",
                    });
                    if (document.getElementById("datepickerinicio").value.length != 0)
                    {
                        calculopordia();
                    }
                });
                $("#datepickerinicio").change(function () {
                    date2 = new flatpickr("#datepickerfin", {
                        minDate: document.getElementById("datepickerinicio").value,
                        minuteIncrement: 30,
                        position: "auto center",
                        enableTime: true,
                        minTime: "8:00",
                        maxTime: "20:00",
                    });
                    if (document.getElementById("datepickerfin").value.length != 0)
                    {
                        calculopordia();
                    }
                });
            </script>            
            <script> //PAra los vehiculos
                var Marcas = [
                <c:forEach items="${Vehiculos}" var="Vehiculos">
                    '<c:out value="${Vehiculos}" />',
                </c:forEach>
                ];
                var tipoDescripcion = [
                <c:forEach items="${TipoVehiculos}" var="TipoVehiculos">
                    '<c:out value="${TipoVehiculos.tipoDescripcion}" />',
                </c:forEach>
                ];
                var marcaDescripcion = [
                <c:forEach items="${TipoVehiculos}" var="TipoVehiculos">
                    '<c:out value="${TipoVehiculos.marcaDescripcion}" />',
                </c:forEach>
                ];
                $(document).ready(function () {
                    $('#ddTipoVehiculo').html('');
                    for (var i = 0; i < Marcas.length; i++) {
                        if ($('#ddMarca').val() == Marcas[i]) {
                            for (var j = 0; j < tipoDescripcion.length; j++)
                            {
                                if (Marcas[i] == marcaDescripcion[j])
                                {
                                    $('#ddTipoVehiculo').append('<option value="' + tipoDescripcion[j] + '"> ' + tipoDescripcion[j] + ' </option>');
                                }
                            }
                        }
                    }
                });
                $('#ddMarca').on('change', function () {
                    $('#ddTipoVehiculo').html('');
                    for (var i = 0; i < Marcas.length; i++) {
                        if ($('#ddMarca').val() == Marcas[i]) {
                            for (var j = 0; j < tipoDescripcion.length; j++)
                            {
                                if (Marcas[i] == marcaDescripcion[j])
                                {
                                    $('#ddTipoVehiculo').append('<option value="' + tipoDescripcion[j] + '"> ' + tipoDescripcion[j] + ' </option>');
                                }
                            }
                        }
                    }
                });
            </script> 
            <script> //EditarVehiculo
                function editarVehiculo(id, descripcion, precio, existencia, estado) {
                    alert(id + " " + descripcion);
                    document.getElementById("txtidvehiculoeditar").value = id;
                    document.getElementById("txtdescripcionvehiculoeditar").value = descripcion;
                    document.getElementById("txtpreciovehiculoeditar").value = precio;
                    document.getElementById("txtexistenciavehiculoeditar").value = existencia;
                    document.getElementById("txtestadovehiculoeditar").value = estado;
                }
            </script>
            <script>//EditarCliente
                function editarcliente(id, nombre, apellido, NIT, telefono, direccion) {
                    alert(nombre + " " + apellido);
                    document.getElementById("txt_idCliente_editar").value = id;
                    document.getElementById("txt_nombre_editar").value = nombre;
                    document.getElementById("txt_apellido_editar").value = apellido;
                    document.getElementById("txt_NIT_editar").value = NIT;
                    document.getElementById("txt_telefono_editar").value = telefono;
                    document.getElementById("txt_direccion_editar").value = direccion;
                }
            </script> 
            <script>//DetallesRenta
                function detalleRenta(id, serie, nombre, apellido, pago, fechaR, fechaD, idvehiculo, marca, tipo) {
                    document.getElementById("txt_idCliente_detalle_renta").value = id;
                    document.getElementById("txt_serie_detalle_renta").value = serie;
                    document.getElementById("txt_nombre_detalle_renta").value = nombre;
                    document.getElementById("txt_apellido_detalle_renta").value = apellido;
                    document.getElementById("txt_pago_detalle_renta").value = pago;
                    document.getElementById("txt_fechaR_detalle_renta").value = fechaR;
                    document.getElementById("txt_fechaD_detalle_renta").value = fechaD;
                    document.getElementById("txt_idVehiculo_detalle_renta").value = idvehiculo;
                    document.getElementById("txt_marca_detalle_renta").value = marca;
                    document.getElementById("txt_tipo_detalle_renta").value = tipo;
                }
            </script> 
            <script>//DevolucionRenta
                function devolucionRenta(id, idVehiculo, nombre, apellido, totalMora) {
                    document.getElementById("txt_idRenta_devolucion").value = id;
                    document.getElementById("txt_idVehiculo_devolucion").value = idVehiculo;
                    document.getElementById("txt_nombre_devolucion").value = nombre;
                    document.getElementById("txt_apellido_devolucion").value = apellido;
                    document.getElementById("txt_mora").value = totalMora;
                    var d = new Date();
                    var datestring = d.getFullYear() + "-" + ("0" + (d.getMonth() + 1)).slice(-2) + "-" + ("0" + d.getDate()).slice(-2) +
                            " " + ("0" + d.getHours()).slice(-2) + ":" + ("0" + d.getMinutes()).slice(-2);
                    document.getElementById("txt_fecha_hora_devolucion").value = datestring;
                }
            </script> 
            <script type="text/javascript"> //populate dropbox
                $(document).ready(function () {
                    var selectBox = document.getElementById("ddcliente");
                    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
                    var fields = selectedValue.split('?');
                    var idclient = fields[0];
                    var nombre = fields[2] + " " + fields[3];
                    var direccion = fields[4];
                    var telefono = fields[5]
                    document.getElementById("txtIDCliente").value = idclient;
                    document.getElementById("txtNombre").value = nombre;
                    document.getElementById("txtDireccion").value = direccion;
                    document.getElementById("txtTelefono").value = telefono;
                });
                function changeFunc() {
                    var selectBox = document.getElementById("ddcliente");
                    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
                    var fields = selectedValue.split('?');
                    var idclient = fields[0];
                    var nombre = fields[2] + " " + fields[3];
                    var direccion = fields[4];
                    var telefono = fields[5]
                    document.getElementById("txtIDCliente").value = idclient;
                    document.getElementById("txtNombre").value = nombre;
                    document.getElementById("txtDireccion").value = direccion;
                    document.getElementById("txtTelefono").value = telefono;
                }

            </script>
            <script> //confirmation
                $(document).on('click', ':not(form)[data-confirm]', function (e) {
                    if (!confirm($(this).data('confirm'))) {
                        e.stopImmediatePropagation();
                        e.preventDefault();
                    }
                });
                $(document).on('submit', 'form[data-confirm]', function (e) {
                    if (!confirm($(this).data('confirm'))) {
                        e.stopImmediatePropagation();
                        e.preventDefault();
                    }
                });
                $(document).on('input', 'select', function (e) {
                    var msg = $(this).children('option:selected').data('confirm');
                    if (msg != undefined && !confirm(msg)) {
                        $(this)[0].selectedIndex = 0;
                    }
                });
            </script> 
            <script>
                function calculopordia() {
                    var dateJS1 = new Date(document.getElementById("datepickerinicio").value);
                    var dateJS2 = new Date(document.getElementById("datepickerfin").value);
                    var Difference_In_Time = dateJS2.getTime() - dateJS1.getTime();
                    var Difference_In_Days = Difference_In_Time / (1000 * 3600 * 24);
                    var dolar = '${TipoCambio}';
                    var totaldolares = Difference_In_Days * 70;
                    var totalquetzales = totaldolares * dolar;
                    $('#txttotaldias').val(Difference_In_Days);
                    $('#txttdolares').val(totaldolares);
                    $('#txttquetz').val(totalquetzales);
                }
            </script>
    </center> 
</body> 
</html> 
