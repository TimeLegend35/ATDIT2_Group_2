package de.badwalden.schule.ui.views;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ImageViewMockup extends VBox {

    public ImageViewMockup(String imageFilePath) {
        Image image = new Image("file:" + imageFilePath);

        // Create an ImageView and set the image
        ImageView imageView = new ImageView(image);

        // Optional: adjust the view settings
        imageView.setFitWidth(896); // Set the width of the ImageView
        imageView.setPreserveRatio(true); // Preserve the image ratio

        // Create a layout and add the ImageView to it
        StackPane root = new StackPane();
        root.getChildren().add(imageView);

        this.getChildren().add(root);
    }
}
