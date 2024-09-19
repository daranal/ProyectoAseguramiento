package Access;

import Conexion.*;
import Models.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author W10
 */
public class DAOFase2 {

    Clientes cliente = new Clientes();
    Vehiculos vehicles = new Vehiculos();
    DocumentoRenta documentoRenta = new DocumentoRenta();
    Marcas marcas = new Marcas();
    Renta renta = new Renta();
    TipoVehiculo tipoVehiculo = new TipoVehiculo();
    String strSql = "";
    Conexion conexion = new Conexion();
    ResultSet rs = null;
    boolean respuesta = false;

    public int lastIdCliente() {
        int getMaxID = 0;
        try {
            strSql = "select MAX(ID_CLIENTE) from CLIENTE";
            conexion.open();
            rs = conexion.executeQuery(strSql);

            while (rs.next()) {
                getMaxID = rs.getInt(1);
            }
            rs.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return getMaxID;
    }

    public int lastIdMarcas() {
        int getMaxID = 0;
        try {
            strSql = "select MAX(ID_MARCA) from MARCA";
            conexion.open();
            rs = conexion.executeQuery(strSql);

            while (rs.next()) {
                getMaxID = rs.getInt(1);
            }
            rs.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return getMaxID + 1;
    }

    public int lastIdRenta() {
        int getMaxID = 0;
        try {
            strSql = "select MAX(ID_RENTA) from RENTA";
            conexion.open();
            rs = conexion.executeQuery(strSql);

            while (rs.next()) {
                getMaxID = rs.getInt(1);
            }
            rs.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOModulos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DAOModulos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getMaxID + 1;
    }

    public int lastIdDevolucion() {
        int getMaxID = 0;
        try {
            strSql = "select MAX(ID_DEVOLUCION) from DEVOLUCION";
            conexion.open();
            rs = conexion.executeQuery(strSql);

            while (rs.next()) {
                getMaxID = rs.getInt(1);
            }
            rs.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOModulos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DAOModulos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getMaxID + 1;
    }

    public int lastIdVehiculo() {
        int getMaxID = 0;
        try {
            strSql = "select MAX(ID_VEHICULO) from VEHICULO";
            conexion.open();
            rs = conexion.executeQuery(strSql);

            while (rs.next()) {
                getMaxID = rs.getInt(1);
            }
            rs.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOModulos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DAOModulos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getMaxID + 1;
    }

    public int lastIdTipoVehiculo() {
        int getMaxID = 0;
        try {
            strSql = "select MAX(ID_TIPO_VEHICULO) from TIPO_VEHICULO";
            conexion.open();
            rs = conexion.executeQuery(strSql);

            while (rs.next()) {
                getMaxID = rs.getInt(1);
            }
            rs.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOModulos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DAOModulos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getMaxID + 1;
    }

    public int getIDVehiculoSelected(String Marca, String Tipo) throws ClassNotFoundException, SQLException, Exception {
        int value = 0;
        try {
            strSql = "SELECT * FROM Vehiculo inner join tipo_vehiculo on vehiculo.id_tipo_vehiculo = tipo_vehiculo.id_tipo_vehiculo inner join marca on vehiculo.id_marca = marca.id_marca \n"
                    + "where marca.descripcion = '" + Marca + "' and tipo_vehiculo.descripcion = '" + Tipo + "' and VEHICULO.ID_VEHICULO_ESTADO = 1";
            conexion.open();
            rs = conexion.executeQuery(strSql);
            while (rs.next()) {
                value = rs.getInt(1);
            }
            rs.close();
            conexion.close();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.toString());

        }
        return value;
    }

    public List listarRentasActuales() throws SQLException, Exception {
        ArrayList<DocumentoRenta> lstDocumentoRentas = new ArrayList<>();
        try {
            strSql = "SELECT * FROM RENTA AS R INNER JOIN CLIENTE AS C ON R.ID_CLIENTE = C.ID_CLIENTE INNER JOIN TIPO_PAGO T ON R.ID_TIPO_PAGO = T.ID_TIPO_PAGO \n"
                    + "INNER JOIN DETALLE_RENTA AS RD ON RD.ID_RENTA = R.ID_RENTA INNER JOIN VEHICULO AS V ON RD.ID_VEHICULO = V.ID_VEHICULO INNER JOIN MARCA AS M ON \n"
                    + "V.ID_MARCA = M.ID_MARCA INNER JOIN TIPO_VEHICULO AS TP ON V.ID_TIPO_VEHICULO = TP.ID_TIPO_VEHICULO WHERE R.ESTADO = 'En Renta'";
            conexion.open();
            rs = conexion.executeQuery(strSql);

            while (rs.next()) {
                DocumentoRenta d = new DocumentoRenta();
                d.setIdRenta(rs.getInt(1));
                d.setSerie(rs.getInt(2));
                d.setFechaRentaSelected(rs.getString(6));
                d.setFechaDevolucionSelected(rs.getString(7));
                d.setTotalCalculated(rs.getFloat(8));
                d.setNombre(rs.getString(12));
                d.setApellido(rs.getString(13));
                d.setTipoPago(rs.getString(17));
                d.setIdVehiculoSelected(rs.getInt(21));
                d.setMarca(rs.getString(29));
                d.setTipo(rs.getString(31));
                        float totalMora=0;        
                        long totalDiasMora =0;
                        SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
                        Date date = new Date();  
                        String fechaHoy = date.toString();
                        String fechaDevolucionInicial = rs.getString(7);

                        Date fecshaDevIn = formato.parse(fechaDevolucionInicial);
                        Date fechaDev = formato.parse("2022-05-28 10:58");
                        if(date.compareTo(fecshaDevIn)>0){
                            totalDiasMora = (fechaDev.getTime() - fecshaDevIn.getTime())/1000/60/60/24;
                        }
                        //System.out.println(fechaDevolucionInicial);
                        float montoTotal = rs.getFloat(8);
                        float mora = (float) (montoTotal * 0.05);

                        if(totalDiasMora> 0){
                            for(int i=0; i<=totalDiasMora; i++){
                                totalMora = totalMora + mora;
                            }
                        }
                
                d.setMora(totalMora);
                lstDocumentoRentas.add(d);
            }
            rs.close();
            conexion.close();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }
        return lstDocumentoRentas;
    }

    public List listarRentas() throws SQLException, Exception {
        ArrayList<DocumentoRenta> lstDocumentoRentas = new ArrayList<>();
        try {
            strSql = "SELECT * FROM RENTA AS R INNER JOIN CLIENTE AS C ON R.ID_CLIENTE = C.ID_CLIENTE INNER JOIN TIPO_PAGO T ON R.ID_TIPO_PAGO = T.ID_TIPO_PAGO \n"
                    + "INNER JOIN DETALLE_RENTA AS RD ON RD.ID_RENTA = R.ID_RENTA INNER JOIN VEHICULO AS V ON RD.ID_VEHICULO = V.ID_VEHICULO INNER JOIN MARCA AS M ON \n"
                    + "V.ID_MARCA = M.ID_MARCA INNER JOIN TIPO_VEHICULO AS TP ON V.ID_TIPO_VEHICULO = TP.ID_TIPO_VEHICULO INNER JOIN DEVOLUCION AS DV ON R.ID_RENTA = DV.ID_RENTA";
            conexion.open();
            rs = conexion.executeQuery(strSql);

            while (rs.next()) {
                DocumentoRenta d = new DocumentoRenta();
                d.setIdRenta(rs.getInt(1));
                d.setSerie(rs.getInt(2));
                d.setFechaRentaSelected(rs.getString(6));
                d.setFechaDevolucionSelected(rs.getString(7));
                d.setTotalCalculated(rs.getFloat(8));
                d.setEstado(rs.getString(9));
                d.setNombre(rs.getString(12));
                d.setApellido(rs.getString(13));
                d.setTipoPago(rs.getString(17));
                d.setIdVehiculoSelected(rs.getInt(21));
                d.setMarca(rs.getString(29));
                d.setTipo(rs.getString(31));
                d.setFechaDevolucion(rs.getString(35));
                d.setObservaciones(rs.getString(37));
                d.setDiasAtraso(rs.getInt(38));
                d.setMora(rs.getDouble(39));
                lstDocumentoRentas.add(d);
            }
            rs.close();
            conexion.close();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }
        return lstDocumentoRentas;
    }

    public List listarMarcas() throws SQLException, Exception {
        List<Marcas> lista = new ArrayList();
        try {
            strSql = "SELECT * FROM MARCA;";
            conexion.open();
            rs = conexion.executeQuery(strSql);
            while (rs.next()) {
                Marcas m = new Marcas();
                m.setIdmarca(rs.getInt("ID_MARCA"));
                m.setDescripcion(rs.getString("DESCRIPCION"));
                lista.add(m);
            }
            rs.close();
            conexion.close();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }
        return lista;
    }

    public List listarClientes() throws SQLException, Exception {

        ArrayList<Clientes> lstClientes = new ArrayList<>();
        try {
            strSql = "SELECT * FROM CLIENTE;";
            conexion.open();
            rs = conexion.executeQuery(strSql);

            while (rs.next()) {
                Clientes client = new Clientes();
                client.setIdCliente(rs.getInt("ID_CLIENTE"));
                client.setNit(rs.getString("NIT"));
                client.setNombre(rs.getString("NOMBRE"));
                client.setApellido(rs.getString("APELLIDO"));
                client.setTelefono(rs.getString("TELEFONO"));
                client.setDireccion(rs.getString("DIRECCION"));
                lstClientes.add(client);
            }
            rs.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lstClientes;
    }

    public List listarVehiculosDisponibles() throws SQLException, Exception {

        ArrayList<Vehiculos> lstVehiculos = new ArrayList<>();
        try {
            strSql = "select * from VEHICULO join MARCA on VEHICULO.ID_MARCA=MARCA.ID_MARCA join VEHICULO_ESTADO on VEHICULO.ID_VEHICULO_ESTADO =VEHICULO_ESTADO.ID_VEHICULO_ESTADO join TIPO_VEHICULO on VEHICULO.ID_TIPO_VEHICULO = TIPO_VEHICULO.ID_TIPO_VEHICULO where VEHICULO.ID_VEHICULO_ESTADO=1; ";
            conexion.open();
            rs = conexion.executeQuery(strSql);

            while (rs.next()) {
                Vehiculos vehicules = new Vehiculos();
                vehicules.setIdVehiculo(rs.getInt(1));
                vehicules.setIdMarca(rs.getInt(2));
                vehicules.setIdTipoVehiculo(rs.getInt(3));
                vehicules.setIdVehiculoEstado(rs.getInt(4));
                vehicules.setModelo(rs.getString(5));
                vehicules.setMarcaDescripcion(rs.getString(7));
                vehicules.setTipoDescripcion(rs.getString(11));
                if (vehicules.getIdVehiculoEstado() == 0) {
                    vehicules.setEstadoDescripcion("En Taller");
                }
                if (vehicules.getIdVehiculoEstado() == 1) {
                    vehicules.setEstadoDescripcion("Activo");
                }
                if (vehicules.getIdVehiculoEstado() == 2) {
                    vehicules.setEstadoDescripcion("En renta");
                }
                lstVehiculos.add(vehicules);
            }
            rs.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }

        return lstVehiculos;
    }

    public List listarTodosVehiculos() throws SQLException, Exception {

        ArrayList<Vehiculos> lstVehiculos = new ArrayList<>();
        try {
            strSql = "select * from VEHICULO join MARCA on VEHICULO.ID_MARCA=MARCA.ID_MARCA join VEHICULO_ESTADO on VEHICULO.ID_VEHICULO_ESTADO =VEHICULO_ESTADO.ID_VEHICULO_ESTADO join TIPO_VEHICULO on VEHICULO.ID_TIPO_VEHICULO = TIPO_VEHICULO.ID_TIPO_VEHICULO";
            conexion.open();
            rs = conexion.executeQuery(strSql);

            while (rs.next()) {
                Vehiculos vehicules = new Vehiculos();
                vehicules.setIdVehiculo(rs.getInt(1));
                vehicules.setIdMarca(rs.getInt(2));
                vehicules.setIdTipoVehiculo(rs.getInt(3));
                vehicules.setIdVehiculoEstado(rs.getInt(4));
                vehicules.setModelo(rs.getString(5));
                vehicules.setMarcaDescripcion(rs.getString(7));
                vehicules.setTipoDescripcion(rs.getString(11));
                if (vehicules.getIdVehiculoEstado() == 0) {
                    vehicules.setEstadoDescripcion("Deshabilitado");
                }
                if (vehicules.getIdVehiculoEstado() == 1) {
                    vehicules.setEstadoDescripcion("Activo");
                }
                if (vehicules.getIdVehiculoEstado() == 2) {
                    vehicules.setEstadoDescripcion("En renta");
                }
                lstVehiculos.add(vehicules);
            }
            rs.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }

        return lstVehiculos;
    }

    public List listarTipoVehiculos() throws SQLException, Exception {
        ArrayList<TipoVehiculo> listaTipoVehiculos = new ArrayList<>();
        try {
            strSql = "SELECT * FROM tipo_vehiculo";
            conexion.open();
            rs = conexion.executeQuery(strSql);
            while (rs.next()) {
                TipoVehiculo tipoVehiculo = new TipoVehiculo();
                tipoVehiculo.setIdTipoVehiculo(rs.getInt(1));
                tipoVehiculo.setDescripcion(rs.getString(2));
                listaTipoVehiculos.add(tipoVehiculo);
            }
            rs.close();
            conexion.close();

        } catch (ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }
        return listaTipoVehiculos;
    }
    
    public String cargarFechaDevolucion(int idRenta) throws SQLException, Exception {
        String returnDate = "";
        try {
            strSql = " Select fecha_devolucion from RENTA where ID_RENTA = " + idRenta;
            conexion.open();
            rs = conexion.executeQuery(strSql);
            while (rs.next()) {
                returnDate=rs.getString(1);
            }
            rs.close();
            conexion.close();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }
        return returnDate;
    }
    
    public float cargarTotalRenta(int idRenta) throws SQLException, Exception {
        float total = 0;
        try {
            strSql = "Select TOTAL from RENTA where ID_RENTA = " + idRenta;
            conexion.open();
            rs = conexion.executeQuery(strSql);
            while (rs.next()) {
                total=rs.getFloat(1);
            }
            rs.close();
            conexion.close();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }
        return total;
    }
    public String agregarCliente(String nombre, String apellido, String NIT, String telefono, String direccion) {
        try {
            int id = lastIdCliente() + 1;
            conexion.open();
            String strSql = "INSERT INTO CLIENTE VALUES (" + id + ",'" + NIT + "', '" + nombre + "', '" + apellido + "'," + telefono + ",'" + direccion + "')";
            conexion.executeSql(strSql);
        } catch (Exception ex) {
            return ex.toString();
        }
        return "Sucess";
    }

    public String eliminarcliente(int id) {
        try {
            conexion.open();
            String strSql = "DELETE FROM CLIENTE WHERE ID_CLIENTE = " + id + "";
            conexion.executeSql(strSql);
            return "sucess";
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return ex.toString();
        }
    }

    public String editarCliente(int id, String nombre, String apellido, String NIT, String Telefono, String Direccion) {
        try {
            conexion.open();
            String strSql = "UPDATE CLIENTE SET NOMBRE = '" + nombre + "', APELLIDO = '" + apellido + "', NIT = '" + NIT + "', TELEFONO = '" + Telefono + "', DIRECCION = '" + Direccion + "' WHERE ID_CLIENTE = '" + id + "'";
            conexion.executeSql(strSql);
        } catch (Exception ex) {
            return ex.toString();
        }

        return "Sucess";
    }

    public String agregarMarca(String Descripcion) {
        int mid = lastIdMarcas();
        strSql = "INSERT INTO MARCA (ID_MARCA, DESCRIPCION) VALUES (" + mid + ",'" + Descripcion + "')";
        try {
            //se abre una conexión hacia la BD
            conexion.open();
            //Se ejecuta la instrucción y retorna si la ejecución fue satisfactoria
            respuesta = conexion.executeSql(strSql);
            //Se cierra la conexión hacia la BD
            conexion.close();
        } catch (ClassNotFoundException ex) {

            return ex.toString();
        } catch (Exception ex) {
            return ex.toString();
        }
        return "Sucess";
    }

    public String agregarTipoVehiculo(String Descripcion) {
        int tpid = lastIdTipoVehiculo();
        strSql = "INSERT INTO TIPO_VEHICULO (ID_TIPO_VEHICULO, DESCRIPCION) VALUES (" + tpid + ",'" + Descripcion + "')";
        try {
            //se abre una conexión hacia la BD
            conexion.open();
            //Se ejecuta la instrucción y retorna si la ejecución fue satisfactoria
            respuesta = conexion.executeSql(strSql);
            //Se cierra la conexión hacia la BD
            conexion.close();
        } catch (ClassNotFoundException ex) {

            return ex.toString();
        } catch (Exception ex) {
            return ex.toString();
        }
        return "Sucess";
    }

    public String agregarRenta(Renta rent, String Marca, String Tipo) throws SQLException {

        int vid = 0;
        try {
            vid = getIDVehiculoSelected(Marca, Tipo);
        } catch (Exception ex) {
            Logger.getLogger(DAOFase2.class.getName()).log(Level.SEVERE, null, ex);
        }
        rent.setIdVehiculoSelected(vid);
        rent.setSerie(rent.getIdRenta());
        strSql = "  insert into RENTA (ID_RENTA, SERIE, ID_CLIENTE, ID_TIPO_PAGO, FECHA_PRESTAMO, FECHA_DEVOLUCION, TOTAL, ESTADO) VALUES (" + rent.getIdRenta()
                + "," + rent.getSerie()
                + "," + rent.getIdCliente()
                + "," + rent.getIdTipoPago()
                + ",'" + rent.getFechaRentaSelected()
                + "','" + rent.getFechaDevolucionSelected()
                + "'," + rent.getTotalCalculated()
                + ",'En renta')"
                + "insert into DETALLE_RENTA (ID_RENTA, SERIE, ID_DETALLE_RENTA, ID_VEHICULO, PRECIO_ALQUILER) Values (" + rent.getIdRenta()
                + "," + rent.getSerie()
                + "," + rent.getIdRenta()
                + "," + rent.getIdVehiculoSelected()
                + "," + rent.getTotalCalculated() + ");"
                + "  update VEHICULO set ID_VEHICULO_ESTADO = 2 where ID_VEHICULO = " + rent.getIdVehiculoSelected();
        //System.out.println(strSql);

        try {
            //se abre una conexión hacia la BD
            conexion.open();
            //Se ejecuta la instrucción y retorna si la ejecución fue satisfactoria
            respuesta = conexion.executeSql(strSql);
            //Se cierra la conexión hacia la BD
            conexion.close();
        } catch (Exception ex) {
            return ex.toString();
        }
        return "Sucess";
    }

    public String agregarVehiculo(int Marca, int tipo, int Modelo, int Estado) throws SQLException {
        int vid = 0;
        try {
            vid = lastIdVehiculo();
        } catch (Exception ex) {
            return ex.toString();
        }
        try {
            conexion.open();
            String strSql = "INSERT INTO VEHICULO VALUES (" + vid + "," + Marca + "," + tipo + "," + Estado + "," + Modelo + ")";
            conexion.executeSql(strSql);
        } catch (Exception ex) {
            return ex.toString();
        }
        return "Sucess";
    }

    public String devolucionVehiculo(int renta, int Vehiculo, String Observacion, String Fecha) {
        int devolucionid = 0;
        devolucionid = lastIdDevolucion();
        try {
            int id = lastIdCliente() + 1;
            conexion.open();
            String strSql = "INSERT INTO DEVOLUCION (ID_DEVOLUCION, ID_RENTA, SERIE, FECHA, OBSERVACIONES, ID_USUARIO, DIAS_ATRASO, MORA) VALUES"
                    + "(" + devolucionid + "," + renta + "," + renta + ", '" + Fecha + "', '" + Observacion + "', 1, 0, 0) "
                    + "UPDATE vehiculo set vehiculo.id_vehiculo_estado = 1 where vehiculo.id_vehiculo = " + Vehiculo + ""
                    + "UPDATE RENTA SET renta.estado = 'Completado' where renta.id_renta = " + renta + "";
            conexion.executeSql(strSql);
        } catch (Exception ex) {
            return ex.toString();
        }
        return "sucess";
    }
    
    public String eliminarprod(int id) {
        try {
            conexion.open();
            String strSql = "DELETE FROM VEHICULO WHERE ID_VEHICULO = " + id + "";
            conexion.executeSql(strSql);
            return "sucess";
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return ex.toString();
        }
    }
    
    public String eliminarMarca(int id) {
        try {
            conexion.open();
            String strSql = "DELETE FROM MARCA WHERE ID_MARCA = " + id + "";
            conexion.executeSql(strSql);
            return "sucess";
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return ex.toString();
        }
    }
    
    public String eliminarTipo(int id) {
        try {
            conexion.open();
            String strSql = "DELETE FROM TIPO_VEHICULO WHERE ID_TIPO_VEHICULO = " + id + "";
            conexion.executeSql(strSql);
            return "sucess";
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return ex.toString();
        }
    }
    
    public float calculoMora(int id) throws Exception{
        float totalMora=0;
        
        int totalDiasMora =0;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd hh:mm");
        Date date = new Date();  
        String fechaHoy = date.toString();
        System.out.println(fechaHoy);
        String fechaDevolucionInicial = cargarFechaDevolucion(id);
        
        Date fechaDevIn = formato.parse(fechaDevolucionInicial);
        Date fechaDev = formato.parse(fechaHoy);
        totalDiasMora = (int) (fechaDev.getTime() - fechaDevIn.getTime())/60/60/24/1000;

        float montoTotal = cargarTotalRenta(id);
        float mora = (float) (montoTotal * 0.05);
        
        if(totalDiasMora> 0){
            for(int i=0; i<=totalDiasMora; i++){
                totalMora = totalMora + mora;
            }
        }
        
        return totalMora;
    }
    //Se necesitan 4 metodos mas para el mantenimiento de Marcas (Editar Eliminar) y Tipo_Vehiculos (Editar, Eliminar)
}
