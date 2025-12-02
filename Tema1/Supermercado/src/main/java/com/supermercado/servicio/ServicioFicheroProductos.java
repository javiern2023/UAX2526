
package com.supermercado.servicio;

import com.supermercado.modelo.Producto;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
        import java.util.List;

/**
 * Servicio para trabajar con fichero de texto (.txt)
 */
public class ServicioFicheroProductos {
    private final Path rutaFichero;

    public ServicioFicheroProductos(String nombreFichero) {
        this.rutaFichero = Paths.get(nombreFichero);
    }

    public void crearFicheroSiNoExiste() throws IOException {
        if (Files.notExists(rutaFichero)) {
            Files.createFile(rutaFichero);
        }
    }

    public void mostrarContenido() throws IOException {
        if (Files.notExists(rutaFichero) || Files.size(rutaFichero) == 0) {
            System.out.println("No hay productos registrados.");
            return;
        }
        List<String> lineas = Files.readAllLines(rutaFichero, StandardCharsets.UTF_8);
        for (String linea : lineas) {
            System.out.println(linea);
        }
    }

    public void guardarProducto(Producto producto) throws IOException {
        // Java 8: usar Files.write() con getBytes()
        String texto = producto.toString() + System.lineSeparator();
        Files.write(rutaFichero, texto.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
