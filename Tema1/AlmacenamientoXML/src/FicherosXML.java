import javax.xml.bind.*;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class FicherosXML {
    private final String rutaArchivo = "data/coches.xml";

    public void inicializarFichero() {
        File carpeta = new File("data");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            guardarCoches(new ListaCoches());
        }
    }

    public ListaCoches leerCoches() {
        try {
            //Para trabajar con ficheros XML
            JAXBContext context = JAXBContext.newInstance(ListaCoches.class);
            //Objeto que servirá para convertir XML en objetos Java
            Unmarshaller unmarshaller = context.createUnmarshaller();
            //Lee el archivo XML y lo convierte en objeto Java
            return (ListaCoches) unmarshaller.unmarshal(new File(rutaArchivo));
        } catch (Exception e) {
            e.printStackTrace();
            return new ListaCoches();
        }
    }

    public void guardarCoches(ListaCoches lista) {
        try {
            JAXBContext context = JAXBContext.newInstance(ListaCoches.class);
            //Objeto para convertir objetos Java en XML
            Marshaller marshaller = context.createMarshaller();
            //Indica si el XML generado está bien formateado
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //Coge la lista y la escribe en esa ruta
            marshaller.marshal(lista, new File(rutaArchivo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertarCoche(Coche nuevo) {
        ListaCoches lista = leerCoches();
        lista.getCoches().add(nuevo);
        guardarCoches(lista);
        System.out.println("Coche insertado correctamente.");
    }

    public void mostrarCoches() {
        ListaCoches lista = leerCoches();
        if (lista.getCoches().isEmpty()) {
            System.out.println("No hay coches guardados.");
        } else {
            System.out.println("Lista de coches:");
            lista.getCoches().forEach(System.out::println);
        }
    }

    public void borrarCochePorMarca(String marca) {
        ListaCoches lista = leerCoches();
        List<Coche> filtrados = lista.getCoches().stream()
                .filter(c -> !c.getMarca().equalsIgnoreCase(marca))
                .collect(Collectors.toList());

        if (filtrados.size() == lista.getCoches().size()) {
            System.out.println("No se encontraron coches con la marca: " + marca);
        } else {
            lista.setCoches(filtrados);
            guardarCoches(lista);
            System.out.println("Coches con marca '" + marca + "' borrados correctamente.");
        }
    }
}
