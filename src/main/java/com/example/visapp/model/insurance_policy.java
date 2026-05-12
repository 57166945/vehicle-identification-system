package com.example.visapp.model;

import java.sql.Date;

public class insurance_policy {
    private int policy_id;
    private int vehicle_id;
    private String insurance_company;
    private String policy_number;
    private Date start_date;
    private Date end_date;
    private String coverage_details;

    public insurance_policy() {

    }

    public insurance_policy(int policy_id, int vehicle_id, String insurance_company, String policy_number, Date start_date, Date end_date, String coverage_details) {
        this.policy_id = policy_id;
        this.vehicle_id = vehicle_id;
        this.insurance_company = insurance_company;
        this.policy_number = policy_number;
        this.start_date = start_date;
        this.end_date = end_date;
        this.coverage_details = coverage_details;
    }

    public int getPolicy_id() { return policy_id; }
    public void setPolicy_id(int policy_id) { this.policy_id = policy_id; }

    public int getVehicle_id() { return vehicle_id; }
    public void setVehicle_id(int vehicle_id) { this.vehicle_id = vehicle_id; }

    public String getInsurance_company() { return insurance_company; }
    public void setInsurance_company(String insurance_company) { this.insurance_company = insurance_company; }

    public String getPolicy_number() { return policy_number; }
    public void setPolicy_number(String policy_number) { this.policy_number = policy_number; }

    public Date getStart_date() { return start_date; }
    public void setStart_date(Date start_date) { this.start_date = start_date; }

    public Date getEnd_date() { return end_date; }
    public void setEnd_date(Date end_date) { this.end_date = end_date; }

    public String getCoverage_details() { return coverage_details; }
    public void setCoverage_details(String coverage_details) {this.coverage_details = coverage_details;
    }
}

