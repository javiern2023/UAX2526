package com.ficheros;

import com.ficheros.controller.ProductosService;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int opcion;
        Scanner sc = new Scanner(System.in);
        ProductosService ps = new ProductosService();

        System.out.println("==MENÚ DE OPCIONES");
        System.out.println("1.- Guardar");
        System.out.println("2.- Leer");
        System.out.println("3.- Salir");
        opcion=sc.nextInt();

        do {
            switch (opcion){
                case 1: ps.guardarProductos();
                    break;
                case 2: ps.leerProductos();
                    break;
                case 3: System.out.println("Hasta pronto");
                    break;
                default: System.out.println("Opcion incorrecta");
            }
        } while (opcion!=3);


    }
}
