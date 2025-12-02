package com.supermercado.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoCSV {
    private String nombre;
    private int precio;
    private int cantidad;

    // Convierte el producto en una línea CSV
    public String aLineaCSV() {
        return nombre + "," + precio + "," + cantidad;
    }

    // Crea un producto a partir de una línea CSV
    public static ProductoCSV desdeLineaCSV(String linea) {
        if (linea == null || linea.isEmpty()) return null;
        String[] partes = linea.split(",");
        if (partes.length != 3) return null;
        try {
            String nombre = partes[0].trim();
            int precio = Integer.parseInt(partes[1].trim());
            int cantidad = Integer.parseInt(partes[2].trim());
            return new ProductoCSV(nombre, precio, cantidad);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
