package de.badwalden.schule.ui.helper;

import de.badwalden.schule.dao.DBConnector;
import de.badwalden.schule.model.Parent;
import de.badwalden.schule.model.Student;
import de.badwalden.schule.model.helper.ModelBuilder;
import de.badwalden.schule.model.helper.Session;
import javafx.scene.control.Alert;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginHelper {
    private static final Logger logger = Logger.getLogger(DBConnector.class.getName());
    private static Session session = Session.getInstance();

    public LoginHelper() {

    }

    public static boolean authenticate(String username, String password) {

        switch (password) {
            case "parent" -> {
                // mocked parent id to use for parent example
                int parentId = 1;

                // build model
                Parent parent = ModelBuilder.buildModelFromParent(parentId);

                // call session again incase session got reset
                session = Session.getInstance();
                // save user in Session
                session.setCurrentUser(parent);
                logger.log(Level.INFO, LanguageHelper.getString("parent_logged_in"));
                return true;
            }
            case "parentOneChild" -> {
                // mocked parent id to use for parent example
                int parentId = 3;

                // build model
                Parent parent = ModelBuilder.buildModelFromParent(parentId);

                // call session again incase session got reset
                session = Session.getInstance();
                // save user in Session
                session.setCurrentUser(parent);
                logger.log(Level.INFO, "Parent with one child logged in");
                return true;
            }
            case "parentYoungChild" -> {
                // mocked parent id to use for parent example
                int parentId = 12;

                // build model
                Parent parent = ModelBuilder.buildModelFromParent(parentId);

                // call session again incase session got reset
                session = Session.getInstance();
                // save user in Session
                session.setCurrentUser(parent);
                logger.log(Level.INFO, "Parent with one child logged in");
                return true;
            }
            case "student" -> {
                // mocked student id
                int studentId = 1;

                // build model
                Student student = ModelBuilder.buildModelFromStudent(studentId);

                // call session again incase session got reset
                session = Session.getInstance();
                // save user in Session
                session.setCurrentUser(student);
                logger.log(Level.INFO, LanguageHelper.getString("student_logged_in"));
                return true;
            }
            default -> {
                DialogHelper.showAlertDialog(Alert.AlertType.ERROR, LanguageHelper.getString("login_failed"), LanguageHelper.getString("invalid_credentials"));
                return false;
            }
        }
    }

}
