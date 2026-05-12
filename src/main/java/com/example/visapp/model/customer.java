package com.example.visapp.model;

public class customer extends Person {
    private int customer_id;
    private String address;
    private String email;

    // EMPTY CONSTRUCTOR
    public customer(){

        super();
    }

    // PARAMETERIZED CONSTRUCTOR
    public customer(int customer_id, String name,String address, String phone, String email) {
        super(name, phone);

        this.customer_id = customer_id;
        this.address = address;
        this.email = email;
    }

    // GETTERS & SETTERS
    public int getCustomerId() { return customer_id; }
    public void setCustomerId(int customer_id) { this.customer_id = customer_id; }

    @Override
    public String getName() { return name; }

    @Override
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String getPhone() { return phone; }

    @Override
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // POLYMORPHISM
    @Override
    public String getRole() {
        return "Customer";
    }
}

