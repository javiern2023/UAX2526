package com.ejemplo.motos.controller;

import com.ejemplo.motos.Model.Moto;
import com.ejemplo.motos.database.GestionBD;

import java.util.List;
import java.util.Scanner;

public class MotoController {

    private final Scanner sc = new Scanner(System.in);
    private final GestionBD gestionBD = new GestionBD();

    public void iniciarMenu () {
        int opcion;
        do {
            System.out.println("== MENU DE OPCIONES ==");
            System.out.println("1.- Guardar moto");
            System.out.println("2.- Mostrar todas las moto");
            System.out.println("3.- Eliminar moto");
            System.out.println("4.- Mostrar motos por marca");
            System.out.println("0.- Salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1: guardarMoto();
                    break;
                case 2: mostrarMotos();
                    break;
                case 3: eliminarMoto();
                    break;
                case 4: mostrarMotosPorMarca();
                    break;
                case 0: System.out.println("Gracias por venir, hasta pronto");
                    break;
                default: System.out.println("Opcion incorrecta");
            }
        }while(opcion != 0);
    }

    private void guardarMoto(){
        System.out.print("Dime la marca: ");
        String marca = sc.nextLine();
        System.out.print("Dime el modelo: ");
        String modelo = sc.nextLine();
        System.out.print("Dime la cilindrada: ");
        String cilindrada = sc.nextLine();
        Moto moto = new Moto(0,marca,modelo,cilindrada);
        gestionBD.insertarMoto(moto);
    }
    private void mostrarMotos(){
        List<Moto> motos = gestionBD.leerMotos();
        if(motos.isEmpty()) System.out.println("No tienes motos registradas");
        else {
            System.out.println("== LISTA DE MOTOS ==");
            for(Moto m : motos){
                System.out.println("ID: "+m.getId());
                System.out.println("MARCA: "+m.getMarca());
                System.out.println("MODELO: "+m.getModelo());
                System.out.println("CILINDRADA: "+m.getCilindrada());
            }
        }
    }
    private void eliminarMoto(){
        mostrarMotos();
        System.out.print("Dime el id de la moto a eliminar: ");
        int id = sc.nextInt();
        gestionBD.eliminarMoto(id);
    }
    private void mostrarMotosPorMarca(){
        System.out.print("Dime la marca de la moto: ");
        String marca = sc.nextLine();
        List<Moto> motos = gestionBD.leerMotosPorMarca(marca);
        if(motos.isEmpty()) System.out.println("No tienes motos registradas con esa marca");
        else {
            System.out.println("== LISTA DE MOTOS ==");
            for(Moto m : motos){
                System.out.println("ID: "+m.getId());
                System.out.println("MARCA: "+m.getMarca());
                System.out.println("MODELO: "+m.getModelo());
                System.out.println("CILINDRADA: "+m.getCilindrada());
            }
        }
    }
}
