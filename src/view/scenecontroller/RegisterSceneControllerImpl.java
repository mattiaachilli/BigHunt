package view.scenecontroller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.Controller;
import controller.ControllerImpl;
import controller.files.UserManager;
import controller.files.UserManagerImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.data.UserData;
/**
 * 
 * Sample Skeleton for 'Register.fxml' Controller Class
 *
 */
public class RegisterSceneControllerImpl extends AbstractSceneController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="userTextField"
    private TextField userTextField; // Value injected by FXMLLoader

    @FXML // fx:id="settingsApplyBtn"
    private Button settingsApplyBtn; // Value injected by FXMLLoader

    @FXML // fx:id="exitBtn"
    private Button exitBtn; // Value injected by FXMLLoader

    @FXML // fx:id="pwdTextField"
    private PasswordField pwdTextField; // Value injected by FXMLLoader

    private final UserManager user = new UserManagerImpl();

    //private final Controller controller; 
    
    @FXML
    void quitGame() {
        Runtime.getRuntime().exit(0);
    }

    @FXML
    void registerBtn() {
        /*Optional<UserData> userData = user.register(this.userTextField.getText(), this.passwordTextField.getText());
        if(userData.isPresent()) {
            //inserire??
            this.getSceneFactory().openMenuScene();
        } else {
            Alert alert = new Alert(AlertType.ERROR, "USER GIA' ESISTENTE", ButtonType.OK);
            alert.showAndWait();
            
        }*/
        //view.getcontr.loginuser/regUser
        this.getSceneFactory().openMenuScene();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert userTextField != null : "fx:id=\"userTextField\" was not injected: check your FXML file 'Register.fxml'.";
        assert settingsApplyBtn != null : "fx:id=\"settingsApplyBtn\" was not injected: check your FXML file 'Register.fxml'.";
        assert exitBtn != null : "fx:id=\"exitBtn\" was not injected: check your FXML file 'Register.fxml'.";
        assert pwdTextField != null : "fx:id=\"pwdTextField\" was not injected: check your FXML file 'Register.fxml'.";

    }
}
