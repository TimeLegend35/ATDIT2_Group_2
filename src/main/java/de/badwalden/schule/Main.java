package de.badwalden.schule;

import de.badwalden.schule.ui.controller.LoginController;
import de.badwalden.schule.ui.views.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginView loginView = new LoginView();

        Scene scene = new Scene(loginView, 1280, 720);
        primaryStage.setTitle("Learning Hub - Bad Walden");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
