package dao;

import dao.ConexionBD;
import model.Empleado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    // Insertar un empleado
    public void insertar(Empleado e) {
        String sql = "INSERT INTO empleados(nombre, apellidos, correo) VALUES(?,?,?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getApellidos());
            ps.setString(3, e.getCorreo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Listar todos los empleados
    public List<Empleado> listarTodos() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleados";
        try (Connection conn = ConexionBD.conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Empleado e = new Empleado();
                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                e.setApellidos(rs.getString("apellidos"));
                e.setCorreo(rs.getString("correo"));
                lista.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    // Crear varios empleados de ejemplo mediante statement
    public void insertarEjemplos() {
        insertar(new Empleado(0, "Juan", "Pérez", "juan.perez@northwind.com"));
        insertar(new Empleado(0, "María", "García", "maria.garcia@northwind.com"));
        insertar(new Empleado(0, "Luis", "Martínez", "luis.martinez@northwind.com"));
    }
}
