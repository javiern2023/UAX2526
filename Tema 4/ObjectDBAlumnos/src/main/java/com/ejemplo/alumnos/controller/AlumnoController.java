package com.ejemplo.alumnos.controller;

import com.ejemplo.alumnos.database.ConexionDB;
import com.ejemplo.alumnos.model.Alumno;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
// Clase DAO (Data Access Object)
//EntityManager, objeto encargado de comunicarse con la base de datos.
public class AlumnoController {

    // Crear
    public Alumno crear(Alumno alumno) {
        EntityManager em = ConexionDB.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin(); //Iniciamos la transacción
        em.persist(alumno); //Insertamos en la base de datos
        tx.commit(); //Confirmamos la transacción

        em.close(); //Cerramos el objeto
        return alumno;
    }

    // Leer por id
    public Alumno obtenerPorId(Long id) {
        EntityManager em = ConexionDB.getEntityManagerFactory().createEntityManager();
        Alumno alumno = em.find(Alumno.class, id); //Método para buscar por id
        em.close();
        return alumno;
    }

    // Listar todos
    public List<Alumno> listarTodos() {
        EntityManager em = ConexionDB.getEntityManagerFactory().createEntityManager();
        //JPQL
        List<Alumno> lista =
                em.createQuery("SELECT a FROM Alumno a", Alumno.class).getResultList();

        em.close();
        return lista;
    }

    // Actualizar
    public boolean actualizar(Alumno alumno) {
        EntityManager em = ConexionDB.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.merge(alumno); //Actualizar la base de datos
        tx.commit();

        em.close();
        return true;
    }

    // Eliminar
    public boolean eliminar(Long id) {
        EntityManager em = ConexionDB.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Alumno alumno = em.find(Alumno.class, id);
        if (alumno == null) {
            em.close();
            return false;
        }

        tx.begin();
        em.remove(alumno); //eliminar la base de datos
        tx.commit();

        em.close();
        return true;
    }
}
