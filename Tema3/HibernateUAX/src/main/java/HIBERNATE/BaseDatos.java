package HIBERNATE;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class BaseDatos {

    public void guardarAlumno(Alumno alumno) {
        Transaction tx = null;
        //Abre una nueva sesión con la base de datos.
        //getSessionFactory singleton que crea las sesiones
        //openSession es una sesión temporar para hacer las operaciones CRUD
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            //Transacción, bloque donde se harán cambios en la base de datos.
            tx = session.beginTransaction();
            session.save(alumno); //Guardar
            tx.commit(); //Confirma la transacción y guarda en la base de datos
            // No es necesario session.close(); porque try-with-resources lo hace solo
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }


    public void eliminarAlumno(int id) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Alumno alumno = session.get(Alumno.class, id);
            if (alumno != null) {
                session.delete(alumno);
            }
            tx.commit();
        }
    }

    public void actualizarNota(int id, double nuevaNota) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Alumno alumno = session.get(Alumno.class, id);
            if (alumno != null) {
                alumno.setNota(nuevaNota);
                session.update(alumno);
            }
            tx.commit();
        }
    }

    public List<Alumno> obtenerTodos() {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            return session.createQuery("from Alumno", Alumno.class).list();
        }
    }
}
