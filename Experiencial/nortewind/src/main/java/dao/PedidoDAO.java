package dao;

import dao.ConexionBD;
import model.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    // Insertar un pedido
    public void insertar(Pedido p) {
        String sql = "INSERT INTO pedidos(id_producto, descripcion, precio_total) VALUES(?,?,?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getIdProducto());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecioTotal());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar todos los pedidos
    public List<Pedido> listarTodos() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedidos";
        try (Connection conn = ConexionBD.conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Pedido p = new Pedido();
                p.setId(rs.getInt("id"));
                p.setIdProducto(rs.getInt("id_producto"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecioTotal(rs.getDouble("precio_total"));
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Insertar pedidos de ejemplo
    public void insertarEjemplos() {
        // Asegúrate de que los productos existen en la base de datos
        insertar(new Pedido(0, 1, "Pedido de Laptop", 1200));
        insertar(new Pedido(0, 2, "Pedido de Monitor", 250));
        insertar(new Pedido(0, 3, "Pedido de Teclado", 80));
    }
}
