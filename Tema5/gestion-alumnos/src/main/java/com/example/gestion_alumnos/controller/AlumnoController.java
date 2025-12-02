package com.example.gestion_alumnos.controller;
// Clase que recibe las peticiones HTTP desde Postman o desde un navegador

import com.example.gestion_alumnos.model.Alumno;
import com.example.gestion_alumnos.service.AlumnoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/* @ RestController. Le indica a Spring que esta clase puede recibir peticiones REST.
 Todo lo que devuelva se convierte automáticamente en JSON*/

// @RequestMapping. Indica que todas las rutas empiezan por /alumnos.
@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    private final AlumnoService service;

    public AlumnoController(AlumnoService service) {
        this.service = service;
    }

    //Post. Se envía un JSON.
    @PostMapping("/alta")
    public Alumno alta(@RequestBody Alumno alumno) { // Convierte el JSON en un alumno.
        return service.altaAlumno(alumno); // Devuelve el alumno guardado.
    }

    // Delete.
    @DeleteMapping("/baja/{expediente}")
    public String baja(@PathVariable int expediente) { //Toma el número de la URL
        if (service.bajaAlumno(expediente)) {
            return "Alumno eliminado";
        }
        return "Alumno no encontrado";
    }

    @PutMapping("/nota/{expediente}/{nota}")
    public String insertarNota(@PathVariable int expediente, @PathVariable double nota) {
        if (service.insertarNota(expediente, nota)) {
            return "Nota actualizada";
        }
        return "Alumno no encontrado";
    }

    @GetMapping("/nota/{expediente}")
    public Optional<Alumno> consultar(@PathVariable int expediente) {
        return service.consultarNota(expediente);
    }

    @GetMapping("/todas")
    public List<Alumno> todas() {
        return service.consultarTodasLasNotas();
    }
}
