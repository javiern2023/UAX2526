package com.uax.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alumno {
    private String expediente;
    private String nombre;
    private String apellidos;
    private Double nota;
}
