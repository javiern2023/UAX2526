package FICHEROS.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import FICHEROS.model.Alumno;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AlumnoJSONRepository {

    private static final String FICHERO = "src/main/resources/alumnos.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public List<Alumno> leerAlumnos() {
        File f = new File(FICHERO);
        if (!f.exists()) return new ArrayList<>();

        try (FileReader fr = new FileReader(f)) {
            return gson.fromJson(fr, new TypeToken<List<Alumno>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void guardarAlumnos(List<Alumno> alumnos) {
        try (FileWriter fw = new FileWriter(FICHERO)) {
            gson.toJson(alumnos, fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
