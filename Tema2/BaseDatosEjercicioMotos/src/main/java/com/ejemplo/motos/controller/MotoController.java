package com.ejemplo.motos.controller;

import com.ejemplo.motos.Model.Moto;
import com.ejemplo.motos.database.GestionBD;

import java.util.List;
import java.util.Scanner;

/**
 * Clase MotoController - Controlador de la interfaz de usuario
 * Gestiona la interacción con el usuario y coordina las operaciones con la base
 * de datos
 */
public class MotoController {

    /** Scanner para leer la entrada del usuario desde la consola */
    private final Scanner sc = new Scanner(System.in);

    /** Instancia de GestionBD para realizar operaciones en la base de datos */
    private final GestionBD gestionBD = new GestionBD();

    /**
     * Inicia el menú principal de la aplicación
     * Muestra las opciones disponibles y ejecuta la acción seleccionada
     * El menú se repite hasta que el usuario seleccione la opción de salir (0)
     */
    public void iniciarMenu() {
        int opcion;

        // Bucle do-while: se ejecuta al menos una vez y continúa mientras opcion != 0
        do {
            // Mostrar el menú de opciones
            System.out.println("== MENU DE OPCIONES ==");
            System.out.println("1.- Guardar moto");
            System.out.println("2.- Mostrar todas las moto");
            System.out.println("3.- Eliminar moto");
            System.out.println("4.- Mostrar motos por marca");
            System.out.println("0.- Salir");

            // Leer la opción del usuario
            opcion = sc.nextInt();
            sc.nextLine(); // Consumir el salto de línea que queda en el buffer

            // Switch para ejecutar la acción según la opción seleccionada
            switch (opcion) {
                case 1:
                    guardarMoto(); // Insertar nueva moto
                    break;
                case 2:
                    mostrarMotos(); // Mostrar todas las motos
                    break;
                case 3:
                    eliminarMoto(); // Eliminar una moto
                    break;
                case 4:
                    mostrarMotosPorMarca(); // Buscar motos por marca
                    break;
                case 0:
                    System.out.println("Gracias por venir, hasta pronto");
                    break;
                default:
                    System.out.println("Opcion incorrecta");
            }
        } while (opcion != 0); // Continuar mientras no se seleccione salir
    }

    /**
     * Método privado para guardar una nueva moto
     * Solicita al usuario los datos de la moto y la inserta en la base de datos
     */
    private void guardarMoto() {
        // Paso 1: Solicitar datos al usuario
        System.out.print("Dime la marca: ");
        String marca = sc.nextLine();

        System.out.print("Dime el modelo: ");
        String modelo = sc.nextLine();

        System.out.print("Dime la cilindrada: ");
        String cilindrada = sc.nextLine();

        // Paso 2: Crear objeto Moto con los datos (ID=0 porque es AUTO_INCREMENT)
        Moto moto = new Moto(0, marca, modelo, cilindrada);

        // Paso 3: Insertar la moto en la base de datos
        gestionBD.insertarMoto(moto);
    }

    /**
     * Método privado para mostrar todas las motos
     * Recupera todas las motos de la BD y las muestra en consola
     */
    private void mostrarMotos() {
        // Paso 1: Obtener todas las motos de la base de datos
        List<Moto> motos = gestionBD.leerMotos();

        // Paso 2: Verificar si hay motos
        if (motos.isEmpty()) {
            System.out.println("No tienes motos registradas");
        } else {
            // Paso 3: Mostrar cada moto con sus datos
            System.out.println("== LISTA DE MOTOS ==");
            for (Moto m : motos) {
                System.out.println("ID: " + m.getId());
                System.out.println("MARCA: " + m.getMarca());
                System.out.println("MODELO: " + m.getModelo());
                System.out.println("CILINDRADA: " + m.getCilindrada());
            }
        }
    }

    /**
     * Método privado para eliminar una moto
     * Muestra las motos disponibles y solicita el ID de la moto a eliminar
     */
    private void eliminarMoto() {
        // Paso 1: Mostrar todas las motos para que el usuario vea los IDs
        mostrarMotos();

        // Paso 2: Solicitar el ID de la moto a eliminar
        System.out.print("Dime el id de la moto a eliminar: ");
        int id = sc.nextInt();

        // Paso 3: Eliminar la moto de la base de datos
        gestionBD.eliminarMoto(id);
    }

    /**
     * Método privado para mostrar motos filtradas por marca
     * Solicita una marca y muestra solo las motos de esa marca
     */
    private void mostrarMotosPorMarca() {
        // Paso 1: Solicitar la marca a buscar
        System.out.print("Dime la marca de la moto: ");
        String marca = sc.nextLine();

        // Paso 2: Buscar motos de esa marca en la base de datos
        List<Moto> motos = gestionBD.leerMotosPorMarca(marca);

        // Paso 3: Verificar si se encontraron motos
        if (motos.isEmpty()) {
            System.out.println("No tienes motos registradas con esa marca");
        } else {
            // Paso 4: Mostrar las motos encontradas
            System.out.println("== LISTA DE MOTOS ==");
            for (Moto m : motos) {
                System.out.println("ID: " + m.getId());
                System.out.println("MARCA: " + m.getMarca());
                System.out.println("MODELO: " + m.getModelo());
                System.out.println("CILINDRADA: " + m.getCilindrada());
            }
        }
    }
}
