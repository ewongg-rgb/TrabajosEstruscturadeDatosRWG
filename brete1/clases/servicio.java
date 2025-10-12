package brete1.clases;

public  class servicio {
    protected int id;
    protected String nombre;
    protected  double precio;
    protected servicio siguiente;
    protected servicio hijoizq;
    protected servicio hijoder;
    
    
    //metodo 
    //constructor
    public servicio(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        
          
    }
    //getter and setter

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
    public servicio getSiguiente() {
        return siguiente;
    }
    public servicio getIzq() {
        return hijoizq;
    }
    public servicio getDer() {
        return hijoder;
    }
    //setter
    public void sethijoizq(servicio izq) {
        this.hijoizq = hijoizq;
    }
    public void setder(servicio der) {
        this.hijoder = hijoder;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;

    
    }

    public void setSiguiente(servicio siguiente) {
        this.siguiente = siguiente;
    }

    //tostring
    @Override
    public String toString() {
        return "servicio [id=" + id + ", nombre=" + nombre + ", precio=" + precio + "]";
    }
    
}
