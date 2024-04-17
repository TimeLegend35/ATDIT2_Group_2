import de.badwalden.schule.deprecated.Subject;
import de.badwalden.schule.model.*;
import de.badwalden.schule.ui.helper.LanguageHelper;
import de.badwalden.schule.ui.helper.LoginHelper;
import de.badwalden.schule.ui.helper.NavigationHelper;
import de.badwalden.schule.ui.helper.Session;
import de.badwalden.schule.ui.views.LoginView;
import de.badwalden.schule.ui.views.MainView;
import de.badwalden.schule.ui.views.SidebarView;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;

public class SchoolTest {

    //TestParentClass
    @Test
    public void testGetChildren(){
        //assemble
        Parent parent;
        List<Student> children;
        parent = new Parent();
        children = new ArrayList<>();
        //act

        //assert
        assertEquals(0, parent.getChildren().size(), "Initially, no children should be present");
    }

    //TestParentClass
    @Test
    public void testSetChildren(){
        //assemble
        Parent parent = null;
        List<Student> children = null;
        Student alice = new Student();
        Student bob = new Student();
        //act
        children.add(alice);
        children.add(bob);
        parent.setChildren(children);
        //assert
        assertEquals(2, parent.getChildren().size(), "Number of children should match after setting");
        assertTrue(parent.getChildren().contains(alice), "A should be present");
        assertTrue(parent.getChildren().contains(bob), "B should be present");
    }

    //TestStudentClass
    @Test
    public void testCompulsorySchooling(){
        //assemble
        Student student;
        student = new Student();
        boolean initialCompulsorySchooling = student.isCompulsorySchooling();
        //act
        student.setCompulsorySchooling(true);
        //assert
        assertTrue(student.isCompulsorySchooling(), "Compulsory schooling should be true after setting");
        assertNotEquals(initialCompulsorySchooling, student.isCompulsorySchooling(), "Compulsory schooling should have changed");
    }

    //TestStudentClass
    @Test
    public void testServiceList(){
        //assemble
        Student student = new Student();
        List<Service> serviceList = new ArrayList<>();
        Service fussball = new Service();
        Service basteln = new Service();
        //act
        serviceList.add(fussball);
        serviceList.add(basteln);
        student.setServiceList(serviceList);
        //assert
        assertEquals(serviceList, student.getServiceList(), "Service list should match after setting");
    }

    //TestStudentClass
    @Test
    public void testGrades(){
        //assemble
        Student student = new Student();
        List<Subject> grades = new ArrayList<>();
        Subject math = new Subject();
        Subject science = new Subject();
        //act
        grades.add(3, math);
        grades.add(1, science);
        student.setGrades(grades);
        //assert
        assertEquals(grades, student.getGrades(), "Grades list should match after setting");
    }

    //TestParentClass
    @Test
    public void testGetId(){
        //assemble
        Parent user = new Parent();
        //act
        int id = 123456;
        user.setId(id);
        //assert
        assertEquals(id, user.getId(), "IDs should match");
    }

    //TestServiceClass
    @Test
    public void testAddAndGetStudentToStudentList(){
        //assemble
        Service basteln = new Service();
        Student alice = new Student();
        //act
        basteln.addStudentToStudentList(alice);
        //assert
        assertTrue(basteln.getStudentList().contains(alice));
    }

    //TestServiceClass
    @Test
    public void testAddStudentWhenServiceIsFull(){
        //assemble
        Student bob = new Student();
        Student clemens = new Student();
        Student dunja = new Student();
        Service sport = new Service();
        //act
        sport.setNumberOfSeats(2);
        sport.addStudentToStudentList(clemens);
        sport.addStudentToStudentList(dunja);
        sport.addStudentToStudentList(bob);
        //assert
        assertFalse(sport.getStudentList().contains(bob));
    }

    //TestLanguageHelperClass
    @Test
    public void testSetLocaleToEnglish (){
        //assemble
        String language = "English";
        //act
        LanguageHelper.setLocale(language);
        //assert
        assertEquals(Locale.US, LanguageHelper.locale, "Locale should be set to English (US)");
    }

    //TestLanguageHelperClass
    @Test
    public void testGetString (){
        //assemble
        LanguageHelper.setLocale("Deutsch");
        String key = "username";
        //act
        String result = LanguageHelper.getString(key);
        //assert
        assertEquals("Benutzername", result, "Expected key message should be retrieved for German locale");
    }

    //TestLoginHelperClass
    @Test
    public void testAuthenticateAdmin (){
        //assemble
        String username = "admin";
        String password = "admin";
        //act
        boolean result = LoginHelper.authenticate(username, password);
        //assert
        assertTrue(result, "Admin authentication should succeed");
        assertTrue(Session.getInstance().getCurrentUser() instanceof Admin, "Current user should be an instance of Admin");
    }

    //TestLoginHelperClass
    @Test
    public void testAuthenticateInvalidCredentials (){
        //assemble
        String username = "invalid";
        String password = "invalid";
        //act
        boolean result = LoginHelper.authenticate(username, password);
        //assert
        assertFalse(result, "Authentication should fail for invalid credentials");
        assertNull(Session.getInstance().getCurrentUser(), "Current user should be null");
    }

    //TestNavigationHelperClass
    @Test
    public void testNavigateToMainView (){
        //assemble
        Stage primaryStage = new Stage();
        NavigationHelper navigationHelper = new NavigationHelper(primaryStage);
        //act
        navigationHelper.navigateTo("MainView");
        Scene scene = primaryStage.getScene();
        //assert
        assertNotNull(scene, "Scene should not be null");
        assertTrue(scene.getRoot() instanceof MainView, "Root node should be an instance of MainView");
    }

    //TestNavigationHelperClass
    @Test
    void testNavigateBack() {
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
    }

    //TestSessionClass
    @Test
    void testSetAndGetUser() {
        //assemble
        Session session = Session.getInstance();
        User user = new Parent();
        //act
        session.setCurrentUser(user);
        User currentUser = session.getCurrentUser();
        //assert
        assertNotNull(currentUser, "Current user should not be null after setting");
        assertEquals(user, currentUser, "Current user should match the one set");
    }

    //TestLoginViewClass
    @Test
    void testUpdateTextsFromResourceBundle() {
        //assemble
        LoginView loginView = new LoginView();
        Text userNameLabel = loginView.userNameLabel;
        TextField userNameTextField = loginView.getUserNameTextField();
        Text passwordLabel = loginView.passwordLabel;
        PasswordField passwordField = loginView.getPasswordField();
        CheckBox stayLoggedInCheckBox = loginView.getStayLoggedInCheckBox();
        Button signInButton = loginView.getSignInButton();
        //act
        LanguageHelper.setLocale("Français");
        loginView.updateTextsFromResourceBundle();
        //assert
        assertEquals("Nom d'utilisateur:", userNameLabel.getText(), "Username label should be updated");
        assertEquals("Mot de passe:", passwordLabel.getText(), "Password label should be updated");
        assertEquals("Rester connecté", stayLoggedInCheckBox.getText(), "Stay logged in checkbox label should be updated");
        assertEquals("Se connecter", signInButton.getText(), "Sign in button text should be updated");
    }

    //TestSidebarViewClass
    @Test
    void testSidebarTitleLabelInitialization() {
        //assemble
        SidebarView sidebarView = new SidebarView();
        //act
        Label titleLabel = (Label) sidebarView.getChildren().get(0);
        //assert
        assertTrue(sidebarView.getChildren().get(0) instanceof Label, "First child should be a Label");
        assertEquals("Learning Hub\nBad Walden", titleLabel.getText(), "Title label should have correct text");
        assertEquals(Font.font("Arial", FontWeight.BOLD, 20), titleLabel.getFont(), "Title label should have correct font");
    }
}
