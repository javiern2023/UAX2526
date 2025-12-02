package com.ejemplo.controller;

import com.ejemplo.database.MongoDBConnection;
import com.ejemplo.model.Alumno;
import com.ejemplo.services.AlumnoService;
import java.util.List;
import java.util.Scanner;

public class AlumnoController {
    private static final AlumnoService service = new AlumnoService();

    public void principal() {
        Scanner sc = new Scanner(System.in);

        for(boolean salir = false; !salir; System.out.println()) {
            mostrarMenu();
            System.out.print("Elige una opción: ");
            switch (sc.nextLine().trim()) {
                case "1":
                    altaAlumno(sc);
                    break;
                case "2":
                    bajaAlumno(sc);
                    break;
                case "3":
                    insertarNota(sc);
                    break;
                case "4":
                    modificarNota(sc);
                    break;
                case "5":
                    consultarNota(sc);
                    break;
                case "6":
                    consultarTodasNotas();
                    break;
                case "0":
                    System.out.println("Saliendo...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        MongoDBConnection.close();
        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("=== GESTIÓN DE ALUMNOS ===");
        System.out.println("1. Dar de alta un alumn@");
        System.out.println("2. Dar de baja un alumn@ (por expediente)");
        System.out.println("3. Insertar una nota a un alumn@ (por expediente)");
        System.out.println("4. Modificar la nota de un alumn@");
        System.out.println("5. Consultar la nota de un alumn@ (por expediente)");
        System.out.println("6. Consultar todas las notas");
        System.out.println("0. Salir");
    }

    private static void altaAlumno(Scanner sc) {
        System.out.print("Expediente: ");
        String exp = sc.nextLine().trim();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Apellidos: ");
        String apellidos = sc.nextLine().trim();
        Alumno a = new Alumno(exp, nombre, apellidos, (Double)null);
        boolean ok = service.addAlumno(a);
        if (ok) {
            System.out.println("Alumno dado de alta correctamente.");
        } else {
            System.out.println("Ya existe un alumno con ese expediente.");
        }

    }

    private static void bajaAlumno(Scanner sc) {
        System.out.print("Expediente a eliminar: ");
        String exp = sc.nextLine().trim();
        boolean ok = service.deleteAlumno(exp);
        if (ok) {
            System.out.println("Alumno eliminado.");
        } else {
            System.out.println("No existe alumno con ese expediente.");
        }

    }

    private static void insertarNota(Scanner sc) {
        System.out.print("Expediente: ");
        String exp = sc.nextLine().trim();
        System.out.print("Nota (ej. 8.5): ");
        String notaStr = sc.nextLine().trim();
        Double nota = null;

        try {
            nota = Double.parseDouble(notaStr);
        } catch (NumberFormatException var5) {
            System.out.println("Nota no válida.");
            return;
        }

        boolean ok = service.insertarNota(exp, nota);
        if (ok) {
            System.out.println("Nota insertada/actualizada.");
        } else {
            System.out.println("No existe alumno con ese expediente.");
        }

    }

    private static void modificarNota(Scanner sc) {
        System.out.print("Expediente: ");
        String exp = sc.nextLine().trim();
        System.out.print("Nueva nota: ");
        String notaStr = sc.nextLine().trim();

        Double nota;
        try {
            nota = Double.parseDouble(notaStr);
        } catch (NumberFormatException var5) {
            System.out.println("Nota no válida.");
            return;
        }

        boolean ok = service.modificarNota(exp, nota);
        if (ok) {
            System.out.println("Nota modificada.");
        } else {
            System.out.println("No existe alumno con ese expediente.");
        }

    }

    private static void consultarNota(Scanner sc) {
        System.out.print("Expediente: ");
        String exp = sc.nextLine().trim();
        Double nota = service.consultarNota(exp);
        if (nota == null) {
            System.out.println("Alumno no encontrado o no tiene nota asignada.");
        } else {
            System.out.println("Nota del expediente " + exp + ": " + nota);
        }

    }

    private static void consultarTodasNotas() {
        List<Alumno> lista = service.consultarTodasNotas();
        if (lista.isEmpty()) {
            System.out.println("No hay alumnos en la base de datos.");
        } else {
            System.out.println("Listado de alumnos y notas:");

            for(Alumno a : lista) {
                String notaText = a.getNota() == null ? "SIN NOTA" : String.valueOf(a.getNota());
                System.out.println(a.getExpediente() + " - " + a.getNombre() + " " + a.getApellidos() + " -> " + notaText);
            }

        }
    }
}
