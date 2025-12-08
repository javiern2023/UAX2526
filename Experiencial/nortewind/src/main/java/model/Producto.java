package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private double precio;
}
