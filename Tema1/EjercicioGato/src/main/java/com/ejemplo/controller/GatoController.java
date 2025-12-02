package com.ejemplo.controller;


import com.ejemplo.io.XMLGatoIO;
import com.ejemplo.model.Gato;

public class GatoController {

    private final XMLGatoIO io = new XMLGatoIO();
    private final String ruta = "src/main/resources/gato.xml";

    public void guardar() throws Exception {
        Gato g = new Gato("987654321", "Garfield", "Persa");
        io.guardarGato(g, ruta);
    }

    public void mostrar() throws Exception {
        Gato g = io.cargarGato(ruta);
        System.out.println("Chip: " + g.getChip());
        System.out.println("Nombre: " + g.getNombre());
        System.out.println("Raza: " + g.getRaza());
    }
}
