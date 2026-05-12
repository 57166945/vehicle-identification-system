package com.example.visapp.model;

public class Person {

    protected String name;
    protected String phone;

    // EMPTY CONSTRUCTOR
    public Person() {

    }

    // PARAMETERIZED CONSTRUCTOR
    public Person(String name, String phone) {

        this.name = name;
        this.phone = phone;
    }

    // GETTERS & SETTERS

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}

    // POLYMORPHISM METHOD
    public String getRole() {return "Person";}

}