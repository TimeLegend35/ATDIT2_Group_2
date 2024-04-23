package ui;

import de.badwalden.schule.ui.helper.NavigationHelper;
import de.badwalden.schule.ui.views.MainView;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the NavigationHelper class.
 */
public class NavigationHelperTest {

    /**
     * Initializes JavaFX platform before running any test method.
     */
    @BeforeAll
    public static void setUp(){
        Platform.startup(() -> {
        });
    }

    /**
     * Tests the navigateTo() method of the NavigationHelper class to navigate to the MainView.
     * Checks if the scene is set with the MainView as the root node.
     */
    @Test
    public void testNavigateToMainView (){
        Platform.runLater(() -> {
            // Assemble
            Stage primaryStage = new Stage();
            NavigationHelper navigationHelper = new NavigationHelper(primaryStage);
            // Act
            navigationHelper.navigateTo("MainView");
            Scene scene = primaryStage.getScene();
            // Assert
            assertNotNull(scene, "Scene should not be null");
            assertTrue(scene.getRoot() instanceof MainView, "Root node should be an instance of MainView");
        });
    }

    /**
     * Tests the navigateBack() method of the NavigationHelper class.
     * Checks if the scene is set back to the main view after navigating back.
     */
    @Test
    public void testNavigateBack() {
        Platform.runLater(() -> {
            // Assemble
            Stage primaryStage = new Stage();
            NavigationHelper navigationHelper = new NavigationHelper(primaryStage);
            navigationHelper.navigateTo("MainView");
            Scene mainScene = primaryStage.getScene();
            // Act
            navigationHelper.navigateTo("LoginView");
            navigationHelper.navigateBack();
            // Assert
            Scene scene = primaryStage.getScene();
            assertNotNull(scene, "Scene should not be null");
            assertEquals(mainScene, scene, "Scene should be the main view after navigating back");
        });
    }

    /**
     * Tests the button click action in the scene.
     * Checks if the scene's root node is updated to a Button instance with the correct text.
     */
    @Test
    public void testButtonClick(){
        Platform.runLater(() -> {
            // Assemble
            Stage primaryStage = new Stage();
            NavigationHelper navigationHelper = new NavigationHelper(primaryStage);
            Scene scene = new Scene(new Button("Test Button"), 200, 200);
            // Act
            primaryStage.setScene(scene);
            // Assert
            assertNotNull(primaryStage.getScene(), "Scene should not be null after button click");
            assertTrue(primaryStage.getScene().getRoot() instanceof Button, "Root node should be an instance of Button");
            assertEquals("Test Button", ((Button) primaryStage.getScene().getRoot()).getText(), "Button text should match");
        });
    }
}

