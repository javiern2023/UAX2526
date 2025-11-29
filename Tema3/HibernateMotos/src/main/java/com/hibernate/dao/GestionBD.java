// Declaración del paquete donde se encuentra esta clase de gestión de base de datos
package com.hibernate.dao;

// Importación de la clase Moto (entidad JPA que representa una moto)
import com.hibernate.model.Moto;
// Importación de SystemException para manejo de excepciones del sistema
import jakarta.transaction.SystemException;
// Importación de Transaction para gestionar transacciones de Hibernate
import org.hibernate.Transaction;
// Importación de Session, la interfaz principal para operaciones CRUD en Hibernate
import org.hibernate.Session;

// Importación de List para manejar colecciones de objetos Moto
import java.util.List;

/**
 * Clase GestionBD - Data Access Object (DAO) para la entidad Moto.
 * Proporciona métodos CRUD (Create, Read, Update, Delete) para interactuar
 * con la tabla 'motos' en la base de datos usando Hibernate.
 * 
 * Cada método sigue el patrón:
 * 1. Abrir sesión
 * 2. Iniciar transacción (para operaciones de escritura)
 * 3. Ejecutar operación
 * 4. Commit de la transacción
 * 5. Manejo de errores con rollback si es necesario
 */
public class GestionBD {

    /**
     * Método para guardar (insertar) una nueva moto en la base de datos.
     * 
     * @param m Objeto Moto a guardar en la base de datos
     * @throws SystemException Si ocurre un error del sistema durante la operación
     */
    public void guardarMoto(Moto m) throws SystemException {
        // Declaramos la variable de transacción inicializada a null
        // Esto nos permite verificar si la transacción se inició en el bloque catch
        Transaction tx = null;
        
        // try-with-resources: Abre una sesión de Hibernate que se cerrará automáticamente
        // HibernateUtil.getSessionFactory(): Obtiene la fábrica de sesiones (Singleton)
        // openSession(): Crea una nueva sesión para interactuar con la base de datos
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            
            // Inicia una nueva transacción
            // Las transacciones aseguran que las operaciones sean atómicas (todo o nada)
            tx = session.beginTransaction();
            
            // persist(m): Marca el objeto 'm' para ser insertado en la base de datos
            // Hibernate generará un INSERT SQL cuando se haga commit
            // El ID se generará automáticamente por la base de datos (@GeneratedValue)
            session.persist(m);
            
            // commit(): Confirma la transacción y ejecuta el INSERT en la base de datos
            // Todos los cambios pendientes se escriben permanentemente
            tx.commit();
            
            // Mensaje de confirmación para el usuario
            System.out.println("Moto insertada en la base de datos");
        }
        catch (Exception e){
            // catch: Captura cualquier excepción que ocurra durante la operación
            
            // Verifica si la transacción se inició (no es null)
            if(tx != null){
                // rollback(): Revierte todos los cambios de la transacción
                // La base de datos vuelve al estado anterior al beginTransaction()
                // Esto mantiene la integridad de los datos en caso de error
                tx.rollback();
            }
            
            // Informa al usuario que hubo un error
            System.out.println("Error al guardar la moto");
            
            // printStackTrace(): Imprime la traza completa del error en la consola
            // Útil para debugging, muestra dónde y por qué ocurrió el error
            e.printStackTrace();
        }
        // Al salir del try-with-resources, la sesión se cierra automáticamente
    }

    /**
     * Método para leer (consultar) todas las motos de la base de datos.
     * 
     * @return List<Moto> Lista con todas las motos encontradas en la base de datos
     */
    public List<Moto> leerMotos(){
        // try-with-resources: Abre y cierra automáticamente la sesión
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            
            // createQuery(): Crea una consulta HQL (Hibernate Query Language)
            // "from Moto": Consulta HQL que selecciona TODAS las filas de la entidad Moto
            //              Equivalente a: SELECT * FROM motos en SQL
            // Moto.class: Especifica el tipo de resultado esperado (type-safe)
            // list(): Ejecuta la consulta y devuelve una lista con todos los resultados
            return session.createQuery("from Moto", Moto.class).list();
        }
        // No necesitamos transacción aquí porque solo estamos LEYENDO datos
        // Las operaciones de lectura no modifican la base de datos
    }

    /**
     * Método para eliminar una moto de la base de datos por su ID.
     * 
     * @param id Identificador único de la moto a eliminar
     * @throws SystemException Si ocurre un error del sistema durante la operación
     */
    public void eliminarMoto(int id) throws SystemException {
        // Declaramos la variable de transacción inicializada a null
        Transaction tx = null;
        
        // try-with-resources: Abre una sesión que se cerrará automáticamente
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            
            // Inicia una nueva transacción (necesaria para operaciones DELETE)
            tx = session.beginTransaction();
            
            // get(): Busca y carga la entidad Moto con el ID especificado
            // Moto.class: Tipo de entidad a buscar
            // id: Valor de la clave primaria
            // Retorna null si no encuentra ninguna moto con ese ID
            Moto m = session.get(Moto.class, id);
            
            // Verifica si se encontró la moto (m no es null)
            if(m != null){
                // remove(m): Marca el objeto para ser eliminado de la base de datos
                // Hibernate generará un DELETE SQL cuando se haga commit
                session.remove(m);
                
                // Mensaje de confirmación
                System.out.println("Moto eliminada de la base de datos");
            }
            else {
                // Si m es null, significa que no existe una moto con ese ID
                System.out.println("La moto no existe con ese id");
            }
            
            // commit(): Confirma la transacción y ejecuta el DELETE (si hubo remove)
            tx.commit();
        }
        catch (Exception e){
            // Captura cualquier excepción durante la operación
            
            // Si la transacción se inició, hacemos rollback
            if(tx != null){
                // Revierte los cambios para mantener la integridad de los datos
                tx.rollback();
            }
            
            // Mensaje de error genérico
            System.out.println("Error al mapear el objeto moto");
        }
        // La sesión se cierra automáticamente al salir del try-with-resources
    }

    /**
     * Método para buscar una moto por su marca.
     * Retorna solo la primera moto encontrada con esa marca.
     * 
     * @param marca Marca de la moto a buscar (ej: "Honda", "Yamaha")
     * @return Moto encontrada o null si no existe ninguna con esa marca
     */
    public Moto buscarPorMarca(String marca){
        // try-with-resources: Abre y cierra automáticamente la sesión
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            
            // createQuery(): Crea una consulta HQL con parámetros
            // "from Moto where marca = :marca": Consulta HQL con un parámetro nombrado
            //    - "from Moto": Selecciona de la entidad Moto
            //    - "where marca = :marca": Filtra por el campo marca
            //    - ":marca": Parámetro nombrado que se sustituirá con setParameter()
            // Equivalente SQL: SELECT * FROM motos WHERE marca = ?
            return session.createQuery("from Moto where marca = :marca", Moto.class)
                    
                    // setParameter(): Asigna el valor al parámetro nombrado
                    // "marca": Nombre del parámetro en la consulta HQL
                    // marca: Valor del parámetro (variable del método)
                    // Esto previene inyección SQL y es más seguro que concatenar strings
                    .setParameter("marca", marca)
                    
                    // setMaxResults(1): Limita los resultados a máximo 1 registro
                    // Optimiza la consulta porque solo necesitamos una moto
                    .setMaxResults(1)
                    
                    // uniqueResult(): Ejecuta la consulta y retorna un único resultado
                    // Retorna null si no encuentra ninguna moto con esa marca
                    // Lanza excepción si hay más de un resultado (pero setMaxResults(1) lo previene)
                    .uniqueResult();
        }
        // No necesitamos transacción porque solo estamos leyendo datos
    }
}
