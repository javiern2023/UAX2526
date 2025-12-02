package com.ficheros.controller;
import com.ficheros.dao.ProductosDAO;
import com.ficheros.model.Producto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductosService {
    private String id, nombre, descripcion;
    private ProductosDAO pdao = new ProductosDAO();
    private List<Producto> listap = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public ProductosService () {

    }

    public void guardarProductos() throws IOException {
        listap = pdao.leerProductos();
        System.out.println("Dime el id");
        id=sc.nextLine();
        System.out.println("Dime el nombre");
        nombre=sc.nextLine();
        System.out.println("Dime la descripcion");
        descripcion=sc.nextLine();
        Producto p = new Producto(id,nombre,descripcion);
        listap.add(p);
        pdao.escribirProductos(listap);
    }

    public void leerProductos() throws FileNotFoundException {
        listap = pdao.leerProductos();

    }
}
