package com.example.visapp.model;

import java.math.BigDecimal;
import java.sql.Date;

public class violation {
    private int violation_id;
    private int vehicle_id;
    private Date violation_date;
    private String violation_type;
    private BigDecimal fine_amount;
    private String status;

    public violation(){

    }

    public violation(int violation_id, int vehicle_id, Date violation_date, String violation_type, BigDecimal fine_amount, String status) {
        this.violation_id = violation_id;
        this.vehicle_id = vehicle_id;
        this.violation_date = violation_date;
        this.violation_type = violation_type;
        this.fine_amount = fine_amount;
        this.status = status;
    }

    public int getViolation_id() { return violation_id; }
    public void setViolation_id(int violation_id) { this.violation_id = violation_id; }

    public int getVehicle_id() { return vehicle_id; }
    public void setVehicle_id(int vehicle_id) { this.vehicle_id = vehicle_id; }

    public Date getViolation_date() { return violation_date; }
    public void setViolation_date(Date violation_date) { this.violation_date = violation_date; }

    public String getViolation_type() { return violation_type; }
    public void setViolation_type(String violation_type) { this.violation_type = violation_type; }

    public BigDecimal getFine_amount() { return fine_amount; }
    public void setFine_amount(BigDecimal fine_amount) { this.fine_amount = fine_amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

