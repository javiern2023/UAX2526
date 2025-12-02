package FICHEROS.services;


import FICHEROS.io.AlumnoCsvRepository;
import FICHEROS.model.Alumno;

import java.util.List;

public class AlumnoServices {

    private final AlumnoCsvRepository almacen;

    public AlumnoServices(AlumnoCsvRepository almacen) {
        this.almacen = almacen;
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
        alumnos.stream()
                .filter(a -> a.getExpediente().equals(expediente))
                .findFirst()
                .ifPresent(a -> a.getNotas().add(nota));
        almacen.guardarAlumnos(alumnos);
    }

    public void modificarNota(String expediente, int indice, double nuevaNota) {
        List<Alumno> alumnos = almacen.leerAlumnos();
        alumnos.stream()
                .filter(a -> a.getExpediente().equals(expediente))
                .findFirst()
                .ifPresent(a -> {
                    if (indice >= 0 && indice < a.getNotas().size()) {
                        a.getNotas().set(indice, nuevaNota);
                    }
                });
        almacen.guardarAlumnos(alumnos);
    }

    public List<Double> consultarNota(String expediente) {
        return almacen.leerAlumnos().stream()
                .filter(a -> a.getExpediente().equals(expediente))
                .findFirst()
                .map(Alumno::getNotas)
                .orElse(null);
    }

    public List<Alumno> consultarTodasLasNotas() {
        return almacen.leerAlumnos();
    }
}
