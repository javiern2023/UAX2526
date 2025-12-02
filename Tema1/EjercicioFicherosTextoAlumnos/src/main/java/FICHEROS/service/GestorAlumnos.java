package FICHEROS.service;

import FICHEROS.model.Alumno;
import java.util.List;

public interface GestorAlumnos {
    void altaAlumno(Alumno alumno);
    void bajaAlumno(String expediente);
    void insertarNota(String expediente, double nota);
    void modificarNota(String expediente, double nota);
    Alumno consultarAlumno(String expediente);
    List<Alumno> consultarTodos();
}

