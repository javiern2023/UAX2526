package com.ejemplo.alumnos;

import com.ejemplo.alumnos.controller.AlumnoController;
import com.ejemplo.alumnos.database.ConexionDB;
import com.ejemplo.alumnos.model.Alumno;

import java.util.List;

public class App {
    public static void main(String[] args) {

        AlumnoController controller = new AlumnoController();

        // Crear alumno
        Alumno nuevo = new Alumno(null, "EXP500", "Luis", "Martínez Soto");
        controller.crear(nuevo);
        System.out.println("Creado: " + nuevo);

        // Listar
        List<Alumno> lista = controller.listarTodos();
        System.out.println("Listado:");
        for (Alumno a : lista) {
            System.out.println(a);
        }

        // Buscar por id
        Alumno encontrado = controller.obtenerPorId(nuevo.getId());
        System.out.println("Encontrado: " + encontrado);

        // Actualizar
        encontrado.setNombre("Luis Miguel");
        controller.actualizar(encontrado);
        System.out.println("Actualizado: " + encontrado);

        // Eliminar
        controller.eliminar(encontrado.getId());
        System.out.println("Eliminado id: " + encontrado.getId());

        ConexionDB.close(); // cerrar fábrica JPA
    }
}
