package com.example.visapp.dao;

import com.example.visapp.controller.LoginController;
import com.example.visapp.model.service_record;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class ServiceRecordDAO {
    private Connection conn = LoginController.getDbConnection().getConnection();

    public List<service_record> findAll() throws SQLException {
        List<service_record> records = new ArrayList<>();
        String sql = "SELECT * FROM service_record";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                records.add(new service_record(rs.getInt("service_id"),
                        rs.getInt("vehicle_id"),
                        rs.getDate("service_date"),
                        rs.getString("service_type"),
                        rs.getString("description"),
                        rs.getBigDecimal("cost")));
            }
        }
        return records;
    }

    public void insert(service_record sr) {

        String sql =
                "INSERT INTO service_record " +
                        "(vehicle_id, service_date, service_type, description, cost) " +
                        "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps =
                     conn.prepareStatement(sql)) {

            ps.setInt(1, sr.getVehicle_id());

            ps.setDate(2, sr.getService_date());

            ps.setString(3, sr.getService_type());

            ps.setString(4, sr.getDescription());

            ps.setBigDecimal(5, sr.getCost());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // =====================================================
    // UPDATE
    // =====================================================

    public void update(service_record sr) {

        String sql = "UPDATE service_record SET " +
                        "vehicle_id=?, " +
                        "service_date=?, " +
                        "service_type=?, " +
                        "description=?, " +
                        "cost=? " +
                        "WHERE service_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, sr.getVehicle_id());

            ps.setDate(2, sr.getService_date());

            ps.setString(3, sr.getService_type());

            ps.setString(4, sr.getDescription());

            ps.setBigDecimal(5, sr.getCost());

            ps.setInt(6, sr.getService_id());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM service_record WHERE record_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public int count() throws SQLException {
        String sql = "SELECT COUNT(*) FROM service_record";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            rs.next();
            return rs.getInt(1);
        }
    }
}