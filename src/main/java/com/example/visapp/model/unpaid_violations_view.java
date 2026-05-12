package com.example.visapp.model;

public class unpaid_violations_view {

    private int violation_id;
    private int vehicle_id;
    private String violation_type;
    private double fine_amount;
    private String status;

    public unpaid_violations_view(
            int violation_id,
            int vehicle_id,
            String violation_type,
            double fine_amount,
            String status
    ) {
        this.violation_id = violation_id;
        this.vehicle_id = vehicle_id;
        this.violation_type = violation_type;
        this.fine_amount = fine_amount;
        this.status = status;
    }

    public int getViolation_id() { return violation_id; }
    public int getVehicle_id() { return vehicle_id; }
    public String getViolation_type() { return violation_type; }
    public double getFine_amount() { return fine_amount; }
    public String getStatus() { return status; }
}