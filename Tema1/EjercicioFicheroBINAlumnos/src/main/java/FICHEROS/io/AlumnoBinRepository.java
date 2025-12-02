package FICHEROS.io;

import FICHEROS.model.Alumno;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoBinRepository {

    //private static final String FICHERO = "alumnos.bin"; RUTA RELATIVA. DENTRO DE LA CARPETA TARGET
    private static final String FICHERO = "src/main/resources/alumnos.bin";


    @SuppressWarnings("unchecked") //que no haga caso a ciertos errores
    public List<Alumno> leerAlumnos() {
        File f = new File(FICHERO);
        if (!f.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (List<Alumno>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void guardarAlumnos(List<Alumno> alumnos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO))) {
            oos.writeObject(alumnos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
