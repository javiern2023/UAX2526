package com.ejemplo.motos.services;

import com.ejemplo.motos.io.JsonService;
import com.ejemplo.motos.model.moto;
import com.ejemplo.motos.model.motoList;

import java.io.IOException;
import java.util.Scanner;

public class motoService {

    private final motoList lista;
    private final Scanner sc = new Scanner(System.in);
    private final JsonService jsonService;

    public motoService(motoList lista, JsonService jsonService){
        this.lista=lista;
        this.jsonService=jsonService;
    }

    public void añadirMoto() throws IOException {
        System.out.println("Marca: ");
        String marca = sc.nextLine();
        System.out.println("Modelo: ");
        String modelo = sc.nextLine();
        System.out.println("Marca: ");
        int cilindrada = sc.nextInt();
        lista.addMoto(new moto(marca, modelo, cilindrada));
        jsonService.guardar(lista);
    }

    public void eliminarMoto() throws IOException {
        System.out.println("Modelo: ");
        String modelo = sc.nextLine();
        lista.removeMoto(modelo);
        jsonService.guardar(lista);
    }
    public void mostrarMotos(){
        if(lista.getMotos().isEmpty()){
            System.out.println("No hay motos en el fichero");
        }
        else {
            for(moto m : lista.getMotos()){
                System.out.println(m);
            }
        }
    }
}
