package HIBERNATE;

import java.util.List;
import java.util.Scanner;

// NUEVAS IMPORTACIONES para redirigir logs
import java.util.logging.LogManager;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class App {

    private static final Scanner sc = new Scanner(System.in);
    private static final BaseDatos dao = new BaseDatos();

    public static void main(String[] args) {
        // Redirigir logs JUL (java.util.logging) -> SLF4J
        LogManager.getLogManager().reset();
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        int opcion;
        do {
            mostrarMenu();
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    darAlta();
                    break;
                case 2:
                    darBaja();
                    break;
                case 3:
                    modificarNota();
                    break;
                case 4:
                    mostrarAlumnos();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }

        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.print(
                "=== Menú Principal ===\n" +
                        "1. Dar de alta alumno\n" +
                        "2. Dar de baja alumno\n" +
                        "3. Modificar nota\n" +
                        "4. Mostrar todos los alumnos\n" +
                        "0. Salir\n" +
                        "Selecciona una opción: "
        );

    }

    private static void darAlta() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = sc.nextLine();
        System.out.print("Dirección: ");
        String direccion = sc.nextLine();
        System.out.print("Nota: ");
        double nota = Double.parseDouble(sc.nextLine());

        Alumno alumno = new Alumno(0, nombre, apellidos, direccion, nota);
        dao.guardarAlumno(alumno);
        System.out.println("Alumno guardado con éxito.");
    }

    private static void darBaja() {
        System.out.print("ID del alumno a eliminar: ");
        int id = Integer.parseInt(sc.nextLine());
        dao.eliminarAlumno(id);
        System.out.println("Alumno eliminado si existía.");
    }

    private static void modificarNota() {
        System.out.print("ID del alumno: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Nueva nota: ");
        double nota = Double.parseDouble(sc.nextLine());
        dao.actualizarNota(id, nota);
        System.out.println("Nota actualizada.");
    }

    private static void mostrarAlumnos() {
        List<Alumno> alumnos = dao.obtenerTodos();
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos en la base de datos.");
        } else {
            alumnos.forEach(System.out::println);
        }
    }
}
