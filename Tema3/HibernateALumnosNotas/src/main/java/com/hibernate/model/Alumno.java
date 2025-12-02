package com.hibernate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity //Esta clase representa un tabla en la base de datos.
@Table(name = "alumnos") //Nombre de la tabla. En caso de no ponerse usa el nombre de la clase.
public class Alumno {

    @Id //Clave primaria
    @Column(name = "expediente", length = 10) //Nombre de la columna
    private String expediente;

    @Column(name = "nombre", length = 50, nullable = false) //Nullable = false, quiere decir NOT NULL
    private String nombre;

    @Column(name = "apellidos", length = 100, nullable = false)
    private String apellidos;
}
/* Hibernate revisará esta clase y:
    1.- Si no existe la tabla alumnos, la creará.
    2.- Si existe pero le faltan columnas, las añadirá.
    3.- Si la tabla ya corresponde, no hace nada.
* */