package brete1.metodos;

import brete1.clases.medicamento;
import brete1.clases.servicio;
import brete1.clases.tratamiento;

public class carritocliente {

    private servicio primero;

    public carritocliente() {
        primero = null;
    }

    public servicio getPrimero() { return primero; }
    public void setPrimero(servicio first) { primero = first; }

    // --- INSERTAR TRATAMIENTO 
    public void insertartratamiento(int id, String nombre, double precio, int cantidad, String indicacion) {
        if (buscarNodo(id) != null) {
            System.out.println("Error: Ya existe un servicio con el id " + id + ". No se puede insertar.");
            return;
        }
        servicio nuevo = new tratamiento(id, nombre, precio, cantidad, indicacion);
        nuevo.setSiguiente(primero);
        primero = nuevo;
    }

    // --- INSERTAR MEDICAMENTO ---
    public void insertarMedInicio(int id, String nombre, double precio, int cantidad, String indicacion) {
        if (buscarNodo(id) != null) {
            System.out.println("Error: Ya existe un servicio con el id " + id + ". No se puede insertar.");
            return;
        }
        servicio nuevo = new medicamento(id, nombre, precio, cantidad, indicacion);
        nuevo.setSiguiente(primero);
        primero = nuevo;
    }

    // --- BUSCAR POR ID ---
    public servicio buscarNodo(int id) {
        servicio actual = primero;
        while (actual != null) {
            if (actual.getId() == id) return actual;
            actual = actual.getSiguiente();
        }
        return null;
    }

    // --- MOSTRAR CARRITO 
    public void mostrarCarrito() {
        if (primero == null) {
            System.out.println("Carrito vacío.");
            return;
        }

        servicio actual = primero;
        double total = 0;

        System.out.println("\n=== CARRITO DEL CLIENTE ===");
        while (actual != null) {
            if (actual instanceof medicamento) {
                medicamento m = (medicamento) actual;
               
                System.out.println(" Medicamento | ID:" + m.getId() +
                                   " | " + m.getNombre() +
                                   " | Cant:" + m.getCantidad() +
                                   " | ₡" + m.getPrecio() +
                                   " | Indicación: " + m.getIndicacion());

            } else if (actual instanceof tratamiento) {
                tratamiento t = (tratamiento) actual;
             
                System.out.println(" Tratamiento | ID:" + t.getId() +
                                   " | " + t.getNombre() +
                                   " | Cant:" + t.getCantidad() +
                                   " | ₡" + t.getPrecio() +
                                   " | Descripción: " + t.getIndicacion() );

            }
            actual = actual.getSiguiente();
        }

        System.out.println("--------------------------------------");

    }


  
    public boolean agregarDesdeCatalogo(catalogoARB catalogo, int id, int unidades) {
        if (unidades <= 0) {
            System.out.println(" Cantidad inválida. Debe ser > 0.");
            return false;
        }

        servicio s = catalogo.buscar(id);
        if (s == null) {
            System.out.println(" El servicio con ID " + id + " no existe en el catálogo.");
            return false;
        }

        int disponible;
        boolean esMed = false;
        if (s instanceof medicamento) {
            disponible = ((medicamento) s).getCantidad();
            esMed = true;
        } else if (s instanceof tratamiento) {
            disponible = ((tratamiento) s).getCantidad();
        } else {
            System.out.println(" Tipo de servicio no soportado.");
            return false;
        }

        if (disponible < unidades) {
            System.out.println("Stock insuficiente. Disponible: " + disponible);
            return false;
        }

        // Descontar del catálogo
        if (esMed) ((medicamento) s).setCantidad(disponible - unidades);
        else       ((tratamiento) s).setCantidad(disponible - unidades);

        // Si ya estaba en el carrito, acumula
        servicio existente = buscarNodo(id);
        if (existente != null) {
            if (existente.getClass() == s.getClass()) {
                if (existente instanceof medicamento) {
                    medicamento m = (medicamento) existente;
                    m.setCantidad(m.getCantidad() + unidades);
                } else {
                    tratamiento t = (tratamiento) existente;
                    t.setCantidad(t.getCantidad() + unidades);
                }
                System.out.println(" Se sumaron " + unidades + " unidades al ítem existente del carrito.");
                return true;
            } else {
                // Revertir reserva si hay conflicto de tipos
                if (esMed) ((medicamento) s).setCantidad(disponible);
                else       ((tratamiento) s).setCantidad(disponible);
                System.out.println("Conflicto de tipos para el mismo ID en carrito.");
                return false;
            }
        }

        // Insertar copia en la lista
        if (esMed) {
            medicamento m = (medicamento) s;
            medicamento copia = new medicamento(m.getId(), m.getNombre(), m.getPrecio(), unidades, m.getIndicacion());
            copia.setSiguiente(primero);
            primero = copia;
        } else {
            tratamiento t = (tratamiento) s;
            tratamiento copia = new tratamiento(t.getId(), t.getNombre(), t.getPrecio(), unidades, t.getIndicacion());
            copia.setSiguiente(primero);
            primero = copia;
        }

        System.out.println(" Agregado al carrito. Unidades: " + unidades);
        return true;
    }
}
