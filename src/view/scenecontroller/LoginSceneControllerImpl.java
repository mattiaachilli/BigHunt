package view.scenecontroller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.files.UserManager;
import controller.files.UserManagerImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import model.data.UserData;
import view.View;

/**
 * 
 * Sample Skeleton for 'Login.fxml' Controller Class
 *
 */
public class LoginSceneControllerImpl extends AbstractSceneController implements LoginSceneController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="userTextField"
    private TextField userTextField; // Value injected by FXMLLoader

    @FXML // fx:id="passwordTextField"
    private PasswordField passwordTextField; // Value injected by FXMLLoader

    @FXML // fx:id="loginBtn"
    private Button loginBtn; // Value injected by FXMLLoader

    @FXML // fx:id="exitBtn"
    private Button exitBtn; // Value injected by FXMLLoader

    // private final UserManager user = new UserManagerImpl();
    private View view;

    @Override
    public void setView(final View view) {
        this.view = view;
    }

    @FXML
    void quitGame() {
        Runtime.getRuntime().exit(0);
    }

    @FXML
    void login() {
        // Optional<UserData> userData = user.login(this.userTextField.getText(),
        // this.passwordTextField.getText());
        if (this.view.getController().loginUser(this.userTextField.getText(), this.passwordTextField.getText())) {
            this.getSceneFactory().openMenuScene();
        } else {
            Alert alert = new Alert(AlertType.ERROR, "USER E/O PASSWORD ERRATI", ButtonType.OK);
            alert.showAndWait();

        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert userTextField != null : "fx:id=\"userTextField\" was not injected: check your FXML file 'Login.fxml'.";
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'Login.fxml'.";
        assert loginBtn != null : "fx:id=\"loginBtn\" was not injected: check your FXML file 'Login.fxml'.";
        assert exitBtn != null : "fx:id=\"exitBtn\" was not injected: check your FXML file 'Login.fxml'.";

    }
}
