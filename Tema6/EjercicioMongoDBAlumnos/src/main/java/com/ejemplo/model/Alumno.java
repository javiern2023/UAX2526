package com.ejemplo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alumno {
    private String expediente; // identificador único
    private String nombre;
    private String apellidos;
    private Double nota; // puede ser null si no tiene nota todavía
}
