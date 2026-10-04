package modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona implements Serializable {

    //identificador de versión de la clase.
    private static final long serialVersionUID = 1L;

    private String nombre;
    private int edad;
    private String ciudad;
}