// Declaración del paquete donde se encuentra esta clase de utilidad
package com.hibernate.dao;

// Importación de la anotación de Lombok que genera automáticamente un método getter
import lombok.Getter;
// Importación de SessionFactory, la interfaz principal de Hibernate para crear sesiones
import org.hibernate.SessionFactory;
// Importación de Configuration, clase que configura Hibernate desde archivos XML o anotaciones
import org.hibernate.cfg.Configuration;

/**
 * Clase de utilidad para gestionar la SessionFactory de Hibernate.
 * Esta clase implementa el patrón Singleton para asegurar que solo exista
 * una única instancia de SessionFactory en toda la aplicación.
 * SessionFactory es thread-safe y costosa de crear, por eso se crea una sola vez.
 */
public class HibernateUtil {
    
    // @Getter: Lombok genera automáticamente el método getSessionFactory()
    // Este método será público y estático, permitiendo acceder a sessionFactory desde cualquier parte
    @Getter
    
    // static: La variable pertenece a la clase, no a instancias individuales
    // final: Una vez inicializada, no puede cambiar (inmutable)
    // SessionFactory: Fábrica que crea objetos Session para interactuar con la base de datos
    // buildSessionFactory(): Se llama automáticamente al cargar la clase para inicializar la variable
    private static final SessionFactory sessionFactory = buildSessionFactory();
    
    // IMPORTANTE: Patrón Singleton - Solo creamos un único objeto SessionFactory
    // Esto es eficiente porque SessionFactory es pesada de crear pero thread-safe para usar

    /**
     * Método privado que construye y configura la SessionFactory.
     * Se ejecuta una sola vez cuando la clase se carga en memoria.
     * 
     * @return SessionFactory configurada y lista para crear sesiones
     * @throws ExceptionInInitializerError si hay algún error en la configuración
     */
    private static SessionFactory buildSessionFactory() {
        try {
            // Bloque try para capturar cualquier error durante la configuración de Hibernate
            
            // Creamos un nuevo objeto Configuration que leerá la configuración de Hibernate
            return new Configuration()
                    
                    // configure("hibernate.cfg.xml"): Lee el archivo de configuración XML
                    // Este archivo contiene: URL de BD, usuario, contraseña, dialecto SQL, etc.
                    // Se busca en src/main/resources/hibernate.cfg.xml
                    .configure("hibernate.cfg.xml")
                    
                    // addAnnotatedClass(): Registra la clase Moto como entidad JPA
                    // Hibernate escaneará las anotaciones (@Entity, @Table, @Column, etc.)
                    // y creará el mapeo entre la clase Java y la tabla de base de datos
                    .addAnnotatedClass(com.hibernate.model.Moto.class)
                    
                    // buildSessionFactory(): Construye la SessionFactory con toda la configuración
                    // Este proceso valida el mapeo, prepara las consultas SQL y establece el pool de conexiones
                    // Es una operación costosa, por eso solo se hace una vez
                    .buildSessionFactory();
                    
        } catch (Throwable ex) {
            // catch (Throwable ex): Captura cualquier tipo de error o excepción
            // Throwable es más amplio que Exception, captura también errores graves
            
            // Imprime el mensaje de error en la salida de error estándar (consola en rojo)
            System.err.println("Error creando SessionFactory: " + ex);
            
            // Lanza una excepción especial para errores durante la inicialización estática
            // Esto detiene la aplicación porque sin SessionFactory no podemos usar Hibernate
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Método público para cerrar la SessionFactory y liberar todos los recursos.
     * Debe llamarse al finalizar la aplicación para:
     * - Cerrar todas las conexiones del pool de conexiones
     * - Liberar memoria y recursos del sistema
     * - Evitar fugas de memoria (memory leaks)
     */
    public static void shutdown() {
        // getSessionFactory(): Método generado automáticamente por @Getter
        // Obtiene la instancia única de SessionFactory
        
        // close(): Cierra la SessionFactory y libera todos los recursos asociados
        // Esto incluye: conexiones de BD, caché de segundo nivel, pools de threads, etc.
        getSessionFactory().close();
    }
}
