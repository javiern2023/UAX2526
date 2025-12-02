package com.ejemplo.motos.services;

import com.ejemplo.motos.model.moto;
import com.ejemplo.motos.model.motoList;
import java.util.Scanner;
import com.ejemplo.motos.io.JsonService;


import java.util.Scanner;

public class motoService {

    private final motoList lista;
    private final Scanner sc = new Scanner(System.in);
    private final JsonService jsonService; // agregado

    public motoService(motoList lista, JsonService jsonService) {
        this.lista = lista;
        this.jsonService = jsonService;
    }

    public void añadirMoto() {
        System.out.print("Marca: ");
        String marca = sc.nextLine();
        System.out.print("Modelo: ");
        String modelo = sc.nextLine();
        System.out.print("Cilindrada: ");
        int cilindrada = Integer.parseInt(sc.nextLine());
        lista.addMoto(new moto(marca, modelo, cilindrada));
        jsonService.guardar(lista); // guardado automático
        System.out.println("✅ Moto añadida y guardada en JSON.");
    }

    public void eliminarMoto() {
        System.out.print("Modelo a eliminar: ");
        String modelo = sc.nextLine();
        lista.removeMoto(modelo);
        jsonService.guardar(lista); // guardado automático
        System.out.println("✅ Moto eliminada y JSON actualizado.");
    }

    public void mostrarMotos() {
        if (lista.getMotos().isEmpty()) {
            System.out.println("⚠️ No hay motos registradas.");
            return;
        }
        for (moto m : lista.getMotos()) {
            System.out.println(m);
        }
    }
}
