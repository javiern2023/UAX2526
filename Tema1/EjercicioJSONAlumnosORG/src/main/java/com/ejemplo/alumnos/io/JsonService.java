package com.ejemplo.alumnos.io;

import com.ejemplo.alumnos.model.Alumno;
import com.ejemplo.alumnos.model.AlumnoList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class JsonService {

    private final String filePath;

    public JsonService(String filePath) {
        this.filePath = filePath;
    }

    // Guardar toda la lista de alumnos en JSON
    public void guardar(AlumnoList lista) {
        JSONArray jsonArray = new JSONArray();

        List<Alumno> alumnos = lista.getAlumnos();
        for (int i = 0; i < alumnos.size(); i++) {
            Alumno a = alumnos.get(i);
            JSONObject jo = new JSONObject();
            jo.put("expediente", a.getExpediente());
            jo.put("nombre", a.getNombre());
            jo.put("apellidos", a.getApellidos());

            // Objeto anidado de notas
            JSONObject notas = new JSONObject();
            notas.put("notaMatematicas", a.getNotas().getNotaMatematicas());
            notas.put("notaProgramacion", a.getNotas().getNotaProgramacion());
            notas.put("notaFisica", a.getNotas().getNotaFisica());

            jo.put("notas", notas);

            jsonArray.put(jo);
        }

        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(filePath))) {
            bw.write(jsonArray.toString(4)); // indentado 4 espacios
            System.out.println("Datos guardados en JSON.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Leer alumnos desde JSON y llenar la lista
    public void leer(AlumnoList lista) {
        lista.getAlumnos().clear(); // limpiar lista antes de leer

        if (!Files.exists(Paths.get(filePath))) {
            System.out.println("Archivo JSON no existe, lista vacía.");
            return;
        }

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = jsonArray.getJSONObject(i);
                String expediente = jo.getString("expediente");
                String nombre = jo.getString("nombre");
                String apellidos = jo.getString("apellidos");

                Alumno a = new Alumno(expediente, nombre, apellidos);

                // Leer notas
                JSONObject notas = jo.getJSONObject("notas");
                if (notas.has("notaMatematicas")) {
                    a.getNotas().setNotaMatematicas(notas.getDouble("notaMatematicas"));
                }
                if (notas.has("notaProgramacion")) {
                    a.getNotas().setNotaProgramacion(notas.getDouble("notaProgramacion"));
                }
                if (notas.has("notaFisica")) {
                    a.getNotas().setNotaFisica(notas.getDouble("notaFisica"));
                }

                lista.addAlumno(a);
            }

            System.out.println("Datos leídos desde JSON.");

        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo JSON.");
        } catch (Exception e) {
            System.out.println("Error al parsear JSON: " + e.getMessage());
        }
    }
}
