package de.badwalden.schule.ui.views;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CalenderView extends VBox {
    public CalenderView() {
        super();
        getChildren().add(new Text("Hier kann Content fuer die Kalender View rein"));
    }
}
