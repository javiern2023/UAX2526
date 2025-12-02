package com.ficheros;

import com.ficheros.model.moto;
import com.ficheros.model.motoList;
import com.ficheros.services.binarioService;
import com.ficheros.services.xmlService;

import jakarta.xml.bind.JAXBException;
import java.util.*;

public class App {

    private static final String BIN_PATH = "src/main/resources/motos.bin";
    private static final String XML_PATH = "src/main/resources/motos.xml";

    public static void main(String[] args) throws JAXBException {
       Scanner sc = new Scanner(System.in);
        binarioService binService = new binarioService(BIN_PATH);
        xmlService xmlService = new xmlService(XML_PATH);

        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE MOTOS ===");
            System.out.println("1. Guardar motos en fichero BINARIO");
            System.out.println("2. Mostrar motos desde fichero BINARIO");
            System.out.println("3. Guardar motos en fichero XML");
            System.out.println("4. Mostrar motos desde fichero XML");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar salto de línea

            switch (opcion) {
                case 1:
                    List<moto> motosBin = pedirMotos(sc);
                    binService.guardarBinario(motosBin);
                    break;
                case 2:
                    List<moto> motosLeidasBin = binService.leerBinario();
                    motosLeidasBin.forEach(System.out::println);
                    break;
                case 3:
                    motoList motosXml = new motoList();
                    motosXml.setMotos(pedirMotos(sc));
                    xmlService.guardarXml(motosXml);
                    break;
                case 4:
                    motoList motosLeidasXml = xmlService.leerXml();
                    motosLeidasXml.getMotos().forEach(System.out::println);
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }

    private static List<moto> pedirMotos(Scanner sc) {
        List<moto> motos = new ArrayList<>();
        String continuar;
        do {
            System.out.print("Marca: ");
            String marca = sc.nextLine();
            System.out.print("Modelo: ");
            String modelo = sc.nextLine();
            System.out.print("Cilindrada: ");
            int cilindrada = sc.nextInt();
            sc.nextLine();
            motos.add(new moto(marca, modelo, cilindrada));

            System.out.print("¿Añadir otra moto? (s/n): ");
            continuar = sc.nextLine();
        } while (continuar.equalsIgnoreCase("s"));
        return motos;
    }
}
