<%-- 
    Document   : ModulosOpciones
    Created on : May 2, 2022, 11:49:27 AM
    Author     : W10
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Models.Modulos"%>
<%@page import="Access.DAOModulos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar/Añadir Modulos</title>
        <jsp:include page="/Header.jsp"></jsp:include>
    </head>
    <body>
        <div class="container">
            <form id="form-work" class="" name="form-work" action="ControllerModulos" method="get">
                    <h1>Modificar/Añadir Modulos</h1>
                  <div class="form-group" > 

                      <%
                         
                          DAOModulos daoMod = new DAOModulos();
                          //Variable que nos envian desde el controller por medio de la instrucción setAttribute
                          int idMod = Integer.parseInt((String) request.getAttribute("idModulo"));
                          Modulos Mod = new Modulos();
                          if(idMod!=0){
                            Mod = daoMod.CargarModulo(idMod);
                          }else{
                              Mod.setId_modulo(idMod);
                          }
                          
                      %>
                            

            <%  if (Mod.getId_modulo()==0) { %>
            <input type="hidden" name="codigo" value="<%= Mod.getId_modulo()%>">
                      <div class="col-md-4">
                          <label class="control-label" for="nombre">Nombre</label>
                          <input name="nombre" class="form-control" placeholder="Nombre" type="text">
                          <label class="control-label" for="descripcion">Descripcion</label>
                          <input name="descripcion" class="form-control" placeholder="Descripcion" type="text">
                      </div>
                      <br>
                      <div class="col-md-3">
                          
                          <label class="control-label" for="path">Path</label>
                          <select name="path">
                          <option value="Principal.jsp">Ninguno</option>
                          <option value="Modulos.jsp">Modulos</option>
                          <option value="Roles.jsp">Roles</option>
                          <option value="Usuarios.jsp">Usuarios</option>
                          <option value="Permisos.jsp">Permisos</option>
                          <option value="Clientes.jsp">Clientes</option>
                          <option value="Vehiculos.jsp">Vehiculos</option>
                          <option value="Marcas.jsp">Marcas</option>
                          <option value="Alquiler.jsp">Renta</option>
                          <option value="Devolucion.jsp">Devolución</option>
                          <option value="R_DocumentoRenta.jsp">Reporte Documento Renta</option>
                          </select>
                          <br>
                          <label class="control-label" for="nivel">Nivel</label>
                          <input name="nivel" class="form-control" placeholder="Nivel" type="text" >
                          <label class="control-label" for="orden">Orden</label>
                          <input name="orden" class="form-control" placeholder="Orden" type="text" >
                                                      <label class="control-label" for="activo">ID Modulo Padre</label>
                          <select name="id_moduloPadre">
                          <option value="0">Ninguno</option>
                          <%
                        DAOModulos daoModulo = new  DAOModulos();
                        List<Modulos> lstModulo = daoModulo.listarModulosPadres();
                        Iterator<Modulos> iteratorMod = lstModulo.iterator();
                        Modulos mod = null;
                        while (iteratorMod.hasNext()){
                            mod = iteratorMod.next();                        
                        %>  
                          <option value="<%= mod.getId_modpadre()%>"><%= mod.getNombre()%></option>
                          <%}%>
                          </select>
                          <br>
                          <label class="control-label" for="activo">Estado (Activo)</label>
                          <select name="activo">
                          <option value="Y">Si</option>
                          <option value="N">No</option>
                          </select>
                          <br>
                                <div class="col-md-3">
                                <button type="submit" name="accion" value="anadir" class="btn btn-success">Añadir</button>
                                </div>
                          
                          
            <%}else {%>
            
                    <input type="hidden" name="codigo" value="<%= Mod.getId_modulo()%>">  
                      <div class="col-md-4">
                          <label class="control-label" for="nombre">Nombre</label>
                          <input name="nombre" class="form-control" placeholder="Nombre" type="text" value ="<%= Mod.getNombre()%>">

                          <label class="control-label" for="descripcion">Descripcion</label>
                          <input name="descripcion" class="form-control" placeholder="Descripcion" type="text" value="<%= Mod.getDescripcion()%>">
                      </div>
                      <div class="col-md-3">
                          <label class="control-label" for="path">Path</label>
                          <select name="path">
                          <option value="Principal.jsp">Ninguno</option>
                          <option value="Modulos.jsp">Modulos</option>
                          <option value="Roles.jsp">Roles</option>
                          <option value="Usuarios.jsp">Usuarios</option>
                          <option value="Permisos.jsp">Permisos</option>
                          <option value="Clientes.jsp">Clientes</option>
                          <option value="Vehiculos.jsp">Vehiculos</option>
                          <option value="Marcas.jsp">Marcas</option>
                          <option value="Alquiler.jsp">Renta</option>
                          <option value="Devolucion.jsp">Devolución</option>
                          <option value="R_DocumentoRenta.jsp">Reporte Documento Renta</option>
                          </select>
                          <br>
                          <label class="control-label" for="nivel">Nivel</label>
                          <input name="nivel" class="form-control" placeholder="Nivel" type="text" value="<%=Mod.getNivel()%>">
                          <label class="control-label" for="orden">Orden</label>
                          <input name="orden" class="form-control" placeholder="Orden" type="text" value="<%=Mod.getOrden()%>">
                          <label class="control-label" for="activo">ID Modulo Padre</label>
                          <select name="id_moduloPadre">
                          <option value="0">Ninguno</option>
                    <%
                        DAOModulos daoModulo = new  DAOModulos();
                        List<Modulos> lstModulo = daoModulo.listarModulosPadres();
                        Iterator<Modulos> iteratorMod = lstModulo.iterator();
                        Modulos mod = null;
                        while (iteratorMod.hasNext()){
                            mod = iteratorMod.next();                        
                    %>   
                          <option value="<%= mod.getId_modpadre()%>"><%= mod.getNombre()%></option>
                          <%}%>
                          </select>
                          <br>
                          <label class="control-label" for="activo">Estado (Activo)</label>
                          <select name="activo">
                          <option value="Y">Si</option>
                          <option value="N">No</option>
                          </select>
                      <br>
                      <br>
                      <div class="col-md-3">
                      <button type="submit" name="accion" value="update" class="btn btn-success">Actualizar</button>
                      </div>
                      <%}%>
                      <br>
                      <br>
                      <div class="col-md-3">
                          <a class="btn btn-success" href="/ProyectoFaseI/ControllerModulos?accion=read"  > Regresar</a>
                      </div>
                  </div> 
            </form>
        </div>       
    </body>
</html>

