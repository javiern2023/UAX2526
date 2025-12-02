package com.uax.dao;

import com.uax.model.Alumno;
import com.uax.util.BaseDatos;

import java.sql.*;
        import java.util.*;

public class AlumnoDAO {

    public void insertar(Alumno a) throws SQLException {
        String sql = "INSERT INTO alumno (expediente, nombre, apellidos, nota) VALUES (?, ?, ?, ?)";
        try (Connection con = BaseDatos.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, a.getExpediente());
            ps.setString(2, a.getNombre());
            ps.setString(3, a.getApellidos());
            if (a.getNota() != null)
                ps.setDouble(4, a.getNota());
            else
                ps.setNull(4, Types.NUMERIC);
            ps.executeUpdate();
        }
    }

    public void eliminar(String expediente) throws SQLException {
        String sql = "DELETE FROM alumno WHERE expediente = ?";
        try (Connection con = BaseDatos.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, expediente);
            ps.executeUpdate();
        }
    }

    public void actualizarNota(String expediente, Double nota) throws SQLException {
        String sql = "UPDATE alumno SET nota = ? WHERE expediente = ?";
        try (Connection con = BaseDatos.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            if (nota != null)
                ps.setDouble(1, nota);
            else
                ps.setNull(1, Types.NUMERIC);
            ps.setString(2, expediente);
            ps.executeUpdate();
        }
    }

    public List<Alumno> listar() throws SQLException {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT expediente, nombre, apellidos, nota FROM alumno";
        try (Connection con = BaseDatos.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                alumnos.add(new Alumno(
                        rs.getString("expediente"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getObject("nota") != null ? rs.getDouble("nota") : null
                ));
            }
        }
        return alumnos;
    }
}
