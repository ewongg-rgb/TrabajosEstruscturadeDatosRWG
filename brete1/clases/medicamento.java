package brete1.clases;


public class medicamento extends servicio {
    private int cantidad;
    private String indicacion;

    //metodo 
    //constructor
    public medicamento(int id, String nombre, double precio, int cantidad, String indicacion) {
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

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setIndicacion(String indicacion) {
        this.indicacion = indicacion;
    }

   @Override
public String toString() {
    return " Medicamento {" +
           "ID=" + id +
           ", Nombre='" + nombre + '\'' +
           ", Precio=" + precio +
           ", Cantidad=" + cantidad +
           ", Indicación='" + indicacion + '\'' +
           '}';
}

}


  

