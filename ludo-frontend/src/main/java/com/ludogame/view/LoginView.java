package com.ludogame.view;

import com.ludogame.controller.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Login View
 * Demonstrates JavaFX GUI construction and Encapsulation
 */
public class LoginView {

    private Stage stage;
    private LoginController controller;

    public LoginView(Stage stage) {
        this.stage = stage;
        this.controller = new LoginController();
        this.controller.setStage(stage);
    }

    public void show() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f0f0f0;");

        // Header
        Label title = new Label("Ludo Game");
        title.setStyle("-fx-font-size: 32px; -fx-font-weight: bold; -fx-text-fill: #333;");
        VBox header = new VBox(title);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(20));
        header.setStyle("-fx-background-color: #007bff;");

        // Login Form
        VBox loginForm = createLoginForm();

        // Center
        VBox center = new VBox(loginForm);
        center.setAlignment(Pos.CENTER);
        center.setPadding(new Insets(40));

        root.setTop(header);
        root.setCenter(center);

        Scene scene = new Scene(root, 500, 400);
        stage.setTitle("Ludo Game - Login");
        stage.setScene(scene);
        stage.show();
    }

    private VBox createLoginForm() {
        VBox form = new VBox(15);
        form.setPadding(new Insets(30));
        form.setStyle("-fx-border-color: #ccc; -fx-border-radius: 5; -fx-background-color: white;");
        form.setMaxWidth(350);

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setPromptText("Enter your email");

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px; -fx-background-color: #007bff; -fx-text-fill: white;");
        loginButton.setPrefWidth(Double.MAX_VALUE);

        Button registerButton = new Button("Register");
        registerButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px; -fx-background-color: #28a745; -fx-text-fill: white;");
        registerButton.setPrefWidth(Double.MAX_VALUE);

        form.getChildren().addAll(
                emailLabel, emailField,
                passwordLabel, passwordField,
                loginButton, registerButton
        );

        return form;
    }
}