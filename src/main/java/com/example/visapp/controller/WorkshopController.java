package com.example.visapp.controller;

import com.example.visapp.dao.ServiceRecordDAO;
import com.example.visapp.dao.VehicleDAO;
import com.example.visapp.model.service_record;
import com.example.visapp.model.vehicle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.sql.Date;

import java.io.FileWriter;

public class WorkshopController {

    // =====================================================
    // VEHICLE TABLE
    // =====================================================

    @FXML private TableView<vehicle> vehicleTable;
    @FXML private TableColumn<vehicle, Integer> colVehicleId;
    @FXML private TableColumn<vehicle, String> colRegNo;
    @FXML private TableColumn<vehicle, String> colMake;
    @FXML private TableColumn<vehicle, String> colModel;
    @FXML private TableColumn<vehicle, Integer> colYear;
    @FXML private TableColumn<vehicle, Integer> colOwnerId;

    // =====================================================
    // SERVICE TABLE
    // =====================================================

    @FXML private TableView<service_record> serviceTable;
    @FXML private TableColumn<service_record, Integer> colRecordId;
    @FXML private TableColumn<service_record, Integer> colVehicleIdSR;
    @FXML private TableColumn<service_record, Date> colServiceDate;
    @FXML private TableColumn<service_record, String> colServiceType;
    @FXML private TableColumn<service_record, String> colDescription;
    @FXML private TableColumn<service_record, BigDecimal> colCost;


    // =====================================================
    // VEHICLE FORM
    // =====================================================

    @FXML private TextField txtVehicleId;
    @FXML private TextField txtRegNo;
    @FXML private TextField txtMake;
    @FXML private TextField txtModel;
    @FXML private TextField txtYear;
    @FXML private TextField txtOwner;

    // =====================================================
    // SERVICE FORM
    // =====================================================

    @FXML private TextField txtRecordId;
    @FXML private TextField txtVehicleIdService;
    @FXML private DatePicker dpServiceDate;
    @FXML private TextField txtServiceType;
    @FXML private TextArea txtServiceDescription;
    @FXML private TextField txtCost;


    // =====================================================
    // DAO
    // =====================================================

    private VehicleDAO vehicleDAO = new VehicleDAO();
    private ServiceRecordDAO serviceDAO = new ServiceRecordDAO();

    // =====================================================
    // INITIALIZE
    // =====================================================

    @FXML
    public void initialize() {

        // VEHICLE TABLE
        colVehicleId.setCellValueFactory(data ->
                new SimpleIntegerProperty(
                        data.getValue().getVehicleId()).asObject());

        colRegNo.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getRegistrationNumber()));

        colMake.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getMake()));

        colModel.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getModel()));

        colYear.setCellValueFactory(data ->
                new SimpleIntegerProperty(
                        data.getValue().getYear()).asObject());

        colOwnerId.setCellValueFactory(data ->
                new SimpleIntegerProperty(
                        data.getValue().getOwner_id()).asObject());

        // SERVICE TABLE
        colRecordId.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(
                        data.getValue().getService_id()).asObject());

        colVehicleIdSR.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(
                        data.getValue().getVehicle_id()).asObject());

        colServiceDate.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        data.getValue().getService_date()));

        colServiceType.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getService_type()));

        colDescription.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getDescription()));

        colCost.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        data.getValue().getCost()));



        // LOAD TABLES
        loadData();

        // AUTO-FILL VEHICLE FORM
        vehicleTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, selected) -> {

                    if (selected != null) {

                        txtVehicleId.setText(
                                String.valueOf(selected.getVehicleId()));

                        txtRegNo.setText(
                                selected.getRegistrationNumber());

                        txtMake.setText(
                                selected.getMake());

                        txtModel.setText(
                                selected.getModel());

                        txtYear.setText(
                                String.valueOf(selected.getYear()));

                        txtOwner.setText(
                                String.valueOf(selected.getOwner_id()));
                    }
                });

        // AUTO-FILL SERVICE FORM
        serviceTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, selected) -> {

                    if (selected != null) {

                        txtRecordId.setText(
                                String.valueOf(selected.getService_id()));

                        txtVehicleIdService.setText(
                                String.valueOf(selected.getVehicle_id()));
                        if (selected.getService_date() != null) {

                            dpServiceDate.setValue(
                                    selected.getService_date().toLocalDate());
                        }
                        txtServiceType.setText(
                                selected.getService_type());

                        txtServiceDescription.setText(
                                selected.getDescription());

                        txtCost.setText(
                                String.valueOf(selected.getCost())
                        );


                    }
                });
    }

    // =====================================================
    // LOAD DATA
    // =====================================================

    private void loadData() {

        try {

            ObservableList<vehicle> vehicleList =
                    FXCollections.observableArrayList(
                            vehicleDAO.findAll());

            vehicleTable.setItems(vehicleList);

            ObservableList<service_record> serviceList =
                    FXCollections.observableArrayList(
                            serviceDAO.findAll());

            serviceTable.setItems(serviceList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =====================================================
    // REFRESH
    // =====================================================

    @FXML
    public void refreshData() {
        loadData();
    }

    // =====================================================
    // DASHBOARD
    // =====================================================

    @FXML
    public void goDashboard() {

        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/com/example/visapp/view/Dashboard.fxml"));

            Stage stage =
                    (Stage) vehicleTable.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Dashboard");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =====================================================
    // ADD VEHICLE
    // =====================================================

    @FXML
    public void openRegisterVehicle() {

        try {
            vehicle v = new vehicle(
                    0,
                    txtRegNo.getText(),
                    txtMake.getText(),
                    txtModel.getText(),
                    Integer.parseInt(txtYear.getText()),
                    Integer.parseInt(txtOwner.getText())
            );

            vehicleDAO.insert(v);
            loadData();
            clearVehicleForm();
            showInfo("Success", "Vehicle added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            showError("Insert Failed",
                    "Failed to add vehicle.");
        }
    }

    // =====================================================
    // UPDATE VEHICLE
    // =====================================================

    @FXML
    public void openUpdateVehicle() {

        try {

            vehicle v = new vehicle(
                    Integer.parseInt(txtVehicleId.getText()),
                    txtRegNo.getText(),
                    txtMake.getText(),
                    txtModel.getText(),
                    Integer.parseInt(txtYear.getText()),
                    Integer.parseInt(txtOwner.getText()));

            vehicleDAO.update(v);

            loadData();

            showInfo("Success",
                    "Vehicle updated successfully.");

        } catch (Exception e) {

            e.printStackTrace();

            showError("Update Failed",
                    "Failed to update vehicle.");
        }
    }

    // =====================================================
    // DELETE VEHICLE
    // =====================================================

    @FXML
    public void deleteVehicle() {

        try {

            int id =
                    Integer.parseInt(txtVehicleId.getText());

            vehicleDAO.delete(id);

            loadData();

            clearVehicleForm();

            showInfo("Success",
                    "Vehicle deleted successfully.");

        } catch (Exception e) {

            e.printStackTrace();

            showError("Delete Failed",
                    "Failed to delete vehicle.");
        }
    }

    // =====================================================
    // ADD SERVICE
    // =====================================================

    @FXML
    public void openAddService() {

        try {

            service_record sr =
                    new service_record(
                            0,
                            Integer.parseInt(txtVehicleIdService.getText()),
                            Date.valueOf(dpServiceDate.getValue()),
                            txtServiceType.getText(),
                            txtServiceDescription.getText(),
                            new java.math.BigDecimal(
                                    txtCost.getText())
                    );

            serviceDAO.insert(sr);

            loadData();

            clearServiceForm();

            showInfo(
                    "Success",
                    "Service record added successfully."
            );

        } catch (Exception e) {

            e.printStackTrace();

            showError(
                    "Insert Failed",
                    "Failed to add service record."
            );
        }
    }

    @FXML
    public void updateService() {

        try {

            service_record sr =
                    new service_record(
                            Integer.parseInt(txtRecordId.getText()),
                            Integer.parseInt(txtVehicleIdService.getText()),
                            Date.valueOf(dpServiceDate.getValue()),
                            txtServiceType.getText(),
                            txtServiceDescription.getText(),
                            new java.math.BigDecimal(
                                    txtCost.getText())
                    );

            serviceDAO.update(sr);

            loadData();

            showInfo(
                    "Success",
                    "Service record updated successfully."
            );

        } catch (Exception e) {

            e.printStackTrace();

            showError(
                    "Update Failed",
                    "Failed to update service record."
            );
        }
    }
    // =====================================================
    // DELETE SERVICE
    // =====================================================

    @FXML
    public void deleteService() {

        try {

            int id =
                    Integer.parseInt(txtRecordId.getText());

            serviceDAO.delete(id);

            loadData();

            clearServiceForm();

            showInfo("Success",
                    "Service record deleted successfully.");

        } catch (Exception e) {

            e.printStackTrace();

            showError("Delete Failed",
                    "Failed to delete service.");
        }
    }

    // =====================================================
    // CLEAR FORMS
    // =====================================================

    @FXML
    public void clearVehicleForm() {

        txtVehicleId.clear();
        txtRegNo.clear();
        txtMake.clear();
        txtModel.clear();
        txtYear.clear();
        txtOwner.clear();
    }

    @FXML
    public void clearServiceForm() {

        txtRecordId.clear();
        txtVehicleIdService.clear();
        txtServiceType.clear();
        txtServiceDescription.clear();
        dpServiceDate.setValue(null);
        txtCost.clear();
    }
    // =====================================================
    // File Handling
    // =====================================================
    @FXML
    public void exportVehicleReport() {

        try {

            FileWriter writer =
                    new FileWriter("vehicle_report.txt");

            for (vehicle v : vehicleTable.getItems()) {

                writer.write(
                        "Vehicle ID: " + v.getVehicleId() +
                                " | Reg No: " + v.getRegistrationNumber() +
                                " | Make: " + v.getMake() +
                                " | Model: " + v.getModel() +
                                "\n"
                );
            }
            writer.close();

            showInfo(
                    "Success",
                    "Vehicle report exported successfully."
            );

        } catch (Exception e) {

            e.printStackTrace();

            showError(
                    "Export Failed",
                    "Could not export report."
            );
        }
    }

    // =====================================================
    // ALERTS
    // =====================================================

    private void showInfo(String title, String message) {

        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    private void showError(String title, String message) {

        Alert alert =
                new Alert(Alert.AlertType.ERROR);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}