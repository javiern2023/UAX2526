import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "coche")
public class Coche implements Serializable {
    private String marca;
    private String modelo;
    private String tipo;
    private double precio;

    // Constructor vacío requerido por JAXB
    public Coche() {}

    // Constructor con todos los campos
    public Coche(String marca, String modelo, String tipo, double precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
        this.precio = precio;
    }

    // JAXB necesita estos getters + setters públicos

    @XmlElement
    public String getMarca() {
        return marca;
    }
    //Sin etiqueta y públicos, es necesario
    public void setMarca(String marca) {
        this.marca = marca;
    }

    @XmlElement
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @XmlElement
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlElement
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return marca + " " + modelo + " (" + tipo + ") - " + precio + "€";
    }
}

