package com.ejemplo.motos.database;

import com.ejemplo.motos.Model.Moto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionBD {

    private List<Moto> listaMotos = new ArrayList<>();

    public void insertarMoto(Moto m){
        String sql = "INSERT INTO motos (marca, modelo, cilindrada) VALUES (?, ?, ?)";
        Connection conn = ConexionBD.conectar();

        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, m.getMarca());
            stmt.setString(2, m.getModelo());
            stmt.setString(3, m.getCilindrada());
            stmt.executeUpdate();
            System.out.println("Insertada la moto en la base de datos");
        }
        catch (SQLException e){
            System.out.println("Error al insertar en la base de datos");
        }
        finally {
            ConexionBD.desconectar(conn);
        }
    }

    public List<Moto> leerMotos(){
        listaMotos.clear();
        String sql = "SELECT * FROM motos";
        Connection conn = ConexionBD.conectar();

        try (Statement st = conn.createStatement()){
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                Moto m = new Moto(
                        rs.getInt("id"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("cilindrada")
                );
                listaMotos.add(m);
            }
        }
        catch (SQLException e){
            System.out.println("Error al leer de la base de datos");
        }
        finally {
            ConexionBD.desconectar(conn);
        }
        return listaMotos;
    }

    public List<Moto> leerMotosPorMarca(String marca){
        listaMotos.clear();
        String sql = "SELECT * FROM motos WHERE marca = ?";
        Connection conn = ConexionBD.conectar();
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, marca);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Moto moto = new Moto (
                        rs.getInt("id"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("cilindrada")
                );
                listaMotos.add(moto);
            }

        }
        catch (SQLException e){
            System.out.println("Error al buscar las motos por marcas");
        }
        finally {
            ConexionBD.desconectar(conn);
        }
        return listaMotos;
    }

    public void eliminarMoto(int id){
        String sql = "DELETE FROM motos WHERE id = ?";
        Connection conn = ConexionBD.conectar();

        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            int filas = stmt.executeUpdate();
            if (filas > 0) System.out.println("Moto eliminada correctamente");
            else System.out.println("No se encontró la moto a eliminar");
        }
        catch (SQLException e){
            System.out.println("Error al eliminar la moto");
        }
        finally {
            ConexionBD.desconectar(conn);
        }
    }
    public List<Moto> getListaMotos(){
        return listaMotos;
    }
}
