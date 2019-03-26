package view.scenecontroller;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class PauseSceneControllerImpl extends AbstractSecondarySceneController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="resumeGameBtn"
    private Button resumeGameBtn; // Value injected by FXMLLoader

    @FXML // fx:id="goToMenuBtn"
    private Button goToMenuBtn; // Value injected by FXMLLoader

    @FXML // fx:id="title"
    private Label title; // Value injected by FXMLLoader

    @FXML // fx:id="pause"
    private BorderPane pause; // Value injected by FXMLLoader

    @FXML
    void resumeGame() {
        this.getSceneFactory().openGameScene();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert resumeGameBtn != null : "fx:id=\"resumeGameBtn\" was not injected: check your FXML file 'Pause.fxml'.";
        assert goToMenuBtn != null : "fx:id=\"goToMenuBtn\" was not injected: check your FXML file 'Pause.fxml'.";
        assert title != null : "fx:id=\"title\" was not injected: check your FXML file 'Pause.fxml'.";
        assert pause != null : "fx:id=\"pause\" was not injected: check your FXML file 'Pause.fxml'.";

    }
}
