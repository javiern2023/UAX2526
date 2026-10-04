package vista;

import modelo.Persona;

import java.util.List;
import java.util.Scanner;

public class PersonaView {

    private final Scanner sc = new Scanner(System.in);

    public void mostrarMenu() {

        System.out.println("\n==============================");
        System.out.println("     FICHERO BINARIO");
        System.out.println("==============================");
        System.out.println("1. Escribir persona");
        System.out.println("2. Leer personas");
        System.out.println("3. Salir");
        System.out.println("==============================");
        System.out.print("Opción: ");
    }

    public int pedirOpcion() {

        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public Persona pedirPersona() {

        System.out.println("\n--- NUEVA PERSONA ---");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Edad: ");
        int edad = Integer.parseInt(sc.nextLine());

        System.out.print("Ciudad: ");
        String ciudad = sc.nextLine();

        return new Persona(nombre, edad, ciudad);
    }

    public void mostrarPersonas(List<Persona> personas) {

        System.out.println("\n--- PERSONAS GUARDADAS ---");

        if (personas.isEmpty()) {
            System.out.println("No hay personas guardadas.");
            return;
        }

        for (Persona persona : personas) {
            System.out.println(persona);
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void cerrarScanner() {
        sc.close();
    }
}