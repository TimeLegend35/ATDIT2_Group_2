package ui;

import de.badwalden.schule.ui.views.SidebarView;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SidebarViewTest {

    @BeforeAll
    public static void setUp(){
        Platform.startup(() -> {
        });
    }

    @Test
    void testSidebarTitleLabelInitialization() {
        Platform.runLater(() -> {
            //assemble
            SidebarView sidebarView = new SidebarView();
            //act
            Label titleLabel = (Label) sidebarView.getChildren().get(0);
            //assert
            assertTrue(sidebarView.getChildren().get(0) instanceof Label, "First child should be a Label");
            assertEquals("Learning Hub\nBad Walden", titleLabel.getText(), "Title label should have correct text");
            assertEquals(Font.font("Arial", FontWeight.BOLD, 20), titleLabel.getFont(), "Title label should have correct font");
        });
    }

}
