package com.ejemplo.motos.controller;

import com.ejemplo.motos.database.ConexionDB;
import com.ejemplo.motos.model.Moto;

import java.sql.*;
import java.util.ArrayList;

public class MotoController {

    public void guardarMoto(Moto moto) {
        String sql = "INSERT INTO motos (marca, modelo, cilindrada) VALUES (?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, moto.getMarca());
            ps.setString(2, moto.getModelo());
            ps.setString(3, moto.getCilindrada());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al guardar moto: " + e.getMessage());
        }
    }

    public ArrayList<Moto> leerMotos() {
        ArrayList<Moto> lista = new ArrayList<>();
        String sql = "SELECT * FROM motos ORDER BY id";

        try (Connection conn = ConexionDB.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Moto moto = new Moto(
                        rs.getInt("id"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("cilindrada")
                );
                lista.add(moto);
            }

        } catch (SQLException e) {
            System.out.println("Error al leer motos: " + e.getMessage());
        }
        return lista;
    }

    public Moto obtenerMotoPorId(int id) {
        String sql = "SELECT * FROM motos WHERE id = ?";
        Moto moto = null;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                moto = new Moto(
                        rs.getInt("id"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("cilindrada")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar moto: " + e.getMessage());
        }
        return moto;
    }

    public void eliminarMoto(int id) {
        String sql = "DELETE FROM motos WHERE id = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al eliminar moto: " + e.getMessage());
        }
    }
}
