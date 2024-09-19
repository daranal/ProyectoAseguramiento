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
public class TipoVehiculo {
    int idTipoVehiculo;
    String Descripcion;

    public TipoVehiculo(int idTipoVehiculo, String Descripcion) {
        this.idTipoVehiculo = idTipoVehiculo;
        this.Descripcion = Descripcion;
    }

    public TipoVehiculo() {
    }

    public int getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    public void setIdTipoVehiculo(int idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
}