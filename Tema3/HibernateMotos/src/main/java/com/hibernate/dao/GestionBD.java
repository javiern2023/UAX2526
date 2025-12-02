package com.hibernate.dao;

import com.hibernate.model.Moto;
import jakarta.transaction.SystemException;
import org.hibernate.Transaction;
import org.hibernate.Session;

import java.util.List;

public class GestionBD {

    public void guardarMoto(Moto m) throws SystemException {
        Transaction tx = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            tx = session.beginTransaction();
            session.persist(m);
            tx.commit();
            System.out.println("Moto insertada en la base de datos");
        }
        catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            System.out.println("Error al guardar la moto");
            e.printStackTrace();
        }
    }

    public List<Moto> leerMotos(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from Moto", Moto.class).list();
        }
    }

    public void eliminarMoto(int id) throws SystemException {
        Transaction tx = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            tx = session.beginTransaction();
            Moto m = session.get(Moto.class, id);
            if(m != null){
                session.remove(m);
                System.out.println("Moto eliminada de la base de datos");
            }
            else System.out.println("La moto no existe con ese id");
            tx.commit();
        }
        catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            System.out.println("Error al mapear el objeto moto");
        }
    }

    public Moto buscarPorMarca(String marca){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from Moto where marca = :marca", Moto.class)
                    .setParameter("marca", marca)
                    .setMaxResults(1)
                    .uniqueResult();
        }
    }
}
