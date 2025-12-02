package JDBC;

import java.util.Scanner;
import java.util.*;

public class App {

    private static final BaseDatos db = new BaseDatos();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean run = true;
        while (run) {
            Menu();
            switch (readInt("Opción: ")) {
                case 1:
                    altaAlumno();
                    break;
                case 2:
                    eliminarAlumno();
                    break;
                case 3:
                    modificarNota();
                    break;
                case 4:
                    listarAlumnos();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    run = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        }
    }

    private static void Menu() {
        System.out.println("\n=== Menú Alumnos ===");
        System.out.println("1. Alta alumno");
        System.out.println("2. Eliminar alumno");
        System.out.println("3. Modificar nota");
        System.out.println("4. Listar alumnos");
        System.out.println("0. Salir");
    }

    private static void altaAlumno() {
        System.out.println("== Alta de alumno ==");
        String nombre = readLine("Nombre: ");
        String apellidos = readLine("Apellidos: ");
        String direccion = readLine("Dirección: ");
        Double nota = readDoubleOrNull("Nota (opcional): ");

        Alumno a = new Alumno(null, nombre, apellidos, direccion, nota);
        int id = db.InsertarAlumno(a);
        if (id > 0) {
            System.out.println("Alumno añadido con ID: " + id);
        } else {
            System.out.println("Error al añadir alumno.");
        }
    }

    private static void eliminarAlumno() {
        System.out.println("== Eliminar alumno ==");
        int id = readInt("ID del alumno: ");
        Alumno a = db.BuscarAlumnoPorId(id);
        if (a == null) {
            System.out.println("No existe alumno con ID " + id);
            return;
        }
        System.out.println("Eliminar a: " + a.getNombre() + " " + a.getApellidos());
        String confirm = readLine("¿Seguro? (s/n): ");
        if (confirm.equalsIgnoreCase("s")) {
            boolean ok = db.EliminarAlumno(id);
            System.out.println(ok ? "Alumno eliminado." : "No se pudo eliminar.");
        } else {
            System.out.println("Cancelado.");
        }
    }

    private static void modificarNota() {
        System.out.println("== Modificar nota ==");
        int id = readInt("ID del alumno: ");
        Alumno a = db.BuscarAlumnoPorId(id);
        if (a == null) {
            System.out.println("No existe alumno con ID " + id);
            return;
        }
        System.out.println("Alumno actual: " + a.getNombre() + " " + a.getApellidos() + " - Nota: " + a.getNota());
        Double nuevaNota = readDoubleOrNull("Nueva nota: ");
        if (nuevaNota == null) {
            System.out.println("Operación cancelada.");
            return;
        }
        boolean ok = db.ModificarNota(id, nuevaNota);
        System.out.println(ok ? "Nota actualizada." : "Error al actualizar nota.");
    }

    private static void listarAlumnos() {
        System.out.println("== Lista de alumnos ==");

        List<Alumno> alumnos = db.MostrarTodosAlumnos();

        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados.");
            return;
        }

        for (Alumno a : alumnos) {
            System.out.printf("ID: %d | Nombre: %s %s | Dirección: %s | Nota: %s%n",
                    a.getId(),
                    a.getNombre(),
                    a.getApellidos(),
                    a.getDireccion(),
                    a.getNota() != null ? a.getNota() : "Sin nota");
        }
    }


    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Introduce un número válido: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer
        return value;
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static Double readDoubleOrNull(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        if (input.trim().isEmpty()) {
            return null;
        }
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Número inválido. Se anula la operación.");
            return null;
        }
    }


}