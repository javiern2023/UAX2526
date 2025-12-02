package misCoches;
import java.util.ArrayList;
import java.util.List;

public class App 
{
    public static void main( String[] args ) {
        FicheroBinario gestor = new FicheroBinario();

        gestor.inicializarFichero();

        // Crear lista de coches
        List<Coche> coches = new ArrayList<>();
        coches.add(new Coche("Toyota", "Corolla", "Sedán", 18000));
        coches.add(new Coche("BMW", "X5", "SUV", 45000));

        // Guardar coches
        gestor.escribirCoches(coches);

        // Leer coches
        List<Coche> leidos = gestor.leerCoches();
        System.out.println("Listado de coches leídos:");
        leidos.forEach(System.out::println);

        // Borrar fichero
        // gestor.borrarCoches();

    }
}