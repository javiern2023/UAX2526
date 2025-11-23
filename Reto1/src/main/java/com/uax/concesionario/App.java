package com.uax.concesionario;

import com.uax.concesionario.controller.ConcesionarioController;

//clase de entrada (entry point) del program
public class App {

    public static void main(String[] args) {
        // Instancia controller que gestiona la interfaz de usuario
        ConcesionarioController controller = new ConcesionarioController();

        // Iniciar el menú
        controller.iniciarMenu();
    }
}
