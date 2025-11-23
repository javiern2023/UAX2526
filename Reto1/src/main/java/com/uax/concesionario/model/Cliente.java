package com.uax.concesionario.model;

public class Cliente {
    private String nombre;
    private String identificacion;
    private String telefono;
    private String correo;

    public Cliente() {
    }

    public Cliente(String nombre, String identificacion, String telefono, String correo) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return String.format("%s (DNI: %s) | Tel: %s | Email: %s",
                nombre, identificacion, telefono, correo);
    }
}
