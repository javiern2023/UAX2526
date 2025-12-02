package docuJSON;
/*
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestorJSON {
    private final String rutaArchivo = "src/main/resources/coches.json";

    // Constructor que asegura existencia de carpeta y archivo
    public GestorJSON() {
        crearArchivoSiNoExiste();
    }

    private void crearArchivoSiNoExiste() {
        try {
            File file = new File(rutaArchivo);
            File carpeta = file.getParentFile();

            if (!carpeta.exists()) {
                boolean creada = carpeta.mkdirs();
                if (creada) System.out.println("Carpeta creada: " + carpeta.getAbsolutePath());
            }

            if (!file.exists()) {
                boolean creado = file.createNewFile();
                if (creado) {
                    try (Writer writer = new FileWriter(file)) {
                        writer.write("[]");  // Lista JSON vacía
                    }
                    System.out.println("Archivo creado: " + file.getAbsolutePath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Coche> leerCoches() {
        File file = new File(rutaArchivo);
        List<Coche> coches = new ArrayList<>();

        if (!file.exists()) return coches;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String linea;
            while ((linea = reader.readLine()) != null) {
                sb.append(linea);
            }

            JSONArray jsonArray = new JSONArray(sb.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                Coche coche = new Coche(
                        obj.getString("marca"),
                        obj.getString("modelo"),
                        obj.getInt("año")
                );
                coches.add(coche);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coches;
    }

    public void guardarCoches(List<Coche> coches) {
        JSONArray jsonArray = new JSONArray();
        for (Coche coche : coches) {
            JSONObject obj = new JSONObject();
            obj.put("marca", coche.getMarca());
            obj.put("modelo", coche.getModelo());
            obj.put("año", coche.getAño());
            jsonArray.put(obj);
        }

        try (Writer writer = new FileWriter(rutaArchivo)) {
            writer.write(jsonArray.toString(4)); // Formato con indentación
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
}*/
