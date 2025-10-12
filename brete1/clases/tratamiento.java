package brete1.clases;

    public class tratamiento extends servicio {
    private int cantidad;
    private String indicacion;
        
    //metodo 
    //constructor
    public tratamiento(int id, String nombre, double precio, int cantidad, String indicacion) {
        super(id, nombre, precio);
        this.cantidad = cantidad;
        this.indicacion = indicacion;
    }
    public int getCantidad() {
        return cantidad;
    }
    public String getIndicacion() {
        return indicacion;
    }
    public void setCantidad(int tipo) {
        this.cantidad = tipo;
    }
    public void setIndicacion(String indicacion) {
        this.indicacion = indicacion;
    }
    @Override
    public String toString() {
    return "Tratamiento {" +
           "ID=" + id +
           ", Nombre='" + nombre + '\'' +
           ", Precio=" + precio +
           ", Duración o Cantidad=" + cantidad +
           ", Descripción='" + indicacion + '\'' +
           '}';
}

   
}