package com.hibernate.dao;

import com.hibernate.model.*;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    @Getter
    private static final SessionFactory sessionFactory = buildSessionFactory();
    // Sólo creamos un único objeto por sesión. sessionFactory sólo lo generamos una vez.

    private static SessionFactory buildSessionFactory() {
        try {
            //Configuración de Hibernate.
            return new Configuration()
                    .configure("hibernate.cfg.xml") // archivo XML de configuración
                    .addAnnotatedClass(com.hibernate.model.Alumno.class) //Clases a mapear
                    .addAnnotatedClass(com.hibernate.model.Nota.class)
                    .buildSessionFactory(); //Se prepara para realizar todas las sentencias SQL.
        } catch (Throwable ex) {
            System.err.println("Error creando SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void shutdown() {
        //Este objeto se genera con el método get. La etiqueta @Getter
        getSessionFactory().close();
    }
}
