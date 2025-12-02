package com.ejemplo.alumnos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class AlumnoList {
    private List<Alumno> alumnos = new ArrayList<>();

    // Añadir un alumno
    public void addAlumno(Alumno alumno) {
        alumnos.add(alumno);
    }

    // Dar de baja un alumno por expediente
    public void removeAlumno(String expediente) {
        // Usamos un bucle tradicional para buscar y eliminar
        for (int i = 0; i < alumnos.size(); i++) {
            if (alumnos.get(i).getExpediente().equals(expediente)) {
                alumnos.remove(i);
                break; // salimos del bucle al eliminar
            }
        }
    }

    // Buscar un alumno por expediente
    public Alumno findAlumno(String expediente) {
        for (int i = 0; i < alumnos.size(); i++) {
            if (alumnos.get(i).getExpediente().equals(expediente)) {
                return alumnos.get(i);
            }
        }
        return null; // no encontrado
    }
}
