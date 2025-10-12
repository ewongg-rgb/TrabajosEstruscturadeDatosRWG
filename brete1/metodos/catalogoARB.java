package brete1.metodos;
//arbol de productos

import brete1.clases.medicamento;
import brete1.clases.servicio;
import brete1.clases.tratamiento;




public class catalogoARB {
    private servicio raiz;

    // Métodos.
    // Constructor.
    public catalogoARB() {
        raiz = null;
    }

    // Getters.
    public servicio getRaiz() {
        return raiz;
    }

    // Setters.
    public void setRaiz(servicio nuevaRaiz) {
        raiz = nuevaRaiz;
    }
    
    private int generarIdUnico() {
    java.util.Random random = new java.util.Random();
    int id;
    do {
        id = random.nextInt(100) + 1; // 1 a 100
    } while (buscar(id) != null); // si ya existe, repite
    return id;
        }

    public boolean estaVacio() {
        return raiz == null;
    }


        // buscar nodo
    public servicio buscar(int idxbuscar) {
    if (estaVacio()) {
        System.out.println("El árbol está vacío.\n");
        return null;
    }

    servicio nodoTemp = raiz;

    while (nodoTemp.getId() != idxbuscar) {
        if (idxbuscar < nodoTemp.getId()) {
            nodoTemp = nodoTemp.getIzq();
        } else {
            nodoTemp = nodoTemp.getDer();
        }

        if (nodoTemp == null) {
            System.out.println("El nodo buscado no se encuentra");
            return null;
        }
    }

    return nodoTemp;
}

public void insertarServiMedicamento(String nombre, double precio, int cantidad, String indicacion) {
    int id = generarIdUnico();
    servicio nuevoNodo = new medicamento(id, nombre, precio, cantidad, indicacion);

    // 1) árbol vacío -> el nuevo pasa a ser la raíz
    if (estaVacio()) {
        raiz = nuevoNodo;
        return;
    }

    // 2) recorrer hasta encontrar el hueco
    servicio nodoActual = raiz;
    servicio padreActual;
    while (true) {
        padreActual = nodoActual;
        if (id < nodoActual.getId()) {
            nodoActual = nodoActual.getIzq();
            if (nodoActual == null) { 
                padreActual.sethijoizq(nuevoNodo);  // ← asegúrate que este setter esté bien
                return; 
            }
        } else if (id > nodoActual.getId()) {
            nodoActual = nodoActual.getDer();
            if (nodoActual == null) { 
                padreActual.setder(nuevoNodo);      // ← idem
                return; 
            }
        } else {
            System.out.println("ID duplicado: " + id + ". No se inserta.\n");
            return;
        }
    }
}

public void insertarServitratamiento(String nombre, double precio, int cantidad, String indicacion) {
    int id = generarIdUnico();
    servicio nuevoNodo = new tratamiento(id, nombre, precio, cantidad, indicacion);

    if (estaVacio()) {
        raiz = nuevoNodo;
        return;
    }

    servicio nodoActual = raiz;
    servicio padreActual;
    while (true) {
        padreActual = nodoActual;
        if (id < nodoActual.getId()) {
            nodoActual = nodoActual.getIzq();
            if (nodoActual == null) { 
                padreActual.sethijoizq(nuevoNodo);
                return; 
            }
        } else if (id > nodoActual.getId()) {
            nodoActual = nodoActual.getDer();
            if (nodoActual == null) { 
                padreActual.setder(nuevoNodo);
                return; 
            }
        } else {
            System.out.println("ID duplicado: " + id + ". No se inserta.\n");
            return;
        }
    }
}
// recorrido en orden
public void enOrdenTipo(servicio nodo, String tipo) {
    if (nodo == null) return;

    enOrdenTipo(nodo.getIzq(), tipo);

    String t = (tipo == null) ? "" : tipo.trim().toLowerCase();

    // acepta singular y plural
    boolean esTrat = t.equals("tratamiento") || t.equals("tratamiento");
    boolean esMed  = t.equals("medicamento") || t.equals("medicamentos");

    if (esTrat && nodo instanceof brete1.clases.tratamiento) {
        System.out.println(nodo);
    } else if (esMed && nodo instanceof brete1.clases.medicamento) {
        System.out.println(nodo);
    }

    enOrdenTipo(nodo.getDer(), tipo);
}


    


    

}


