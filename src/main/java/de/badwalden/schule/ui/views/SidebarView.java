package de.badwalden.schule.ui.views;

import de.badwalden.schule.model.Admin;
import de.badwalden.schule.ui.helper.LanguageHelper;
import de.badwalden.schule.ui.helper.Session;
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

import java.util.Arrays;
import java.util.stream.Collectors;

import static de.badwalden.schule.Main.navigationHelper;

public class SidebarView extends VBox {

    public SidebarView() {
        setPadding(new Insets(10));
        setSpacing(8);

        Label titleLabel = new Label(LanguageHelper.getString("sidebar_title")+"\nBad Walden");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setPadding(new Insets(10, 0, 10, 0));

        getChildren().add(titleLabel);

        Label schoolLabel = new Label(LanguageHelper.getString("school"));
        schoolLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // You might want to add a style class to your buttons for CSS styling
        Button calendarButton = new Button(LanguageHelper.getString("calendar"));
        calendarButton.setId("Kalender");
        Button gradesButton = new Button(LanguageHelper.getString("grades"));
        gradesButton.setId("Noten");
        Button absencesButton = new Button(LanguageHelper.getString("sick_note"));
        absencesButton.setId("Krankmeldungen");
        Button classButton = new Button(LanguageHelper.getString("class"));
        classButton.setId("Klasse");

        Label careLabel = new Label(LanguageHelper.getString("care_offer"));
        careLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        Button betreuungsmarktplatzButton = new Button(LanguageHelper.getString("care_offer_market_place"));
        betreuungsmarktplatzButton.setId("Betreuungsmarktplatz");

        Label notificationLabel = new Label(LanguageHelper.getString("notifications"));
        notificationLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        Button notificationsButton = new Button(LanguageHelper.getString("notifications"));
        notificationsButton.setId("Benachrichtigungen");

        Label organizationalLabel = new Label(LanguageHelper.getString("organisation"));
        organizationalLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        Button registrationButton = new Button(LanguageHelper.getString("school_registration"));
        registrationButton.setId("Schulanmeldung");
        Button deregistrationButton = new Button(LanguageHelper.getString("school_cancellation"));
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

        String fullName = Session.getInstance().getCurrentUser().getFirstName() + " " + Session.getInstance().getCurrentUser().getLastName();
        String initials = Arrays.stream(fullName.split(" "))
                .map(name -> name.substring(0, 1))
                .collect(Collectors.joining());

        if(Session.getInstance().getCurrentUser() instanceof Admin) {
            fullName = "Administrator";
            initials = "ADMIN";
        }

        // Placeholder for logo (replace with your logo)
        Label logoLabel = new Label(initials);
        logoLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // Placeholder for name (replace with actual name)
        Label nameLabel = new Label(fullName);
        nameLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        // Logout button
        Button logoutButton = new Button(LanguageHelper.getString("logout"));
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
