package com.example.visapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private Connection conn;

    public DBConnection(String user, String pass) throws SQLException, ClassNotFoundException {
        // Load PostgreSQL driver
        Class.forName("org.postgresql.Driver");

        // Supabase connection string
        // Replace <project-id> with your actual Supabase project ref (found in Supabase dashboard)
        String url = "jdbc:postgresql://aws-0-eu-west-1.pooler.supabase.com:5432/postgres?";

        conn = DriverManager.getConnection(url, user, pass);
    }

    public Connection getConnection() {
        return conn;
    }

    public void close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
