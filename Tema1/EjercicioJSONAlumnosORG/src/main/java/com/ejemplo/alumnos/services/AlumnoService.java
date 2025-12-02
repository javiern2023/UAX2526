package com.ejemplo.alumnos.services;

import com.ejemplo.alumnos.model.Alumno;
import com.ejemplo.alumnos.model.AlumnoList;

import java.util.Scanner;

public class AlumnoService {
    private final AlumnoList alumnoList;
    private final Scanner sc = new Scanner(System.in);

    public AlumnoService(AlumnoList alumnoList) {
        this.alumnoList = alumnoList;
    }

    public void darAlta() {
        System.out.print("Expediente: ");
        String exp = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = sc.nextLine();
        Alumno alumno = new Alumno(exp, nombre, apellidos);
        alumnoList.addAlumno(alumno);
        System.out.println("Alumno dado de alta.");
    }

    public void darBaja() {
        System.out.print("Expediente del alumno a dar de baja: ");
        String exp = sc.nextLine();
        alumnoList.removeAlumno(exp);
        System.out.println("Alumno dado de baja si existía.");
    }

    public void insertarNota() {
        System.out.print("Expediente: ");
        String exp = sc.nextLine();
        Alumno a = alumnoList.findAlumno(exp);
        if (a != null) {
            System.out.print("Nota Matemáticas: ");
            a.getNotas().setNotaMatematicas(Double.parseDouble(sc.nextLine()));
            System.out.print("Nota Programación: ");
            a.getNotas().setNotaProgramacion(Double.parseDouble(sc.nextLine()));
            System.out.print("Nota Física: ");
            a.getNotas().setNotaFisica(Double.parseDouble(sc.nextLine()));
            System.out.println("Notas insertadas.");
        } else {
            System.out.println("Alumno no encontrado.");
        }
    }

    public void modificarNota() {
        insertarNota(); // misma lógica para modificar
    }

    public void consultarNota() {
        System.out.print("Expediente: ");
        String exp = sc.nextLine();
        Alumno a = alumnoList.findAlumno(exp);
        if (a != null) {
            System.out.println(a.getNotas());
        } else {
            System.out.println("Alumno no encontrado.");
        }
    }

    public void consultarTodasNotas() {
        alumnoList.getAlumnos().forEach(a -> {
            System.out.println(a.getExpediente() + " - " + a.getNombre() + " " + a.getApellidos());
            System.out.println(a.getNotas());
            System.out.println("---------------------");
        });
    }
}
