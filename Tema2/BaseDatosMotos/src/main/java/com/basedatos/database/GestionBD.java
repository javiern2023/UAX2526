package com.basedatos.database;

import com.basedatos.model.Moto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



/**
 * Clase encargada de gestionar la conexión y operaciones con la base de datos MySQL.
 * Incluye métodos para crear la base de datos y tablas si no existen, así como CRUD para motos.
 */
public class GestionBD {

    /**
     * Método que inicializa la base de datos.
     * - Establece conexión con la BD.
     * - Selecciona la BD.
     * - Crea la tabla "motos" si no existe.
     */
    private void inicializarBD() {
        // 🔹 1️⃣ Primera conexión: al servidor sin indicar BD
        try (Connection connRoot = DriverManager.getConnection("jdbc:mysql://localhost:8889/", "root", "root");
             Statement stmt = connRoot.createStatement()) {
        } catch (SQLException e) {
            System.err.println("❌ Error al crear la base de datos: " + e.getMessage());
        }

        // 🔹 2️⃣ Segunda conexión: ya conectados a la base 'uax'
        try (Connection connDB = ConexionBD.conectar(); // Ahora SI apunta a jdbc:mysql://localhost:8889/uax
             Statement stmt = connDB.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS motos (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "marca VARCHAR(50), " +
                    "modelo VARCHAR(50), " +
                    "cilindrada VARCHAR(10))");
            System.out.println("✔ Tabla 'motos' verificada o creada");
        } catch (SQLException e) {
            System.err.println("❌ Error al crear tabla 'motos': " + e.getMessage());
        }
    }

    /**
     * Constructor de GestionBD.
     * Llama al método inicializarBD() para asegurarse de que la BD y tablas estén listas.
     */
    public GestionBD() {
        inicializarBD();
    }

    // Lista que almacena temporalmente las motos recuperadas de la base de datos
    private List<Moto> listaMotos = new ArrayList<>(); // en ficheros si es obligatoria la lista para pasar a json, u otros

    /**
     * Inserta una nueva moto en la base de datos.
     * @param moto Objeto de tipo Moto con los datos a insertar.
     */
    public void insertarMoto(Moto moto) {
        String sql = "INSERT INTO motos (marca, modelo, cilindrada) VALUES (?, ?, ?)";
        Connection conn = ConexionBD.conectar();

        // Preparamos la sentencia SQL para insertar datos de forma segura
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

    /**
     * Lee todas las motos de la base de datos.
     * @return Lista de objetos Moto recuperados.
     */
    public List<Moto> leerMotos() {
        listaMotos.clear();
        String sql = "SELECT * FROM motos";
        Connection conn = ConexionBD.conectar();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Recuperamos los datos de cada fila del resultado y creamos un objeto Moto
                Moto m = new Moto(
                        rs.getInt("id"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("cilindrada")
                );
                listaMotos.add(m);
            }

        } catch (SQLException e) {
            System.out.println("Error al leer motos: " + e.getMessage());
        } finally {
            ConexionBD.desconectar(conn);
        }

        return listaMotos;
    }

    /**
     * Lee motos filtrando por marca.
     * @param marcaBuscada Marca que se desea buscar.
     * @return Lista de motos que coinciden con la búsqueda.
     */
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

    /**
     * Elimina una moto de la base de datos según su ID.
     * @param id Identificador de la moto a eliminar.
     */
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

    /**
     * Devuelve la lista de motos almacenadas temporalmente.
     * @return Lista de motos.
     */
    public List<Moto> getListaMotos() {
        return listaMotos;
    }
}
