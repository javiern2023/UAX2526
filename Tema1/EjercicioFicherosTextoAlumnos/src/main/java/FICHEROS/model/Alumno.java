package FICHEROS.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alumno {
    private String expediente;
    private String nombre;
    private String apellidos;
    private double nota;
}
