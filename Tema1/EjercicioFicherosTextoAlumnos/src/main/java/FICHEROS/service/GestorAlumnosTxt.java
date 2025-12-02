package FICHEROS.service;

import FICHEROS.io.AlmacenTxt;
import FICHEROS.model.Alumno;

import java.util.ArrayList;
import java.util.List;

public class GestorAlumnosTxt implements GestorAlumnos {

    private final AlmacenTxt almacen;

    public GestorAlumnosTxt(String rutaArchivo) {
        this.almacen = new AlmacenTxt(rutaArchivo);
    }

    @Override
    public void altaAlumno(Alumno alumno) {
        List<Alumno> alumnos = almacen.leerAlumnos();
        alumnos.add(alumno);
        almacen.guardarAlumnos(alumnos);
    }

    @Override
    public void bajaAlumno(String expediente) {
        List<Alumno> alumnos = almacen.leerAlumnos();
        alumnos.removeIf(a -> a.getExpediente().equals(expediente));
        almacen.guardarAlumnos(alumnos);
    }

    @Override
    public void insertarNota(String expediente, double nota) {
        List<Alumno> alumnos = almacen.leerAlumnos();
        for (Alumno a : alumnos) {
            if (a.getExpediente().equals(expediente)) {
                a.setNota(nota);
            }
        }
        almacen.guardarAlumnos(alumnos);
    }

    @Override
    public void modificarNota(String expediente, double nota) {
        insertarNota(expediente, nota); // misma lógica
    }

    @Override
    public Alumno consultarAlumno(String expediente) {
        //Convierte en un stream la lista de alumnos que devuelve el método
        return almacen.leerAlumnos().stream()
                //lambda. Se queda con los alumnos que tienen ese expediente
                .filter(a -> a.getExpediente().equals(expediente))
                //Devuelve el primer elemento de la lista
                .findFirst()
                //Si la anterior línea no devuelve alumno, devuelve un null
                .orElse(null);
    }

    @Override
    public List<Alumno> consultarTodos() {
        return almacen.leerAlumnos();
    }
}

