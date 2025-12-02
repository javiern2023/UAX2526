import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FicherosXML gestor = new FicherosXML();
        gestor.inicializarFichero();
        Scanner sc = new Scanner(System.in);

        int opcion;

        do {
            System.out.println("\n========= MENÚ COCHES XML =========");
            System.out.println("1. Mostrar coches");
            System.out.println("2. Insertar coche");
            System.out.println("3. Borrar coche por marca");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

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
                    sc.nextLine(); // limpiar

                    Coche nuevo = new Coche(marca, modelo, tipo, precio);
                    gestor.insertarCoche(nuevo);
                    break;
                case 3:
                    System.out.print("Introduce la marca a eliminar: ");
                    String marcaEliminar = sc.nextLine();
                    gestor.borrarCochePorMarca(marcaEliminar);
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }

        } while (opcion != 4);
    }
}
