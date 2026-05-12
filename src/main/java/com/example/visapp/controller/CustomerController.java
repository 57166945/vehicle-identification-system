package com.example.visapp.controller;

import com.example.visapp.dao.CustomerDAO;
import com.example.visapp.dao.CustomerQueryDAO;
import com.example.visapp.model.customer;
import com.example.visapp.model.customer_query;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Date;

public class CustomerController {

    // ================= TABLES =================
    @FXML private TableView<customer> customerTable;
    @FXML private TableView<customer_query> queryTable;

    @FXML private TableColumn<customer, Integer> colCustomerId;
    @FXML private TableColumn<customer, String> colName;
    @FXML private TableColumn<customer, String> colAddress;
    @FXML private TableColumn<customer, String> colContact;
    @FXML private TableColumn<customer, String> colEmail;

    @FXML private TableColumn<customer_query, Integer> colQueryId;
    @FXML private TableColumn<customer_query, Integer> colCustomerIdQ;
    @FXML private TableColumn<customer_query, Integer> colVehicleIdQ;
    @FXML private TableColumn<customer_query, Date> colQueryDate;
    @FXML private TableColumn<customer_query, String> colQueryText;
    @FXML private TableColumn<customer_query, String> colResponse;

    // ================= CUSTOMER FIELDS =================
    @FXML private TextField txtCustomerId;
    @FXML private TextField txtName;
    @FXML private TextField txtAddress;
    @FXML private TextField txtPhone;
    @FXML private TextField txtEmail;

    // ================= QUERY FIELDS =================
    @FXML private TextField txtQueryId;
    @FXML private TextField txtQueryCustomerId;
    @FXML private TextField txtVehicleId;
    @FXML private DatePicker dpQueryDate;
    @FXML private TextArea txtQueryText;
    @FXML private TextField txtResponse;


    // ================= DAO =================
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final CustomerQueryDAO queryDAO = new CustomerQueryDAO();

    // ================= INIT =================
    @FXML
    public void initialize() {

        setupColumns();
        loadData();
        setupRowSelection();
    }

    // ================= COLUMN BINDING =================
    private void setupColumns() {

        // ================= CUSTOMER TABLE =================
        colCustomerId.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(data.getValue().getCustomerId()).asObject());

        colName.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));

        colAddress.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getAddress()));

        colContact.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getPhone()));

        colEmail.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));

        // ================= CUSTOMER QUERY TABLE =================
        colQueryId.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(data.getValue().getQueryId()).asObject());

        colCustomerIdQ.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(data.getValue().getCustomerId()).asObject());

        colVehicleIdQ.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(data.getValue().getVehicleId()).asObject());

        colQueryDate.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getQueryDate()));

        colQueryText.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getQuery_text()));

        colResponse.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getResponseText()));

    }

    // ================= LOAD DATA =================
    private void loadData() {
        try {
            customerTable.setItems(
                    FXCollections.observableArrayList(customerDAO.getAll())
            );

            queryTable.setItems(
                    FXCollections.observableArrayList(queryDAO.findAll())
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= AUTO-FILL =================
    private void setupRowSelection() {

        customerTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, c) -> {
            if (c != null) {
                txtCustomerId.setText(String.valueOf(c.getCustomerId()));
                txtName.setText(c.getName());
                txtAddress.setText(c.getAddress());
                txtPhone.setText(c.getPhone());
                txtEmail.setText(c.getEmail());
            }
        });

        queryTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, q) -> {
            if (q != null) {
                txtQueryId.setText(String.valueOf(q.getQueryId()));
                txtQueryCustomerId.setText(String.valueOf(q.getCustomerId()));
                txtVehicleId.setText(String.valueOf(q.getVehicleId()));
                if (q.getQueryDate() != null) {
                    dpQueryDate.setValue(q.getQueryDate().toLocalDate());
                }
                txtQueryText.setText(q.getQuery_text());
                txtResponse.setText(q.getResponseText());


            }
        });
    }

    // ================= CUSTOMER CRUD =================

    @FXML
    private void addCustomer() {
        customer c = new customer();
        c.setName(txtName.getText());
        c.setAddress(txtAddress.getText());
        c.setPhone(txtPhone.getText());
        c.setEmail(txtEmail.getText());

        customerDAO.insert(c);
        loadData();
        clearCustomer();
    }

    @FXML
    private void updateCustomer() {
        customer c = new customer();
        c.setCustomerId(Integer.parseInt(txtCustomerId.getText()));
        c.setName(txtName.getText());
        c.setAddress(txtAddress.getText());
        c.setPhone(txtPhone.getText());
        c.setEmail(txtEmail.getText());

        customerDAO.update(c);
        loadData();
    }

    @FXML
    private void deleteCustomer() {
        int id = Integer.parseInt(txtCustomerId.getText());
        customerDAO.delete(id);
        loadData();
        clearCustomer();
    }

    // ================= CUSTOMER QUERY CRUD =================

    @FXML
    private void addQuery() {
        customer_query q = new customer_query();
        q.setCustomerId(Integer.parseInt(txtQueryCustomerId.getText()));
        q.setVehicleId(Integer.parseInt(txtVehicleId.getText()));
        q.setQueryDate(Date.valueOf(dpQueryDate.getValue()));
        q.setQuery_text(txtQueryText.getText());
        q.setResponseText(txtResponse.getText());

        queryDAO.insert(q);
        loadData();
        clearQuery();
    }

    @FXML
    private void updateQuery() {
        customer_query q = new customer_query();
        q.setQueryId(Integer.parseInt(txtQueryId.getText()));
        q.setCustomerId(Integer.parseInt(txtQueryCustomerId.getText()));
        q.setVehicleId(Integer.parseInt(txtVehicleId.getText()));
        q.setQueryDate(Date.valueOf(dpQueryDate.getValue()));
        q.setQuery_text(txtQueryText.getText());
        q.setResponseText(txtResponse.getText());

        queryDAO.update(q);
        loadData();
    }

    @FXML
    private void deleteQuery() {
        int id = Integer.parseInt(txtQueryId.getText());
        queryDAO.delete(id);
        loadData();
        clearQuery();
    }

    // ================= CLEAR FIELDS =================

    public void clearCustomer() {
        txtCustomerId.clear();
        txtName.clear();
        txtAddress.clear();
        txtPhone.clear();
        txtEmail.clear();
    }

    public void clearQuery() {
        txtQueryId.clear();
        txtQueryCustomerId.clear();
        txtVehicleId.clear();
        dpQueryDate.setValue(null);
        txtQueryText.clear();
        txtResponse.clear();
    }

    // ================= EXIT TO DASHBOARD =================
    @FXML
    private void goToDashboard() {
        try {
            javafx.fxml.FXMLLoader loader =
                    new javafx.fxml.FXMLLoader(getClass().getResource("/com/example/visapp/view/Dashboard.fxml"));

            javafx.scene.Parent root = loader.load();

            javafx.stage.Stage stage =
                    (javafx.stage.Stage) customerTable.getScene().getWindow();

            stage.setScene(new javafx.scene.Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}