package com.uax.concesionario.model;

public class Coche {
    private String id;
    private String marca;
    private String modelo;
    private int anioFabricacion;
    private double precio;
    private String color;
    private String matricula;

    public Coche() {
    }

    public Coche(String id, String marca, String modelo, int anioFabricacion, double precio, String color,
            String matricula) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.anioFabricacion = anioFabricacion;
        this.precio = precio;
        this.color = color;
        this.matricula = matricula;
    }

    public Coche(String marca, String modelo, int anioFabricacion, double precio, String color) {
        this.marca = marca;
        this.modelo = modelo;
        this.anioFabricacion = anioFabricacion;
        this.precio = precio;
        this.color = color;
        this.matricula = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnioFabricacion() {
        return anioFabricacion;
    }

    public void setAnioFabricacion(int anioFabricacion) {
        this.anioFabricacion = anioFabricacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | %s %s (%d) | Color: %s | Precio: %.2f€%s",
                id, marca, modelo, anioFabricacion, color, precio,
                (matricula != null && !matricula.isEmpty()) ? " | Matrícula: " + matricula : "");
    }
}
