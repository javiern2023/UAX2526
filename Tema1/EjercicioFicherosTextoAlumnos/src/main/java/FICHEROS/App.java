package FICHEROS;

import FICHEROS.model.Alumno;
import FICHEROS.service.GestorAlumnos;
import FICHEROS.service.GestorAlumnosTxt;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        GestorAlumnos gestor = new GestorAlumnosTxt("src/main/resources/alumnos.txt");
        Scanner sc = new Scanner(System.in);
        String opcion;

        do {
            System.out.println("\nGESTIÓN DE ALUMNOS");
            System.out.println("1. Alta alumno");
            System.out.println("2. Baja alumno");
            System.out.println("3. Insertar nota");
            System.out.println("4. Modificar nota");
            System.out.println("5. Consultar alumno");
            System.out.println("6. Consultar todos");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("Expediente: ");
                    String exp = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nom = sc.nextLine();
                    System.out.print("Apellidos: ");
                    String ape = sc.nextLine();
                    System.out.print("Nota: ");
                    double nota = Double.parseDouble(sc.nextLine());
                    gestor.altaAlumno(new Alumno(exp, nom, ape, nota));
                    break;
                case "2":
                    System.out.print("Expediente: ");
                    gestor.bajaAlumno(sc.nextLine());
                    break;
                case "3":
                case "4":
                    System.out.print("Expediente: ");
                    exp = sc.nextLine();
                    System.out.print("Nueva nota: ");
                    nota = Double.parseDouble(sc.nextLine());
                    gestor.modificarNota(exp, nota);
                    break;
                case "5":
                    System.out.print("Expediente: ");
                    Alumno a = gestor.consultarAlumno(sc.nextLine());
                    System.out.println(a != null ? a : "Alumno no encontrado.");
                    break;
                case "6":
                    List<Alumno> todos = gestor.consultarTodos();
                    todos.forEach(System.out::println);
                    break;
                case "0":
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (!opcion.equals("0"));
    }
}
