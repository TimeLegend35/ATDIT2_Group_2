package de.badwalden.schule.ui.views;

import de.badwalden.schule.model.Admin;
import de.badwalden.schule.model.User;
import de.badwalden.schule.ui.helper.Session;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CalenderView extends VBox {
    public CalenderView() {
        super();
        // check what user type is logged in and plot according
        User user = Session.getInstance().getCurrentUser();

        if (user instanceof Admin) {
            getChildren().add(new Text("Hier kann Content fuer die Kalender View rein"));
        }
    }
}
