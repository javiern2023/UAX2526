package com.ejemplo.motos.database;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/*
*   EntityManagerFactory: crea objetos EntityManager.
    EntityManager: es el objeto que permite realizar operaciones en la BD (persistir, buscar, borrar…).
    Persistence: proporciona acceso a createEntityManagerFactory().*/

public class ConexionBD {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("objectdb-motos"); //Busca en el archivo xml

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

}