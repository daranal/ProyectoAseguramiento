package Models;

public class Marcas {

    public int getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(int idmarca) {
        this.idmarca = idmarca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Marcas() {
    }

    public Marcas(int idmarca, String descripcion) {
        this.idmarca = idmarca;
        this.descripcion = descripcion;
    }

    int idmarca;
    String descripcion;

}
