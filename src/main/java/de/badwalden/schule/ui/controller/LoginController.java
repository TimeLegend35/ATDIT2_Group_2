package de.badwalden.schule.ui.controller;

import de.badwalden.schule.Main;
import de.badwalden.schule.ui.helper.LoginHelper;
import de.badwalden.schule.ui.helper.DialogHelper;
import de.badwalden.schule.ui.views.LoginView;
import javafx.scene.control.Alert.AlertType;

public class LoginController {

    private LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
    }

    public void handleLoginButtonPressed() {
        // auth
        LoginHelper loginHelper = new LoginHelper(this);
        loginHelper.authenticate();
        // backdoor temp
        if (loginView.getPasswordField().getText().equals("admin")) {
            showMainView();
            return;
        }
        if (loginHelper.get_auth()) {
            showMainView();
        } else {
            DialogHelper.showAlertDialog(AlertType.ERROR, "Login Failed", "Invalid username or password.");
        }
    }

    private boolean isValidCredentials(String username, String password) {
        // Replace with real authentication logic
        return "admin".equals(username) && "pw".equals(password);
    }

    private void showMainView() {
        Main.navigationHelper.navigateTo("MainView");
    }

    public void handleLanguageChange(String newLanguage) {
        // For demonstration, print the selected language
        System.out.println("Language selected: " + newLanguage);
        // Here, you could add more complex logic, like updating the UI text based on the selected language
    }

    public LoginView getLoginView() {
        return loginView;
    }
}


