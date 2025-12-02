package com.ejemplo.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ejemplo.model.Gato;

import java.io.FileReader;
import java.io.FileWriter;

public class GsonGatoIO {

    public void guardarGato(Gato gato, String rutaJSON) throws Exception {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();//Genera JSON con formato de saltos
        try (FileWriter writer = new FileWriter(rutaJSON)) {
            gson.toJson(gato, writer);//Convierte el objeto gato a JSON
        }
    }

    public Gato cargarGato(String rutaJSON) throws Exception {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(rutaJSON)) {
            return gson.fromJson(reader, Gato.class);//devuelve el fichero JSON para convertirlo en un objeto gato
        }
    }
}
