package com.ejemplo.motos.controller;

import com.ejemplo.motos.database.ConexionBD;
import com.ejemplo.motos.model.Moto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery; //Consultas JPA
import java.util.List;

public class MotoController {

    public void guardarMoto(Moto moto) {
        EntityManager em = ConexionBD.getEntityManager();
        em.getTransaction().begin();
        em.persist(moto);
        em.getTransaction().commit();
        em.close();
    }

    public List<Moto> leerMotos() {
        EntityManager em = ConexionBD.getEntityManager();
        TypedQuery<Moto> query = em.createQuery("SELECT m FROM Moto m", Moto.class);
        List<Moto> motos = query.getResultList();
        em.close();
        return motos;
    }

    public Moto obtenerMotoPorId(long id) {
        EntityManager em = ConexionBD.getEntityManager();
        Moto moto = em.find(Moto.class, id);
        em.close();
        return moto;
    }

    public void eliminarMoto(long id) {
        EntityManager em = ConexionBD.getEntityManager();
        em.getTransaction().begin();
        Moto moto = em.find(Moto.class, id);
        if (moto != null) {
            em.remove(moto);
        }
        em.getTransaction().commit();
        em.close();
    }
}
