import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    private final String nombreFichero = "Coches.csv";

    public List<Coche> leerCoches() {
        List<Coche> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
            String linea;
            br.readLine(); // Saltar encabezado
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    String marca = partes[0].trim();
                    String modelo = partes[1].trim();
                    String tipo = partes[2].trim();
                    double precio = Double.parseDouble(partes[3].trim());

                    lista.add(new Coche(marca, modelo, tipo, precio));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void mostrarCoches() {
        List<Coche> coches = leerCoches();
        coches.forEach(System.out::println);
    }

    public void insertarCoche(Coche nuevo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(nombreFichero, true))) {
            pw.printf("%s,%s,%s,%.2f%n",
                    nuevo.getMarca(),
                    nuevo.getModelo(),
                    nuevo.getTipo(),
                    nuevo.getPrecio());
            System.out.println("Coche insertado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void borrarCochePorModelo(String modeloAEliminar) {
        List<Coche> coches = leerCoches();

        List<Coche> actualizados = coches.stream()
                .filter(c -> !c.getModelo().equalsIgnoreCase(modeloAEliminar))
                .collect(Collectors.toList());

        try (PrintWriter pw = new PrintWriter(new FileWriter(nombreFichero))) {
            pw.println("marca,modelo,tipo,precio"); // Encabezado
            for (Coche c : actualizados) {
                pw.printf("%s,%s,%s,%.2f%n",
                        c.getMarca(),
                        c.getModelo(),
                        c.getTipo(),
                        c.getPrecio());
            }
            System.out.println("Coche(s) borrado(s) correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

