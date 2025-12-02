package com.ejemplo.alumnos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alumno implements Serializable {
    private String expediente;
    private String nombre;
    private String apellidos;
    private Notas notas = new Notas();

    //Lo uso más adelante sin todos los campos
    public Alumno(String expediente, String nombre, String apellidos) {
        this.expediente = expediente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.notas = new Notas(); // inicializamos el objeto anidado
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Notas implements Serializable {
        private Double notaMatematicas;
        private Double notaProgramacion;
        private Double notaFisica;
    }
}
