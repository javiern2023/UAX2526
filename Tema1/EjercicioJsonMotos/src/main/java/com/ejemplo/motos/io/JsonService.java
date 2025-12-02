package com.ejemplo.motos.io;

import com.ejemplo.motos.model.motoList;
import com.ejemplo.motos.model.moto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonService {

    private final String filePath;
    private final Gson gson;

    public JsonService(String filePath) {
        this.filePath = filePath;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // Guardar lista completa en JSON
    public void guardar(motoList lista) {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(lista.getMotos(), writer);
            System.out.println("Motos guardadas en JSON.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Leer lista de JSON
    public motoList leer() {
        motoList lista = new motoList();
        if (!Files.exists(Paths.get(filePath))) {
            return lista; // archivo no existe, lista vacía
        }

        try (Reader reader = new FileReader(filePath)) {
            moto[] motos = gson.fromJson(reader, moto[].class);
            if (motos != null) {
                for (moto m : motos) {
                    lista.addMoto(m);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
