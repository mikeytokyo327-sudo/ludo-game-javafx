package com.ludogame;

import com.ludogame.view.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main Application Class for Ludo Game Frontend
 * Demonstrates Abstraction by extending JavaFX Application
 */
public class LudoFrontendApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginView loginView = new LoginView(primaryStage);
        loginView.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}