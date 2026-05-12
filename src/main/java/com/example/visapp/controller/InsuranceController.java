package com.example.visapp.controller;

import com.example.visapp.dao.InsurancePolicyDAO;
import com.example.visapp.dao.ClaimDAO;
import com.example.visapp.model.insurance_policy;
import com.example.visapp.model.claim;
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

public class InsuranceController {
    // ================= POLICY TABLE =================
    @FXML private TableView<insurance_policy> policyTable;
    @FXML private TableColumn<insurance_policy, Integer> colPolicyId;
    @FXML private TableColumn<insurance_policy, Integer> colVehicleId;
    @FXML private TableColumn<insurance_policy, String> colProvider;
    @FXML private TableColumn<insurance_policy, String> colPolicyNumber;
    @FXML private TableColumn<insurance_policy, java.sql.Date> colStartDate;
    @FXML private TableColumn<insurance_policy, java.sql.Date> colEndDate;
    @FXML private TableColumn<insurance_policy, String> colCoverageDetails;

    // ================= CLAIM TABLE =================
    @FXML private TableView<claim> claimTable;
    @FXML private TableColumn<claim, Integer> colClaimId;
    @FXML private TableColumn<claim, Integer> colPolicyIdC;
    @FXML private TableColumn<claim, java.sql.Date> colClaimDate;
    @FXML private TableColumn<claim, BigDecimal> colClaimAmount;
    @FXML private TableColumn<claim, String> colStatus;

    // ================= POLICY FIELDS =================
    @FXML private TextField txtPolicyId;
    @FXML private TextField txtVehicleId;
    @FXML private TextField txtProvider;
    @FXML private TextField txtPolicyNumber;
    @FXML private DatePicker dpStartDate;
    @FXML private DatePicker dpEndDate;
    @FXML private TextField txtCoverageDetails;

    // ================= CLAIM FIELDS =================
    @FXML private TextField txtClaimId;
    @FXML private TextField txtPolicyIdClaim;
    @FXML private DatePicker dpClaimDate;
    @FXML private TextField txtClaimAmount;
    @FXML private ComboBox<String> cmbStatus;

    // ================= DAO =================
    private InsurancePolicyDAO policyDAO = new InsurancePolicyDAO();
    private ClaimDAO claimDAO = new ClaimDAO();

    // ================= INITIALIZE =================
    @FXML
    public void initialize() {

        setupColumns();

        cmbStatus.setItems(FXCollections.observableArrayList(
                "Pending",
                "Approved",
                "Rejected"
        ));

        loadData();
        setupRowSelection();
    }

    // ================= COLUMN SETUP =================
    private void setupColumns() {

        // POLICY TABLE
        colPolicyId.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getPolicy_id()).asObject());

        colVehicleId.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getVehicle_id()).asObject());

        colProvider.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getInsurance_company()));

        colPolicyNumber.setCellValueFactory(data ->
                new  SimpleStringProperty(data.getValue().getPolicy_number()));

        colStartDate.setCellValueFactory(data ->
                new SimpleObjectProperty<>(data.getValue().getStart_date()));

        colEndDate.setCellValueFactory(data ->
                new SimpleObjectProperty<>(data.getValue().getEnd_date()));

        colCoverageDetails.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getCoverage_details()));

        // CLAIM TABLE
        colClaimId.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getClaim_id()).asObject());

        colPolicyIdC.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getPolicy_id()).asObject());

        colClaimDate.setCellValueFactory(data ->
                new SimpleObjectProperty<>(data.getValue().getClaim_date()));

        colClaimAmount.setCellValueFactory(data ->
                new SimpleObjectProperty(data.getValue().getClaim_amount()));

        colStatus.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getStatus()));
    }

    // ================= LOAD DATA =================
    private void loadData() {

        policyTable.setItems(
                FXCollections.observableArrayList(
                        policyDAO.findAll()
                )
        );

        claimTable.setItems(
                FXCollections.observableArrayList(
                        claimDAO.findAll()
                )
        );
    }

    // ================= AUTO FILL =================
    private void setupRowSelection() {

        policyTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, p) -> {

            if (p != null) {

                txtPolicyId.setText(String.valueOf(p.getPolicy_id()));
                txtVehicleId.setText(String.valueOf(p.getVehicle_id()));
                txtProvider.setText(p.getInsurance_company());
                txtPolicyNumber.setText(p.getPolicy_number());
                if (p.getStart_date() != null) {
                    dpStartDate.setValue(p.getStart_date().toLocalDate());
                }

                if (p.getEnd_date() != null) {
                    dpEndDate.setValue(p.getEnd_date().toLocalDate());
                }
                txtCoverageDetails.setText(p.getCoverage_details());

            }
        });

        claimTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, c) -> {

            if (c != null) {

                txtClaimId.setText(String.valueOf(c.getClaim_id()));
                txtPolicyIdClaim.setText(String.valueOf(c.getPolicy_id()));
                if (c.getClaim_date() != null) {
                    dpClaimDate.setValue(c.getClaim_date().toLocalDate());
                }
                txtClaimAmount.setText(String.valueOf(c.getClaim_amount()));
                cmbStatus.setValue(c.getStatus());
            }
        });
    }

    // ================= POLICY CRUD =================

    @FXML
    private void addPolicy() {

        insurance_policy p = new insurance_policy();

        p.setVehicle_id(Integer.parseInt(txtVehicleId.getText()));
        p.setInsurance_company(txtProvider.getText());
        p.setPolicy_number(txtPolicyNumber.getText());
        p.setStart_date(Date.valueOf(dpStartDate.getValue()));
        p.setEnd_date(Date.valueOf(dpEndDate.getValue()));
        p.setCoverage_details(txtCoverageDetails.getText());

        policyDAO.insert(p);

        loadData();
        clearPolicy();
    }

    @FXML
    private void updatePolicy() {

        insurance_policy p = new insurance_policy();

        p.setPolicy_id(Integer.parseInt(txtPolicyId.getText()));
        p.setVehicle_id(Integer.parseInt(txtVehicleId.getText()));
        p.setInsurance_company(txtProvider.getText());
        p.setPolicy_number(txtPolicyNumber.getText());
        p.setStart_date(Date.valueOf(dpStartDate.getValue()));
        p.setEnd_date(Date.valueOf(dpEndDate.getValue()));
        p.setCoverage_details(txtCoverageDetails.getText());

        policyDAO.update(p);

        loadData();
    }

    @FXML
    private void deletePolicy() {

        int id = Integer.parseInt(txtPolicyId.getText());

        policyDAO.delete(id);

        loadData();
        clearPolicy();
    }

    @FXML
    private void clearPolicy() {

        txtPolicyId.clear();
        txtVehicleId.clear();
        txtProvider.clear();
        txtPolicyNumber.clear();
        dpStartDate.setValue(null);
        dpEndDate.setValue(null);
        txtCoverageDetails.setText(null);
    }

    // ================= CLAIM CRUD =================

    @FXML
    private void addClaim() {

        claim c = new claim();

        c.setPolicy_id(Integer.parseInt(txtPolicyIdClaim.getText()));

        c.setClaim_date(Date.valueOf(dpClaimDate.getValue()));

        c.setClaim_amount(BigDecimal.valueOf(Double.parseDouble(txtClaimAmount.getText())));

        c.setStatus(cmbStatus.getValue());

        claimDAO.insert(c);

        loadData();
        clearClaim();
    }

    @FXML
    private void updateClaim() {

        claim c = new claim();

        c.setClaim_id(Integer.parseInt(txtClaimId.getText()));

        c.setPolicy_id(Integer.parseInt(txtPolicyIdClaim.getText()));

        c.setClaim_date(Date.valueOf(dpClaimDate.getValue()));

        c.setClaim_amount(BigDecimal.valueOf(Double.parseDouble(txtClaimAmount.getText())));

        c.setStatus(cmbStatus.getValue());

        claimDAO.update(c);

        loadData();
    }

    @FXML
    private void deleteClaim() {

        int id = Integer.parseInt(txtClaimId.getText());

        claimDAO.delete(id);

        loadData();
        clearClaim();
    }

    @FXML
    private void clearClaim() {

        txtClaimId.clear();
        txtPolicyIdClaim.clear();

        txtClaimAmount.clear();

        cmbStatus.setValue(null);

        dpClaimDate.setValue(null);
    }

    // ================= EXIT =================

    @FXML
    private void goToDashboard() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("/com/example/visapp/view/Dashboard.fxml"));

            Parent root = loader.load();

            Stage stage =
                    (Stage) policyTable.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
