package com.example.visapp.controller;

import com.example.visapp.model.Person;
import com.example.visapp.model.PoliceOfficer;
import com.example.visapp.model.customer;
import com.example.visapp.model. vehicle_owner_view;
import com.example.visapp.model. active_insurance_view;
import com.example.visapp.model. unpaid_violations_view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import com.example.visapp.dao.*;

import java.util.Arrays;
import java.util.List;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class DashboardController {
    @FXML private Label totalVehiclesLabel;
    @FXML private Label totalCustomersLabel;
    @FXML private Label totalPoliciesLabel;
    @FXML private Label totalReportsLabel;

    @FXML private TableView<vehicle_owner_view> vehicleOwnerTable;
    @FXML private TableView<active_insurance_view> insuranceViewTable;
    @FXML private TableView<unpaid_violations_view> violationViewTable;

    @FXML private TableColumn<vehicle_owner_view, Integer> colVehicleId;
    @FXML private TableColumn<vehicle_owner_view, String> colRegistration;
    @FXML private TableColumn<vehicle_owner_view, String> colMake;
    @FXML private TableColumn<vehicle_owner_view, String> colModel;
    @FXML private TableColumn<vehicle_owner_view, Integer> colYear;
    @FXML private TableColumn<vehicle_owner_view, Integer> colCustomerId;
    @FXML private TableColumn<vehicle_owner_view, String> colOwnerName;
    @FXML private TableColumn<vehicle_owner_view, String> colPhone;
    @FXML private TableColumn<vehicle_owner_view, String> colEmail;

    @FXML private TableColumn<active_insurance_view, Integer> colPolicyId;
    @FXML private TableColumn<active_insurance_view, String> colPolicyNumber;
    @FXML private TableColumn<active_insurance_view, String> colInsuranceCompany;
    @FXML private TableColumn<active_insurance_view, String> colStartDate;
    @FXML private TableColumn<active_insurance_view, String> colEndDate;
    @FXML private TableColumn<active_insurance_view, String> colVehicleReg;
    @FXML private TableColumn<active_insurance_view, String> colVehicleMake;
    @FXML private TableColumn<active_insurance_view, String> colVehicleModel;

    @FXML private TableColumn<unpaid_violations_view, Integer> colViolationId;
    @FXML private TableColumn<unpaid_violations_view, Integer> colViolationVehicleId;
    @FXML private TableColumn<unpaid_violations_view, String> colViolationType;
    @FXML private TableColumn<unpaid_violations_view, Double> colFineAmount;
    @FXML private TableColumn<unpaid_violations_view, String> colViolationStatus;

    @FXML private VBox vehicleListBox;
    @FXML private Pagination vehiclePagination;

    private final int ITEMS_PER_PAGE = 5;

    private VehicleDAO VehicleDAO = new VehicleDAO();
    private CustomerDAO CustomerDAO = new CustomerDAO();
    private InsurancePolicyDAO PolicyDAO = new InsurancePolicyDAO();
    private PoliceReportDAO ReportDAO = new PoliceReportDAO();
    private ViewDAO viewDAO = new ViewDAO();

    private List<String> dummyVehicles = Arrays.asList(
            "Toyota Hilux",
            "BMW X5",
            "Ford Ranger",
            "Audi A4",
            "Mercedes C200",
            "VW Polo",
            "Honda Fit",
            "Nissan NP200",
            "Toyota Corolla",
            "Hyundai i20",
            "Mazda CX5",
            "Isuzu DMAX",
            "Kia Picanto",
            "Range Rover",
            "Jeep Wrangler",
            "Volvo XC90",
            "Chevrolet Cruze",
            "Subaru Forester",
            "Land Cruiser",
            "Mini Cooper"
    );

    @FXML
    public void initialize() {
        setupViewTables();

        Person p1 = new customer(1,
                "John Doe",
                "Maseru",
                "59000000",
                "john@gmail.com");

        Person p2 = new PoliceOfficer(
                "Officer Smith",
                "58000000");

        System.out.println(p1.getRole());
        System.out.println(p2.getRole());

        setupPagination();
        try {
            totalVehiclesLabel.setText("Vehicles: " + VehicleDAO.count());
            totalCustomersLabel.setText("Customers: " + CustomerDAO.count());
            totalPoliciesLabel.setText("Policies: " + PolicyDAO.count());
            totalReportsLabel.setText("Reports: " + ReportDAO.count());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void setupViewTables() {

        // VEHICLE OWNER VIEW

        colVehicleId.setCellValueFactory(
                new PropertyValueFactory<>("vehicle_id"));

        colRegistration.setCellValueFactory(
                new PropertyValueFactory<>("registration_number"));

        colMake.setCellValueFactory(
                new PropertyValueFactory<>("make"));

        colModel.setCellValueFactory(
                new PropertyValueFactory<>("model"));

        colYear.setCellValueFactory(
                new PropertyValueFactory<>("year"));

        colCustomerId.setCellValueFactory(
                new PropertyValueFactory<>("customer_id"));

        colOwnerName.setCellValueFactory(
                new PropertyValueFactory<>("owner_name"));

        colPhone.setCellValueFactory(
                new PropertyValueFactory<>("phone"));

        colEmail.setCellValueFactory(
                new PropertyValueFactory<>("email"));

        vehicleOwnerTable.setItems(
                viewDAO.getVehicleOwnerView());

        // INSURANCE VIEW

        colPolicyId.setCellValueFactory(
                new PropertyValueFactory<>("policy_id"));

        colPolicyNumber.setCellValueFactory(
                new PropertyValueFactory<>("policy_number"));

        colInsuranceCompany.setCellValueFactory(
                new PropertyValueFactory<>("insurance_company"));

        colStartDate.setCellValueFactory(
                new PropertyValueFactory<>("start_date"));

        colEndDate.setCellValueFactory(
                new PropertyValueFactory<>("end_date"));

        colVehicleReg.setCellValueFactory(
                new PropertyValueFactory<>("registration_number"));

        colVehicleMake.setCellValueFactory(
                new PropertyValueFactory<>("make"));

        colVehicleModel.setCellValueFactory(
                new PropertyValueFactory<>("model"));

        insuranceViewTable.setItems(
                viewDAO.getInsuranceView());

        // VIOLATION VIEW

        colViolationId.setCellValueFactory(
                new PropertyValueFactory<>("violation_id"));

        colViolationVehicleId.setCellValueFactory(
                new PropertyValueFactory<>("vehicle_id"));

        colViolationType.setCellValueFactory(
                new PropertyValueFactory<>("violation_type"));

        colFineAmount.setCellValueFactory(
                new PropertyValueFactory<>("fine_amount"));

        colViolationStatus.setCellValueFactory(
                new PropertyValueFactory<>("status"));

        violationViewTable.setItems(
                viewDAO.getViolationView());
    }

    private void setupPagination() {

        int pageCount = (int) Math.ceil((double) dummyVehicles.size() / ITEMS_PER_PAGE);

        vehiclePagination.setPageCount(pageCount);

        vehiclePagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {

            loadPage(newIndex.intValue());
        });

        loadPage(0);
    }
    private void loadPage(int pageIndex) {

        vehicleListBox.getChildren().clear();

        int start = pageIndex * ITEMS_PER_PAGE;
        int end = Math.min(start + ITEMS_PER_PAGE, dummyVehicles.size());

        for (int i = start; i < end; i++) {

            Label lbl = new Label(dummyVehicles.get(i));

            lbl.setStyle(
                    "-fx-background-color: black;" +
                            "-fx-padding: 10;" +
                            "-fx-background-radius: 8;" +
                            "-fx-border-radius: 8;" +
                            "-fx-border-color: #dcdcdc;"
            );
            vehicleListBox.getChildren().add(lbl);
        }
    }

    @FXML
    public void openUsersModule() { openModule("/com/example/visapp/view/Users.fxml",
            "Users Module"); }
    @FXML
    public void openWorkshopModule() { openModule("/com/example/visapp/view/Workshop.fxml",
            "Workshop Module"); }
    @FXML
    public void openCustomerModule() { openModule("/com/example/visapp/view/Customer.fxml",
            "Customer Module"); }
    @FXML
    public void openInsuranceModule() { openModule("/com/example/visapp/view/Insurance.fxml",
            "Insurance Module"); }
    @FXML
    public void openPoliceModule() { openModule("/com/example/visapp/view/Police.fxml",
            "Police Module"); }

    private void openModule(String fxmlPath, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleLogout() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/visapp/view/Login.fxml"));

            Stage stage = (Stage) totalVehiclesLabel.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
