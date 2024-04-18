package de.badwalden.schule.ui.helper;

import de.badwalden.schule.dao.DBConnector;
import de.badwalden.schule.dao.LoginHelperDAO;
import de.badwalden.schule.model.Parent;
import de.badwalden.schule.model.Student;
import de.badwalden.schule.model.helper.ModelBuilder;
import de.badwalden.schule.ui.controller.LoginController;
import de.badwalden.schule.model.User;
import de.badwalden.schule.model.Admin;
import javafx.scene.control.Alert;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;

public class LoginHelper {
//    private final int salt = 5;

    public LoginHelper() {

    }

    public static boolean authenticate(String username, String password) {
//        LoginHelperDAO dbconn = new LoginHelperDAO();
//        String db_password = "";
//
//        String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt(salt));
//
//        auth = BCrypt.checkpw(db_password, hashed_password);


        if (password.equals("admin")) {
            Admin user = new Admin();
            // save user in Session
            Session.getInstance().setCurrentUser(user);
            System.out.println("Admin logged in");

            return true;
        } else if (password.equals("parent")) {
            // mocked parent id to use for parent example
            int parentId = 1;

            // build model
            Parent parent = ModelBuilder.buildModelFromParent(parentId);

            // save user in Session
            Session.getInstance().setCurrentUser(parent);
            System.out.println("Parent logged in");
            return true;
        } else if (password.equals("parentOneChild")) {
            Parent parent = new Parent();
            parent.setId(1);

            Student child1 = new Student();
            child1.setFirstName("Friedrich");

            ArrayList<Student> children = new ArrayList<>();
            children.add(child1);
            parent.setChildren(children);

            // save user in Session
            Session.getInstance().setCurrentUser(parent);
            System.out.println("Parent logged in");
            return true;
        } else {
            DialogHelper.showAlertDialog(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
            return false;
        }
    }

}
