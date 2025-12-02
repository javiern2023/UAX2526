package com.hibernate.controller;

import com.hibernate.dao.GestionBD;
import com.hibernate.model.Alumno;
import com.hibernate.model.Nota;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.hibernate.dao.HibernateUtil;

import java.util.List;
import java.util.Scanner;

public class AppController {

    private GestionBD gestionBD = new GestionBD();
    private Scanner sc = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("========== MENÚ ALUMNOS ==========");
            System.out.println("1. Dar de alta alumno");
            System.out.println("2. Dar de baja alumno");
            System.out.println("3. Insertar nota");
            System.out.println("4. Modificar nota");
            System.out.println("5. Consultar nota de un alumno");
            System.out.println("6. Consultar todas las notas");
            System.out.println("0. Salir");
            System.out.println("==================================");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    altaAlumno();
                    break;
                case 2:
                    bajaAlumno();
                    break;
                case 3:
                    insertarNota();
                    break;
                case 4:
                    modificarNota();
                    break;
                case 5:
                    consultarNota();
                    break;
                case 6:
                    consultarTodasNotas();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción incorrecta");
                    break;
            }

        } while (opcion != 0);
    }

    // Alta alumno
    private void altaAlumno() {
        System.out.print("Expediente: ");
        String expediente = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = sc.nextLine();

        Alumno a = new Alumno(expediente, nombre, apellidos);
        gestionBD.altaAlumno(a);
    }

    // Baja alumno
    private void bajaAlumno() {
        System.out.print("Expediente del alumno a eliminar: ");
        String expediente = sc.nextLine();
        gestionBD.bajaAlumno(expediente);
    }

    // Insertar nota por expediente
    private void insertarNota() {
        System.out.print("Expediente del alumno: ");
        String expediente = sc.nextLine();

        Alumno alumno = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            alumno = session.get(Alumno.class, expediente);
            if (alumno != null) {
                System.out.print("Nota: ");
                double valorNota = sc.nextDouble();
                sc.nextLine();
                gestionBD.insertarNota(alumno, valorNota);
            } else {
                System.out.println("Alumno no encontrado.");
            }
        }


    }

    // Modificar nota por expediente
    private void modificarNota() {
        System.out.print("Expediente del alumno: ");
        String expediente = sc.nextLine();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Alumno alumno = session.get(Alumno.class, expediente);

            if (alumno != null) {
                List<Nota> notas = session.createQuery(
                                "from Nota n where n.alumno.expediente = :exp", Nota.class)
                        .setParameter("exp", expediente)
                        .list();

                if (notas.isEmpty()) {
                    System.out.println("El alumno no tiene notas registradas.");
                    tx.rollback();
                } else {
                    System.out.println("Notas actuales:");
                    for (int i = 0; i < notas.size(); i++) {
                        System.out.println((i + 1) + ". " + notas.get(i).getNota());
                    }
                    System.out.print("Seleccione la nota a modificar (número): ");
                    int index = sc.nextInt() - 1;
                    sc.nextLine();

                    if (index >= 0 && index < notas.size()) {
                        System.out.print("Nueva nota: ");
                        double nuevaNota = sc.nextDouble();
                        sc.nextLine();

                        notas.get(index).setNota(nuevaNota);
                        session.merge(notas.get(index));
                        tx.commit();
                        System.out.println("Nota modificada correctamente.");
                    } else {
                        System.out.println("Número de nota inválido.");
                        tx.rollback();
                    }
                }
            } else {
                System.out.println("Alumno no encontrado.");
                tx.rollback();
            }
        } catch (Exception e) {
            System.out.println("Error modificando nota: " + e.getMessage());
        }
    }

    // Consultar notas por expediente
    private void consultarNota() {
        System.out.print("Expediente del alumno: ");
        String expediente = sc.nextLine();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Alumno alumno = session.get(Alumno.class, expediente);
            if (alumno != null) {
                List<Nota> notas = session.createQuery(
                                "from Nota n where n.alumno.expediente = :exp", Nota.class)
                        .setParameter("exp", expediente)
                        .list();

                if (notas.isEmpty()) {
                    System.out.println("El alumno no tiene notas registradas.");
                } else {
                    System.out.println("Notas del alumno:");
                    for (Nota n : notas) {
                        System.out.println("Nota: " + n.getNota());
                    }
                }
            } else {
                System.out.println("Alumno no encontrado.");
            }
        }
    }

    // Consultar todas las notas
    private void consultarTodasNotas() {
        List<Nota> notas = gestionBD.consultarTodasNotas();
        if (notas.isEmpty()) {
            System.out.println("No hay notas registradas.");
        } else {
            for (Nota n : notas) {
                System.out.println("Alumno: " + n.getAlumno().getExpediente() +
                        " | Nota: " + n.getNota());
            }
        }
    }
}
