package com.example.visapp.model;

import java.sql.Date;

public class police_report {
    private int report_id;
    private int vehicle_id;
    private Date report_date;
    private String report_type;
    private String description;
    private String officer_name;
    private String station_name;

    public police_report(){

    }

    public police_report(int report_id, int vehicle_id, Date report_date, String report_type, String description, String officer_name, String station_name) {
        this.report_id = report_id;
        this.vehicle_id = vehicle_id;
        this.report_date = report_date;
        this.report_type = report_type;
        this.description = description;
        this.officer_name = officer_name;
        this.station_name = station_name;
    }

    public int getReport_id() { return report_id; }
    public void setReport_id(int report_id) { this.report_id = report_id; }

    public int getVehicle_id() { return vehicle_id; }
    public void setVehicle_id(int vehicle_id) { this.vehicle_id = vehicle_id; }

    public Date getReport_date() { return report_date; }
    public void setReport_date(Date report_date) { this.report_date = report_date; }

    public String getReport_type() { return report_type; }
    public void setReport_type(String report_type) { this.report_type = report_type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getOfficer_name() { return officer_name; }
    public void setOfficer_name(String officer_name) { this.officer_name = officer_name; }

    public String getStation_name() { return station_name; }
    public void setStation_name(String station_name) { this.station_name = station_name; }
}
