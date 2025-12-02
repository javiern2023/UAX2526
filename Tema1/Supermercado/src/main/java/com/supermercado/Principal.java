
package com.supermercado;

import com.supermercado.modelo.Producto;
import com.supermercado.servicio.ServicioFicheroProductos;

import java.io.IOException;
import java.util.Scanner;

public class Principal {
    private static final String NOMBRE_FICHERO = "productos.txt";
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ServicioFicheroProductos servicio = new ServicioFicheroProductos(NOMBRE_FICHERO);

        try {
            servicio.crearFicheroSiNoExiste();
        } catch (IOException e) {
            System.err.println("Error al crear el fichero: " + e.getMessage());
            return;
        }

        boolean salir = false;
        while (!salir) {
            System.out.println("\n=== GESTIÓN DE PRODUCTOS ===");
            System.out.println("1. Mostrar productos");
            System.out.println("2. Añadir producto");
            System.out.println("3. Salir");
            System.out.print("Opción: ");
            String opcion = sc.nextLine().trim();

            switch (opcion) {
                case "1":
                    try {
                        servicio.mostrarContenido();
                    } catch (IOException e) {
                        System.err.println("Error leyendo fichero: " + e.getMessage());
                    }
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

    private static void anadirProducto(ServicioFicheroProductos servicio) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();

        int precio = leerEntero("Precio (€ entero): ");
        int cantidad = leerEntero("Cantidad: ");

        Producto p = Producto.builder()
                .nombre(nombre)
                .precio(precio)
                .cantidad(cantidad)
                .build();

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
                    System.out.println("El número debe ser >= 0");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número entero válido.");
            }
        }
    }
}
