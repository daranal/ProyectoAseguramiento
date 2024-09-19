/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Access.DAOFase2;
import Models.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;

@WebServlet("/")

public class ControladorAlquiler extends HttpServlet {

    private void loadTables(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        DAOFase2 DAO = new DAOFase2();
        int idrenta = DAO.lastIdRenta();
        List<Vehiculos> DDVehiculos = DAO.listarVehiculosDisponibles();
        List<Clientes> TablaClientes = DAO.listarClientes();
        List<Vehiculos> TablaVehiculos = DAO.listarTodosVehiculos();
        List<Marcas> TablaMarcas = DAO.listarMarcas();
        List<TipoVehiculo> TablaTipoVehiculos = DAO.listarTipoVehiculos();
        List<Renta> TablaRentas = DAO.listarRentasActuales();
        List<Renta> TablaTodasRentas = DAO.listarRentas();
        List<String> oMarcasDisp = new ArrayList<>();
        List<String> rMarcasDisp = new ArrayList<>();
        for (Vehiculos v : DDVehiculos) {
            oMarcasDisp.add(v.getMarcaDescripcion());
        }     
        Set<String> uniqueMarcas = new HashSet<>();
        for (String m : oMarcasDisp) {
            uniqueMarcas.add(m);
        }  
        rMarcasDisp = new ArrayList<>(uniqueMarcas);
        request.setAttribute("TipoCambio", 7.70);
        request.setAttribute("IDRenta", idrenta);
        request.setAttribute("TablaTodasRentas", TablaTodasRentas);
        request.setAttribute("TablaMarcas", TablaMarcas);
        request.setAttribute("TablaTipoVehiculos", TablaTipoVehiculos);
        request.setAttribute("TablaClientes", TablaClientes);
        request.setAttribute("Vehiculos", rMarcasDisp);
        request.setAttribute("TipoVehiculos", DDVehiculos);
        request.setAttribute("TablaVehiculos", TablaVehiculos);
        request.setAttribute("TablaRentas", TablaRentas);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        DAOFase2 DAO = new DAOFase2();
        try {
            loadTables(request, response);
            String action = request.getServletPath();
            try {
                switch (action) {
                    case "/":
                        RequestDispatcher vista = request.getRequestDispatcher("/Login.jsp"); 
                        vista.forward(request, response);
                    case "/Paginas/eliminarcliente":
                        int idcliente = Integer.parseInt(request.getParameter("id"));
                        eliminarcliente(request, response, idcliente);
                        break;
                    case "/Paginas/eliminarprod":
                        int idvehiculo = Integer.parseInt(request.getParameter("id"));
                        eliminarprod(request, response, idvehiculo);
                    break;
                    case "/Paginas/eliminarMarca":
                        int idMarca = Integer.parseInt(request.getParameter("id"));
                        eliminarMarca(request, response, idMarca);
                    break;
                    case "/Paginas/eliminarTipo":
                        int idTipo = Integer.parseInt(request.getParameter("id"));
                        eliminarTipo(request, response, idTipo);
                    break;
                }
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String buttonClicked = request.getParameter("submit");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            if (null == "buttonClicked") {
            } else if ("AgregarCliente".equals(buttonClicked)) {
                agregarCliente(request, response);
            } else if ("GuardarCambiosCliente".equals(buttonClicked)) {
                editarCliente(request, response);
            } else if ("GuardarDevolucion".equals(buttonClicked)) {
                devolucion(request, response);
            } else if ("AgregarRenta".equals(buttonClicked)) {
                agregarRenta(request, response);
            } else if ("AgregarVehiculo".equals(buttonClicked)) {
                agregarVehiculo(request, response);
            } else if ("AgregarMarca".equals(buttonClicked)) {
                agregarMarca(request, response);
            } else if ("AgregarTipoVehiculo".equals(buttonClicked)) {
                agregarTipoVehiculo(request, response);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.toString());;
        } catch (Exception ex) {
            Logger.getLogger(ControladorAlquiler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void editarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        DAOFase2 DAO = new DAOFase2();
        String nombre, apellido, nit, telefono, direccion;
        int id = Integer.parseInt(request.getParameter("txt_idCliente_editar"));
        nombre = request.getParameter("txt_nombre_editar");
        apellido = request.getParameter("txt_apellido_editar");
        nit = request.getParameter("txt_NIT_editar");
        telefono = request.getParameter("txt_telefono_editar");
        direccion = request.getParameter("txt_direccion_editar");
        String status = "disabled";
        status = DAO.editarCliente(id, nombre, apellido, nit, telefono, direccion);
        out.println("<meta http-equiv='refresh' content='1;URL=Alquiler.jsp'>");
        out.println("<h1 style='color:red;'>" + status + "</h1>");
        out.println("<h3 style='color:black;'>Redirigiendo</h3>");
    }

    private static void agregarRenta(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        DAOFase2 DAO = new DAOFase2();
        Renta renta = new Renta();
        renta.setIdRenta(DAO.lastIdRenta());
        renta.setIdCliente(Integer.parseInt(request.getParameter("txtIDCliente")));
        renta.setFechaRentaSelected(request.getParameter("datepickerinicio"));
        renta.setFechaDevolucionSelected(request.getParameter("datepickerfin"));
        renta.setTotalCalculated(Float.parseFloat(request.getParameter("txttquetz")));
        String marca = request.getParameter("ddMarca");
        String tipo = request.getParameter("ddTipoVehiculo");
        String tipopago = request.getParameter("stipopago");
        renta.setIdTipoPago(Integer.parseInt(tipopago));
        System.out.println(renta.getIdTipoPago());
        String status = "disabled";
        status = DAO.agregarRenta(renta, marca, tipo);
        out.println("<meta http-equiv='refresh' content='1;URL=Alquiler.jsp'>");
        out.println("<h1 style='color:red;'>" + status + "</h1>");
        out.println("<h3 style='color:black;'>Redirigiendo</h3>");
    }

    private static void agregarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        DAOFase2 DAO = new DAOFase2();
        String nombre, apellido, nit, telefono, direccion;
        nombre = request.getParameter("txt_nombre_agregar");
        apellido = request.getParameter("txt_apellido_agregar");
        nit = request.getParameter("txt_NIT_agregar");
        telefono = request.getParameter("txt_telefono_agregar");
        direccion = request.getParameter("txt_direccion_agregar");
        String status = "disabled";
        status = DAO.agregarCliente(nombre, apellido, nit, telefono, direccion);
        out.println("<meta http-equiv='refresh' content='1;URL=Alquiler.jsp'>");
        out.println("<h1 style='color:red;'>" + status + "</h1>");
        out.println("<h3 style='color:black;'>Redirigiendo</h3>");
    }

    private void eliminarcliente(HttpServletRequest request, HttpServletResponse response, int idcliente) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        DAOFase2 DAO = new DAOFase2();
        String status = "disabled";
        status = DAO.eliminarcliente(idcliente);
        out.println("<meta http-equiv='refresh' content='1;URL=Alquiler.jsp'>");
        out.println("<h1 style='color:red;'>" + status + "</h1>");
        out.println("<h3 style='color:black;'>Redirigiendo</h3>");
    }
    
    private void devolucion(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        DAOFase2 DAO = new DAOFase2();
        String status = "disabled";
        int id = Integer.parseInt(request.getParameter("txt_idRenta_devolucion"));
        String observacion = request.getParameter("txt_observacion_devolucion");
        String fecha = request.getParameter("txt_fecha_hora_devolucion");
        String fechaDevolucion = DAO.cargarFechaDevolucion(id);
        //fechaDevolucion = "2022-05-05 10:50";
        int vehiculoid = Integer.parseInt(request.getParameter("txt_idVehiculo_devolucion"));
        
        //Calculando Mora
        int totalDiasMora =0;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd hh:mm");
        Date fechaInDev = formato.parse(fechaDevolucion);
        Date fechaDev = formato.parse(fecha);
        totalDiasMora = (int) (fechaDev.getTime() - fechaInDev.getTime())/60/60/24/1000;
        
        float montoTotal = DAO.cargarTotalRenta(id);
        float mora = (float) (montoTotal * 0.05);
        float totalMora=0;
        
        if(totalDiasMora> 0){
            for(int i=0; i<=totalDiasMora; i++){
                totalMora = totalMora + mora;
            }
        }
        
        status = DAO.devolucionVehiculo(id, vehiculoid, observacion, fecha);
        
        out.println("<meta http-equiv='refresh' content='1;URL=Alquiler.jsp'>");
        System.out.println("<a>\" data-confirm=\"¿Deseas elimar el registro? " +totalMora+ "   "+fechaDevolucion+ "   "+fecha+"\"</a>");
        out.println("<h1 style='color:red;'>" + status + "</h1>");
        out.println("<h3 style='color:black;'>Redirigiendo</h3>");
    }
    
    private void eliminarprod(HttpServletRequest request, HttpServletResponse response, int idProducto) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        DAOFase2 DAO = new DAOFase2();
        String status = "disabled";
        status = DAO.eliminarprod(idProducto);
        out.println("<meta http-equiv='refresh' content='1;URL=Alquiler.jsp'>");
        out.println("<h1 style='color:red;'>" + status + "</h1>");
        out.println("<h3 style='color:black;'>Redirigiendo</h3>");
    }
    
    private void agregarVehiculo(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        DAOFase2 DAO = new DAOFase2();
        String status = "disabled";
        int marcaid = Integer.parseInt(request.getParameter("ddAgregarVehiculoMarca"));
        int tipoid = Integer.parseInt(request.getParameter("ddAgregarVehiculoTipo"));
        int modelo = Integer.parseInt(request.getParameter("txtAgregarModelo"));
        int estadoid = Integer.parseInt(request.getParameter("ddAgregarVehiculoEstado"));
        status = DAO.agregarVehiculo(marcaid, tipoid, modelo, estadoid);
        out.println("<meta http-equiv='refresh' content='1;URL=Alquiler.jsp'>");
        out.println("<h1 style='color:red;'>" + status + "</h1>");
        out.println("<h3 style='color:black;'>Redirigiendo</h3>");
    }

    private void agregarMarca(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        DAOFase2 DAO = new DAOFase2();
        String Descripcion = request.getParameter("txtAgregarMarcaDescripcion");
        String status = "disabled";
        status = DAO.agregarMarca(Descripcion);
        out.println("<meta http-equiv='refresh' content='1;URL=Alquiler.jsp'>");
        out.println("<h1 style='color:red;'>" + status + "</h1>");
        out.println("<h3 style='color:black;'>Redirigiendo</h3>");
    }

    private void agregarTipoVehiculo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        DAOFase2 DAO = new DAOFase2();
        String Descripcion = request.getParameter("txtAgregarDescripcionTipo");
        String status = "disabled";
        status = DAO.agregarTipoVehiculo(Descripcion);
        out.println("<meta http-equiv='refresh' content='1;URL=Alquiler.jsp'>");
        out.println("<h1 style='color:red;'>" + status + "</h1>");
        out.println("<h3 style='color:black;'>Redirigiendo</h3>");
    }
    
    private void eliminarMarca(HttpServletRequest request, HttpServletResponse response, int idMarca) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        DAOFase2 DAO = new DAOFase2();
        String status = "disabled";
        status = DAO.eliminarprod(idMarca);
        out.println("<meta http-equiv='refresh' content='1;URL=Alquiler.jsp'>");
        out.println("<h1 style='color:red;'>" + status + "</h1>");
        out.println("<h3 style='color:black;'>Redirigiendo</h3>");
    }
    
    private void eliminarTipo(HttpServletRequest request, HttpServletResponse response, int idMarca) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        DAOFase2 DAO = new DAOFase2();
        String status = "disabled";
        status = DAO.eliminarTipo(idMarca);
        out.println("<meta http-equiv='refresh' content='1;URL=Alquiler.jsp'>");
        out.println("<h1 style='color:red;'>" + status + "</h1>");
        out.println("<h3 style='color:black;'>Redirigiendo</h3>");
    }
}
