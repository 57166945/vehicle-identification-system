package com.example.visapp.model;

public class vehicle_owner_view {

    private int vehicle_id;
    private String registration_number;
    private String make;
    private String model;
    private int year;
    private int customer_id;
    private String owner_name;
    private String phone;
    private String email;

    public vehicle_owner_view(
            int vehicle_id,
            String registration_number,
            String make,
            String model,
            int year,
            int customer_id,
            String owner_name,
            String phone,
            String email
    ) {
        this.vehicle_id = vehicle_id;
        this.registration_number = registration_number;
        this.make = make;
        this.model = model;
        this.year = year;
        this.customer_id = customer_id;
        this.owner_name = owner_name;
        this.phone = phone;
        this.email = email;
    }

    public int getVehicle_id() { return vehicle_id; }
    public String getRegistration_number() { return registration_number; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public int getCustomer_id() { return customer_id; }
    public String getOwner_name() { return owner_name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
}