package de.badwalden.schule.ui.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import static de.badwalden.schule.Main.navigationHelper;

public class SidebarView extends VBox {

    public SidebarView() {
        setPadding(new Insets(10));
        setSpacing(8);

        Label titleLabel = new Label("Learning Hub\nBad Walden");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setPadding(new Insets(10, 0, 10, 0));

        getChildren().add(titleLabel);

        Label schoolLabel = new Label("Schulisches");
        schoolLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // You might want to add a style class to your buttons for CSS styling
        Button calendarButton = new Button("Kalender");
        calendarButton.setId("Kalender");
        Button gradesButton = new Button("Noten");
        gradesButton.setId("Noten");
        Button absencesButton = new Button("Krankmeldungen");
        absencesButton.setId("Krankmeldungen");
        Button classButton = new Button("Klasse");
        classButton.setId("Klasse");

        Label careLabel = new Label("Betreuung");
        careLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        Button betreuungsmarktplatzButton = new Button("Betreuungsmarktplatz");
        betreuungsmarktplatzButton.setId("Betreuungsmarktplatz");

        Label notificationLabel = new Label("Benachrichtigungen");
        notificationLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        Button notificationsButton = new Button("Benachrichtigungen");
        notificationsButton.setId("Benachrichtigungen");

        Label organizationalLabel = new Label("Organisatorisches");
        organizationalLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        Button registrationButton = new Button("Schulanmeldung");
        registrationButton.setId("Schulanmeldung");
        Button deregistrationButton = new Button("Schulabmeldung");
        deregistrationButton.setId("Schulabmeldung");

        getChildren().addAll(
                schoolLabel, calendarButton, gradesButton, absencesButton, classButton,
                new Separator(),
                careLabel, betreuungsmarktplatzButton,
                new Separator(),
                notificationLabel, notificationsButton,
                new Separator(),
                organizationalLabel, registrationButton, deregistrationButton
        );

        // HBox for logo, name, and logout button
        HBox bottomBox = new HBox();
        bottomBox.setSpacing(10);
        bottomBox.setAlignment(Pos.BOTTOM_CENTER);

        // Placeholder for logo (replace with your logo)
        Label logoLabel = new Label("Logo");
        logoLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // Placeholder for name (replace with actual name)
        Label nameLabel = new Label("Your Name");
        nameLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        // Logout button
        Button logoutButton = new Button("Logout");
        logoutButton.getStyleClass().add("logout-button");
        logoutButton.setId("logoutButton");
        // Session should be reseted here!!!
        logoutButton.setOnAction(event -> navigationHelper.navigateTo("LoginView"));

        VBox.setVgrow(bottomBox, Priority.ALWAYS); // Allow bottomBox to grow vertically
        setAlignment(Pos.TOP_CENTER); // Align children to top center

        bottomBox.getChildren().addAll(logoLabel, nameLabel, logoutButton);
        getChildren().add(bottomBox);
    }

    // here one could add access to the buttons etc. with getters
}
