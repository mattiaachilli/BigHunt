

package view.scenecontroller;
import javafx.fxml.FXML;

/**
 *
 * Sample Skeleton for 'Menu.fxml' Controller Class.
 *
 */
public class MenuSceneControllerImpl extends AbstractSceneController {

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
