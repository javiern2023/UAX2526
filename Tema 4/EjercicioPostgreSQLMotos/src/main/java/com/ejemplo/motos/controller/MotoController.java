package com.ejemplo.motos.controller;

import com.ejemplo.motos.database.GestionDB;
import com.ejemplo.motos.model.Moto;

import java.util.ArrayList;
import java.util.Scanner;

public class MotoController {
    private static final Scanner sc = new Scanner(System.in);
    private static final GestionDB gdb = new GestionDB();

    public void menuOpciones(){
        int opcion;
        System.out.println("==Menú de opciones==");
        System.out.println("1.- Insertar Moto");
        System.out.println("2.- Leer motos");
        System.out.println("3.- Eliminar moto");
        System.out.println("0.- Salir");
        opcion=sc.nextInt();
        sc.nextLine(); // Consume the leftover newline character
        switch (opcion){
            case 1: insertarMoto();
                break;
            case 2: leerMotos();
                break;
            case 3: eliminarMoto();
                break;
            case 0: System.out.println("Adios");
                break;
            default: System.out.println("Opcion incorrecta");
        }
    }

    private void insertarMoto(){
        System.out.println("dime la marca:");
        String marca=sc.nextLine();
        System.out.println("dime el modelo:");
        String modelo=sc.nextLine();
        System.out.println("dime la cilindrada:");
        String cilindrada=sc.nextLine();
        Moto m = new Moto(marca, modelo, cilindrada);
        gdb.insertarMoto(m);
    }
    private void leerMotos(){
        ArrayList<Moto> listaMotos = new ArrayList<>();
        listaMotos = gdb.leerMotos();
        if (listaMotos.isEmpty()) System.out.println("No tienes motos en la base de datos");
        else {
            for(Moto m: listaMotos){
                System.out.println("La marca es :" + m.getMarca());
                System.out.println("El modelo es :" + m.getModelo());
                System.out.println("La cilindrada es :" + m.getCilindrada());
            }

        }
    }
    private void eliminarMoto() {
        System.out.println("Dime el id de la moto a eliminar");
        int id = sc.nextInt();
        gdb.eliminarMoto(id);
    }
}
