package com.ejemplo.motos.model;

public class Moto {
    private int id;
    private String marca;
    private String modelo;
    private String cilindrada;

    // Constructor con todos los campos
    public Moto(int id, String marca, String modelo, String cilindrada) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.cilindrada = cilindrada;
    }

    // Constructor sin id porque se autogenera
    public Moto(String marca, String modelo, String cilindrada) {
        this.marca = marca;
        this.modelo = modelo;
        this.cilindrada = cilindrada;
    }

    // Constructor vacío
    public Moto() {
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(String cilindrada) {
        this.cilindrada = cilindrada;
    }
}
