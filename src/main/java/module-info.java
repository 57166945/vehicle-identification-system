module com.example.visapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.visapp to javafx.fxml;
    opens com.example.visapp.model to javafx.fxml;
    opens com.example.visapp.controller to javafx.fxml;
    opens com.example.visapp.dao to javafx.fxml;

    exports com.example.visapp.model;
    exports com.example.visapp.controller;
    exports com.example.visapp.dao;
    exports com.example.visapp;
}