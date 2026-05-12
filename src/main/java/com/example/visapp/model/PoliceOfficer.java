package com.example.visapp.model;

public class PoliceOfficer extends Person {

    public PoliceOfficer(String name, String phone) {
        super(name, phone);
    }

    @Override
    public String getRole() {
        return "Police Officer";
    }
}
