package de.badwalden.schule.ui.controller;

import de.badwalden.schule.ui.helper.LoginHelper;
import de.badwalden.schule.ui.views.LoginView;
import de.badwalden.schule.ui.views.MainView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginController {

    private LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        attachEvents();
    }

    private void attachEvents() {
        loginView.getSignInButton().setOnAction(event -> handleLoginButtonPressed());
    }

    private void handleLoginButtonPressed() {
        // auth
        LoginHelper loginHelper = new LoginHelper(this);
        loginHelper.authenticate();
        if (loginHelper.get_auth()) {
            showMainView();
        } else {
            loginView.showAlertDialog(AlertType.ERROR, "Login Failed", "Invalid username or password.");
        }
    }

    private boolean isValidCredentials(String username, String password) {
        // Replace with real authentication logic
        return "admin".equals(username) && "pw".equals(password);
    }

    private void showMainView() {
        MainView mainView = new MainView();
        MainController mainController = new MainController(mainView);  // Associate MainController

        Scene mainScene = new Scene(mainView, 1280, 720);  // Consider extracting scene size as constants or configuration

        Stage primaryStage = (Stage) loginView.getSignInButton().getScene().getWindow();
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Main View - Bad Walden");  // Set the title for the main window
        primaryStage.show();  // Refresh the stage to show the MainView
    }

    public LoginView getLoginView() {
        return loginView;
    }
}


