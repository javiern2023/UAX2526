package docuJSON;

import java.util.Scanner;

public class App {
    public static void main( String[] args ) {

        GestorJSON gestor = new GestorJSON();
        Scanner sc = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\n====== MENÚ COCHES JSON ======");
            System.out.println("1. Mostrar coches");
            System.out.println("2. Insertar coche");
            System.out.println("3. Borrar por marca");
            System.out.println("4. Salir");
            System.out.print("Elige opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpia buffer

            switch (opcion) {
                case 1:
                    gestor.mostrarCoches();
                    break;
                case 2:
                    System.out.print("Marca: ");
                    String marca = sc.nextLine();
                    System.out.print("Modelo: ");
                    String modelo = sc.nextLine();
                    System.out.print("Tipo: ");
                    String tipo = sc.nextLine();
                    System.out.print("Precio: ");
                    double precio = sc.nextDouble();
                    sc.nextLine();

                    Coche coche = new Coche(marca, modelo, tipo, precio);
                    gestor.insertarCoche(coche);
                    break;
                case 3:
                    System.out.print("Marca a eliminar: ");
                    String marcaBorrar = sc.nextLine();
                    gestor.borrarPorMarca(marcaBorrar);
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 4);
    }
}

