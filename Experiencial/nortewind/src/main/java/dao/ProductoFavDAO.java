package dao;

import dao.ConexionBD;
import model.ProductoFav;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductoFavDAO {

    public void insertar(ProductoFav pf) {
        String sql = "INSERT INTO productos_fav(id_producto) VALUES(?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, pf.getIdProducto());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
