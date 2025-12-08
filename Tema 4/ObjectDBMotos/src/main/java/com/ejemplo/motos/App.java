package com.ejemplo.motos;

import com.ejemplo.motos.controller.MotoController;
import com.ejemplo.motos.model.Moto;

import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        MotoController dao = new MotoController();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("----- GESTIÓN DE MOTOS -----");
            System.out.println("1. Guardar moto");
            System.out.println("2. Leer todas las motos");
            System.out.println("3. Mostrar moto por ID");
            System.out.println("4. Eliminar moto");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    sc.nextLine();
                    System.out.print("Marca: ");
                    String marca = sc.nextLine();
                    System.out.print("Modelo: ");
                    String modelo = sc.nextLine();
                    System.out.print("Cilindrada: ");
                    int cilindrada = sc.nextInt();

                    dao.guardarMoto(new Moto(0, marca, modelo, cilindrada));
                    System.out.println("Moto guardada.\n");
                    break;

                case 2:
                    List<Moto> motos = dao.leerMotos();
                    System.out.println("--- LISTADO DE MOTOS ---");
                    for (Moto m : motos) {
                        System.out.println(m);
                    }
                    System.out.println();
                    break;

                case 3:
                    System.out.print("ID de la moto: ");
                    long idBuscar = sc.nextLong();
                    Moto moto = dao.obtenerMotoPorId(idBuscar);
                    if (moto != null)
                        System.out.println("Moto encontrada: " + moto + "\n");
                    else
                        System.out.println("No existe una moto con ese ID.\n");
                    break;

                case 4:
                    System.out.print("ID de la moto: ");
                    long idEliminar = sc.nextLong();
                    dao.eliminarMoto(idEliminar);
                    System.out.println("Moto eliminada si existía.\n");
                    break;
            }

        } while (opcion != 0);

        System.out.println("Programa finalizado.");
    }
}
