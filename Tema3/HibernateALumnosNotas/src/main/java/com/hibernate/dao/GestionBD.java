package com.hibernate.dao;

import com.hibernate.model.Alumno;
import com.hibernate.model.Nota;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class GestionBD {
    /* SessionFactory: objeto único usado para crear sesiones
    * Session: conexion a una base de datos
    * Transaction: agrupa operaciones para que se ejecuten de forma segura. Si falla se hace rollback
    * */

    /* Operaciones con session:
    1.- session.persist(obj): Insertar un objeto nuevo
    2.- session.remove(obj): Eliminar un objeto existente
    3.- session.get(Clase.class, id): Buscar por clave primaria
    4.- session.find(Clase.class, id): Igual que get pero JPA
    5.- session.merge(obj): Actualizar campos de un objeto existente
    6.- session.saveOrUpdate(obj): Inserta si no existe, actualiza si existe
    * */
    // Alta alumno
    public void altaAlumno(Alumno a) {
        Transaction tx = null; //
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction(); //Indica que todo lo que se haga es parte de la misma operación
            session.persist(a); //Hibernate convierte el objeto Alumno en un insert SQL
            tx.commit(); //Confirmamos el cambio si el alumno se guardó realmente
            System.out.println("Alumno añadido correctamente.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("Error al añadir alumno: " + e.getMessage());
        }
    }

    // Baja alumno
    public void bajaAlumno(String expediente) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Alumno a = session.get(Alumno.class, expediente);
            if (a != null) session.remove(a);
            tx.commit();
            System.out.println("Alumno eliminado.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("Error al eliminar alumno: " + e.getMessage());
        }
    }

    // Insertar nota
    public void insertarNota(Alumno a, double valorNota) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Nota n = new Nota();
            n.setAlumno(a);
            n.setNota(valorNota);
            session.persist(n);
            tx.commit();
            System.out.println("Nota insertada correctamente.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("Error al insertar nota: " + e.getMessage());
        }
    }

    // Consultar todas las notas
    public List<Nota> consultarTodasNotas() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //Devuelve todos los objetos de tipo nota y los guarda en la lista.
            return session.createQuery("from Nota", Nota.class).list(); //HQL
        }
    }
}
