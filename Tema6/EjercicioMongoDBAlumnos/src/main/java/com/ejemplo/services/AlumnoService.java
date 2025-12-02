package com.ejemplo.services;

import com.ejemplo.database.MongoDBConnection;
import com.ejemplo.model.Alumno;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import java.util.ArrayList;
import java.util.List;

public class AlumnoService {

    private final MongoCollection<Document> collection;

    public AlumnoService() {
        this.collection = MongoDBConnection.getCollection();
    }

    // Helper: Alumno -> Document
    private Document toDocument(Alumno a) {
        Document d = new Document();
        d.append("expediente", a.getExpediente());
        d.append("nombre", a.getNombre());
        d.append("apellidos", a.getApellidos());
        if (a.getNota() != null) {
            d.append("nota", a.getNota());
        } else {
            d.append("nota", null);
        }
        return d;
    }

    // Helper: Document -> Alumno
    private Alumno fromDocument(Document d) {
        if (d == null) return null;
        String expediente = d.getString("expediente");
        String nombre = d.getString("nombre");
        String apellidos = d.getString("apellidos");
        Double nota = null;
        Object notaObj = d.get("nota");
        if (notaObj instanceof Number) {
            nota = ((Number) notaObj).doubleValue();
        }
        return new Alumno(expediente, nombre, apellidos, nota);
    }

    // 1) Dar de alta un alumno
    public boolean addAlumno(Alumno a) {
        // comprobar si ya existe por expediente
        Bson filter = Filters.eq("expediente", a.getExpediente());
        Document existing = collection.find(filter).first();
        if (existing != null) {
            return false; // ya existe
        }
        Document doc = toDocument(a);
        collection.insertOne(doc);
        return true;
    }

    // 2) Dar de baja (por expediente)
    public boolean deleteAlumno(String expediente) {
        Bson filter = Filters.eq("expediente", expediente);
        DeleteResult result = collection.deleteOne(filter);
        return result.getDeletedCount() > 0;
    }

    // 3) Insertar nota (si ya existía, la sobrescribe)
    public boolean insertarNota(String expediente, Double nota) {
        Bson filter = Filters.eq("expediente", expediente);
        Document existing = collection.find(filter).first();
        if (existing == null) {
            return false; // no existe alumno
        }
        Document update = new Document("$set", new Document("nota", nota));
        UpdateResult result = collection.updateOne(filter, update);
        return result.getModifiedCount() > 0;
    }

    // 4) Modificar nota (mismo comportamiento que insertar en esta implementación)
    public boolean modificarNota(String expediente, Double nuevaNota) {
        return insertarNota(expediente, nuevaNota);
    }

    // 5) Consultar nota por expediente
    public Double consultarNota(String expediente) {
        Bson filter = Filters.eq("expediente", expediente);
        Document doc = collection.find(filter).first();
        if (doc == null) return null;
        Object notaObj = doc.get("nota");
        if (notaObj instanceof Number) {
            return ((Number) notaObj).doubleValue();
        } else {
            return null;
        }
    }

    // 6) Consultar todas las notas (devuelve lista de alumnos)
    public List<Alumno> consultarTodasNotas() {
        List<Alumno> lista = new ArrayList<>();
        FindIterable<Document> iter = collection.find();
        for (Document d : iter) {
            Alumno a = fromDocument(d);
            lista.add(a);
        }
        return lista;
    }

    // Método auxiliar para obtener alumno por expediente (opcional)
    public Alumno getAlumno(String expediente) {
        Bson filter = Filters.eq("expediente", expediente);
        Document d = collection.find(filter).first();
        return fromDocument(d);
    }
}
