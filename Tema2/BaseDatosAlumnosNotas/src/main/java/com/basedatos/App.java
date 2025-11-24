package com.basedatos;

import com.basedatos.controller.AppController;
import com.basedatos.database.GestionBD;

/**
 * App.java — Punto de entrada de la aplicación.
 * Aquí se inicia el programa, se crean las tablas necesarias
 * y se lanza el menú principal controlado por AppController.
 *
 * Flujo general:
 *  1) Crear instancia de GestionBD para permitir la creación de tablas.
 *  2) Llamar a crearTablasSiNoExisten() para asegurar que alumnos/notas existen.
 *  3) Crear el controlador de la app.
 *  4) Mostrar el menú y gestionar la interacción con el usuario.
 */
public class
App {
    public static void main(String[] args) {
        // 1) Aseguramos que las tablas 'alumnos' y 'notas' existan.
        //    Si no existen, se crean automáticamente mediante DDL.
        //    Esto permite ejecutar la app en una BD vacía sin errores.
        GestionBD.crearTablasSiNoExisten();

        // 2) Creamos el controlador principal, encargado del menú y la lógica de interacción.
        AppController app = new AppController();

        // 3) Lanzamos el menú que permitirá:
        //      - dar de alta/baja alumnos
        //      - insertar/modificar notas
        //      - consultar notas individuales o todas
        app.mostrarMenu();

    }
}
