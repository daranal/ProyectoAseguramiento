package Models;

public class DocumentoRenta extends Renta{

    private String nombre;
    private String apellido;
    private String tipoPago;
    private String fecha;
    private String Marca, Tipo;
    String observaciones;
    int diasAtraso;
    double mora;

    public DocumentoRenta(String nombre, String apellido, String tipoPago, String fecha, String Marca, String Tipo, int diasAtraso, double mora, String observaciones) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoPago = tipoPago;
        this.fecha = fecha;
        this.Marca = Marca;
        this.Tipo = Tipo;
        this.diasAtraso = diasAtraso;
        this.mora = mora;
        this.observaciones = observaciones;
    }   

    public DocumentoRenta() {        
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    
    public int getDiasAtraso() {
        return diasAtraso;
    }

    public void setDiasAtraso(int diasAtraso) {
        this.diasAtraso = diasAtraso;
    }

    public double getMora() {
        return mora;
    }

    public void setMora(double mora) {
        this.mora = mora;
    }
    
    

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}