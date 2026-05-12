package com.example.visapp.dao;

import com.example.visapp.DBConnection;
import com.example.visapp.model.vehicle_owner_view;
import com.example.visapp.model.active_insurance_view;
import com.example.visapp.model.unpaid_violations_view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ViewDAO {

    private Connection conn;

    public ViewDAO() {

        try {

            conn = new DBConnection(
                    "postgres.awdrswdcmoovuyowmmpj",
                    "Doctor@45paduka"
            ).getConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =========================================
    // VEHICLE OWNER VIEW
    // =========================================

    public ObservableList<vehicle_owner_view> getVehicleOwnerView() {

        ObservableList<vehicle_owner_view> list =
                FXCollections.observableArrayList();

        try {

            String sql =
                    "SELECT * FROM vehicle_owner_view";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                list.add(new vehicle_owner_view(

                        rs.getInt("vehicle_id"),
                        rs.getString("registration_number"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getInt("customer_id"),
                        rs.getString("owner_name"),
                        rs.getString("phone"),
                        rs.getString("email")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // =========================================
    // ACTIVE INSURANCE VIEW
    // =========================================

    public ObservableList<active_insurance_view> getInsuranceView() {

        ObservableList<active_insurance_view> list =
                FXCollections.observableArrayList();

        try {

            String sql =
                    "SELECT * FROM active_insurance_view";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                list.add(new active_insurance_view(

                        rs.getInt("policy_id"),
                        rs.getString("policy_number"),
                        rs.getString("insurance_company"),
                        rs.getString("start_date"),
                        rs.getString("end_date"),
                        rs.getString("registration_number"),
                        rs.getString("make"),
                        rs.getString("model")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // =========================================
    // UNPAID VIOLATIONS VIEW
    // =========================================

    public ObservableList<unpaid_violations_view> getViolationView() {

        ObservableList<unpaid_violations_view> list =
                FXCollections.observableArrayList();

        try {

            String sql =
                    "SELECT * FROM unpaid_violations_view";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                list.add(new unpaid_violations_view(

                        rs.getInt("violation_id"),
                        rs.getInt("vehicle_id"),
                        rs.getString("violation_type"),
                        rs.getDouble("fine_amount"),
                        rs.getString("status")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}