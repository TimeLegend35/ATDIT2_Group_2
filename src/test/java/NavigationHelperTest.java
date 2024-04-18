import de.badwalden.schule.ui.helper.NavigationHelper;
import de.badwalden.schule.ui.views.MainView;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NavigationHelperTest {

    @BeforeAll
    public static void setUp(){
        Platform.startup(() -> {
        });
    }

    @Test
    public void testNavigateToMainView (){
        Platform.runLater(() -> {
        //assemble
        Stage primaryStage = new Stage();
        NavigationHelper navigationHelper = new NavigationHelper(primaryStage);
        //act
        navigationHelper.navigateTo("MainView");
        Scene scene = primaryStage.getScene();
        //assert
        assertNotNull(scene, "Scene should not be null");
        assertTrue(scene.getRoot() instanceof MainView, "Root node should be an instance of MainView");
        });
    }

    @Test
    void testNavigateBack() {
            Platform.runLater(() -> {
        //assemble
        Stage primaryStage = new Stage();
        NavigationHelper navigationHelper = new NavigationHelper(primaryStage);
        navigationHelper.navigateTo("MainView");
        Scene mainScene = primaryStage.getScene();
        //act
        navigationHelper.navigateTo("LoginView");
        navigationHelper.navigateBack();
        //assert
        Scene scene = primaryStage.getScene();
        assertNotNull(scene, "Scene should not be null");
        assertEquals(mainScene, scene, "Scene should be the main view after navigating back");
    });
    }

}
