package com.example.visapp.controller;

import com.example.visapp.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterController {
    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private ComboBox<String> cmbRole;

    @FXML
    public void initialize() {

        cmbRole.getItems().addAll(
                "Admin",
                "Police Officer",
                "Insurance Agent",
                "Workshop",
                "Customer"
        );
    }

    @FXML
    public void registerUser() {

        try {

            DBConnection db =
                    new DBConnection(
                            "postgres.awdrswdcmoovuyowmmpj",
                            "Doctor@45paduka"
                    );

            Connection conn =
                    db.getConnection();

            String sql =
                    "INSERT INTO users(username, password, role) VALUES(?,?,?)";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, txtUsername.getText());
            ps.setString(2, txtPassword.getText());
            ps.setString(3, cmbRole.getValue());

            ps.executeUpdate();

            Alert alert =
                    new Alert(Alert.AlertType.INFORMATION);

            alert.setContentText("Registration successful.");
            alert.showAndWait();

            goLogin();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @FXML
    public void goLogin() {

        try {

            Stage stage =
                    (Stage) txtUsername.getScene().getWindow();

            Parent root =
                    FXMLLoader.load(
                            getClass().getResource(
                                    "/com/example/visapp/view/Login.fxml"));

            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
