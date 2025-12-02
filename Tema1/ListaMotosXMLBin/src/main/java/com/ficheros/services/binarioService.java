package com.ficheros.services;


import com.ficheros.model.moto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class binarioService {

    private final String filePath;

    public binarioService(String filePath){
        this.filePath=filePath;
    }

    public void guardarBinario(List<moto> motos){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))){
            oos.writeObject(motos);
            System.out.println("Motos guardadas en el fichero binario");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public List<moto> leerBinario(){
        List<moto> motos = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))){
            motos = (List<moto>) ois.readObject();
            System.out.println("Motos leidas desde el archivo binario");
        } catch (Exception e) {
            System.out.println("No se puede acceder al fichero binario");
        }
        return motos;
    }
}
