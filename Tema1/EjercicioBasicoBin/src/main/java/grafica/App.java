package grafica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class App {

    private static final Scanner sc = new Scanner(System.in);

    private static final String FICHERO =
            "src/main/resources/archivo.bin";

    public static void main(String[] args) {

        // Crear la carpeta resources si no existe
        File carpeta = new File("src/main/resources");

        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        boolean salir = false;

        while (!salir) {

            System.out.println("\n=== MENÚ FICHERO BINARIO ===");
            System.out.println("1. Escribir registro");
            System.out.println("2. Leer registros");
            System.out.println("3. Salir");
            System.out.print("Opción: ");

            String opcion = sc.nextLine();

            switch (opcion) {

                case "1":
                    escribirRegistro();
                    break;

                case "2":
                    leerRegistros();
                    break;

                case "3":
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
    // 1. ESCRIBIR EN EL FICHERO BINARIO
    // =====================================================

    private static void escribirRegistro() {

        System.out.println("\n--- ESCRIBIR REGISTRO ---");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Edad: ");
        int edad = Integer.parseInt(sc.nextLine());

        System.out.print("Ciudad: ");
        String ciudad = sc.nextLine();

        try (DataOutputStream dos =
                     new DataOutputStream(
                             new FileOutputStream(FICHERO, true))) {

            // Escribir los datos en formato binario
            dos.writeUTF(nombre);
            dos.writeInt(edad);
            dos.writeUTF(ciudad);

            System.out.println("Registro guardado correctamente.");

        } catch (IOException e) {

            System.out.println(
                    "Error escribiendo en el fichero: "
                            + e.getMessage()
            );
        }
    }

    // =====================================================
    // 2. LEER EL FICHERO BINARIO
    // =====================================================

    private static void leerRegistros() {

        System.out.println("\n--- LEER REGISTROS ---");

        File fichero = new File(FICHERO);

        if (!fichero.exists()) {
            System.out.println("El fichero no existe.");
            return;
        }

        try (DataInputStream dis =
                     new DataInputStream(
                             new FileInputStream(FICHERO))) {

            while (true) {

                String nombre = dis.readUTF();
                int edad = dis.readInt();
                String ciudad = dis.readUTF();

                System.out.println("-------------------------");
                System.out.println("Nombre: " + nombre);
                System.out.println("Edad: " + edad);
                System.out.println("Ciudad: " + ciudad);
            }

        } catch (EOFException e) {

            // Fin normal del fichero
            System.out.println("-------------------------");

        } catch (IOException e) {

            System.out.println(
                    "Error leyendo el fichero: "
                            + e.getMessage()
            );
        }
    }
}