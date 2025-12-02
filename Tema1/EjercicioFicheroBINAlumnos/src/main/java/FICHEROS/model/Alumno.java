package FICHEROS.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alumno implements Serializable {

    private static final long serialVersionUID = 1L;

    private String expediente;
    private String nombre;
    private String apellidos;
    private List<Double> notas;

    @Override
    public String toString() {
        return expediente + " - " + nombre + " " + apellidos + " -> " + notas;
    }
}
