/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author ivanp
 */
public class Modulos {
    int id_modulo,nivel, id_modpadre;
    String nombre, descripcion, path, orden, fecha_crea, fecha_mod, usuario_crea, usuario_mod;
    String activo;

    public Modulos() {
    }

    public Modulos(int id_modulo, String nombre, String descripcion, String path, String orden, int id_modpadre, String fecha_crea, String fecha_mod, String usuario_crea, String usuario_mod, String activo) {
        this.id_modulo = id_modulo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.path = path;
        this.orden = orden;
        this.id_modpadre = id_modpadre;
        this.fecha_crea = fecha_crea;
        this.fecha_mod = fecha_mod;
        this.usuario_crea = usuario_crea;
        this.usuario_mod = usuario_mod;
        this.activo = activo;
    }

    public int getId_modulo() {
        return id_modulo;
    }

    public void setId_modulo(int id_modulo) {
        this.id_modulo = id_modulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public int getId_modpadre() {
        return id_modpadre;
    }

    public void setId_modpadre(int id_modpadre) {
        this.id_modpadre = id_modpadre;
    }

    public String getFecha_crea() {
        return fecha_crea;
    }

    public void setFecha_crea(String fecha_crea) {
        this.fecha_crea = fecha_crea;
    }

    public String getFecha_mod() {
        return fecha_mod;
    }

    public void setFecha_mod(String fecha_mod) {
        this.fecha_mod = fecha_mod;
    }

    public String getUsuario_crea() {
        return usuario_crea;
    }

    public void setUsuario_crea(String usuario_crea) {
        this.usuario_crea = usuario_crea;
    }

    public String getUsuario_mod() {
        return usuario_mod;
    }

    public void setUsuario_mod(String usuario_mod) {
        this.usuario_mod = usuario_mod;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }


    
    
}
