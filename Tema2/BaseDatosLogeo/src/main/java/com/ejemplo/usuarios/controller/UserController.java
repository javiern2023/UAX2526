package com.ejemplo.usuarios.controller;

import com.ejemplo.usuarios.database.GestionBD;
import com.ejemplo.usuarios.model.Usuario;

public class UserController {

    private GestionBD gestionBD = new GestionBD();

    public Usuario login(String usuario, String contrasena) {
        return gestionBD.login(usuario, contrasena);
    }

    public boolean registrar(Usuario user) {
        // Primero comprobar si existe
        if (gestionBD.comprobarUsuario(user.getUsuario())) {
            return false; // Ya existe
        }
        // Insertar si no existe
        return gestionBD.insertarUsuario(user);
    }
}
