package com.example.visapp.dao;

import com.example.visapp.model.insurance_policy;
import com.example.visapp.controller.LoginController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InsurancePolicyDAO {

    private Connection conn =
            LoginController.getDbConnection().getConnection();

    // ================= FIND ALL =================
    public List<insurance_policy> findAll() {

        List<insurance_policy> policies = new ArrayList<>();

        String sql = "SELECT * FROM insurance_policy";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                policies.add(new insurance_policy(
                        rs.getInt("policy_id"),
                        rs.getInt("vehicle_id"),
                        rs.getString("insurance_company"),
                        rs.getString("policy_number"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getString("coverage_details")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return policies;
    }

    // ================= INSERT =================
    public void insert(insurance_policy ip) {

        String sql =
                "INSERT INTO insurance_policy(vehicle_id, insurance_company, policy_number, start_date, end_date, coverage_details) VALUES(?,?,?,?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ip.getVehicle_id());
            ps.setString(2, ip.getInsurance_company());
            ps.setString(3, ip.getPolicy_number());
            ps.setDate(4, ip.getStart_date());
            ps.setDate(5, ip.getEnd_date());
            ps.setString(6, ip.getCoverage_details());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= UPDATE =================
    public void update(insurance_policy ip) {

        String sql =
                "UPDATE insurance_policy SET vehicle_id=?, insurance_company=?, policy_number=?, start_date=?, end_date=?, coverage_details=? WHERE policy_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ip.getVehicle_id());
            ps.setString(2, ip.getInsurance_company());
            ps.setString(3, ip.getPolicy_number());
            ps.setDate(4, ip.getStart_date());
            ps.setDate(5, ip.getEnd_date());
            ps.setString(6, ip.getCoverage_details());
            ps.setInt(7, ip.getPolicy_id());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= DELETE =================
    public void delete(int id) {

        String sql =
                "DELETE FROM insurance_policy WHERE policy_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= COUNT =================
    public int count() {

        String sql = "SELECT COUNT(*) FROM insurance_policy";

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