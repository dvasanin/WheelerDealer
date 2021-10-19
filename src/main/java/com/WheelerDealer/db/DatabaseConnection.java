package com.WheelerDealer.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection conn = null;

    static {
        String url = "jdbc:postgresql://localhost:5432/wheelerdealer?user=postgres&password=nekasifra";
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return conn;
    }

}
