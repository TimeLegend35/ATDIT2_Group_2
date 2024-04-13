package de.badwalden.schule.ui.helper;

import de.badwalden.schule.dao.LoginHelperDAO;
import de.badwalden.schule.ui.controller.LoginController;
import javafx.scene.control.Alert;
import org.mindrot.jbcrypt.BCrypt;

public class LoginHelper {
    private LoginController loginController;
    private boolean auth;
    private String username, password;
    private final int salt = 5;

    public LoginHelper(LoginController loginController) {
        this.loginController = loginController;
        this.username =  loginController.getLoginView().getUserNameTextField().getText();
        this.password = loginController.getLoginView().getPasswordField().getText();;
        auth = false;
    }

    public boolean get_auth() {
        return auth;
    }

    public void authenticate() {
        LoginHelperDAO dbconn = new LoginHelperDAO();
        String db_password = "";

        String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt(salt));

        auth = BCrypt.checkpw(db_password, hashed_password);

        if (!auth) {
            DialogHelper.showAlertDialog(Alert.AlertType.ERROR, "Login Failed", "Wrong Username or Password");
        }
    }
}
