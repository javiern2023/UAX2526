package com.ejemplo.usuarios.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Usuario {
    private String usuario;
    private String contrasena;
    private String nombre;
    private String apellidos;
    private String movil;
}
