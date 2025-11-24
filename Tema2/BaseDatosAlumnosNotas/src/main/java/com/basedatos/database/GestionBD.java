/**
 * GestionBD.java — Capa de acceso a datos (DAO/Repositorio)
 * Línea a línea: explicaciones detalladas de qué ocurre y por qué.
 *
 * Objetivo:
 *  - Proveer operaciones CRUD sobre alumnos y notas usando JDBC.
 *  - Crear automáticamente las tablas necesarias si no existen.
 */
package com.basedatos.database;

/* ====== IMPORTS: Traemos clases JDBC y nuestro modelo ====== */
import com.basedatos.model.Alumno; // 🧩 POJO del dominio que representa a un alumno
import java.sql.*;               // 🔌 JDBC: Connection, Statement, PreparedStatement, ResultSet, SQLException...
      // 📦 (Opcional) Estructuras de apoyo si fuesen necesarias

/**
 * Clase de servicio de base de datos.
 * - Mantiene métodos independientes (conexión por llamada) para evitar estado compartido.
 * - Cada método:
 *     1) Abre conexión (ConexionBD.conectar())
 *     2) Prepara sentencia (Statement/PreparedStatement)
 *     3) Ejecuta SQL
 *     4) Gestiona excepciones (SQLException)
 *     5) Cierra conexión en finally
 */
public class GestionBD {

    public static void crearTablasSiNoExisten() {
        Connection con = ConexionBD.conectar(); // 1) Abrimos una conexión nueva contra MySQL (usa parámetros definidos en ConexionBD)

        try (Statement st = con.createStatement()) { // 2) Creamos un Statement simple; try-with-resources asegura cierre automático aunque falle algo

            // 3) Definimos el DDL (Data Definition Language) para la tabla 'alumnos'
            //    - expediente: clave primaria (VARCHAR(20)) → identifica unívocamente al alumno
            //    - nombre/apellidos: NOT NULL para exigir datos obligatorios
            String sqlAlumnos = """
                        CREATE TABLE IF NOT EXISTS alumnos (
                            expediente VARCHAR(20) PRIMARY KEY,
                            nombre VARCHAR(100) NOT NULL,
                            apellidos VARCHAR(150) NOT NULL
                        );
                    """;
            st.execute(sqlAlumnos); // 4) Ejecutamos el DDL de creación (si ya existe, no hace nada por el IF NOT EXISTS)

            // 5) Definimos la tabla 'notas'
            //    - expediente: FK que referencia a alumnos(expediente)
            //    - nota: DOUBLE para permitir decimales
            //    Nota: la FK evita notas huérfanas y garantiza integridad referencial
            String sqlNotas = """
                        CREATE TABLE IF NOT EXISTS notas (
                            expediente VARCHAR(20),
                            nota DOUBLE,
                            FOREIGN KEY (expediente) REFERENCES alumnos(expediente)
                        );
                    """;
            st.execute(sqlNotas); // 6) Ejecutamos la creación de 'notas'

            System.out.println("Tablas comprobadas/creadas correctamente."); // 7) Mensaje informativo al usuario

        } catch (SQLException e) {
            System.out.println("Error al crear tablas: " + e.getMessage()); // ⚠️ Mostramos el motivo exacto devuelto por JDBC
        } finally {
            ConexionBD.desconectar(con); // 8) Cerramos explícitamente la conexión abierta al inicio del método
        }
    }


    // 1. Alta alumno
    public void altaAlumno(Alumno a) {
        Connection con = ConexionBD.conectar(); // 1) Nueva conexión por operación para aislar fallos y liberar recursos ASAP
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO alumnos VALUES (?, ?, ?)"); // 2) Preparamos sentencia con parámetros (?) → evita SQL Injection y controla tipos
            ps.setString(1, a.getExpediente()); // 3) Sustituimos cada ? por el valor correspondiente (orden: 1,2,3)
            ps.setString(2, a.getNombre()); // 3) Sustituimos cada ? por el valor correspondiente (orden: 1,2,3)
            ps.setString(3, a.getApellidos()); // 3) Sustituimos cada ? por el valor correspondiente (orden: 1,2,3)
            ps.executeUpdate(); // 4) Ejecuta el INSERT; devuelve nº filas afectadas (no lo usamos aquí)
            System.out.println("Alumno añadido correctamente."); // 5) Confirmamos al usuario
        } catch (SQLException e) {
            System.out.println("Error al insertar alumno: " + e.getMessage()); // ⚠️ Mostramos detalle de la causa (p.ej., PK duplicada)
        } finally {
            ConexionBD.desconectar(con); // 6) Cierre garantizado de la conexión para no agotar el pool
        }
    }

    // 2. Baja alumno
    public void bajaAlumno(String expediente) {
        Connection con = ConexionBD.conectar(); // 1) Abrimos conexión nueva
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM alumnos WHERE expediente=?"); // 2) DELETE con parámetro → borra por expediente exacto
            ps.setString(1, expediente); // 3) Sustituye el ? por el expediente recibido
            ps.executeUpdate(); // 4) Ejecuta el borrado; si no existe el expediente, afectará 0 filas
            System.out.println("Alumno eliminado."); // 5) Feedback
        } catch (SQLException e) {
            System.out.println("Error al eliminar alumno: " + e.getMessage()); // ⚠️ e.g., FK constraint si existen notas
        } finally {
            ConexionBD.desconectar(con); // 6) Cerramos conexión
        }
    }

    // 3. Insertar nota
    public void insertarNota(String expediente, double nota) {
        Connection con = ConexionBD.conectar(); // 1) Conexión
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO notas VALUES (?, ?)"); // 2) INSERT en 'notas' (expediente debe existir en 'alumnos' por la FK)
            ps.setString(1, expediente); // 3) Parametrizamos expediente y nota
            ps.setDouble(2, nota); // 3) Parametrizamos expediente y nota
            ps.executeUpdate(); // 4) Insertamos la fila
            System.out.println("Nota insertada."); // 5) Feedback
        } catch (SQLException e) {
            System.out.println("Error al insertar nota: " + e.getMessage()); // ⚠️ Posibles causas: FK violada, tipos inválidos
        } finally {
            ConexionBD.desconectar(con); // 6) Cierre de conexión
        }
    }

    // 4. Modificar nota
    public void modificarNota(String expediente, double nuevaNota) {
        Connection con = ConexionBD.conectar(); // 1) Conexión
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE notas SET nota=? WHERE expediente=?"); // 2) UPDATE por expediente
            ps.setDouble(1, nuevaNota); // 3) Parámetros (nota nueva, expediente)
            ps.setString(2, expediente); // 3) Parámetros (nota nueva, expediente)
            ps.executeUpdate(); // 4) Ejecuta actualización
            System.out.println("Nota modificada."); // 5) Feedback
        } catch (SQLException e) {
            System.out.println("Error al modificar nota: " + e.getMessage()); // ⚠️ Si no existe el expediente, afectará 0 filas
        } finally {
            ConexionBD.desconectar(con); // 6) Cierre
        }
    }

    // 5. Consultar nota de un alumno
    public void consultarNota(String expediente) {
        Connection con = ConexionBD.conectar(); // 1) Conexión
        try {
            PreparedStatement ps = con.prepareStatement("SELECT nota FROM notas WHERE expediente=?"); // 2) SELECT de la nota por expediente
            ps.setString(1, expediente); // 3) Parámetro de búsqueda
            ResultSet rs = ps.executeQuery(); // 4) Ejecuta consulta y devuelve ResultSet (cursor)
            if (rs.next()) // 5) Mueve cursor a primera fila si existe
                System.out.println("Nota: " + rs.getDouble("nota")); // 6) Leemos columna 'nota' por label
            else
                System.out.println("No hay nota registrada."); // 6b) No hay registro para ese expediente
        } catch (SQLException e) {
            System.out.println("Error al consultar nota: " + e.getMessage()); // ⚠️ Mensaje de diagnóstico
        } finally {
            ConexionBD.desconectar(con); // 7) Cierre
        }
    }

    // 6. Consultar todas las notas
    public void consultarTodasNotas() {
        Connection con = ConexionBD.conectar(); // 1) Conexión
        try {
            Statement st = con.createStatement(); // 2) Statement simple para una consulta sin parámetros
            ResultSet rs = st.executeQuery("SELECT * FROM notas"); // 3) Ejecuta SELECT y entrega ResultSet
            while (rs.next()) { // 4) Recorremos cada fila del cursor minetras tenga contenido
                System.out.printf("Expediente: %s - Nota: %.2f%n", rs.getString("expediente"), rs.getDouble("nota")); // 5) Leemos columnas 'expediente' y 'nota' // printf: %s=String, %d=entero, %.2f=decimal 2 decimales, %n=salto de línea portátil
            }
        } catch (SQLException e) {
            System.out.println("Error al leer notas: " + e.getMessage()); // ⚠️ Diagnóstico
        } finally {
            ConexionBD.desconectar(con); // 6) Cierre
        }
    }
}
