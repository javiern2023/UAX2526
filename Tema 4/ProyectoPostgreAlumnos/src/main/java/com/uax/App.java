package com.uax;

import com.uax.dao.AlumnoDAO;
import com.uax.model.Alumno;

import java.util.List;
import java.util.Scanner;

public class App {
    private static final Scanner sc = new Scanner(System.in);
    private static final AlumnoDAO dao = new AlumnoDAO();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- MENÚ ALUMNOS ---");
            System.out.println("1. Insertar alumno");
            System.out.println("2. Eliminar alumno");
            System.out.println("3. Insertar/modificar nota");
            System.out.println("4. Listar alumnos");
            System.out.println("0. Salir");
            System.out.print("Elige opción: ");
            String op = sc.nextLine();

            try {
                switch (op) {
                    case "1" -> insertarAlumno();
                    case "2" -> eliminarAlumno();
                    case "3" -> modificarNota();
                    case "4" -> listarAlumnos();
                    case "0" -> { System.out.println("Saliendo..."); return; }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void insertarAlumno() throws Exception {
        System.out.print("Expediente: "); String exp = sc.nextLine();
        System.out.print("Nombre: "); String nom = sc.nextLine();
        System.out.print("Apellidos: "); String ape = sc.nextLine();
        System.out.print("Nota (dejar vacía si no hay): "); String n = sc.nextLine();
        Double nota = n.isBlank() ? null : Double.parseDouble(n);
        dao.insertar(new Alumno(exp, nom, ape, nota));
        System.out.println("Alumno insertado.");
    }

    private static void eliminarAlumno() throws Exception {
        System.out.print("Expediente a eliminar: "); String exp = sc.nextLine();
        dao.eliminar(exp);
        System.out.println("Alumno eliminado si existía.");
    }

    private static void modificarNota() throws Exception {
        System.out.print("Expediente: "); String exp = sc.nextLine();
        System.out.print("Nueva nota (dejar vacía para null): "); String n = sc.nextLine();
        Double nota = n.isBlank() ? null : Double.parseDouble(n);
        dao.actualizarNota(exp, nota);
        System.out.println("Nota actualizada.");
    }

    private static void listarAlumnos() throws Exception {
        List<Alumno> alumnos = dao.listar();
        System.out.println("\n--- Lista de alumnos ---");
        for (Alumno a : alumnos) {
            System.out.println(a);
        }
    }
}
