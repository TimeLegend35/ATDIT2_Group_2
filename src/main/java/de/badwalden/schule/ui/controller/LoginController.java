package de.badwalden.schule.ui.controller;

import de.badwalden.schule.Main;
import de.badwalden.schule.model.User;
import de.badwalden.schule.ui.helper.LanguageHelper;
import de.badwalden.schule.ui.helper.LoginHelper;
import de.badwalden.schule.ui.helper.DialogHelper;
import de.badwalden.schule.ui.views.LoginView;
import javafx.scene.control.Alert.AlertType;

import java.util.Locale;

import static de.badwalden.schule.Main.navigationHelper;

public class LoginController {

    private LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
    }

    public void handleLoginButtonPressed() {
        // authenticate user
        boolean login = LoginHelper.authenticate(loginView.getUserNameTextField().getText(), loginView.getPasswordField().getText());
        if(login) {
            showMainView();
        }
    }

    private void showMainView() {
        navigationHelper.navigateTo("MainView");
    }

    public void handleLanguageChange(String newLanguage) {
        // For demonstration, print the selected language
        System.out.println("Language selected: " + newLanguage);
        LanguageHelper.setLocale(newLanguage);
        loginView.updateTextsFromResourceBundle();
    }

    public LoginView getLoginView() {
        return loginView;
    }
}


