package com.basedatos.controller;

import com.basedatos.database.GestionBD;
import com.basedatos.model.Alumno;

import java.util.Scanner;

public class AppController {

    private GestionBD gestionBD = new GestionBD();
    private Scanner sc = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("========== MENÚ ALUMNOS ==========");
            System.out.println("1. Dar de alta alumno");
            System.out.println("2. Dar de baja alumno");
            System.out.println("3. Insertar nota");
            System.out.println("4. Modificar nota");
            System.out.println("5. Consultar nota de un alumno");
            System.out.println("6. Consultar todas las notas");
            System.out.println("0. Salir");
            System.out.println("==================================");
            System.out.print("Seleccione una opción: ");


            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpia el buffer

            switch (opcion) {
                case 1:
                    altaAlumno();
                    break;
                case 2:
                    bajaAlumno();
                    break;
                case 3:
                    insertarNota();
                    break;
                case 4:
                    modificarNota();
                    break;
                case 5:
                    consultarNota();
                    break;
                case 6:
                    consultarTodasNotas();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }

        } while (opcion != 0);
    }

    private void altaAlumno() {
        System.out.print("Expediente: ");
        String expediente = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = sc.nextLine();

        Alumno a = new Alumno(expediente, nombre, apellidos);
        gestionBD.altaAlumno(a);
    }

    private void bajaAlumno() {
        System.out.print("Expediente del alumno a eliminar: ");
        gestionBD.bajaAlumno(sc.nextLine());
    }

    private void insertarNota() {
        System.out.print("Expediente: ");
        String expediente = sc.nextLine();
        System.out.print("Nota: ");
        double nota = sc.nextDouble();
        sc.nextLine();
        gestionBD.insertarNota(expediente, nota);
    }

    private void modificarNota() {
        System.out.print("Expediente: ");
        String expediente = sc.nextLine();
        System.out.print("Nueva nota: ");
        double nuevaNota = sc.nextDouble();
        sc.nextLine();
        gestionBD.modificarNota(expediente, nuevaNota);
    }

    private void consultarNota() {
        System.out.print("Expediente: ");
        gestionBD.consultarNota(sc.nextLine());
    }

    private void consultarTodasNotas() {
        gestionBD.consultarTodasNotas();
    }
}
