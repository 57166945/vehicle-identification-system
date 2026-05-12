package com.example.visapp.dao;

import com.example.visapp.controller.LoginController;
import com.example.visapp.model.vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {
    private Connection conn = LoginController.getDbConnection().getConnection();

    public List<vehicle> findAll() throws SQLException {
        List<vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicle";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                vehicles.add(new vehicle(rs.getInt("vehicle_id"),
                        rs.getString("registration_number"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getInt("owner_id")));
            }
        }
        return vehicles;
    }

    public void insert(vehicle v) throws SQLException {
        String sql = "CALL register_vehicle(?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, v.getRegistrationNumber());
            ps.setString(2, v.getMake());
            ps.setString(3, v.getModel());
            ps.setInt(4, v.getYear());
            ps.setInt(5, v.getOwner_id());
            ps.executeUpdate();
        }
    }

    public void update(vehicle v) throws SQLException {
        String sql = "UPDATE vehicle SET registration_number=?, make=?, model=?, year=?, owner_id=? WHERE vehicle_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, v.getRegistrationNumber());
            ps.setString(2, v.getMake());
            ps.setString(3, v.getModel());
            ps.setInt(4, v.getYear());
            ps.setInt(5, v.getOwner_id());
            ps.setInt(6, v.getVehicleId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM vehicle WHERE vehicle_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public int count() throws SQLException {
        String sql = "SELECT COUNT(*) FROM vehicle";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            rs.next();
            return rs.getInt(1);
        }
    }
}
