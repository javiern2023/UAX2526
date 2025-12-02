package com.example.gestion_alumnos.repository;

import org.springframework.data.jpa.repository.JpaRepository; //Spring Data
import com.example.gestion_alumnos.model.Alumno;
import org.springframework.stereotype.Repository;

/*Esto permite:
    1.- Guardar alumnos
    2.- Borrarlos
    3.- Buscar por expediente
    4.- Listar todos
    5.- Modificar notas*/

// Indica a Spring que esta interfaz forma parte de la capa de acceso a datos.
@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
}
