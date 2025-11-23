package com.uax.concesionario.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// Gestiona las conexiones a MySQL
// Métodos estáticos para facilitar el acceso
// JDBC para conectarse a MySQL

public class ConexionBD {
    // CONSTANTES DE CONFIGURACIÓN DE LA BASE DE DATOS

    private static final String URL_SERVER = "jdbc:mysql://localhost:8889";

    private static final String URL = "jdbc:mysql://localhost:8889/concesionario_uax";

    private static final String USER = "root";

    private static final String PASS = "root";

    private static final String DB_NAME = "concesionario_uax";

    // controla si ya se inicializó la base de datos
    private static boolean initialized = false;

    // Método para establecer una conexión con la base de datos
    // Crea automáticamente la base de datos y tabla si no existen

    public static Connection conectar() {
        try {
            if (!initialized) {
                initializeDatabase();
                initialized = true;
            }
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos");
            System.out.println("Detalles del error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Inicializa la base de datos creándola si no existe
    // También crea la tabla 'ventas' si no existe
    private static void initializeDatabase() {
        Connection conn = null;
        try {
            // Conectar al servidor MySQL sin especificar base de datos
            conn = DriverManager.getConnection(URL_SERVER, USER, PASS);
            Statement stmt = conn.createStatement();

            // Crear base de datos si no existe
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            stmt.execute("USE " + DB_NAME);
            // Base de datos verificada/creada

            // Seleccionar la base de datos
            stmt.executeUpdate("USE " + DB_NAME);

            // Crear tabla de ventas si no existe
            String createTable = """
                        CREATE TABLE IF NOT EXISTS ventas (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            coche_id VARCHAR(50) NOT NULL,
                            marca VARCHAR(100) NOT NULL,
                            modelo VARCHAR(100) NOT NULL,
                            anio_fabricacion INT NOT NULL,
                            precio DECIMAL(10,2) NOT NULL,
                            color VARCHAR(50) NOT NULL,
                            matricula VARCHAR(20),
                            cliente_nombre VARCHAR(200) NOT NULL,
                            cliente_identificacion VARCHAR(50) NOT NULL,
                            cliente_telefono VARCHAR(20) NOT NULL,
                            cliente_correo VARCHAR(100) NOT NULL,
                            fecha_venta VARCHAR(50) NOT NULL,
                            INDEX idx_fecha_venta(fecha_venta)
                        )
                    """;
            stmt.executeUpdate(createTable);
            // Tabla verificada/creada correctame

            stmt.close();

        } catch (SQLException e) {
            System.err.println("Error al inicializar la base de datos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // Ignorar error al cerrar
                }
            }
        }
    }

    // Método para cerrar una conexión a la base de datos y liberar recursos del
    // servidor
    public static void desconectar(Connection conn) {
        try {
            // Verificamos que la conexión existe y está abierta antes de cerrarla
            if (conn != null && !conn.isClosed()) {
                // Cerramos la conexión
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión");
        }
    }
}
