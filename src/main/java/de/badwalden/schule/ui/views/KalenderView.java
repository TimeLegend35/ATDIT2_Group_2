package de.badwalden.schule.ui.views;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class KalenderView extends VBox {
    public KalenderView() {
        super();
        getChildren().add(new Text("Hier kann Content fuer die Kalender View rein"));
    }
}
