/**
 * Sample Skeleton for 'SelectMode.fxml' Controller Class
 */

package view.scenecontroller;

import java.net.URL;
import java.util.ResourceBundle;

import controller.matches.GameMode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
//import utility.GameMode;

public class SelectModeSceneControllerImpl extends AbstractSecondarySceneController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="survivalBtn"
    private Button survivalBtn; // Value injected by FXMLLoader

    @FXML // fx:id="arcadeBtn"
    private Button arcadeBtn; // Value injected by FXMLLoader

    @FXML
    void startArcadeGame() {
        this.getSceneFactory().setGameMode(GameMode.STORY_MODE);
        this.getSceneFactory().openGameScene();
    }

    @FXML
    void startSurvivalGame() {
        this.getSceneFactory().setGameMode(GameMode.SURVIVAL_MODE);
        this.getSceneFactory().openGameScene();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'SelectMode.fxml'.";
        assert survivalBtn != null : "fx:id=\"survivalBtn\" was not injected: check your FXML file 'SelectMode.fxml'.";
        assert arcadeBtn != null : "fx:id=\"arcadeBtn\" was not injected: check your FXML file 'SelectMode.fxml'.";

    }
}
