package com.ficheros.services;

import com.ficheros.model.moto;
import com.ficheros.model.motoList;
import java.util.Scanner;

public class motoService {

    private final motoList motoList = new motoList();
    private final Scanner sc = new Scanner(System.in);

    public motoList getMotos() {
        return motoList;
    }

    public void agregarMoto() {
        System.out.print("Marca: ");
        String marca = sc.nextLine();
        System.out.print("Modelo: ");
        String modelo = sc.nextLine();
        System.out.print("Cilindrada: ");
        int cilindrada = Integer.parseInt(sc.nextLine());

        motoList.addMoto(new moto(marca, modelo, cilindrada));
        System.out.println("Moto añadida correctamente.");
    }

    public void mostrarMotos(motoList lista) {
        System.out.println("LISTA DE MOTOS:");
        for (moto m : lista.getMotos()) {
            System.out.println(m);
        }
        System.out.println();
    }
}
