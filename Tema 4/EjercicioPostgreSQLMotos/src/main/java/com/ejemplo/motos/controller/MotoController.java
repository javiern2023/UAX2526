package com.ejemplo.motos.controller;

import com.ejemplo.motos.database.GestionDB;
import com.ejemplo.motos.model.Moto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MotoController {
    private static final Scanner sc = new Scanner(System.in);
    private static final GestionDB gdb = new GestionDB();

    public void menuOpciones(){
        int opcion;
        System.out.println("==Menú de opciones==");
        System.out.println("1.- Insertar Moto");
        System.out.println("0.- Salir");
        opcion=sc.nextInt();
        switch (opcion){
            case 1: insertarMoto();
                break;
            case 2: leerMotos();
                break;
            default: System.out.println("Opcion incorrecta");
        }
    }

    private void insertarMoto(){
        System.out.println("dime la marca:");
        String marca=sc.nextLine();
        Moto m = new Moto(marca, modelo, cilindrada);
        gdb.insertarMoto(m);
    }
    private void leerMotos(){
        ArrayList<Moto> listaMotos = new ArrayList<>();
        listaMotos = gdb.leerMotos();
        if (listaMotos.isEmpty()) System.out.println("No tienes motos en la base de datos");
        else {
            for(Moto m: listaMotos){
                System.out.println("La marca es :");
            }

        }
    }
    private void eliminarMoto() throws SQLException {
        System.out.println("Dime el id de la moto a eliminar");
        int id = sc.nextInt();
        gdb.eliminarMoto(id);
    }
}
