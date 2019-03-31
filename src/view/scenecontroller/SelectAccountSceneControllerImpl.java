package view.scenecontroller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * 
 * Sample Skeleton for 'SelecAccount.fxml' Controller Class
 *
 */
public class SelectAccountSceneControllerImpl extends AbstractSceneController {
    
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="loginBtn"
    private Button loginBtn; // Value injected by FXMLLoader

    @FXML // fx:id="exitBtn"
    private Button exitBtn; // Value injected by FXMLLoader

    @FXML
    void goToLogin() {
        this.getSceneFactory().openLoginScene();
    }

    @FXML
    void goToRegister() {
        this.getSceneFactory().openRegisterScene();
    }

    @FXML
    void quitGame() {
        Runtime.getRuntime().exit(0);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert loginBtn != null : "fx:id=\"loginBtn\" was not injected: check your FXML file 'SelectAccount.fxml'.";
        assert exitBtn != null : "fx:id=\"exitBtn\" was not injected: check your FXML file 'SelectAccount.fxml'.";

    }
}
