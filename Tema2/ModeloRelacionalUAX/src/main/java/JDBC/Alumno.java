package JDBC;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alumno {
    private Integer id;
    private String nombre;
    private String apellidos;
    private String direccion;
    private Double nota;
}
