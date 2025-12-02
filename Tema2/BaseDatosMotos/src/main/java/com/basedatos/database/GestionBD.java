package com.basedatos.database;

import com.basedatos.model.Moto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionBD {

    // Lista de motos en memoria
    private List<Moto> listaMotos = new ArrayList<>();

    // INSERTAR
    public void insertarMoto(Moto moto) {
        String sql = "INSERT INTO motos (marca, modelo, cilindrada) VALUES (?, ?, ?)";
        Connection conn = ConexionBD.conectar();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, moto.getMarca());
            stmt.setString(2, moto.getModelo());
            stmt.setString(3, moto.getCilindrada());
            stmt.executeUpdate();
            System.out.println("Moto guardada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar moto: " + e.getMessage());
        } finally {
            ConexionBD.desconectar(conn);
        }
    }

    // LEER
    public List<Moto> leerMotos() {
        listaMotos.clear();
        String sql = "SELECT * FROM motos";
        Connection conn = ConexionBD.conectar();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Moto moto = new Moto(
                        rs.getInt("id"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("cilindrada")
                );
                listaMotos.add(moto);
            }

        } catch (SQLException e) {
            System.out.println("Error al leer motos: " + e.getMessage());
        } finally {
            ConexionBD.desconectar(conn);
        }

        return listaMotos;
    }

    //MOSTRAR DATOS DE UNA MOTO EN CONCRETO
    public List<Moto> leerMotosPorMarca(String marcaBuscada) {
        listaMotos.clear();
        String sql = "SELECT * FROM motos WHERE marca LIKE ?";
        Connection conn = ConexionBD.conectar();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + marcaBuscada + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Moto moto = new Moto(
                            rs.getInt("id"),
                            rs.getString("marca"),
                            rs.getString("modelo"),
                            rs.getString("cilindrada")
                    );
                    listaMotos.add(moto);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar motos por marca: " + e.getMessage());
        } finally {
            ConexionBD.desconectar(conn);
        }

        return listaMotos;
    }

    // ELIMINAR
    public void eliminarMoto(int id) {
        String sql = "DELETE FROM motos WHERE id = ?";
        Connection conn = ConexionBD.conectar();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Moto eliminada correctamente.");
            } else {
                System.out.println("No se encontró una moto con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar moto: " + e.getMessage());
        } finally {
            ConexionBD.desconectar(conn);
        }
    }

    public List<Moto> getListaMotos() {
        return listaMotos;
    }
}
