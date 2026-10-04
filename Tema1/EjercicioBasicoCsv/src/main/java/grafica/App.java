package grafica;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

public class App {

    private static final Scanner sc = new Scanner(System.in);

    // Fichero CSV que vamos a utilizar
    private static final String FICHERO =
            "src/main/resources/archivo.csv";

    public static void main(String[] args) {

        // Crear la carpeta resources si no existe
        File carpeta = new File("src/main/resources");

        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        boolean salir = false;

        while (!salir) {

            System.out.println("\n=== MENÚ DE FICHERO CSV ===");
            System.out.println("1. Escribir registro carácter a carácter");
            System.out.println("2. Escribir registro completo");
            System.out.println("3. Leer fichero carácter a carácter");
            System.out.println("4. Leer fichero línea a línea");
            System.out.println("5. Salir");
            System.out.print("Opción: ");

            String opcion = sc.nextLine().trim();

            switch (opcion) {

                case "1":
                    escribirCaracteres();
                    break;

                case "2":
                    escribirLinea();
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

        sc.close();
    }

    // =====================================================
    // 1. ESCRIBIR CARÁCTER A CARÁCTER
    // =====================================================

    private static void escribirCaracteres() {

        System.out.println("\n--- ESCRIBIR CARÁCTER A CARÁCTER ---");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Edad: ");
        String edad = sc.nextLine();

        System.out.print("Ciudad: ");
        String ciudad = sc.nextLine();

        // Crear el registro CSV
        String registro = nombre + ";" + edad + ";" + ciudad;

        try (FileWriter fw = new FileWriter(FICHERO, true)) {

            // Escribir carácter a carácter
            for (char c : registro.toCharArray()) {
                fw.write(c);
            }

            // Salto de línea
            fw.write(System.lineSeparator());

            System.out.println("Registro guardado correctamente.");

        } catch (IOException e) {

            System.out.println(
                    "Error escribiendo en el fichero: "
                            + e.getMessage()
            );
        }
    }

    // =====================================================
    // 2. ESCRIBIR LÍNEA COMPLETA
    // =====================================================

    private static void escribirLinea() {

        System.out.println("\n--- ESCRIBIR REGISTRO COMPLETO ---");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Edad: ");
        String edad = sc.nextLine();

        System.out.print("Ciudad: ");
        String ciudad = sc.nextLine();

        // Crear una línea CSV
        String registro =
                nombre + ";" + edad + ";" + ciudad
                        + System.lineSeparator();

        try {

            Files.write(
                    Paths.get(FICHERO), //Ruta de Java
                    registro.getBytes(StandardCharsets.UTF_8), //Convertimos a bytes
                    StandardOpenOption.CREATE, //Crea el archivo si no existe
                    StandardOpenOption.APPEND //Añades al final del archivo
            );

            System.out.println("Registro guardado correctamente.");

        } catch (IOException e) {

            System.out.println(
                    "Error escribiendo en el fichero: "
                            + e.getMessage()
            );
        }
    }

    // =====================================================
    // 3. LEER CARÁCTER A CARÁCTER
    // =====================================================

    private static void leerCaracteres() {

        System.out.println("\n--- LEER CARÁCTER A CARÁCTER ---");

        File fichero = new File(FICHERO);

        if (!fichero.exists()) {
            System.out.println("El fichero no existe.");
            return;
        }

        try (FileReader fr = new FileReader(FICHERO)) {

            int c;

            while ((c = fr.read()) != -1) {
                System.out.print((char) c);
            }

            System.out.println();

        } catch (IOException e) {

            System.out.println(
                    "Error leyendo el fichero: "
                            + e.getMessage()
            );
        }
    }

    // =====================================================
    // 4. LEER LÍNEA A LÍNEA
    // =====================================================

    private static void leerLineas() {

        System.out.println("\n--- LEER REGISTROS CSV ---");

        File fichero = new File(FICHERO);

        if (!fichero.exists()) {
            System.out.println("El fichero no existe.");
            return;
        }

        try {
            //Cada línea del archivo se guarda en la lista
            List<String> lineas = Files.readAllLines(
                    Paths.get(FICHERO),
                    StandardCharsets.UTF_8
            );

            for (String linea : lineas) {

                // Separar las columnas usando ;
                String[] datos = linea.split(";");

                if (datos.length == 3) {

                    System.out.println("-------------------------");
                    System.out.println("Nombre: " + datos[0]);
                    System.out.println("Edad: " + datos[1]);
                    System.out.println("Ciudad: " + datos[2]);
                }
            }

            System.out.println("-------------------------");

        } catch (IOException e) {

            System.out.println(
                    "Error leyendo el fichero: "
                            + e.getMessage()
            );
        }
    }
}