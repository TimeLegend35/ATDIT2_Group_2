package de.badwalden.schule.ui.controller;

import de.badwalden.schule.ui.helper.Language;
import de.badwalden.schule.ui.helper.LanguageHelper;
import de.badwalden.schule.ui.helper.LoginHelper;
import de.badwalden.schule.ui.views.LoginView;

import static de.badwalden.schule.Main.navigationHelper;

public class LoginController {

    private LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
    }

    /**
     * Handles the login button press event.
     *
     * @param username the username entered by the user
     * @param password the password entered by the user
     */
    public void handleLoginButtonPressed(String username, String password) {
        // authenticate user
        boolean login = LoginHelper.authenticate(username, password);
        if (login) {
            showMainView();
        }
    }

    /**
     * Shows the main view by navigating to it using the navigation helper.
     */
    private void showMainView() {
        navigationHelper.navigateTo("MainView");
    }

    /**
     * Handles the language change event.
     *
     * @param newLanguage the new language selected by the user
     */
    public void handleLanguageChange(Language newLanguage) {
        LanguageHelper.setLocale(newLanguage);
        loginView.updateTextsFromResourceBundle();
    }

    public LoginView getLoginView() {
        return loginView;
    }
}


