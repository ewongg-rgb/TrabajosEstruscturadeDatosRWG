package brete1.metodos;
import java.util.ArrayList;

import brete1.clases.cliente;

public class colaclientes {
    private final ArrayList<cliente> colaClientes;

    public colaclientes() {
        colaClientes = new ArrayList<>();
    }

    public void insertar(cliente nuevoCliente) {
        if (existeCedula(nuevoCliente.getCedula())) {
            System.out.println("Error: Ya existe un cliente con la cédula " + nuevoCliente.getCedula() + ". No se puede insertar el cliente.\n");
            return;
        }
        
        colaClientes.add(nuevoCliente);
        System.out.println("Cliente agregado correctamente.\n");
    }

    public cliente remover() {
        if (colaClientes.isEmpty()) {
            System.out.println("No se puede remover, la cola está vacía.\n");
            return null;
        }
        return colaClientes.remove(0);
    }
    public cliente verFrente() {
        if (colaClientes.isEmpty()) {
            System.out.println("La cola está vacía.\n");
            return null;
        }
        return colaClientes.get(0);
    }

    public void mostrarCola() {
        if (colaClientes.isEmpty()) {
            System.out.println("La cola está vacía.\n");
            return;
        }
        System.out.println("=== CLIENTES EN COLA ===");

        for (int i = 0; i < colaClientes.size(); i++) {
         cliente c = colaClientes.get(i);
        System.out.println(c);
        }
        System.out.println();
    }

    // Mueve un cliente de una posición a otra (0 = frente)
    public void moverClientePorPosicion(int indiceOrigen, int indiceDestino ) {
        indiceDestino = 0;
    if (colaClientes.isEmpty()) {
        System.out.println("La cola está vacía.\n");
        return;
    }

    if (indiceOrigen < 0 || indiceOrigen >= colaClientes.size() ||
        indiceDestino < 0 || indiceDestino >= colaClientes.size()) {
        System.out.println("Índices inválidos. No se puede mover el cliente.\n");
        return;
    }

    // Extraer cliente temporalmente
    cliente clienteTemp = colaClientes.get(indiceOrigen);
    colaClientes.remove(indiceOrigen);
    colaClientes.add(indiceDestino, clienteTemp);

    System.out.println("Cliente " + clienteTemp.getNombrecompleto()
    +" movido de la posición " + indiceOrigen +" a la posición "
     + indiceDestino + "");

     
}
public boolean existeCedula(int cedulaIngresada) {
    for (int i = 0; i < colaClientes.size(); i++) {
        int cedulaExistente = colaClientes.get(i).getCedula(); // extrae la cédula del cliente actual

        if (cedulaExistente == cedulaIngresada) {
            return true; // se encontró un cliente con esa cédula
        }
    }
    return false; // no existe ningún cliente con esa cédula
}

 public cliente atenderConPrioridad() {
    if (colaClientes.isEmpty()) {
        System.out.println("No hay clientes en cola.");
        return null;
    }

    // Busca el primero preferencial
    for (int i = 0; i < colaClientes.size(); i++) {
        cliente actual = colaClientes.get(i);
        if (actual.isPreferencial()) {
            System.out.println("\nAtendiendo cliente preferencial: " + actual.getNombrecompleto());
            System.out.println("Carrito del cliente:");
            actual.getCarrito().mostrarCarrito();  //  Mostrar carrito del cliente
            return colaClientes.remove(i);         // Luego lo remueve
        }
    }

    // Si no hay preferenciales, atiende al primero en la cola
    cliente normal = colaClientes.remove(0);
    System.out.println("\nAtendiendo cliente: " + normal.getNombrecompleto());
    System.out.println("Carrito del cliente:");
    normal.getCarrito().mostrarCarrito();          //Mostrar carrito del cliente
    return normal;
}

    
}
