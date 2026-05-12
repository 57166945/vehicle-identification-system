package com.example.visapp.dao;

import com.example.visapp.controller.LoginController;
import com.example.visapp.model.claim;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClaimDAO {

    private Connection conn =
            LoginController.getDbConnection().getConnection();

    // ================= FIND ALL =================
    public List<claim> findAll() {

        List<claim> claims = new ArrayList<>();

        String sql = "SELECT * FROM claim";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                claims.add(new claim(
                        rs.getInt("claim_id"),
                        rs.getInt("policy_id"),
                        rs.getDate("claim_date"),
                        rs.getBigDecimal("claim_amount"),
                        rs.getString("status")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return claims;
    }

    // ================= INSERT =================
    public void insert(claim c) {

        String sql =
                "INSERT INTO claim(policy_id, claim_date, claim_amount, status) VALUES(?,?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.getPolicy_id());
            ps.setDate(2, c.getClaim_date());
            ps.setBigDecimal(3, c.getClaim_amount());
            ps.setString(4, c.getStatus());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= UPDATE =================
    public void update(claim c) {

        String sql =
                "UPDATE claim SET policy_id=?, claim_date=?, claim_amount=?, status=? WHERE claim_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.getPolicy_id());
            ps.setDate(2, c.getClaim_date());
            ps.setBigDecimal(3, c.getClaim_amount());
            ps.setString(4, c.getStatus());
            ps.setInt(5, c.getClaim_id());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= DELETE =================
    public void delete(int id) {

        String sql =
                "DELETE FROM claim WHERE claim_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= COUNT =================
    public int count() {

        String sql = "SELECT COUNT(*) FROM claim";

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