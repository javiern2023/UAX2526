package misCoches;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FicheroBinario {
    private final String nombreFichero = "data/coches.bin";

    public void inicializarFichero() {
        try {
            File carpeta = new File("data");
            if (!carpeta.exists()) {
                if (carpeta.mkdirs()) {
                    System.out.println("Carpeta 'data' creada.");
                }
            }

            File fichero = new File(nombreFichero);
            if (!fichero.exists()) {
                // Crear archivo vacío con lista vacía
                try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fichero))) {
                    ObjectOutputStream oos = new ObjectOutputStream(bos);
                    oos.writeObject(new ArrayList<Coche>());
                    oos.flush();
                    System.out.println("Archivo 'coches.bin' creado.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void escribirCoches(List<Coche> coches) {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(nombreFichero))) {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(coches);
            oos.flush();
            System.out.println("Coches guardados correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Coche> leerCoches() {
        List<Coche> coches = new ArrayList<>();
        File file = new File(nombreFichero);
        if (!file.exists()) return coches;

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(nombreFichero))) {
            ObjectInputStream ois = new ObjectInputStream(bis);
            coches = (List<Coche>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return coches;
    }

    public void borrarCochePorMarca(String marca) {
        List<Coche> coches = leerCoches();

        List<Coche> actualizados = coches.stream()
                .filter(c -> !c.getMarca().equalsIgnoreCase(marca))
                .collect(Collectors.toList());

        if (actualizados.size() == coches.size()) {
            System.out.println("No se encontró ningún coche con la marca: " + marca);
        } else {
            escribirCoches(actualizados);
            System.out.println("Coche(s) de marca '" + marca + "' borrado(s) correctamente.");
        }
    }

    public void borrarCoches() {
        File file = new File(nombreFichero);
        if (file.exists() && file.delete()) {
            System.out.println("Archivo binario eliminado correctamente.");
        } else {
            System.out.println("No se pudo eliminar el archivo.");
        }
    }
}
