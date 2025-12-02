package com.ejemplo.alumnos;

import com.ejemplo.alumnos.io.JsonService;
import com.ejemplo.alumnos.model.AlumnoList;
import com.ejemplo.alumnos.services.AlumnoService;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        AlumnoList lista = new AlumnoList();
        AlumnoService service = new AlumnoService(lista);
        JsonService jsonService = new JsonService("src/main/resources/alumnos.json");

        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("===== MENÚ ALUMNOS =====");
            System.out.println("1. Dar de alta alumno");
            System.out.println("2. Dar de baja alumno");
            System.out.println("3. Insertar nota");
            System.out.println("4. Modificar nota");
            System.out.println("5. Consultar nota");
            System.out.println("6. Consultar todas las notas");
            System.out.println("7. Guardar en JSON");
            System.out.println("8. Leer desde JSON");
            System.out.println("0. Salir");
            System.out.print("Elige opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    service.darAlta();
                    break;
                case 2:
                    service.darBaja();
                    break;
                case 3:
                    service.insertarNota();
                    break;
                case 4:
                    service.modificarNota();
                    break;
                case 5:
                    service.consultarNota();
                    break;
                case 6:
                    service.consultarTodasNotas();
                    break;
                case 7:
                    jsonService.guardar(lista);
                    break;
                case 8:
                    jsonService.leer(lista);
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida, prueba de nuevo.");
            }

        } while (opcion != 0);

        sc.close();
    }
}
