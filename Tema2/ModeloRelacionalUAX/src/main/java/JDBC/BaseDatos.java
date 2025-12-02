package JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDatos {

    private static final String URL = "jdbc:mysql://localhost:3306/uax?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // o tu contraseña si tienes una

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public int InsertarAlumno(Alumno alumno) {
        String sql = "INSERT INTO alumnos (nombre, apellidos, direccion, nota) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellidos());
            ps.setString(3, alumno.getDireccion());
            if (alumno.getNota() == null) ps.setNull(4, Types.DOUBLE);
            else ps.setDouble(4, alumno.getNota());

            int affected = ps.executeUpdate();
            if (affected == 0) return -1;

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    alumno.setId(id);
                    return id;
                }
            }
            return -1;
        } catch (SQLException e) {
            System.err.println("Error al añadir alumno: " + e.getMessage());
            return -1;
        }
    }

    public boolean EliminarAlumno(int id) {
        String sql = "DELETE FROM alumnos WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar alumno: " + e.getMessage());
            return false;
        }
    }

    public boolean ModificarNota(int id, double nuevaNota) {
        String sql = "UPDATE alumnos SET nota = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, nuevaNota);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar nota: " + e.getMessage());
            return false;
        }
    }

    public List<Alumno> MostrarTodosAlumnos() {
        List<Alumno> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, apellidos, direccion, nota FROM alumnos ORDER BY id";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Alumno a = new Alumno();
                a.setId(rs.getInt("id"));
                a.setNombre(rs.getString("nombre"));
                a.setApellidos(rs.getString("apellidos"));
                a.setDireccion(rs.getString("direccion"));
                double nota = rs.getDouble("nota");
                a.setNota(rs.wasNull() ? null : nota);
                lista.add(a);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener alumnos: " + e.getMessage());
        }
        return lista;
    }

    public Alumno BuscarAlumnoPorId(int id) {
        String sql = "SELECT * FROM alumnos WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Alumno(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("apellidos"),
                            rs.getString("direccion"),
                            rs.wasNull() ? null : rs.getDouble("nota")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar alumno: " + e.getMessage());
        }
        return null;
    }
}
