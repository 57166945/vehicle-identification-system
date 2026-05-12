package com.example.visapp.dao;

import com.example.visapp.controller.LoginController;
import com.example.visapp.model.violation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViolationDAO {

    private Connection conn =
            LoginController.getDbConnection().getConnection();

    // ================= GET ALL =================
    public List<violation> getAll() {

        List<violation> violations = new ArrayList<>();

        String sql = "SELECT * FROM violation";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                violations.add(new violation(
                        rs.getInt("violation_id"),
                        rs.getInt("vehicle_id"),
                        rs.getDate("violation_date"),
                        rs.getString("violation_type"),
                        rs.getBigDecimal("fine_amount"),
                        rs.getString("status")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return violations;
    }

    // ================= INSERT =================
    public void insert(violation v) {

        String sql =
                "INSERT INTO violation(vehicle_id, violation_date, violation_type, fine_amount, status) VALUES(?,?,?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, v.getVehicle_id());
            ps.setDate(2, v.getViolation_date());
            ps.setString(3, v.getViolation_type());
            ps.setBigDecimal(4, v.getFine_amount());
            ps.setString(5, v.getStatus());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= UPDATE =================
    public void update(violation v) {

        String sql =
                "UPDATE violation SET vehicle_id=?, violation_date=?, violation_type=?, fine_amount=?, status=? WHERE violation_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, v.getVehicle_id());
            ps.setDate(2, v.getViolation_date());
            ps.setString(3, v.getViolation_type());
            ps.setBigDecimal(4, v.getFine_amount());
            ps.setString(5, v.getStatus());
            ps.setInt(6, v.getViolation_id());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= DELETE =================
    public void delete(int id) {

        String sql =
                "DELETE FROM violation WHERE violation_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= COUNT =================
    public int count() {

        String sql = "SELECT COUNT(*) FROM violation";

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