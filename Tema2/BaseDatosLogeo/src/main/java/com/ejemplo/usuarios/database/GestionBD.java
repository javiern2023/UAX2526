package com.ejemplo.usuarios.database;

import com.ejemplo.usuarios.model.Usuario;

import java.sql.*;

public class GestionBD {

    public boolean comprobarUsuario(String usuario) {
        try (Connection conn = ConexionBD.getConnection()) {
            String sql = "SELECT usuario FROM usuarios WHERE usuario = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true si existe
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertarUsuario(Usuario user) {
        try (Connection conn = ConexionBD.getConnection()) {
            String sql = "INSERT INTO usuarios (usuario, contrasena, nombre, apellidos, movil) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUsuario());
            stmt.setString(2, user.getContrasena());
            stmt.setString(3, user.getNombre());
            stmt.setString(4, user.getApellidos());
            stmt.setString(5, user.getMovil());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("ERROR insertando usuario: " + e.getMessage());
            return false;
        }
    }

    public Usuario login(String usuario, String contrasena) {
        try (Connection conn = ConexionBD.getConnection()) {
            String sql = "SELECT * FROM usuarios WHERE usuario = ? AND contrasena = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getString("usuario"),
                        rs.getString("contrasena"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("movil")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Login incorrecto
    }
}
