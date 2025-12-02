package FICHEROS;

import FICHEROS.io.AlumnoCsvRepository;
import FICHEROS.model.Alumno;
import FICHEROS.services.AlumnoServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AlumnoCsvRepository repo = new AlumnoCsvRepository();
        AlumnoServices service = new AlumnoServices(repo);

        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE ALUMNOS (CSV) ===");
            System.out.println("1. Dar de alta un alumno");
            System.out.println("2. Dar de baja un alumno");
            System.out.println("3. Insertar nota a un alumno");
            System.out.println("4. Modificar nota de un alumno");
            System.out.println("5. Consultar notas de un alumno");
            System.out.println("6. Consultar todos los alumnos");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("Expediente: ");
                    String expedienteAlta = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Apellidos: ");
                    String apellidos = sc.nextLine();
                    Alumno nuevo = new Alumno(expedienteAlta, nombre, apellidos, new ArrayList<Double>());
                    service.darAlta(nuevo);
                    System.out.println("Alumno dado de alta correctamente.");
                    break;

                case 2:
                    System.out.print("Expediente del alumno a eliminar: ");
                    String expedienteBaja = sc.nextLine();
                    service.darBaja(expedienteBaja);
                    System.out.println("Alumno eliminado (si existía).");
                    break;

                case 3:
                    System.out.print("Expediente del alumno: ");
                    String expedienteNota = sc.nextLine();
                    System.out.print("Nota a insertar: ");
                    try {
                        double nota = Double.parseDouble(sc.nextLine());
                        service.insertarNota(expedienteNota, nota);
                        System.out.println("Nota añadida correctamente.");
                    } catch (NumberFormatException e) {
                        System.out.println("Debe introducir un número válido para la nota.");
                    }
                    break;

                case 4:
                    System.out.print("Expediente del alumno: ");
                    String expedienteMod = sc.nextLine();
                    System.out.print("Índice de la nota a modificar (comienza en 0): ");
                    try {
                        int indice = Integer.parseInt(sc.nextLine());
                        System.out.print("Nueva nota: ");
                        double nuevaNota = Double.parseDouble(sc.nextLine());
                        service.modificarNota(expedienteMod, indice, nuevaNota);
                        System.out.println("Nota modificada correctamente.");
                    } catch (NumberFormatException e) {
                        System.out.println("Debe introducir valores numéricos válidos.");
                    }
                    break;

                case 5:
                    System.out.print("Expediente del alumno: ");
                    String expedienteConsulta = sc.nextLine();
                    List<Double> notas = service.consultarNota(expedienteConsulta);
                    if (notas != null)
                        System.out.println("Notas del alumno " + expedienteConsulta + ": " + notas);
                    else
                        System.out.println("Alumno no encontrado.");
                    break;

                case 6:
                    List<Alumno> todos = service.consultarTodasLasNotas();
                    if (todos.isEmpty()) {
                        System.out.println("No hay alumnos registrados.");
                    } else {
                        System.out.println("=== Listado de alumnos ===");
                        for (Alumno a : todos) {
                            System.out.println(a);
                        }
                    }
                    break;

                case 7:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }

        } while (opcion != 7);
    }
}
