package com.supermercado;

import com.supermercado.modelo.ProductoCSV;
import com.supermercado.servicio.ServicioFicheroProductosCSV;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class PrincipalCSV {
    private static final String NOMBRE_FICHERO = "productos.csv";
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ServicioFicheroProductosCSV servicio = new ServicioFicheroProductosCSV(NOMBRE_FICHERO);

        try {
            servicio.crearFicheroSiNoExiste();
        } catch (IOException e) {
            System.err.println("Error al crear el fichero: " + e.getMessage());
            return;
        }

        boolean salir = false;
        while (!salir) {
            System.out.println("\n=== GESTIÓN DE PRODUCTOS CSV ===");
            System.out.println("1. Mostrar productos");
            System.out.println("2. Añadir producto");
            System.out.println("3. Salir");
            System.out.print("Opción: ");
            String opcion = sc.nextLine().trim();

            switch (opcion) {
                case "1":
                    mostrarProductos(servicio);
                    break;
                case "2":
                    anadirProducto(servicio);
                    break;
                case "3":
                    salir = true;
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void mostrarProductos(ServicioFicheroProductosCSV servicio) {
        try {
            List<ProductoCSV> productos = servicio.leerTodos();
            if (productos.isEmpty()) {
                System.out.println("No hay productos registrados.");
                return;
            }
            System.out.printf("%-3s %-20s %-10s %-10s%n", "N", "Nombre", "Precio", "Cantidad");
            System.out.println("-------------------------------------------");
            int i = 1;
            for (ProductoCSV p : productos) {
                System.out.printf("%-3d %-20s %-10d %-10d%n", i++, p.getNombre(), p.getPrecio(), p.getCantidad());
            }
        } catch (IOException e) {
            System.err.println("Error leyendo fichero: " + e.getMessage());
        }
    }

    private static void anadirProducto(ServicioFicheroProductosCSV servicio) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        int precio = leerEntero("Precio (€ entero): ");
        int cantidad = leerEntero("Cantidad: ");

        ProductoCSV p = new ProductoCSV(nombre, precio, cantidad);
        try {
            servicio.guardarProducto(p);
            System.out.println("Producto guardado correctamente.");
        } catch (IOException e) {
            System.err.println("Error guardando producto: " + e.getMessage());
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = sc.nextLine().trim();
            try {
                int valor = Integer.parseInt(entrada);
                if (valor < 0) {
                    System.out.println("Debe ser >= 0");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número entero válido.");
            }
        }
    }
}
