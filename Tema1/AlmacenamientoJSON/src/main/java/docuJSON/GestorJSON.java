package docuJSON;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestorJSON {
    private final String rutaArchivo = "src/main/resources/coches.json";
    private final Gson gson = new Gson();

    // Constructor que asegura existencia de carpeta y archivo
    public GestorJSON() {
        crearArchivoSiNoExiste();
    }

    private void crearArchivoSiNoExiste() {
        try {
            File file = new File(rutaArchivo);
            File carpeta = file.getParentFile();

            // Si la carpeta no existe, la creamos
            if (!carpeta.exists()) {
                boolean creada = carpeta.mkdirs();
                if (creada) {
                    System.out.println("Carpeta creada: " + carpeta.getAbsolutePath());
                }
            }

            // Si el archivo no existe, lo creamos con contenido inicial vacío (lista JSON vacía)
            if (!file.exists()) {
                boolean creado = file.createNewFile();
                if (creado) {
                    try (Writer writer = new FileWriter(file)) {
                        writer.write("[]");  // Lista JSON vacía para evitar errores al leer
                    }
                    System.out.println("Archivo creado: " + file.getAbsolutePath());
                }
            }
        } catch (IOException e) {
            System.err.println("Error creando archivo o carpeta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Coche> leerCoches() {
        File file = new File(rutaArchivo);
        if (!file.exists()) return new ArrayList<>();

        try (Reader reader = new FileReader(file)) {
            //Leere lista de coches
            Type listType = new TypeToken<List<Coche>>() {}.getType();
            //retorna una lista de coches obtenida del reader
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void guardarCoches(List<Coche> coches) {
        try (Writer writer = new FileWriter(rutaArchivo)) {
            gson.toJson(coches, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertarCoche(Coche nuevo) {
        List<Coche> coches = leerCoches();
        coches.add(nuevo);
        guardarCoches(coches);
        System.out.println("Coche insertado correctamente.");
    }

    public void borrarPorMarca(String marca) {
        List<Coche> coches = leerCoches();
        List<Coche> filtrados = coches.stream()
                .filter(c -> !c.getMarca().equalsIgnoreCase(marca))
                .collect(Collectors.toList());
        guardarCoches(filtrados);
        System.out.println("Coche(s) borrado(s) correctamente.");
    }

    public void mostrarCoches() {
        List<Coche> coches = leerCoches();
        if (coches.isEmpty()) {
            System.out.println("No hay coches registrados.");
        } else {
            System.out.println("Lista de coches:");
            coches.forEach(System.out::println);
        }
    }
}
