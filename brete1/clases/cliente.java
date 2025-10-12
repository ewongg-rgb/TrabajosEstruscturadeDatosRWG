
package brete1.clases;
import brete1.metodos.carritocliente;

public class cliente {
    private String nombrecompleto;
    private int cedula;
    private boolean preferencial;
    private carritocliente carrito;
    // este mae tiene que tener un acarrito de servicios
    
    //constructor
    public cliente(String nombrecompleto, int cedula, boolean preferencial) {
        this.nombrecompleto = nombrecompleto;
        this.cedula = cedula;
        this.preferencial = preferencial;
        this.carrito = new carritocliente();
    }
    //getter and setter
    public String getNombrecompleto() {
        return nombrecompleto;
    }
    public int getCedula() {
        return cedula;
    }
    public boolean isPreferencial() {
        return preferencial;
    }
    public carritocliente getCarrito() {
        return carrito;
    }

    //setter
    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }   
    public void setCedula(int cedula) {
        this.cedula = cedula;
    }
    public void setPreferencial(boolean preferencial) {
        this.preferencial = preferencial;
    }
    public void setCarrito(carritocliente carrito) {
        this.carrito = carrito;
    }

    //toString
    @Override
    public String toString() {
        return "cliente [nombrecompleto=" + nombrecompleto + ", cedula=" + cedula + ", preferencial=" + preferencial
                + carrito + "]";
    }
     

}
