package com.ejemplo.usuarios;

import com.ejemplo.usuarios.controller.UserController;
import com.ejemplo.usuarios.model.Usuario;

import java.util.Scanner;

public class App {

    private static final Scanner sc = new Scanner(System.in);
    private static final UserController controller = new UserController();

    public static void main(String[] args) {

        int opcion;

        do {
            mostrarMenu();
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    login();
                    break;
                case 2:
                    registrar();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida, inténtelo de nuevo.");
            }

        } while (opcion != 0);
    }

    // ------------------- MÉTODOS --------------------

    private static void mostrarMenu() {
        System.out.println("\n--- MENÚ INICIO ---");
        System.out.println("1. Logearse");
        System.out.println("2. Registrarse");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
    }

    private static void login() {
        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();

        Usuario u = controller.login(usuario, contrasena);

        if (u != null) {
            System.out.println("Login correcto");
            System.out.println("Usuario: " + u.getUsuario());
            System.out.println("Nombre completo: " + u.getNombre() + " " + u.getApellidos());
            System.out.println("Móvil: " + u.getMovil());
        } else {
            System.out.println("Usuario o contraseña incorrectos");
        }
    }

    private static void registrar() {
        System.out.print("Nuevo usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Nueva contraseña: ");
        String contrasena = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = sc.nextLine();
        System.out.print("Móvil: ");
        String movil = sc.nextLine();

        Usuario nuevo = new Usuario(usuario, contrasena, nombre, apellidos, movil);

        boolean registrado = controller.registrar(nuevo);

        if (registrado) {
            System.out.println("Usuario registrado correctamente");
        } else {
            System.out.println("El usuario ya existe. No se ha podido registrar.");
        }
    }
}
