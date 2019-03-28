package view.scenecontroller;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameOverSceneControllerImpl extends AbstractSecondarySceneController{

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="scoreLabel"
    private Label scoreLabel; // Value injected by FXMLLoader

    @FXML // fx:id="killsLabel"
    private Label killsLabel; // Value injected by FXMLLoader

    @FXML // fx:id="newgame"
    private Button newgame; // Value injected by FXMLLoader

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="title"
    private Label title; // Value injected by FXMLLoader

    @FXML // fx:id="timePlayedLabel"
    private Label timePlayedLabel; // Value injected by FXMLLoader

    @FXML
    void startNewGame() {
        //start new game
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert scoreLabel != null : "fx:id=\"scoreLabel\" was not injected: check your FXML file 'GameOver.fxml'.";
        assert killsLabel != null : "fx:id=\"killsLabel\" was not injected: check your FXML file 'GameOver.fxml'.";
        assert newgame != null : "fx:id=\"newgame\" was not injected: check your FXML file 'GameOver.fxml'.";
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'GameOver.fxml'.";
        assert title != null : "fx:id=\"title\" was not injected: check your FXML file 'GameOver.fxml'.";
        assert timePlayedLabel != null : "fx:id=\"timePlayedLabel\" was not injected: check your FXML file 'GameOver.fxml'.";

    }
}