package com.supermercado.servicio;

import com.supermercado.modelo.ProductoCSV;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioFicheroProductosCSV {
    private final Path rutaFichero;

    public ServicioFicheroProductosCSV(String nombreFichero) {
        this.rutaFichero = Paths.get(nombreFichero);
    }

    public void crearFicheroSiNoExiste() throws IOException {
        if (Files.notExists(rutaFichero)) {
            Files.createFile(rutaFichero);
            // Añadimos cabecera CSV
            String cabecera = "Nombre,Precio,Cantidad" + System.lineSeparator();
            Files.write(rutaFichero, cabecera.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        }
    }

    // Guardar un producto en CSV
    public void guardarProducto(ProductoCSV producto) throws IOException {
        String linea = producto.aLineaCSV() + System.lineSeparator();
        Files.write(rutaFichero, linea.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    // Leer todos los productos desde CSV (ignorando la cabecera)
    public List<ProductoCSV> leerTodos() throws IOException {
        List<ProductoCSV> productos = new ArrayList<>();
        if (Files.notExists(rutaFichero)) return productos;

        List<String> lineas = Files.readAllLines(rutaFichero, StandardCharsets.UTF_8);
        for (int i = 1; i < lineas.size(); i++) { // Saltamos cabecera
            ProductoCSV p = ProductoCSV.desdeLineaCSV(lineas.get(i));
            if (p != null) productos.add(p);
        }
        return productos;
    }
}
