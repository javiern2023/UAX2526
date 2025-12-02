package com.ejemplo.alumnos.database;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*EntityManagerFactory: crea objetos EntityManager.
EntityManager: es el objeto que permite realizar operaciones en la BD (persistir, buscar, borrar…).
Persistence: proporciona acceso a createEntityManagerFactory().*/

public class ConexionDB {

    private static final EntityManagerFactory emf =
            //Crea directamente el archivo alumnos.db dentro de la carpeta db.
            Persistence.createEntityManagerFactory("objectdb:db/universidad.odb");

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    public static void close() {
        emf.close();
    }
}
