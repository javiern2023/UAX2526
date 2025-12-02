package com.basedatos.database;

import com.basedatos.model.Alumno;
import java.sql.*;
        import java.util.ArrayList;

public class GestionBD {

    // 1. Alta alumno
    public void altaAlumno(Alumno a) {
        Connection con = ConexionBD.conectar();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO alumnos VALUES (?, ?, ?)");
            ps.setString(1, a.getExpediente());
            ps.setString(2, a.getNombre());
            ps.setString(3, a.getApellidos());
            ps.executeUpdate();
            System.out.println("Alumno añadido correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar alumno.");
        } finally {
            ConexionBD.desconectar(con);
        }
    }

    // 2. Baja alumno
    public void bajaAlumno(String expediente) {
        Connection con = ConexionBD.conectar();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM alumnos WHERE expediente=?");
            ps.setString(1, expediente);
            ps.executeUpdate();
            System.out.println("Alumno eliminado.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar alumno.");
        } finally {
            ConexionBD.desconectar(con);
        }
    }

    // 3. Insertar nota
    public void insertarNota(String expediente, double nota) {
        Connection con = ConexionBD.conectar();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO notas VALUES (?, ?)");
            ps.setString(1, expediente);
            ps.setDouble(2, nota);
            ps.executeUpdate();
            System.out.println("Nota insertada.");
        } catch (SQLException e) {
            System.out.println("Error al insertar nota.");
        } finally {
            ConexionBD.desconectar(con);
        }
    }

    // 4. Modificar nota
    public void modificarNota(String expediente, double nuevaNota) {
        Connection con = ConexionBD.conectar();
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE notas SET nota=? WHERE expediente=?");
            ps.setDouble(1, nuevaNota);
            ps.setString(2, expediente);
            ps.executeUpdate();
            System.out.println("Nota modificada.");
        } catch (SQLException e) {
            System.out.println("Error al modificar nota.");
        } finally {
            ConexionBD.desconectar(con);
        }
    }

    // 5. Consultar nota de un alumno
    public void consultarNota(String expediente) {
        Connection con = ConexionBD.conectar();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT nota FROM notas WHERE expediente=?");
            ps.setString(1, expediente);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) System.out.println("Nota: " + rs.getDouble("nota"));
            else System.out.println("No hay nota registrada.");
        } catch (SQLException e) {
            System.out.println("Error al consultar nota.");
        } finally {
            ConexionBD.desconectar(con);
        }
    }

    // 6. Consultar todas las notas
    public void consultarTodasNotas() {
        Connection con = ConexionBD.conectar();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM notas");
            while (rs.next()) {
                System.out.printf("Expediente: %s - Nota: %.2f%n", rs.getString("expediente"), rs.getDouble("nota"));
            }
        } catch (SQLException e) {
            System.out.println("Error al leer notas.");
        } finally {
            ConexionBD.desconectar(con);
        }
    }
}
