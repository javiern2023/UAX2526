package FICHEROS.services;

import FICHEROS.io.AlumnoXMLRepository;
import FICHEROS.model.Alumno;

import java.util.List;

public class AlumnoService {

    private final AlumnoXMLRepository almacen;

    public AlumnoService(AlumnoXMLRepository repo) {
        this.almacen = repo;
    }

    public void darAlta(Alumno alumno) {
        List<Alumno> alumnos = almacen.leerAlumnos();
        alumnos.add(alumno);
        almacen.guardarAlumnos(alumnos);
    }

    public void darBaja(String expediente) {
        List<Alumno> alumnos = almacen.leerAlumnos();
        alumnos.removeIf(a -> a.getExpediente().equals(expediente));
        almacen.guardarAlumnos(alumnos);
    }

    public void insertarNota(String expediente, double nota) {
        List<Alumno> alumnos = almacen.leerAlumnos();
        for (Alumno a : alumnos) {
            if (a.getExpediente().equals(expediente)) {
                a.getNotas().add(nota);
                break;
            }
        }
        almacen.guardarAlumnos(alumnos);
    }

    public void modificarNota(String expediente, int indice, double nuevaNota) {
        List<Alumno> alumnos = almacen.leerAlumnos();
        for (Alumno a : alumnos) {
            if (a.getExpediente().equals(expediente)) {
                if (indice >= 0 && indice < a.getNotas().size()) {
                    a.getNotas().set(indice, nuevaNota);
                }
                break;
            }
        }
        almacen.guardarAlumnos(alumnos);
    }

    public List<Double> consultarNota(String expediente) {
        List<Alumno> alumnos = almacen.leerAlumnos();
        for (Alumno a : alumnos) {
            if (a.getExpediente().equals(expediente)) {
                return a.getNotas();
            }
        }
        return null;
    }

    public List<Alumno> consultarTodasLasNotas() {
        return almacen.leerAlumnos();
    }
}
