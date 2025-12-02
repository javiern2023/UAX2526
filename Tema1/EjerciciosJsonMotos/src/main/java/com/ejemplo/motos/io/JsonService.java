package com.ejemplo.motos.io;

import com.ejemplo.motos.model.moto;
import com.ejemplo.motos.model.motoList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class JsonService {

    private final String filePath;
    private final Gson gson;

    public JsonService(String filePath){
        this.filePath=filePath;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void guardar(motoList lista) throws IOException {
        try(Writer w = new FileWriter(filePath)){
            gson.toJson(lista.getMotos(), w);
            System.out.println("Moto guardada correctamente en el fichero");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public motoList leer() throws IOException {
        motoList lista = new motoList();
        try(Reader r = new FileReader(filePath)){
            moto[] motos = gson.fromJson(r, moto[].class);
            if(motos != null){
                for(moto m: motos){
                    lista.addMoto(m);
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return lista;
    }
}
