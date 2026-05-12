package com.example.visapp.controller;

import com.example.visapp.dao.PoliceReportDAO;
import com.example.visapp.dao.ViolationDAO;
import com.example.visapp.model.police_report;
import com.example.visapp.model.violation;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import javafx.collections.FXCollections;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.*;

import javafx.stage.Stage;

import java.math.BigDecimal;
import java.sql.Date;

public class PoliceController {

    // ================= REPORT TABLE =================
    @FXML private TableView<police_report> reportTable;

    @FXML private TableColumn<police_report, Integer> colReportId;
    @FXML private TableColumn<police_report, Integer> colVehicleIdR;
    @FXML private TableColumn<police_report, Date> colReportDate;
    @FXML private TableColumn<police_report, String> colReportType;
    @FXML private TableColumn<police_report, String> colDescription;
    @FXML private TableColumn<police_report, String> colOfficerName;
    @FXML private TableColumn<police_report, String> colStationName;

    // ================= VIOLATION TABLE =================
    @FXML private TableView<violation> violationTable;

    @FXML private TableColumn<violation, Integer> colViolationId;
    @FXML private TableColumn<violation, Integer> colVehicleIdV;
    @FXML private TableColumn<violation, Date> colViolationDate;
    @FXML private TableColumn<violation, String> colViolationType;
    @FXML private TableColumn<violation, BigDecimal> colFineAmount;
    @FXML private TableColumn<violation, String> colStatus;

    // ================= REPORT FORM =================
    @FXML private TextField txtReportId;
    @FXML private TextField txtVehicleIdReport;
    @FXML private DatePicker dpReportDate;
    @FXML private ComboBox<String> cmbReportType;
    @FXML private TextArea txtReportDescription;
    @FXML private TextField txtOfficerName;
    @FXML private TextField txtStationName;

    // ================= VIOLATION FORM =================
    @FXML private TextField txtViolationId;
    @FXML private TextField txtVehicleIdViolation;

    @FXML private DatePicker dpViolationDate;

    @FXML private TextField txtViolationType;
    @FXML private TextField txtFineAmount;

    @FXML private ComboBox<String> cmbViolationStatus;

    // ================= DAO =================
    private final PoliceReportDAO reportDAO =
            new PoliceReportDAO();

    private final ViolationDAO violationDAO =
            new ViolationDAO();

    // ================= INITIALIZE =================
    @FXML
    public void initialize() {

        setupColumns();

        loadData();

        setupRowSelection();

        cmbReportType.setItems(
                FXCollections.observableArrayList(
                        "Accident",
                        "Theft",
                        "Inspection",
                        "Other"
                )
        );

        cmbViolationStatus.setItems(
                FXCollections.observableArrayList(
                        "Pending",
                        "Paid",
                        "Cancelled"
                )
        );
    }

    // ================= COLUMN SETUP =================
    private void setupColumns() {

        // REPORT TABLE
        colReportId.setCellValueFactory(data ->
                new SimpleIntegerProperty(
                        data.getValue().getReport_id()
                ).asObject());

        colVehicleIdR.setCellValueFactory(data ->
                new SimpleIntegerProperty(
                        data.getValue().getVehicle_id()
                ).asObject());

        colReportDate.setCellValueFactory(data ->
                new SimpleObjectProperty<>(
                        data.getValue().getReport_date()
                ));

        colReportType.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getReport_type()
                ));

        colDescription.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getDescription()
                ));

        colOfficerName.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getOfficer_name()
                ));

        colStationName.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getStation_name()
                ));

        // VIOLATION TABLE
        colViolationId.setCellValueFactory(data ->
                new SimpleIntegerProperty(
                        data.getValue().getViolation_id()
                ).asObject());

        colVehicleIdV.setCellValueFactory(data ->
                new SimpleIntegerProperty(
                        data.getValue().getVehicle_id()
                ).asObject());

        colViolationDate.setCellValueFactory(data ->
                new SimpleObjectProperty<>(
                        data.getValue().getViolation_date()
                ));

        colViolationType.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getViolation_type()
                ));

        colFineAmount.setCellValueFactory(data ->
                new SimpleObjectProperty<>(
                        data.getValue().getFine_amount()
                ));

        colStatus.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getStatus()
                ));
    }

    // ================= LOAD DATA =================
    private void loadData() {

        reportTable.setItems(
                FXCollections.observableArrayList(
                        reportDAO.getAll()
                )
        );

        violationTable.setItems(
                FXCollections.observableArrayList(
                        violationDAO.getAll()
                )
        );
    }

    // ================= AUTO FILL =================
    private void setupRowSelection() {

        reportTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, r) -> {

                    if (r != null) {

                        txtReportId.setText(
                                String.valueOf(r.getReport_id())
                        );

                        txtVehicleIdReport.setText(
                                String.valueOf(r.getVehicle_id())
                        );

                        dpReportDate.setValue(
                                r.getReport_date().toLocalDate()
                        );

                        cmbReportType.setValue(
                                r.getReport_type()
                        );

                        txtReportDescription.setText(
                                r.getDescription()
                        );

                        txtOfficerName.setText(
                                r.getOfficer_name()
                        );
                        txtStationName.setText(
                                r.getStation_name()
                        );
                    }
                });

        violationTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, v) -> {

                    if (v != null) {

                        txtViolationId.setText(
                                String.valueOf(v.getViolation_id())
                        );

                        txtVehicleIdViolation.setText(
                                String.valueOf(v.getVehicle_id())
                        );

                        dpViolationDate.setValue(
                                v.getViolation_date().toLocalDate()
                        );

                        txtViolationType.setText(
                                v.getViolation_type()
                        );

                        txtFineAmount.setText(
                                String.valueOf(v.getFine_amount())
                        );

                        cmbViolationStatus.setValue(
                                v.getStatus()
                        );
                    }
                });
    }

    // ================= REPORT CRUD =================

    @FXML
    private void addReport() {

        police_report r = new police_report();

        r.setVehicle_id(
                Integer.parseInt(txtVehicleIdReport.getText())
        );

        r.setReport_date(
                Date.valueOf(dpReportDate.getValue())
        );

        r.setReport_type(
                cmbReportType.getValue()
        );

        r.setDescription(
                txtReportDescription.getText()
        );

        r.setOfficer_name(
                txtOfficerName.getText()
        );

        r.setStation_name(
                txtStationName.getText()
        );

        reportDAO.insert(r);

        loadData();
        clearReport();
    }

    @FXML
    private void updateReport() {

        police_report r = new police_report();

        r.setReport_id(
                Integer.parseInt(txtReportId.getText())
        );

        r.setVehicle_id(
                Integer.parseInt(txtVehicleIdReport.getText())
        );

        r.setReport_date(
                Date.valueOf(dpReportDate.getValue())
        );

        r.setReport_type(
                cmbReportType.getValue()
        );

        r.setDescription(
                txtReportDescription.getText()
        );

        r.setOfficer_name(
                txtOfficerName.getText()
        );

        r.setStation_name(
                txtStationName.getText()
        );

        reportDAO.update(r);

        loadData();
    }

    @FXML
    private void deleteReport() {

        int id =
                Integer.parseInt(txtReportId.getText());

        reportDAO.delete(id);

        loadData();
        clearReport();
    }

    @FXML
    private void clearReport() {
        txtReportId.clear();
        txtVehicleIdReport.clear();
        dpReportDate.setValue(null);
        cmbReportType.setValue(null);
        txtReportDescription.clear();
        txtOfficerName.clear();
        txtStationName.clear();
    }

    // ================= VIOLATION CRUD =================

    @FXML
    private void addViolation() {

        violation v = new violation();

        v.setVehicle_id(
                Integer.parseInt(txtVehicleIdViolation.getText())
        );

        v.setViolation_date(
                Date.valueOf(dpViolationDate.getValue())
        );

        v.setViolation_type(
                txtViolationType.getText()
        );

        v.setFine_amount(
                new BigDecimal(txtFineAmount.getText())
        );

        v.setStatus(
                cmbViolationStatus.getValue()
        );

        violationDAO.insert(v);

        loadData();
        clearViolation();
    }

    @FXML
    private void updateViolation() {

        violation v = new violation();

        v.setViolation_id(
                Integer.parseInt(txtViolationId.getText())
        );

        v.setVehicle_id(
                Integer.parseInt(txtVehicleIdViolation.getText())
        );

        v.setViolation_date(
                Date.valueOf(dpViolationDate.getValue())
        );

        v.setViolation_type(
                txtViolationType.getText()
        );

        v.setFine_amount(
                new BigDecimal(txtFineAmount.getText())
        );

        v.setStatus(
                cmbViolationStatus.getValue()
        );

        violationDAO.update(v);

        loadData();
    }

    @FXML
    private void deleteViolation() {

        int id =
                Integer.parseInt(txtViolationId.getText());

        violationDAO.delete(id);

        loadData();
        clearViolation();
    }

    @FXML
    private void clearViolation() {
        txtViolationId.clear();
        txtVehicleIdViolation.clear();
        dpViolationDate.setValue(null);
        txtViolationType.clear();
        txtFineAmount.clear();
        cmbViolationStatus.setValue(null);
    }

    // ================= DASHBOARD =================
    @FXML
    private void goToDashboard() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource("/com/example/visapp/view/dashboard.fxml"
                            )
                    );

            Parent root = loader.load();

            Stage stage =
                    (Stage) reportTable.getScene().getWindow();

            stage.setScene(new Scene(root));

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}