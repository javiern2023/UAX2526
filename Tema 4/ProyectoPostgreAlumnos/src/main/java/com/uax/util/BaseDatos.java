package com.uax.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class BaseDatos {


        private static final String URL = "jdbc:postgresql://localhost:5432/universidad";
        private static final String USER = "postgres";
        private static final String PASS = "1234";


        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASS);
        }

}
