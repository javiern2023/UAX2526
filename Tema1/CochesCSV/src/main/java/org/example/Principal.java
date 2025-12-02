package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Principal {
    public static void main(String[] args) {
        Controller c = new Controller();

        // Mostrar coches actuales
        System.out.println("Listado actual de coches:");
        c.mostrarCoches();

        // Insertar nuevo coche
       Coche nuevo = new Coche("Ford", "Focus", "Hatchback", 18000);
        c.insertarCoche(nuevo);

        // Borrar coche por modelo
        c.borrarCochePorModelo("Civic");

        // Mostrar coches después de cambios
        System.out.println("\nListado actualizado de coches:");
        c.mostrarCoches();
    }
}