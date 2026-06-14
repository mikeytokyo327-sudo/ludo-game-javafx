package com.ludogame.controller;

import com.ludogame.service.ApiClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

/**
 * Login Controller
 * Demonstrates MVC pattern with FXML controller
 */
@Slf4j
public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void handleLogin(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Email and password cannot be empty");
            return;
        }

        try {
            String token = ApiClient.login(email, password);
            log.info("User logged in successfully");
            showAlert(Alert.AlertType.INFORMATION, "Success", "Login successful!");
            // Navigate to dashboard or game view
        } catch (Exception e) {
            log.error("Login failed: {}", e.getMessage());
            showAlert(Alert.AlertType.ERROR, "Login Error", "Invalid email or password");
        }
    }

    @FXML
    public void handleRegister(ActionEvent event) {
        // Navigate to registration view
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}