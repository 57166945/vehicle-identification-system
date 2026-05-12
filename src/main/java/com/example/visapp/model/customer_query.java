package com.example.visapp.model;

import java.sql.Date;

public class customer_query {
    private int query_id;
    private int customer_id;
    private int vehicle_id;
    private Date query_date;
    private String query_text;
    private String response_text;

    public customer_query() {

    }

    public customer_query(int query_id, int customer_id, int vehicle_id, Date query_date, String query_text, String response_text) {
        this.query_id = query_id;
        this.customer_id = customer_id;
        this.vehicle_id = vehicle_id;
        this.query_date = query_date;
        this.query_text = query_text;
        this.response_text = response_text;
    }

    public int getQueryId() { return query_id; }
    public void setQueryId(int query_id) { this.query_id = query_id; }

    public int getCustomerId() { return customer_id; }
    public void setCustomerId(int customer_id) { this.customer_id = customer_id; }

    public int getVehicleId() { return vehicle_id; }
    public void setVehicleId(int vehicle_id) { this.vehicle_id = vehicle_id; }

    public Date getQueryDate() { return query_date; }
    public void setQueryDate(Date query_date) { this.query_date = query_date; }

    public String getQuery_text() { return query_text; }
    public void setQuery_text(String query_text) { this.query_text = query_text; }

    public String getResponseText() { return response_text; }
    public void setResponseText(String response_text) { this.response_text = response_text; }


}

