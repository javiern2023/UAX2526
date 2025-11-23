package com.uax.concesionario.model;

//Representa la venta de un coche.
//Almacena la información de la transacción, vinculando un coche vendido con el cliente comprador y la fecha de venta.

public class VentaCoche {
    private int id;
    private Coche coche;
    private Cliente cliente;
    private String fechaVenta;

    public VentaCoche() {
    }

    public VentaCoche(int id, Coche coche, Cliente cliente, String fechaVenta) {
        this.id = id;
        this.coche = coche;
        this.cliente = cliente;
        this.fechaVenta = fechaVenta;
    }

    public VentaCoche(Coche coche, Cliente cliente, String fechaVenta) {
        this.coche = coche;
        this.cliente = cliente;
        this.fechaVenta = fechaVenta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Coche getCoche() {
        return coche;
    }

    public void setCoche(Coche coche) {
        this.coche = coche;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    @Override
    public String toString() {
        return String.format("VENTA #%d - Fecha: %s \nCoche: %s\nCliente: %s",
                id, fechaVenta, coche.toString(), cliente.toString());
    }
}
