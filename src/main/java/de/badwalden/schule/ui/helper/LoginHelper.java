package de.badwalden.schule.ui.helper;

import de.badwalden.schule.dao.DBConnector;
import de.badwalden.schule.model.Parent;
import de.badwalden.schule.model.Student;
import de.badwalden.schule.model.helper.ModelBuilder;
import de.badwalden.schule.ui.controller.LoginController;
import de.badwalden.schule.model.User;
import de.badwalden.schule.model.Admin;
import javafx.scene.control.Alert;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginHelper {
//    private final int salt = 5;
    private static final Logger logger = Logger.getLogger(DBConnector.class.getName());

    public LoginHelper() {

    }

    public static boolean authenticate(String username, String password) {
//        LoginHelperDAO dbconn = new LoginHelperDAO();
//        String db_password = "";
//
//        String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt(salt));
//
//        auth = BCrypt.checkpw(db_password, hashed_password);


        switch (password) {
            case "admin" -> {
                // todo update or delete
                // this makes no sense :)))

                Admin user = new Admin();
                // save user in Session
                Session.getInstance().setCurrentUser(user);
                logger.log(Level.INFO, "Admin logged in");
                return true;
            }
            case "parent" -> {
                // mocked parent id to use for parent example
                int parentId = 1;

                // build model
                Parent parent = ModelBuilder.buildModelFromParent(parentId);

                // save user in Session
                Session.getInstance().setCurrentUser(parent);
                logger.log(Level.INFO, "Parent logged in");
                return true;
            }
            case "student" -> {
                // mocked student id
                int studentId = 1;

                // build model
                Student student = ModelBuilder.buildModelFromStudent(studentId);

                // save user in Session
                Session.getInstance().setCurrentUser(student);
                logger.log(Level.INFO, "Student logged in");
                return true;
            }
            case "parentOneChild" -> {
                // todo need to work out this usecase or delete it!!!
                // this wont work :)))

                Parent parent = new Parent();
                parent.setId(1);

                Student child1 = new Student();
                child1.setFirstName("Friedrich");

                ArrayList<Student> children = new ArrayList<>();
                children.add(child1);
                parent.setChildren(children);

                // save user in Session
                Session.getInstance().setCurrentUser(parent);
                logger.log(Level.INFO, "Parent logged in");
                return true;
            }
            default -> {
                DialogHelper.showAlertDialog(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
                return false;
            }
        }
    }

}
