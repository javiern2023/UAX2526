package com.uax.concesionario.controller;

import com.uax.concesionario.database.GestionBD;
import com.uax.concesionario.model.Coche;
import com.uax.concesionario.model.Cliente;
import com.uax.concesionario.model.VentaCoche;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import java.util.Scanner;

// Controlador de la interfaz, interacción con el usuario
public class ConcesionarioController {

    private final Scanner sc = new Scanner(System.in);

    private final GestionBD gestionBD = new GestionBD();

    // Método principal inicia el menú, muestra las opciones disponibles y la ejecuta.
    // Se repite hasta que seleccione la opción de salir
    public void iniciarMenu() {

        int opcion;

        do {
            mostrarMenu();
            opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    registrarCocheNuevo();
                    break;
                case 2:
                    venderCoche();
                    break;
                case 3:
                    verCochesDisponibles();
                    break;
                case 4:
                    verCochesVendidos();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción del 1 al 5.");
            }

            if (opcion != 5) {
                esperarEnter();
            }

        } while (opcion != 5);

        sc.close();
    }

    private void mostrarMenu() {
        System.out.println("\n" + obtenerEstadisticas());
        System.out.println("""

                            MENÚ PRINCIPAL CONCESIONARIO UAX S.L.

                 1. Dar de alta un coche nuevo
                 2. Vender un coche a un cliente
                 3. Ver coches disponibles para venta
                 4. Ver coches vendidos
                 5. Salir

                """);
    }

    private String obtenerEstadisticas() {
        int disponibles = gestionBD.leerCochesDisponibles().size();
        int vendidos = gestionBD.obtenerTotalVentas();

        return String.format("""

                 ESTADÍSTICAS DEL CONCESIONARIO

                 Coches disponibles: %d
                 Coches vendidos: %d
                 Total coches: %d

                """, disponibles, vendidos, disponibles + vendidos);
    }

 
    private int leerOpcion() {
        System.out.print("Seleccione una opción: ");
        try {
            int opcion = sc.nextInt();
            sc.nextLine();
            return opcion;
        } catch (Exception e) {
            sc.nextLine();
            return -1;
        }
    }

    private void registrarCocheNuevo() {
        System.out.println("\n   REGISTRAR COCHE NUEVO\n");

        try {
            System.out.print("ID del coche: ");
            String id = sc.nextLine().trim();

            if (id.isEmpty()) {
                System.out.println("El ID no puede estar vacío.");
                return;
            }

            System.out.print("Marca: ");
            String marca = sc.nextLine().trim();

            System.out.print("Modelo: ");
            String modelo = sc.nextLine().trim();

            System.out.print("Año de fabricación: ");
            int anio = sc.nextInt();
            sc.nextLine();

            System.out.print("Precio (€): ");
            double precio = sc.nextDouble();
            sc.nextLine();

            System.out.print("Color: ");
            String color = sc.nextLine().trim();

            System.out.print("Matrícula (opcional, Enter para omitir): ");
            String matricula = sc.nextLine().trim();

            Coche coche = new Coche(id, marca, modelo, anio, precio, color, matricula);
            gestionBD.agregarCoche(coche);

        } catch (Exception e) {
            System.out.println("Error al registrar el coche: " + e.getMessage());
            sc.nextLine();
        }
    }

    private void venderCoche() {
        System.out.println("\n   VENDER COCHE A CLIENTE\n");

        List<Coche> disponibles = gestionBD.leerCochesDisponibles();

        if (disponibles.isEmpty()) {
            System.out.println("No hay coches disponibles para vender.");
            return;
        }

        System.out.println("Coches disponibles:");
        for (Coche coche : disponibles) {
            System.out.println("  " + coche);
        }

        try {
            System.out.print("\nID del coche a vender: ");
            String cocheId = sc.nextLine().trim();

            Coche coche = gestionBD.buscarCochePorId(cocheId);
            if (coche == null) {
                System.out.println("No se encontró el coche con ID: " + cocheId);
                return;
            }

            System.out.println("\nCoche seleccionado: " + coche);
            System.out.println("\n   DATOS DEL CLIENTE\n");

            System.out.print("Nombre completo: ");
            String nombre = sc.nextLine().trim();

            System.out.print("DNI/Identificación: ");
            String identificacion = sc.nextLine().trim();

            System.out.print("Teléfono: ");
            String telefono = sc.nextLine().trim();

            System.out.print("Correo electrónico: ");
            String correo = sc.nextLine().trim();

            Cliente cliente = new Cliente(nombre, identificacion, telefono, correo);

            // Crear venta con fecha
            String fechaVenta = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            VentaCoche venta = new VentaCoche(coche, cliente, fechaVenta);

            // Insertar venta en BD
            boolean ventaInsertada = gestionBD.insertarVenta(venta);

            if (ventaInsertada) {
                // Eliminar coche del inventario
                boolean cocheEliminado = gestionBD.eliminarCocheDisponible(cocheId);

                if (cocheEliminado) {
                    System.out.println("\nVenta procesada exitosamente.");
                    System.out.println("  - Coche vendido: " + coche.getMarca() + " " + coche.getModelo());
                    System.out.println("  - Cliente: " + cliente.getNombre());
                    System.out.println("  - Fecha: " + fechaVenta);
                } else {
                    System.out.println("La venta se registró pero no se pudo eliminar del inventario.");
                }
            } else {
                System.out.println("Error al procesar la venta.");
            }

        } catch (Exception e) {
            System.out.println("Error al procesar la venta: " + e.getMessage());
        }
    }

    private void verCochesDisponibles() {
        System.out.println("\n   COCHES DISPONIBLES PARA VENTA\n");

        List<Coche> coches = gestionBD.leerCochesDisponibles();

        if (coches.isEmpty()) {
            System.out.println("No hay coches disponibles.");
            return;
        }

        System.out.println("Total de coches disponibles: " + coches.size() + "\n");

        for (int i = 0; i < coches.size(); i++) {
            System.out.println((i + 1) + ". " + coches.get(i));
        }
    }

    private void verCochesVendidos() {
        System.out.println("\n   COCHES VENDIDOS\n");

        List<VentaCoche> ventas = gestionBD.leerTodasLasVentas();

        if (ventas.isEmpty()) {
            System.out.println("No se han realizado ventas aún.");
            return;
        }

        System.out.println("Total de ventas: " + ventas.size() + "\n");

        for (VentaCoche venta : ventas) {
            System.out.println(venta);
        }
    }

    private void esperarEnter() {
        System.out.print("\nPresione Enter para continuar...");
        sc.nextLine();
    }
}
