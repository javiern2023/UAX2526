package misCoches;

import java.io.Serializable;

public class Coche implements Serializable {
    private String marca;
    private String modelo;
    private String tipo;
    private double precio;

    public Coche(String marca, String modelo, String tipo, double precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return marca + " " + modelo + " (" + tipo + ") - " + precio + "€";
    }

    public String getMarca() {
        return this.marca;
    }
}
