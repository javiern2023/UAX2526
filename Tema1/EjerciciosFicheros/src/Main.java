//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== MENÚ DE GESTIÓN DE ARCHIVOS ===");
            System.out.println("1. Crear archivo");
            System.out.println("2. Borrar archivo");
            System.out.println("3. Copiar archivo");
            System.out.println("4. Añadir texto al final");
            System.out.println("5. Leer archivo (FileReader)");
            System.out.println("6. Leer archivo (BufferedReader)");
            System.out.println("7. Leer archivo (Scanner)");
            System.out.println("8. Escribir archivo (PrintWriter)");
            System.out.println("9. Salir");
            System.out.print("Elige una opción: ");

            opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion) {
                case 1:
                    System.out.print("Nombre del archivo a crear: ");
                    String archivoCrear = scanner.nextLine();
                    try {
                        crearFichero(archivoCrear);
                    } catch (IOException e) {
                        System.out.println("Error creando archivo.");
                    }
                    break;
                case 2:
                    System.out.print("Nombre del archivo a borrar: ");
                    String archivoBorrar = scanner.nextLine();
                    borrarFichero(archivoBorrar);
                    break;
                case 3:
                    System.out.print("Archivo origen: ");
                    String origen = scanner.nextLine();
                    System.out.print("Archivo destino: ");
                    String destino = scanner.nextLine();
                    copiarFichero(origen, destino);
                    break;
                case 4:
                    System.out.print("Nombre del archivo: ");
                    String archivoTexto = scanner.nextLine();
                    System.out.print("Texto a añadir: ");
                    String texto = scanner.nextLine();
                    añadirTextoAlFinal(archivoTexto, texto);
                    break;
                case 5:
                    System.out.print("Nombre del archivo a leer (FileReader): ");
                    String archivoFR = scanner.nextLine();
                    leerConFileReader(archivoFR);
                    break;
                case 6:
                    System.out.print("Nombre del archivo a leer (BufferedReader): ");
                    String archivoBR = scanner.nextLine();
                    lecturaConBufferedReader(archivoBR);
                    break;
                case 7:
                    System.out.print("Nombre del archivo a leer (Scanner): ");
                    String archivoSC = scanner.nextLine();
                    lecturaConScanner(archivoSC);
                    break;
                case 8:
                    System.out.print("Nombre del archivo: ");
                    String archivoPW = scanner.nextLine();
                    System.out.print("Texto a escribir (sobrescribe): ");
                    String textoPW = scanner.nextLine();
                    escribirConPrintWriter(archivoPW, textoPW);
                    break;
                case 9:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }

        } while (opcion != 9);

        scanner.close();
    }

    public static void crearFichero(String nombreArchivo) throws IOException {
            // No creamos el fichero. Sólo crea un objeto en memoria que representa la ruta.
            File archivo = new File(nombreArchivo);

            // Creamos el fichero
            if (archivo.createNewFile()) {
                // Si el fichero no existía y se creó correctamente
                System.out.println("Fichero creado: " + archivo.getName());
            } else {
                // Si el fichero ya existe
                System.out.println("El fichero ya existe.");
            }
    }
    public static void borrarFichero(String nombreArchivo) {
        // Creamos el objeto File que representa el archivo
        File archivo = new File(nombreArchivo);

        // Comprobamos si existe antes de intentar borrarlo
        if (archivo.exists()) {
            // Intentamos borrar el archivo
            if (archivo.delete()) {
                System.out.println("Fichero eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar el fichero.");
            }
        } else {
            System.out.println("El fichero no existe.");
        }
    }
    public static void copiarFichero(String origen, String destino) {
        File archivoOrigen = new File(origen);
        File archivoDestino = new File(destino);

        // Comprobamos que el archivo origen exista y sea un archivo (no directorio)
        if (!archivoOrigen.exists() || !archivoOrigen.isFile()) {
            System.out.println("El archivo de origen no existe o no es un archivo válido: " + origen);
            return; // Salimos sin copiar
        }

        Path rutaOrigen = archivoOrigen.toPath();
        Path rutaDestino = archivoDestino.toPath();

        try {
            //Si al archivo destino ya existe, sobreescribe.
            Files.copy(rutaOrigen, rutaDestino, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo copiado con éxito de '" + origen + "' a '" + destino + "'");
        } catch (IOException e) {
            System.out.println("Error al copiar el archivo:");
            e.printStackTrace();
        }
    }
    public static void añadirTextoAlFinal(String nombreArchivo, String texto) {
        //Modo append es añadir
        try (FileWriter fw = new FileWriter(nombreArchivo, true)) { // true = modo append
            fw.write(texto);
            fw.write(System.lineSeparator()); // Para salto de línea
            System.out.println("Texto añadido correctamente.");
        } catch (IOException e) {
            System.out.println("Error escribiendo en el fichero:");
            e.printStackTrace();
        }
    }
    public static void escribirConFileWriter(String archivo, String texto) {
        try (FileWriter fw = new FileWriter(archivo)) { // in append: sobrescribes
            fw.write(texto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void añadirConFileWriter(String archivo, String texto) {
        try (FileWriter fw = new FileWriter(archivo, true)) { // true = append
            fw.write(texto);
            fw.write(System.lineSeparator()); // salto de línea
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void escribirConBufferedWriter(String archivo, String texto) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            bw.write(texto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void añadirConBufferedWriter(String archivo, String texto) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(texto);
            bw.newLine();  // añade salto de línea
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void escribirConPrintWriter(String archivo, String texto) {
        try (PrintWriter pw = new PrintWriter(archivo)) {
            pw.println(texto); // añade salto de línea automáticamente
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void añadirConPrintWriter(String archivo, String texto) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, true))) {
            pw.println(texto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void escribirConFiles(String archivo, String texto) {
        try {
            Files.write(Paths.get(archivo), texto.getBytes()); // sobrescribe
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void añadirConFiles(String archivo, String texto) {
        try {
            Files.write(Paths.get(archivo), texto.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void escribirLineasConFiles(String archivo, List<String> lineas) {
        Path path = Paths.get(archivo);
        try {
            Files.write(path, lineas); // Sobrescribe y escribe todas las líneas
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void leerConFileReader (String archivo){
       //Lee el contenido carácter a carácter
        try {
            FileReader fr = new FileReader(archivo);
            int caracter;
            while ((caracter = fr.read()) != -1) {
                System.out.print((char) caracter);
            }
            fr.close();
       } catch (IOException e) {
            e.printStackTrace();
       }
    }
    public static void lecturaConBufferedReader (String archivo){
        //Lee el archivo línea por línea
       try {
           BufferedReader br = new BufferedReader(new FileReader(archivo));
           String linea;

           while ((linea = br.readLine()) != null) {
                System.out.println(linea);
           }
           br.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
    }
    public static void lecturaConScanner (String archivo) {
        //La manera más flexible, permite leer línea por línea, palabra por palabra, números,....
        try {
            Scanner sc = new Scanner(new File(archivo));

            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                System.out.println(linea);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

