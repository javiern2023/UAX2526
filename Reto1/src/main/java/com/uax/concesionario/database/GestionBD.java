package com.uax.concesionario.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.uax.concesionario.model.Coche;
import com.uax.concesionario.model.Cliente;
import com.uax.concesionario.model.VentaCoche;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionBD {

    // CONSTANTES
    private static final String NOMBRE_ARCHIVO_JSON = "coches_disponibles.json";
    private static final String RUTA_ARCHIVO_JSON;
    private final Gson gson;

    // Inicialización estática para determinar la ruta del archivo
    static {
        String directorioProyecto = System.getProperty("user.dir");
        // Verificar si estamos en el directorio raíz o en el módulo
        String rutaBase = directorioProyecto + "/src/main/resources/" + NOMBRE_ARCHIVO_JSON;
        File archivo = new File(rutaBase);

        // Si no existe la ruta directa, probamos con la ruta del módulo Reto1
        if (!archivo.getParentFile().exists()) {
            String rutaModulo = directorioProyecto + "/Reto1/src/main/resources/" + NOMBRE_ARCHIVO_JSON;
            File archivoModulo = new File(rutaModulo);
            if (archivoModulo.getParentFile().exists()) {
                rutaBase = rutaModulo;
            }
        }

        RUTA_ARCHIVO_JSON = rutaBase;
    }

    // Constructor - Inicializa Gson

    public GestionBD() {
        this.gson = new GsonBuilder().create();
    }

    // MÉTODOS PARA GESTIÓN

    // Lee todos los coches disponibles desde el archivo JSON local primero, si no
    // existe carga desde resources como plantilla inicial

    // Devuelve una lista de coches disponibles
    public List<Coche> leerCochesDisponibles() {
        List<Coche> coches = new ArrayList<>();

        // Intentar primero desde archivo local (modificable)
        File archivoLocal = new File(RUTA_ARCHIVO_JSON);
        if (archivoLocal.exists()) {
            try (Reader reader = new FileReader(archivoLocal)) {
                Type listType = new TypeToken<ArrayList<Coche>>() {
                }.getType();
                coches = gson.fromJson(reader, listType);

                if (coches == null) {
                    coches = new ArrayList<>();
                }
                return coches;
            } catch (IOException e) {
                System.err.println("Error al leer archivo local: " + e.getMessage());
            }
        }

        // Si no existe archivo local, cargar desde resources y crear copia local
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(NOMBRE_ARCHIVO_JSON)) {
            if (is != null) {
                Reader reader = new InputStreamReader(is);
                Type listType = new TypeToken<ArrayList<Coche>>() {
                }.getType();
                coches = gson.fromJson(reader, listType);

                if (coches == null) {
                    coches = new ArrayList<>();
                }

                // Crear copia local para futuras modificaciones
                guardarCochesDisponibles(coches);
                return coches;
            }
        } catch (IOException e) {
            System.err.println("Error al leer desde resources: " + e.getMessage());
        }

        return coches;
    }

    // Guarda la lista de coches disponibles en el archivo JSON local
    // Devuelve true si se guardó correctamente, false en caso contrario

    private void guardarCochesDisponibles(List<Coche> coches) {
        try (Writer writer = new FileWriter(RUTA_ARCHIVO_JSON)) {
            gson.toJson(coches, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar coches: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Agrega un nuevo coche al inventario disponible
    // Devuelve true si se agregó correctamente, false en caso contrario

    public boolean agregarCoche(Coche coche) {
        List<Coche> coches = leerCochesDisponibles();

        // Verificar si ya existe un coche con ese ID
        for (Coche c : coches) {
            if (c.getId().equals(coche.getId())) {
                System.out.println("Ya existe un coche con el ID: " + coche.getId());
                return false;
            }
        }

        coches.add(coche);
        guardarCochesDisponibles(coches);
        System.out.println("Coche registrado exitosamente en el inventario");
        return true;
    }

    // Elimina un coche del inventario disponible
    // Devuelve true si se eliminó correctamente, false en caso contrario

    public boolean eliminarCocheDisponible(String id) {
        List<Coche> coches = leerCochesDisponibles();
        boolean eliminado = coches.removeIf(c -> c.getId().equals(id));

        if (eliminado) {
            guardarCochesDisponibles(coches);
        }

        return eliminado;
    }

    // Busca un coche por su ID en el inventario disponible
    // Devuelve el coche encontrado, o null si no existe

    public Coche buscarCochePorId(String id) {
        List<Coche> coches = leerCochesDisponibles();

        for (Coche c : coches) {
            if (c.getId().equals(id)) {
                return c;
            }
        }

        return null;
    }

    // Inserta una nueva venta en la base de datos
    // Devuelve true si se insertó correctamente, false en caso contrario
    public boolean insertarVenta(VentaCoche venta) {
        String sql = """
                INSERT INTO ventas (
                    coche_id, marca, modelo, anio_fabricacion, precio, color, matricula,
                    cliente_nombre, cliente_identificacion, cliente_telefono, cliente_correo,
                    fecha_venta
                ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        Connection conn = ConexionBD.conectar();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            Coche coche = venta.getCoche();
            Cliente cliente = venta.getCliente();

            pstmt.setString(1, coche.getId());
            pstmt.setString(2, coche.getMarca());
            pstmt.setString(3, coche.getModelo());
            pstmt.setInt(4, coche.getAnioFabricacion());
            pstmt.setDouble(5, coche.getPrecio());
            pstmt.setString(6, coche.getColor());
            pstmt.setString(7, coche.getMatricula());
            pstmt.setString(8, cliente.getNombre());
            pstmt.setString(9, cliente.getIdentificacion());
            pstmt.setString(10, cliente.getTelefono());
            pstmt.setString(11, cliente.getCorreo());
            pstmt.setString(12, venta.getFechaVenta());

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Venta registrada en la base de datos");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar venta: " + e.getMessage());
            e.printStackTrace();
        } finally {
            ConexionBD.desconectar(conn);
        }

        return false;
    }

    // Lee todas las ventas registradas en la base de datos
    // Devuelve una lista de ventas

    public List<VentaCoche> leerTodasLasVentas() {
        List<VentaCoche> ventas = new ArrayList<>();
        String sql = "SELECT * FROM ventas ORDER BY id DESC";

        Connection conn = ConexionBD.conectar();

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Coche coche = new Coche(
                        rs.getString("coche_id"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("anio_fabricacion"),
                        rs.getDouble("precio"),
                        rs.getString("color"),
                        rs.getString("matricula"));

                Cliente cliente = new Cliente(
                        rs.getString("cliente_nombre"),
                        rs.getString("cliente_identificacion"),
                        rs.getString("cliente_telefono"),
                        rs.getString("cliente_correo"));

                VentaCoche venta = new VentaCoche(
                        rs.getInt("id"),
                        coche,
                        cliente,
                        rs.getString("fecha_venta"));

                ventas.add(venta);
            }

        } catch (SQLException e) {
            System.err.println("Error al leer ventas: " + e.getMessage());
            e.printStackTrace();
        } finally {
            ConexionBD.desconectar(conn);
        }

        return ventas;
    }

    // Lee las ventas de un cliente específico

    public List<VentaCoche> leerVentasPorCliente(String clienteIdentificacion) {
        List<VentaCoche> ventas = new ArrayList<>();
        String sql = "SELECT * FROM ventas WHERE cliente_identificacion = ? ORDER BY id DESC";

        Connection conn = ConexionBD.conectar();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, clienteIdentificacion);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Coche coche = new Coche(
                        rs.getString("coche_id"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("anio_fabricacion"),
                        rs.getDouble("precio"),
                        rs.getString("color"),
                        rs.getString("matricula"));

                Cliente cliente = new Cliente(
                        rs.getString("cliente_nombre"),
                        rs.getString("cliente_identificacion"),
                        rs.getString("cliente_telefono"),
                        rs.getString("cliente_correo"));

                VentaCoche venta = new VentaCoche(
                        rs.getInt("id"),
                        coche,
                        cliente,
                        rs.getString("fecha_venta"));

                ventas.add(venta);
            }

        } catch (SQLException e) {
            System.err.println("Error al leer ventas por cliente: " + e.getMessage());
            e.printStackTrace();
        } finally {
            ConexionBD.desconectar(conn);
        }

        return ventas;
    }

    // Obtiene el número total de ventas realizadas
    // Devuelve el número total de ventas
    public int obtenerTotalVentas() {
        String sql = "SELECT COUNT(*) AS total FROM ventas";

        Connection conn = ConexionBD.conectar();

        if (conn == null) {
            System.err.println("No se pudo conectar a la base de datos para obtener ventas.");
            return 0;
        }

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt("total");
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener total de ventas: " + e.getMessage());
        } finally {
            ConexionBD.desconectar(conn);
        }

        return 0;
    }
}
