package de.badwalden.schule;

import de.badwalden.schule.ui.controller.LoginController;
import de.badwalden.schule.ui.helper.Language;
import de.badwalden.schule.ui.helper.LanguageHelper;
import de.badwalden.schule.ui.helper.NavigationHelper;
import de.badwalden.schule.ui.views.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static NavigationHelper navigationHelper;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        navigationHelper = new NavigationHelper(primaryStage);
        navigationHelper.navigateTo("LoginView");
        primaryStage.show();

    }
}
