package FICHEROS.io;

import FICHEROS.model.Alumno;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AlmacenTxt {

    private final File archivo;

    public AlmacenTxt(String ruta) {
        this.archivo = new File(ruta);
        crearArchivoSiNoExiste();
    }

    private void crearArchivoSiNoExiste() {
        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el archivo", e);
        }
    }

    public List<Alumno> leerAlumnos() {
        List<Alumno> alumnos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    Alumno a = new Alumno(
                            partes[0],
                            partes[1],
                            partes[2],
                            Double.parseDouble(partes[3])
                    );
                    alumnos.add(a);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return alumnos;
    }

    public void guardarAlumnos(List<Alumno> alumnos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (Alumno a : alumnos) {
                bw.write(a.getExpediente() + ";" + a.getNombre() + ";" + a.getApellidos() + ";" + a.getNota());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}
