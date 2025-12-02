package com.ejemplo.motos.database;

import com.ejemplo.motos.model.Moto;

import java.sql.*;
import java.util.ArrayList;

public class GestionDB {

    public void insertarMoto(Moto m){
        String sql = "INSERT INTO motos (marca, modelo, cilindrada) VALUES (?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,m.getMarca());
            ps.setString(2,m.getModelo());
            ps.setString(3,m.getCilindrada());
            ps.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("Error al insertar la moto en la base de datos");
        }

    }

    public ArrayList<Moto> leerMotos (){
        ArrayList<Moto> listaMotos = new ArrayList<>();
        String sql= "SELECT * FROM motos";
        try (Connection conn = ConexionDB.getConnection()){
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Moto m = new Moto(
                    rs.getInt("id"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getString("cilindrada")
                );
                listaMotos.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Error al devolver las motos");
        }
        return listaMotos;
    }

    public void eliminarMoto(int id) throws SQLException {
        String sql = "DELETE FROM motos WHERE id = ?";
        try(Connection conn = ConexionDB.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("Error a la hora de eliminar la moto");
        }
    }

    public Moto obtenerMotoPorId(int id){
        Moto m = null;
        String sql= "SELECT * FROM motos WHERE id = ?";
        try (Connection conn = ConexionDB.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next()){
                m = new Moto(
                        rs.getInt("id"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("cilindrada")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al devolver las motos");
        }
        return m;
    }
}
