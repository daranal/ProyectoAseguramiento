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
public class Renta {

    int idVehiculoSelected, serie, idRenta, idCliente, idUsuario, idTipoPago;
    String fechaRentaSelected, fechaDevolucionSelected, estado;
    float totalCalculated;
    String fechaPrestamo, fechaDevolucion;
    float total;

    public Renta() {
    }

    public Renta(int idVehiculoSelected, int serie, int idRenta, int idCliente, int idUsuario, int idTipoPago, String fechaRentaSelected, String fechaDevolucionSelected, String estado, float totalCalculated, String fechaPrestamo, String fechaDevolucion, float total) {
        this.idVehiculoSelected = idVehiculoSelected;
        this.serie = serie;
        this.idRenta = idRenta;
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
        this.idTipoPago = idTipoPago;
        this.fechaRentaSelected = fechaRentaSelected;
        this.fechaDevolucionSelected = fechaDevolucionSelected;
        this.estado = estado;
        this.totalCalculated = totalCalculated;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdVehiculoSelected() {
        return idVehiculoSelected;
    }

    public void setIdVehiculoSelected(int idVehiculoSelected) {
        this.idVehiculoSelected = idVehiculoSelected;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public int getIdRenta() {
        return idRenta;
    }

    public void setIdRenta(int idRenta) {
        this.idRenta = idRenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(int idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public String getFechaRentaSelected() {
        return fechaRentaSelected;
    }

    public void setFechaRentaSelected(String fechaRentaSelected) {
        this.fechaRentaSelected = fechaRentaSelected;
    }

    public String getFechaDevolucionSelected() {
        return fechaDevolucionSelected;
    }

    public void setFechaDevolucionSelected(String fechaDevolucionSelected) {
        this.fechaDevolucionSelected = fechaDevolucionSelected;
    }

    public float getTotalCalculated() {
        return totalCalculated;
    }

    public void setTotalCalculated(float totalCalculated) {
        this.totalCalculated = totalCalculated;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

}
