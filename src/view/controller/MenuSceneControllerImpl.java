

package view.controller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

/**
 * 
 * Sample Skeleton for 'Menu.fxml' Controller Class
 *
 */
public class MenuSceneControllerImpl extends AbstractSceneController{

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="menu"
    private BorderPane menu; // Value injected by FXMLLoader

    @FXML
    private void openGameModeSelection() {
        this.getSceneFactory().openSelectModeScene();
    }

    @FXML
    private void openAchievements() {
        this.getSceneFactory().openAchievementsScene();
    }

    @FXML
    private void openHighScores() {
        this.getSceneFactory().openHighScoresScene();
    }

    @FXML
    private void openManual() {
        this.getSceneFactory().OpenManualScene();
    }

    @FXML
    private void openSettings() {
        this.getSceneFactory().openSettingsScene();
    }

    @FXML
    private void quitGame() {
        Runtime.getRuntime().exit(0);
    }

}
