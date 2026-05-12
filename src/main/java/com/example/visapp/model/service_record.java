package com.example.visapp.model;

import java.math.BigDecimal;
import java.sql.Date;

public class service_record {
    private int service_id;
    private int vehicle_id;
    private Date service_date;
    private String service_type;
    private String description;
    private BigDecimal cost;

    public service_record(int service_id, int vehicle_id, Date service_date, String service_type, String description, BigDecimal cost) {
        this.service_id = service_id;
        this.vehicle_id = vehicle_id;
        this.service_date = service_date;
        this.service_type = service_type;
        this.description = description;
        this.cost = cost;
    }

    public int getService_id() { return service_id; }
    public void setService_id(int service_id) { this.service_id = service_id; }

    public int getVehicle_id() { return vehicle_id; }
    public void setVehicle_id(int vehicle_id) { this.vehicle_id = vehicle_id; }

    public Date getService_date() { return service_date; }
    public void setService_date(Date service_date) { this.service_date = service_date; }

    public String getService_type() { return service_type; }
    public void setService_type(String service_type) { this.service_type = service_type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getCost() { return cost; }
    public void setCost(BigDecimal cost) { this.cost = cost; }
}
