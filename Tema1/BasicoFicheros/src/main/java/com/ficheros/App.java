package com.ficheros;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final Scanner sc = new Scanner(System.in);
    private static final String RUTA_RESOURCES = "src/main/resources";

    public static void main(String[] args) {
        // Crear carpeta resources si no existe
        File carpeta = new File(RUTA_RESOURCES);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        boolean salir = false;
        while (!salir) {
            System.out.println("\n=== MENÚ DE FICHEROS ===");
            System.out.println("1. Escribir en fichero carácter a carácter");
            System.out.println("2. Escribir en fichero línea completa");
            System.out.println("3. Leer fichero carácter a carácter");
            System.out.println("4. Leer fichero línea completa");
            System.out.println("5. Salir");
            System.out.print("Opción: ");
            String opcion = sc.nextLine().trim();

            switch (opcion) {
                case "1":
                    escribirCaracteres();
                    break;
                case "2":
                    escribirLineas();
                    break;
                case "3":
                    leerCaracteres();
                    break;
                case "4":
                    leerLineas();
                    break;
                case "5":
                    salir = true;
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static String elegirFichero() {
        System.out.print("¿Fichero .txt o .csv? (txt/csv): ");
        String tipo = sc.nextLine().trim().toLowerCase();
        if (!tipo.equals("csv")) tipo = "txt";
        return RUTA_RESOURCES + "/archivo." + tipo;
    }

    private static void escribirCaracteres() {
        String fichero = elegirFichero();
        System.out.println("Introduce texto (termina con Enter):");
        String texto = sc.nextLine();

        try (FileWriter fw = new FileWriter(fichero, true)) { // 'true' para append
            for (char c : texto.toCharArray()) {
                fw.write(c);
            }
            fw.write(System.lineSeparator()); // salto de línea al final
            System.out.println("Texto guardado carácter a carácter en " + fichero);
        } catch (IOException e) {
            System.err.println("Error escribiendo en el fichero: " + e.getMessage());
        }
    }

    private static void escribirLineas() {
        String fichero = elegirFichero();
        System.out.println("Introduce líneas (escribe 'fin' para terminar):");

        try {
            while (true) {
                System.out.print("> ");
                String linea = sc.nextLine();
                if (linea.equalsIgnoreCase("fin")) break;
                Files.write(Paths.get(fichero), (linea + System.lineSeparator()).getBytes(StandardCharsets.UTF_8),
                        StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }
            System.out.println("Texto guardado en " + fichero);
        } catch (IOException e) {
            System.err.println("Error escribiendo en el fichero: " + e.getMessage());
        }
    }

    private static void leerCaracteres() {
        String fichero = elegirFichero();

        try (FileReader fr = new FileReader(fichero)) {
            int c;
            System.out.println("\nContenido de " + fichero + " (carácter a carácter):");
            while ((c = fr.read()) != -1) {
                System.out.print((char) c);
            }
            System.out.println();
        } catch (IOException e) {
            System.err.println("Error leyendo el fichero: " + e.getMessage());
        }
    }

    private static void leerLineas() {
        String fichero = elegirFichero();
        try {
            if (!Files.exists(Paths.get(fichero))) {
                System.out.println("El fichero no existe.");
                return;
            }
            System.out.println("\nContenido de " + fichero + " (líneas completas):");
            List<String> lineas = Files.readAllLines(Paths.get(fichero), StandardCharsets.UTF_8);
            for (String linea : lineas) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.err.println("Error leyendo el fichero: " + e.getMessage());
        }
    }
}
