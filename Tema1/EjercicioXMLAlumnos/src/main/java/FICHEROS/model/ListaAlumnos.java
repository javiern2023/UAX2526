package FICHEROS.model;
// Esta clase sirve para que JAXB pueda serializar una lista de alumnos correctamente
import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Data
@XmlRootElement(name = "alumnos")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaAlumnos {

    @XmlElement(name = "alumno")
    private List<Alumno> alumnos = new ArrayList<>();
}
