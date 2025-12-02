package FICHEROS.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
        import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//JAXB: API que permite convertir objetos a XML y viceversa
@XmlRootElement(name = "alumno")
@XmlAccessorType(XmlAccessType.FIELD) //Accede a los campos sin usar getter y setters
public class Alumno {

    @XmlElement
    private String expediente;

    @XmlElement
    private String nombre;

    @XmlElement
    private String apellidos;

    @XmlElementWrapper(name = "notas")
    @XmlElement(name = "nota")
    private List<Double> notas;

    @Override
    public String toString() {
        return expediente + " - " + nombre + " " + apellidos + " -> " + notas;
    }
}
