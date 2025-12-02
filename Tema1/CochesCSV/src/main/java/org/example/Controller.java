package org.example;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    private final String nombreFichero = "data/Coches.csv";

    public List<Coche> leerCoches() {
        List<Coche> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
            String linea = br.readLine(); // Saltar encabezado
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    String marca = partes[0].trim();
                    String modelo = partes[1].trim();
                    String tipo = partes[2].trim();
                    String precioStr = partes[3].trim().replace(",", ".");
                    double precio = Double.parseDouble(precioStr);

                    lista.add(new Coche(marca, modelo, tipo, precio));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void mostrarCoches() {
        List<Coche> coches = leerCoches();
        coches.forEach(System.out::println);
    }

    public void insertarCoche(Coche nuevo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(nombreFichero, true))) {
            // Convertimos el precio a formato con coma decimal
            String precioStr = String.format("%.2f", nuevo.getPrecio()).replace(".", ",");

            // Escribimos con punto y coma, y el precio formateado
            pw.printf("%s;%s;%s;%s%n",
                    nuevo.getMarca(),
                    nuevo.getModelo(),
                    nuevo.getTipo(),
                    precioStr);
            System.out.println("Coche insertado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void borrarCochePorModelo(String modeloAEliminar) {
        List<Coche> coches = leerCoches();

        List<Coche> actualizados = coches.stream()
                .filter(c -> !c.getModelo().equalsIgnoreCase(modeloAEliminar))
                .collect(Collectors.toList());

        try (PrintWriter pw = new PrintWriter(new FileWriter(nombreFichero))) {
            pw.println("marca;modelo;tipo;precio"); // Encabezado con ;
            for (Coche c : actualizados) {
                // Formateamos el precio con coma decimal
                String precioStr = String.format("%.2f", c.getPrecio()).replace(".", ",");
                pw.printf("%s;%s;%s;%s%n",
                        c.getMarca(),
                        c.getModelo(),
                        c.getTipo(),
                        precioStr);
            }
            System.out.println("Coche(s) borrado(s) correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

