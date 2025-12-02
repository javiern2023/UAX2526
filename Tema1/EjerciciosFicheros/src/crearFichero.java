import java.io.File;
import java.io.IOException;

public class crearFichero {
    public static void crearFichero() throws IOException {
        // No creamos el fichero. Sólo crea un objeto en memoria que representa la ruta.
        File archivo = new File("ejemplo.txt");

        // Creamos el fichero
        if (archivo.createNewFile()) {
            // Si el fichero no existía y se creó correctamente
            System.out.println("Fichero creado: " + archivo.getName());
        } else {
            // Si el fichero ya existe
            System.out.println("El fichero ya existe.");
        }
    }
}
