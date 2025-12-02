package com.example.gestion_alumnos.service;


import com.example.gestion_alumnos.model.Alumno;
import com.example.gestion_alumnos.repository.AlumnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional; //Para evitar errores contra la base de datos

// Contiene lógica del negocio
@Service
public class AlumnoService {

    private final AlumnoRepository repository;

    //Spring inyecta automáticamente una instancia real
    public AlumnoService(AlumnoRepository repository) {
        this.repository = repository;
    }

    public Alumno altaAlumno(Alumno alumno) {
        return repository.save(alumno); //Insertar o actualizar.
    }

    public boolean bajaAlumno(int expediente) {
        if (repository.existsById(expediente)) {
            repository.deleteById(expediente);
            return true;
        }
        return false;
    }

    public boolean insertarNota(int expediente, double nota) {
        // Optional es una clase de Java que envuelve un valor que puede o no existir.
        // Evitamos el error NullPointerException
        Optional<Alumno> opt = repository.findById(expediente);
        if (opt.isPresent()) {  //Si existe
            Alumno a = opt.get(); // Lo obtiene
            a.setNota(nota); // Cambia su nota en el objeto
            repository.save(a); // Lo guarda
            return true; // Devuelve true si lo encontró.
        }
        return false;
    }

    public boolean modificarNota(int expediente, double nuevaNota) {
        return insertarNota(expediente, nuevaNota);
    }

    public Optional<Alumno> consultarNota(int expediente) {
        return repository.findById(expediente);
    }

    public List<Alumno> consultarTodasLasNotas() {
        return repository.findAll();
    }
}
