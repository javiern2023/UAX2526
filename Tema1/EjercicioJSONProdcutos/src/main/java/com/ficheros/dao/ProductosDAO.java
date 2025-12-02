package com.ficheros.dao;

import com.ficheros.model.Producto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductosDAO {

    private static final String FICHERO = "src/main/resources/productos.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public List<Producto> leerProductos() throws FileNotFoundException {
        File fichero = new File(FICHERO);
        if(!fichero.exists()) return new ArrayList<>();

        try(FileReader fr = new FileReader(fichero)){
            return gson.fromJson(fr, new TypeToken<List<Producto>>(){}.getType());
        }
        catch (IOException e){
            System.out.println("Error");
            return new ArrayList<>();
        }
    }

    public void escribirProductos(List<Producto> pro) throws IOException {
        File fichero = new File(FICHERO);
        try(FileWriter fw = new FileWriter(fichero)){
            gson.toJson(pro, fw);
        }
        catch(IOException e){
            System.out.println("Error");
        }
    }
}
