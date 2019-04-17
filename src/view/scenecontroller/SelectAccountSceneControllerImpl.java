package view.scenecontroller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

/**
 *
 * Sample Skeleton for 'SelecAccount.fxml' Controller Class.
 *
 */
public class SelectAccountSceneControllerImpl extends AbstractSceneController {

    private static final int FONT_SIZE = 26;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="loginBtn"
    private Button loginBtn; // Value injected by FXMLLoader

    @FXML // fx:id="exitBtn"
    private Button exitBtn; // Value injected by FXMLLoader


    /**
     * Go to login scene.
     */
    @FXML
    protected final void goToLogin() {
        this.getSceneFactory().openLoginScene();
    }


    /**
     * Go to register scene.
     */
    @FXML
    protected final void goToRegister() {
        this.getSceneFactory().openRegisterScene();
    }

    /**
     * Quit game.
     */
    @FXML
    protected final void quitGame() {
        Runtime.getRuntime().exit(0);
    }

    /**
     * Initialize.
     */
    @FXML
    protected final void initialize() {
        assert loginBtn != null : "fx:id=\"loginBtn\" was not injected: check your FXML file 'SelectAccount.fxml'.";
        assert exitBtn != null : "fx:id=\"exitBtn\" was not injected: check your FXML file 'SelectAccount.fxml'.";
        this.exitBtn.setFont(new Font(FONT_SIZE));
    }
}
