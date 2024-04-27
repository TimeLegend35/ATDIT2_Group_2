package de.badwalden.schule.ui.controller;

import de.badwalden.schule.Main;
import de.badwalden.schule.model.User;
import de.badwalden.schule.ui.helper.Language;
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

    public void handleLoginButtonPressed(String username, String password) {
        // authenticate user
        boolean login = LoginHelper.authenticate(username, password);
        if(login) {
            showMainView();
        }
    }

    private void showMainView() {
        navigationHelper.navigateTo("MainView");
    }

    public void handleLanguageChange(Language newLanguage) {
        LanguageHelper.setLocale(newLanguage);
        loginView.updateTextsFromResourceBundle();
    }
}


