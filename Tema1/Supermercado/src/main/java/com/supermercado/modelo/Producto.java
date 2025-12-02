package com.supermercado.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Producto del supermercado (precio entero).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {
    private String nombre;
    private int precio;
    private int cantidad;

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\n" +
                "Precio: " + precio + "\n" +
                "Cantidad: " + cantidad + "\n" +
                "--------------------";
    }
}
