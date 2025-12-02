package com.ejemplo.motos.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:postgresql://localhost:5432/concesionario";
    private static final String USER = "postgres";
    private static final String PASS = "1234";

    public static Connection getConnection () throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
