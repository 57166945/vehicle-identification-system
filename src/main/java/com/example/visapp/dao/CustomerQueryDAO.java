package com.example.visapp.dao;

import com.example.visapp.model.customer_query;
import com.example.visapp.controller.LoginController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerQueryDAO {

    private Connection conn = LoginController.getDbConnection().getConnection();

    // ================= FIND ALL =================
    public List<customer_query> findAll() {
        List<customer_query> queries = new ArrayList<>();
        String sql = "SELECT * FROM customer_query";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                queries.add(new customer_query(
                        rs.getInt("query_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("vehicle_id"),
                        rs.getDate("query_date"),
                        rs.getString("query_text"),
                        rs.getString("response_text")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return queries;
    }

    // ================= INSERT =================
    public void insert(customer_query cq) {
        String sql = "INSERT INTO customer_query(customer_id, vehicle_id, query_date, query_text, response_text) VALUES(?,?,?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cq.getCustomerId());
            ps.setInt(2, cq.getVehicleId());
            ps.setDate(3, cq.getQueryDate());
            ps.setString(4, cq.getQuery_text());
            ps.setString(5, cq.getResponseText());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= UPDATE =================
    public void update(customer_query cq) {
        String sql = "UPDATE customer_query SET customer_id=?, vehicle_id=?, query_date=?, query_text=?, response_text=? WHERE query_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cq.getCustomerId());
            ps.setInt(2, cq.getVehicleId());
            ps.setDate(3, cq.getQueryDate());
            ps.setString(4, cq.getQuery_text());
            ps.setString(5, cq.getResponseText());
            ps.setInt(6, cq.getQueryId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= DELETE =================
    public void delete(int id) {
        String sql = "DELETE FROM customer_query WHERE query_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= COUNT =================
    public int count() {
        String sql = "SELECT COUNT(*) FROM customer_query";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}