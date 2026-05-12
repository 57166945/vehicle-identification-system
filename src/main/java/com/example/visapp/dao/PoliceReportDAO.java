package com.example.visapp.dao;

import com.example.visapp.controller.LoginController;
import com.example.visapp.model.police_report;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PoliceReportDAO {

    private Connection conn =
            LoginController.getDbConnection().getConnection();

    // ================= GET ALL =================
    public List<police_report> getAll() {

        List<police_report> reports = new ArrayList<>();

        String sql = "SELECT * FROM police_report";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                reports.add(new police_report(
                        rs.getInt("report_id"),
                        rs.getInt("vehicle_id"),
                        rs.getDate("report_date"),
                        rs.getString("report_type"),
                        rs.getString("description"),
                        rs.getString("officer_name"),
                        rs.getString("station_name")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reports;
    }

    // ================= INSERT =================
    public void insert(police_report pr) {

        String sql =
                "INSERT INTO police_report(vehicle_id, report_date, report_type, description, officer_name, station_name) VALUES(?,?,?,?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pr.getVehicle_id());
            ps.setDate(2, pr.getReport_date());
            ps.setString(3, pr.getReport_type());
            ps.setString(4, pr.getDescription());
            ps.setString(5, pr.getOfficer_name());
            ps.setString(6, pr.getStation_name());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= UPDATE =================
    public void update(police_report pr) {

        String sql =
                "UPDATE police_report SET vehicle_id=?, report_date=?, report_type=?, description=?, officer_name=?, station_name=? WHERE report_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pr.getVehicle_id());
            ps.setDate(2, pr.getReport_date());
            ps.setString(3, pr.getReport_type());
            ps.setString(4, pr.getDescription());
            ps.setString(5, pr.getOfficer_name());
            ps.setString(6, pr.getStation_name());
            ps.setInt(7, pr.getReport_id());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= DELETE =================
    public void delete(int id) {

        String sql =
                "DELETE FROM police_report WHERE report_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ================= COUNT =================
    public int count() {

        String sql = "SELECT COUNT(*) FROM police_report";

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