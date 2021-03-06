/**
 * Sample Skeleton for 'SelectMode.fxml' Controller Class
 */

package view.scenecontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
//import utility.GameMode;
import model.matches.GameMode;

/**
 * Class for manage new game mode.
 *
 */
public class SelectModeSceneControllerImpl extends AbstractSecondarySceneController {

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="survivalBtn"
    private Button survivalBtn; // Value injected by FXMLLoader

    @FXML // fx:id="arcadeBtn"
    private Button arcadeBtn; // Value injected by FXMLLoader

    /**
     * Open story game scene.
     */
    @FXML
    protected final void startArcadeGame() {
        this.getSceneFactory().setGameMode(GameMode.STORY_MODE);
        this.getSceneFactory().openGameScene();
    }

    /**
     * Open survival game scene.
     */
    @FXML
    protected final void startSurvivalGame() {
        this.getSceneFactory().setGameMode(GameMode.SURVIVAL_MODE);
        this.getSceneFactory().openGameScene();
    }

    /**
     * Initialize.
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    protected final void initialize() {
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'SelectMode.fxml'.";
        assert survivalBtn != null : "fx:id=\"survivalBtn\" was not injected: check your FXML file 'SelectMode.fxml'.";
        assert arcadeBtn != null : "fx:id=\"arcadeBtn\" was not injected: check your FXML file 'SelectMode.fxml'.";

    }
}
