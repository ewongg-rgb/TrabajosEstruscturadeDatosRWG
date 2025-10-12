package brete1;

import java.util.InputMismatchException;
import java.util.Scanner;

import brete1.clases.cliente;
import brete1.metodos.catalogoARB;
import brete1.metodos.colaclientes;

public class main {

    private static final Scanner sc = new Scanner(System.in);
    private static final catalogoARB catalogo = new catalogoARB();
    private static final colaclientes cola = new colaclientes();

    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        int op;
        do {
            System.out.println("\n===== MENU FARMACIA =====");
            System.out.println("1. Agregar tratamiento");
            System.out.println("2. Agregar medicamento");
            System.out.println("3. Ver catálogo (InOrden)");
            System.out.println("4. Registrar cliente (y cargar carrito)");
            System.out.println("5. Ver cola de clientes");
            System.out.println("6. Ver carrito del cliente al frente");
            System.out.println("7. Atender cliente (prioridad a preferenciales)");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            op = leerEntero();

            switch (op) {
                case 1:
                    agregarTratamiento();
                    break;
                case 2:
                    agregarMedicamento();
                    break;
                case 3:
                    verCatalogo();
                    break;
                case 4:
                    registrarClienteYCargarCarrito();
                    break;
                case 5:
                    cola.mostrarCola();
                    break;
                case 6:
                    verCarritoDelFrente();
                    break;
                case 7:
                    atenderConPrioridad();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        } while (op != 0);
    }

    // -------- catálogo --------
    private static void agregarTratamiento() {
        System.out.println("\n-- Agregar Tratamiento --");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Precio: ");
        double precio = leerDouble();
        System.out.print("Cantidad (sesiones): ");
        int cantidad = leerEntero();
        System.out.print("Descripción/Indicaciones: ");
        String indic = sc.nextLine().trim();

        catalogo.insertarServitratamiento(nombre, precio, cantidad, indic);
        System.out.println("Tratamiento agregado correctamente.");
    }

    private static void agregarMedicamento() {
        System.out.println("\n-- Agregar Medicamento --");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Precio: ");
        double precio = leerDouble();
        System.out.print("Cantidad (stock): ");
        int cantidad = leerEntero();
        System.out.print("Indicaciones: ");
        String indic = sc.nextLine().trim();

        catalogo.insertarServiMedicamento(nombre, precio, cantidad, indic);
        System.out.println("Medicamento agregado correctamente.");
    }

    private static void verCatalogo() {
        if (catalogo.getRaiz() == null) {
            System.out.println("El catálogo está vacío.");
            return;
        }
        System.out.println("\n== TRATAMIENTOS ==");
        catalogo.enOrdenTipo(catalogo.getRaiz(), "tratamiento");
        System.out.println("\n== MEDICAMENTOS ==");
        catalogo.enOrdenTipo(catalogo.getRaiz(), "medicamento");
    }

    // -------- clientes / cola --------
    private static void registrarClienteYCargarCarrito() {
        System.out.println("\n-- Registrar Cliente --");
        System.out.print("Nombre completo: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Cédula (entero): ");
        int cedula = leerEntero();
        System.out.print("¿Preferencial? (S/N): ");
        boolean pref = sc.nextLine().trim().equalsIgnoreCase("S");

        cliente nuevo = new cliente(nombre, cedula, pref);

        // Agregar ítems al carrito (opcional)
        if (catalogo.getRaiz() == null) {
            System.out.println("Catálogo vacío. No se pueden agregar ítems al carrito ahora.");
        } else {
            boolean seguir = true;
            while (seguir) {
                System.out.println("\n¿Agregar ítems al carrito?");
                System.out.println("1) Ver/Agregar Medicamentos");
                System.out.println("2) Ver/Agregar Tratamientos");
                System.out.println("0) Terminar");
                System.out.print("Opción: ");
                int op = leerEntero();

                switch (op) {
                    case 1:
                        System.out.println("\n== MEDICAMENTOS DISPONIBLES ==");
                        catalogo.enOrdenTipo(catalogo.getRaiz(), "medicamento");
                        System.out.print("ID del medicamento: ");
                        int idM = leerEntero();
                        System.out.print("Unidades: ");
                        int uM = leerEntero();
                        nuevo.getCarrito().agregarDesdeCatalogo(catalogo, idM, uM);
                        break;
                    case 2:
                        System.out.println("\n== TRATAMIENTOS DISPONIBLES ==");
                        catalogo.enOrdenTipo(catalogo.getRaiz(), "tratamiento");
                        System.out.print("ID del tratamiento: ");
                        int idT = leerEntero();
                        System.out.print("Sesiones: ");
                        int uT = leerEntero();
                        nuevo.getCarrito().agregarDesdeCatalogo(catalogo, idT, uT);
                        break;
                    case 0:
                        seguir = false;
                        break;
                    default:
                        System.out.println("Opción inválida.");
                        break;
                }
            }
        }

        cola.insertar(nuevo);
    }

    private static void verCarritoDelFrente() {
        cliente frente = cola.verFrente();
        if (frente != null) {
            System.out.println("Cliente al frente: " + frente.getNombrecompleto());
            frente.getCarrito().mostrarCarrito();
        }
    }

    private static void atenderConPrioridad() {
        cliente atendido = cola.atenderConPrioridad();
        if (atendido != null) {
            System.out.println("Atendido: " + atendido.getNombrecompleto());
        }
    }

    // -------- utilidades de entrada --------
    private static int leerEntero() {
        while (true) {
            try {
                int n = sc.nextInt();
                sc.nextLine();
                return n;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.print("Ingrese un número entero válido: ");
            }
        }
    }

    private static double leerDouble() {
        while (true) {
            try {
                double n = sc.nextDouble();
                sc.nextLine();
                return n;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.print("Ingrese un número válido: ");
            }
        }
    }
}
