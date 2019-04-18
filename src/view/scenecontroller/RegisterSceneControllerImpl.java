package view.scenecontroller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import view.View;

/**
 *
 * Sample Skeleton for 'Register.fxml' Controller Class.
 *
 */
public class RegisterSceneControllerImpl extends AbstractSceneController implements RegisterSceneController {

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

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    // private final UserManager user = new UserManagerImpl();
    private View view;

    @Override
    public final void setView(final View view) {
        this.view = view;
    }

    @Override
    public final void callRegister() {
        this.registerBtn();
    }
    /**
     * Quit game.
     */
    @FXML
    protected final void quitGame() {
        Runtime.getRuntime().exit(0);
    }

    /**
     * Go to login scene.
     */
    @FXML
    protected final void goToLogin() {
        this.getSceneFactory().openLoginScene();
    }

    /**
     * Register button.
     */
    @FXML
    protected final void registerBtn() {
        if (this.pwdTextField.getText().isEmpty() || this.userTextField.getText().isEmpty()) {
            this.showAlert("COMPILARE TUTTI I CAMPI");
        } else if (this.view.getController().registerUser(this.userTextField.getText(), this.pwdTextField.getText())) {
            this.getSceneFactory().openMenuScene();
        } else {
            this.showAlert("USER GIA' ESISTENTE");
            this.resetTextField();
        }
    }

    private void showAlert(final String text) {
        final Alert alert = new Alert(AlertType.ERROR, text, ButtonType.OK);
        alert.showAndWait();
    }

    private void resetTextField() {
        this.userTextField.setText("");
        this.pwdTextField.setText("");
    }

    /**
     * Initialize.
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    protected final void initialize() {
        assert userTextField != null : "fx:id=\"userTextField\" was not injected: check your FXML file 'Register.fxml'.";
        assert settingsApplyBtn != null : "fx:id=\"settingsApplyBtn\" was not injected: check your FXML file 'Register.fxml'.";
        assert exitBtn != null : "fx:id=\"exitBtn\" was not injected: check your FXML file 'Register.fxml'.";
        assert pwdTextField != null : "fx:id=\"pwdTextField\" was not injected: check your FXML file 'Register.fxml'.";
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'Register.fxml'.";
        }
}
