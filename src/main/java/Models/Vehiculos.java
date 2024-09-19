/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author W10
 */
public class Vehiculos {
    int idVehiculo, idMarca, idTipoVehiculo, idVehiculoEstado;
    String modelo;
    String tipoDescripcion, marcaDescripcion, estadoDescripcion;

    public Vehiculos() {
    }

    public Vehiculos(int idVehiculo, int idMarca, int idTipoVehiculo, int idVehiculoEstado, String modelo, String tipoDescripcion, String marcaDescripcion, String estadoDescripcion) {
        this.idVehiculo = idVehiculo;
        this.idMarca = idMarca;
        this.idTipoVehiculo = idTipoVehiculo;
        this.idVehiculoEstado = idVehiculoEstado;
        this.modelo = modelo;
        this.tipoDescripcion = tipoDescripcion;
        this.marcaDescripcion = marcaDescripcion;
        this.estadoDescripcion = estadoDescripcion;
    }

    public String getEstadoDescripcion() {
        return estadoDescripcion;
    }

    public void setEstadoDescripcion(String estadoDescripcion) {
        this.estadoDescripcion = estadoDescripcion;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public int getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    public void setIdTipoVehiculo(int idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public int getIdVehiculoEstado() {
        return idVehiculoEstado;
    }

    public void setIdVehiculoEstado(int idVehiculoEstado) {
        this.idVehiculoEstado = idVehiculoEstado;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipoDescripcion() {
        return tipoDescripcion;
    }

    public void setTipoDescripcion(String tipoDescripcion) {
        this.tipoDescripcion = tipoDescripcion;
    }

    public String getMarcaDescripcion() {
        return marcaDescripcion;
    }

    public void setMarcaDescripcion(String marcaDescripcion) {
        this.marcaDescripcion = marcaDescripcion;
    }
    
    
}
