package com.ejemplo.motos;

import com.ejemplo.motos.io.JsonService;
import com.ejemplo.motos.model.motoList;
import com.ejemplo.motos.services.motoService;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        String filePath = "src/main/resources/motos.json";
        JsonService jsonService = new JsonService(filePath);
        motoList lista = jsonService.leer(); // leer motos existentes
        motoService service = new motoService(lista, jsonService); // inyectamos JsonService
        Scanner sc = new Scanner(System.in);

        int opcion = -1;
        do {
            System.out.println("\n=== GESTIÓN DE MOTOS ===");
            System.out.println("1. Añadir moto");
            System.out.println("2. Eliminar moto");
            System.out.println("3. Mostrar motos");
            System.out.println("0. Salir");
            System.out.print("Elige opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1: service.añadirMoto(); break;
                case 2: service.eliminarMoto(); break;
                case 3: service.mostrarMotos(); break;
                case 0: System.out.println("Saliendo..."); break;
                default: System.out.println("Opción inválida."); break;
            }

        } while (opcion != 0);
    }
}

