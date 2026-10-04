package grafica;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class App {

    private static final Scanner sc = new Scanner(System.in);
    private static final String FICHERO = "src/main/resources/archivo.txt";

    public static void main(String[] args) {

        // Crear carpeta resources si no existe
        File carpeta = new File("src/main/resources");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== MENÚ FICHERO TXT ===");
            System.out.println("1. Escribir carácter a carácter");
            System.out.println("2. Escribir línea completa");
            System.out.println("3. Leer carácter a carácter");
            System.out.println("4. Leer línea completa");
            System.out.println("5. Salir");
            System.out.print("Opción: ");

            String opcion = sc.nextLine();

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

    private static void escribirCaracteres() {

        System.out.println("Introduce un texto:");
        String texto = sc.nextLine();

        try (FileWriter fw = new FileWriter(FICHERO, true)) {

            for (char c : texto.toCharArray()) { //Convierte un string en un array de caracteres
                fw.write(c);
            }

            fw.write(System.lineSeparator());

            System.out.println("Texto guardado en archivo.txt");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void escribirLineas() {

        System.out.println("Introduce líneas.");
        System.out.println("Escribe 'fin' para terminar.");

        try {

            while (true) {

                System.out.print("> ");
                String linea = sc.nextLine();

                if (linea.equalsIgnoreCase("fin")) {
                    break;
                }

                Files.write(
                        Paths.get(FICHERO), //convierte el fichero en un objeto path
                        (linea + System.lineSeparator()).getBytes(StandardCharsets.UTF_8), //Convierte el array en un array de bytes
                        java.nio.file.StandardOpenOption.CREATE, //Si el fichero no existe lo creas
                        java.nio.file.StandardOpenOption.APPEND //Escribe al final, si no se pones sobreescribe
                );
            }

            System.out.println("Texto guardado en archivo.txt");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void leerCaracteres() {

        try (FileReader fr = new FileReader(FICHERO)) {

            int c;

            System.out.println("\nContenido del fichero:");

            while ((c = fr.read()) != -1) {
                System.out.print((char) c);
            }

            System.out.println();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void leerLineas() {

        Path ruta = Paths.get(FICHERO);

        if (!Files.exists(ruta)) {
            System.out.println("El fichero no existe.");
            return;
        }

        try {

            List<String> lineas =
                    Files.readAllLines(ruta, StandardCharsets.UTF_8); //lee todas las líneas del fichero y lo convierte en una lista

            System.out.println("\nContenido del fichero:");

            for (String linea : lineas) {
                System.out.println(linea);
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}