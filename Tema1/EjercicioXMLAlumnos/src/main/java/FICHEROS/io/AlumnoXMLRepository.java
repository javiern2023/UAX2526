package FICHEROS.io;

import FICHEROS.model.Alumno;
import FICHEROS.model.ListaAlumnos;

import javax.xml.bind.*;
        import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AlumnoXMLRepository {

    private static final String FICHERO = "src/main/resources/alumnos.xml";

    public List<Alumno> leerAlumnos() {
        File f = new File(FICHERO);
        if (!f.exists()) return new ArrayList<>();

        try {
            JAXBContext context = JAXBContext.newInstance(ListaAlumnos.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ListaAlumnos lista = (ListaAlumnos) unmarshaller.unmarshal(f);
            return lista.getAlumnos();
        } catch (JAXBException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void guardarAlumnos(List<Alumno> alumnos) {
        try {
            JAXBContext context = JAXBContext.newInstance(ListaAlumnos.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            ListaAlumnos lista = new ListaAlumnos();
            lista.setAlumnos(alumnos);

            marshaller.marshal(lista, new File(FICHERO));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
