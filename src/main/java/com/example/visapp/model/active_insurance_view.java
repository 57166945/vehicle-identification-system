package com.example.visapp.model;

public class active_insurance_view {

    private int policy_id;
    private String policy_number;
    private String insurance_company;
    private String start_date;
    private String end_date;
    private String registration_number;
    private String make;
    private String model;

    public active_insurance_view(
            int policy_id,
            String policy_number,
            String insurance_company,
            String start_date,
            String end_date,
            String registration_number,
            String make,
            String model
    ) {
        this.policy_id = policy_id;
        this.policy_number = policy_number;
        this.insurance_company = insurance_company;
        this.start_date = start_date;
        this.end_date = end_date;
        this.registration_number = registration_number;
        this.make = make;
        this.model = model;
    }

    public int getPolicy_id() { return policy_id; }
    public String getPolicy_number() { return policy_number; }
    public String getInsurance_company() { return insurance_company; }
    public String getStart_date() { return start_date; }
    public String getEnd_date() { return end_date; }
    public String getRegistration_number() { return registration_number; }
    public String getMake() { return make; }
    public String getModel() { return model; }
}