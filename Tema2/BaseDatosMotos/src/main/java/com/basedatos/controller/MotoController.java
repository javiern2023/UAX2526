package com.basedatos.controller;

import com.basedatos.database.GestionBD;
import com.basedatos.model.Moto;

import java.util.List;
import java.util.Scanner;

public class MotoController {

    private final GestionBD gestionBD = new GestionBD();
    private final Scanner sc = new Scanner(System.in);

    public void iniciarMenu() {
        int opcion;
        do {
            System.out.println("===== GESTIÓN DE MOTOS =====");
            System.out.println("1. Guardar moto");
            System.out.println("2. Mostrar todas las motos");
            System.out.println("3. Eliminar moto");
            System.out.println("4. Mostrar motos por marca");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    guardarMoto();
                    break;
                case 2:
                    mostrarMotos();
                    break;
                case 3:
                    eliminarMoto();
                    break;
                case 4:
                    mostrarMotosPorMarca();
                    break;
                case 0:
                    System.out.println("Hasta pronto...");
                    break;
                default:
                    System.out.println("Opción incorrecta");
                    break;
            }


        } while (opcion != 0);
    }

    private void guardarMoto() {
        System.out.print("Marca: ");
        String marca = sc.nextLine();
        System.out.print("Modelo: ");
        String modelo = sc.nextLine();
        System.out.print("Cilindrada: ");
        String cilindrada = sc.nextLine();

        Moto moto = new Moto(0, marca, modelo, cilindrada);
        gestionBD.insertarMoto(moto);
    }

    private void mostrarMotos() {
        List<Moto> motos = gestionBD.leerMotos();
        if (motos.isEmpty()) {
            System.out.println("No hay motos registradas.");
        } else {
            System.out.println("--- LISTA DE MOTOS ---");
            for (Moto m : motos) {
                System.out.println("ID: " + m.getId() + " | Marca: " + m.getMarca()
                        + " | Modelo: " + m.getModelo()
                        + " | Cilindrada: " + m.getCilindrada());
            }
        }
    }

    private void eliminarMoto() {
        System.out.print("Introduce el ID de la moto a eliminar: ");
        int id = sc.nextInt();
        gestionBD.eliminarMoto(id);
    }

    private void mostrarMotosPorMarca() {
        System.out.print("Introduce la marca que quieres buscar: ");
        String marca = sc.nextLine();

        List<Moto> motos = gestionBD.leerMotosPorMarca(marca);
        if (motos.isEmpty()) {
            System.out.println("No se encontraron motos con la marca '" + marca + "'.");
        } else {
            System.out.println("--- MOTOS DE LA MARCA '" + marca + "' ---");
            for (Moto m : motos) {
                System.out.println("ID: " + m.getId() + " | Marca: " + m.getMarca()
                        + " | Modelo: " + m.getModelo()
                        + " | Cilindrada: " + m.getCilindrada());
            }
        }
    }
}
