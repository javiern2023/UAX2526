package com.hibernate.controller;

import com.hibernate.dao.GestionBD;
import com.hibernate.model.Moto;
import jakarta.transaction.SystemException;

import java.util.List;
import java.util.Scanner;

public class AppController {
    private GestionBD gbd = new GestionBD();
    private Scanner sc = new Scanner(System.in);

    public void mostrarMenu() throws SystemException {
        int opcion;
        do {
            System.out.println("== MENÚ DE MOTOS ==");
            System.out.println("1.- Guardar moto");
            System.out.println("2.- Leer motos");
            System.out.println("3.- Eliminar moto");
            System.out.println("4.- Mostrar moto por marca");
            System.out.println("0.- Salir");
            opcion=sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1: guardarMoto();
                    break;
                case 2: mostrarMotos();
                    break;
                case 3: eliminarMoto();
                    break;
                case 4: mostrarPorMarca();
                    break;
                case 0: System.out.println("Hasta pronto");
                    break;
                default: System.out.println("Opcion incorrecta");
            }
        }while(opcion!=0);
    }

    private void guardarMoto() throws SystemException {
        System.out.println("Dime la marca: ");
        String marca = sc.nextLine();
        System.out.println("Dime el modelo: ");
        String modelo = sc.nextLine();
        System.out.println("Dime la cilindrada: ");
        String cilindrada = sc.nextLine();

        gbd.guardarMoto(new Moto(marca, modelo, cilindrada));
    }

    private void mostrarMotos(){
        List<Moto> listaMotos = gbd.leerMotos();
        if(listaMotos.isEmpty()) System.out.println("No hay motos en la base de datos");
        else {
            for (Moto m: listaMotos){
                System.out.println("___________________________");
                System.out.println("Marca: "+m.getMarca());
                System.out.println("Modelo: "+m.getModelo());
                System.out.println("Cilindrada: "+m.getCilindrada());
            }
        }
    }

    private void eliminarMoto() throws SystemException {
        //mostrarMotos();
        System.out.println("Dime el id de la moto a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();
        gbd.eliminarMoto(id);
    }

    private void mostrarPorMarca(){
        System.out.println("Dime la marca de la moto a buscar: ");
        String marca = sc.nextLine();
        Moto m = gbd.buscarPorMarca(marca);
        if (m != null){
            System.out.println("___________________________");
            System.out.println("Marca: "+m.getMarca());
            System.out.println("Modelo: "+m.getModelo());
            System.out.println("Cilindrada: "+m.getCilindrada());
        }
        else System.out.println("Esa marca no existe");
    }
}
