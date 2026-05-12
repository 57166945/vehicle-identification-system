package com.example.visapp.controller;

import com.example.visapp.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import javafx.animation.FadeTransition;
import javafx.scene.effect.DropShadow;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    // DEMO USERS COMBOBOX
    @FXML private ComboBox<String> cmbDemoUsers;

    @FXML private ProgressBar loginProgressBar;
    @FXML private ProgressIndicator loginIndicator;

    @FXML private Button loginButton;
    @FXML private Button registerButton;

    private static DBConnection dbConnection;

    public static DBConnection getDbConnection() {
        return dbConnection;
    }

    @FXML
    public void initialize() {

        cmbDemoUsers.getItems().addAll(
                "Admin",
                "Police Officer",
                "Insurance Agent"
        );

        cmbDemoUsers.setOnAction(e -> autoFillDemoCredentials());

        FadeTransition fade =
                new FadeTransition(Duration.seconds(1.5), registerButton);

        fade.setFromValue(1.0);
        fade.setToValue(0.3);
        fade.setCycleCount(FadeTransition.INDEFINITE);
        fade.setAutoReverse(true);

        fade.play();
    }

    // =====================================================
    // AUTO FILL DEMO USERS
    // =====================================================

    private void autoFillDemoCredentials() {

        String selected =
                cmbDemoUsers.getValue();

        if (selected == null) {
            return;
        }

        switch (selected) {

            case "Admin":

                usernameField.setText("molato");
                passwordField.setText("1234");
                break;

            case "Police Officer":

                usernameField.setText("thapelotipi");
                passwordField.setText("102030");
                break;

            case "Insurance Agent":

                usernameField.setText("leputlap");
                passwordField.setText("p2015");
                break;
        }
    }

    // =====================================================
    // LOGIN
    // =====================================================

    @FXML
    public void handleLogin() {

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {

            showAlert("Please enter username and password.");
            return;
        }

        try {

            // CONNECT DATABASE
            dbConnection =
                    new DBConnection(
                            "postgres.awdrswdcmoovuyowmmpj",
                            "Doctor@45paduka"
                    );

            Connection conn =
                    dbConnection.getConnection();

            String sql =
                    "SELECT * FROM users WHERE username=? AND password=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                loginProgressBar.setProgress(0);
                loginIndicator.setProgress(0);

                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.seconds(0.3), e -> {
                            loginProgressBar.setProgress(0.3);
                            loginIndicator.setProgress(0.3);
                        }),

                        new KeyFrame(Duration.seconds(0.6), e -> {
                            loginProgressBar.setProgress(0.6);
                            loginIndicator.setProgress(0.6);
                        }),

                        new KeyFrame(Duration.seconds(1), e -> {
                            loginProgressBar.setProgress(1);
                            loginIndicator.setProgress(1);
                        })
                );

                timeline.setOnFinished(event -> {

                    try {
                    Stage stage =
                        (Stage) usernameField.getScene().getWindow();

                Parent root =
                        FXMLLoader.load(
                                getClass().getResource(
                                        "/com/example/visapp/view/Dashboard.fxml"));

                stage.setScene(new Scene(root));
                stage.setTitle("VIS Dashboard");
                stage.show();

            } catch (Exception ex) {

                    ex.printStackTrace();

                    showAlert("Failed to load dashboard.");
                }
            });

            timeline.play();
        }else {

                showAlert("Invalid username or password.");
            }

        } catch (Exception e) {

            e.printStackTrace();

            showAlert("Login failed.");
        }
    }

    // ==========================================
    // REGISTER NEW USER
    // ==========================================

    @FXML
    public void openRegister() {

        try {

            Stage stage =
                    (Stage) usernameField.getScene().getWindow();

            Parent root =
                    FXMLLoader.load(
                            getClass().getResource(
                                    "/com/example/visapp/view/Register.fxml"));

            stage.setScene(new Scene(root));
            stage.setTitle("Register");
            stage.show();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // ==========================================
    // ALERT
    // ==========================================

    private void showAlert(String message) {

        Alert alert =
                new Alert(Alert.AlertType.ERROR);

        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}