

package view.scenecontroller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

/**
 * 
 * Sample Skeleton for 'Menu.fxml' Controller Class.
 *
 */
public class MenuSceneControllerImpl extends AbstractSceneController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="menu"
    private BorderPane menu; // Value injected by FXMLLoader

    /**
     * Open game mode scene.
     */
    @FXML
    protected void openGameModeSelection() {
        this.getSceneFactory().openSelectModeScene();
    }
    /**
     * Open achievements scene.
     */
    @FXML
    protected void openAchievements() {
        this.getSceneFactory().openAchievementsScene();
    }
    /**
     * Open highscore scene.
     */
    @FXML
    protected void openHighScores() {
        this.getSceneFactory().openHighScoresScene();
    }
    /**
     * Open manual scene.
     */
    @FXML
    protected void openManual() {
        this.getSceneFactory().openManualScene();
    }

    /**
     * Open setting scene.
     */
    @FXML
    protected void openSettings() {
        this.getSceneFactory().openSettingsScene();
    }

    /**
     * QUit game.
     */
    @FXML
    protected void quitGame() {
        Runtime.getRuntime().exit(0);
    }

}
