package com.ejemplo.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBConnection {

    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "universidad";
    private static final String COLLECTION_NAME = "alumnos";

    private static MongoClient mongoClient;

    // Obtener cliente singleton
    public static MongoClient getClient() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(CONNECTION_STRING);
        }
        return mongoClient;
    }

    // Obtener base de datos
    public static MongoDatabase getDatabase() {
        return getClient().getDatabase(DATABASE_NAME);
    }

    // Obtener colección de documentos
    public static MongoCollection<Document> getCollection() {
        return getDatabase().getCollection(COLLECTION_NAME);
    }

    // Cerrar cliente (opcional)
    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }
    }
}
