package com.ejemplo.alumnos.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Alumno {

    @Id
    @GeneratedValue
    private Long id;

    private String expediente;
    private String nombre;
    private String apellidos;
}
