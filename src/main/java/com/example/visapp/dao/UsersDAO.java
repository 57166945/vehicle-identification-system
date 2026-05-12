package com.example.visapp.dao;

import com.example.visapp.controller.LoginController;
import com.example.visapp.model.users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {

    private Connection conn =
            LoginController.getDbConnection().getConnection();

    // =========================================
    // FIND ALL USERS
    // =========================================
    public List<users> findAll() throws SQLException {

        List<users> usersList = new ArrayList<>();

        String sql = "SELECT * FROM users ORDER BY user_id ASC";

        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)
        ) {

            while (rs.next()) {

                usersList.add(new users(
                                rs.getInt("user_id"),
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("role")
                        )
                );
            }
        }

        return usersList;
    }

    // =========================================
    // INSERT USER
    // =========================================
    public void insert(users users) throws SQLException {

        String sql = """
                INSERT INTO users (username, password, role)
                VALUES (?, ?, ?)
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, users.getUsername());
            ps.setString(2, users.getPassword());
            ps.setString(3, users.getRole());

            ps.executeUpdate();
        }
    }

    // =========================================
    // UPDATE USER
    // =========================================
    public void update(users users) throws SQLException {

        String sql = """
                UPDATE users
                SET
                    username = ?,
                    password = ?,
                    role = ?
                WHERE user_id = ?
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, users.getUsername());
            ps.setString(2, users.getPassword());
            ps.setString(3, users.getRole());
            ps.setInt(4, users.getUserId());

            ps.executeUpdate();
        }
    }

    // =========================================
    // DELETE USER
    // =========================================
    public void delete(int id) throws SQLException {

        String sql = "DELETE FROM users WHERE user_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();
        }
    }

    // =========================================
    // SEARCH USERS
    // =========================================
    public List<users> search(String keyword) throws SQLException {

        List<users> usersList = new ArrayList<>();

        String sql = """
                SELECT * FROM users WHERE
                    LOWER(username) LIKE LOWER(?)
                    OR LOWER(password) LIKE LOWER(?)
                    OR LOWER(role) LIKE LOWER(?)
                ORDER BY user_id ASC
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            String searchKeyword = "%" + keyword + "%";

            ps.setString(1, searchKeyword);
            ps.setString(2, searchKeyword);
            ps.setString(3, searchKeyword);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                usersList.add(new users(
                                rs.getInt("user_id"),
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("role")
                        )
                );
            }
        }

        return usersList;
    }

    // =========================================
    // COUNT USERS
    // =========================================
    public int count() throws SQLException {

        String sql = "SELECT COUNT(*) FROM users";

        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)
        ) {

            rs.next();

            return rs.getInt(1);
        }
    }
}