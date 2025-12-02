package FICHEROS.io;

import FICHEROS.model.Alumno;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
        import java.nio.file.*;
        import java.util.*;
        import java.util.stream.Collectors;

public class AlumnoCsvRepository {

    private static final String RUTA = "src/main/resources/alumnos.csv";

    /** Lee todos los alumnos del fichero CSV */
    public List<Alumno> leerAlumnos() {
        List<Alumno> alumnos = new ArrayList<>();
        try {
            Path path = Paths.get(RUTA);
            if (!Files.exists(path)) {
                Files.createFile(path);
                return alumnos;
            }

            try (Reader reader = Files.newBufferedReader(path);
                 CSVReader csvReader = new CSVReader(reader)) {

                String[] linea;
                while ((linea = csvReader.readNext()) != null) {
                    if (linea.length < 3) continue;

                    String expediente = linea[0];
                    String nombre = linea[1];
                    String apellidos = linea[2];
                    List<Double> notas = new ArrayList<>();

                    if (linea.length > 3 && !linea[3].isEmpty()) {
                        notas = Arrays.stream(linea[3].split(","))
                                .map(Double::parseDouble)
                                .collect(Collectors.toList());
                    }

                    alumnos.add(new Alumno(expediente, nombre, apellidos, notas));
                }
            }
        } catch (IOException | CsvValidationException e) {
            System.err.println("Error al leer CSV: " + e.getMessage());
        }
        return alumnos;
    }

    /** Guarda todos los alumnos en el fichero CSV */
    public void guardarAlumnos(List<Alumno> alumnos) {
        try (Writer writer = Files.newBufferedWriter(Paths.get(RUTA));
             CSVWriter csvWriter = new CSVWriter(writer)) {

            for (Alumno a : alumnos) {
                String notas = a.getNotas().stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(","));
                csvWriter.writeNext(new String[]{
                        a.getExpediente(),
                        a.getNombre(),
                        a.getApellidos(),
                        notas
                });
            }
        } catch (IOException e) {
            System.err.println("Error al guardar CSV: " + e.getMessage());
        }
    }
}
