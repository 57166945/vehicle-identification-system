package com.example.visapp.controller;

import com.example.visapp.dao.UsersDAO;
import com.example.visapp.model.users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class UsersController {

    @FXML private TableView<users> usersTable;

    @FXML private TableColumn<users, Integer> colUserId;
    @FXML private TableColumn<users, String> colUsername;
    @FXML private TableColumn<users, String> colPassword;
    @FXML private TableColumn<users, String> colRole;

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private ComboBox<String> roleComboBox;
    @FXML private TextField searchField;

    private UsersDAO usersDAO = new UsersDAO();

    private users selectedUser;

    @FXML
    public void initialize() {

        roleComboBox.setItems(FXCollections.observableArrayList(
                "Admin",
                "Police Officer",
                "Insurance Agent",
                "Customer",
                "Workshop"
        ));

        colUserId.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(
                        data.getValue().getUserId()).asObject());

        colUsername.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getUsername()));

        colPassword.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getPassword()));

        colRole.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getRole()));

        loadUsers();

        usersTable.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> {

                    if (newSelection != null) {

                        selectedUser = newSelection;
                        usernameField.setText(newSelection.getUsername());
                        passwordField.setText(newSelection.getPassword());
                        roleComboBox.setValue(newSelection.getRole());
                    }
                });
    }

    private void loadUsers() {
        try {
            ObservableList<users> list =
                    FXCollections.observableArrayList(usersDAO.findAll());

            usersTable.setItems(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addUser() {

        try {

            users users = new users();
            users.setUserId(users.getUserId());
            users.setUsername(usernameField.getText());
            users.setPassword(passwordField.getText());
            users.setRole(roleComboBox.getValue());

            usersDAO.insert(users);

            loadUsers();
            clearForm();

            showAlert("Success", "User added successfully.");

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to add user.");
        }
    }

    @FXML
    public void updateUser() {

        if (selectedUser == null) {
            showAlert("Warning", "Select a user first.");
            return;
        }

        try {

            selectedUser.setUsername(usernameField.getText());
            selectedUser.setPassword(passwordField.getText());
            selectedUser.setRole(roleComboBox.getValue());

            usersDAO.update(selectedUser);

            loadUsers();
            clearForm();

            showAlert("Success", "User updated successfully.");

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to update user.");
        }
    }

    @FXML
    public void deleteUser() {

        if (selectedUser == null) {
            showAlert("Warning", "Select a user first.");
            return;
        }

        try {

            usersDAO.delete(selectedUser.getUserId());

            loadUsers();
            clearForm();

            showAlert("Success", "User deleted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to delete user.");
        }
    }

    @FXML
    public void clearForm() {
        usernameField.clear();
        passwordField.clear();

        roleComboBox.setValue(null);

        selectedUser = null;
    }

    @FXML
    public void refreshUsers() {
        loadUsers();
    }

    @FXML
    public void searchUsers() {

        String keyword = searchField.getText().trim();

        try {

            ObservableList<users> list =
                    FXCollections.observableArrayList(
                            usersDAO.search(keyword));

            usersTable.setItems(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goDashboard() {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/com/example/visapp/view/Dashboard.fxml"));

            Stage stage = (Stage) usersTable.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Dashboard");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setContentText(message);

        alert.showAndWait();
    }
}