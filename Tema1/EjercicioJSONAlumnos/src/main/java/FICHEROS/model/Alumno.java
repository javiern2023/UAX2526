package FICHEROS.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alumno {
    private String expediente;
    private String nombre;
    private String apellidos;
    private List<Double> notas;

    @Override
    public String toString() {
        return expediente + " - " + nombre + " " + apellidos + " -> " + notas;
    }
}
