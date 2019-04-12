package view.scenecontroller;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.achievements.Achievement;
import model.achievements.AchievementType;

/**
 *
 * Sample Skeleton for 'Achievements.fxml' Controller Class.
 *
 */
public class AchievementsSceneControllerImpl extends AbstractSecondarySceneController
implements AchievementSceneController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="kills"
    private Label kills; // Value injected by FXMLLoader

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="powerUpUsed"
    private Label powerUpUsed; // Value injected by FXMLLoader

    @FXML // fx:id="totalScore"
    private Label totalScore; // Value injected by FXMLLoader

    @FXML // fx:id="matchPlayed"
    private Label matchPlayed; // Value injected by FXMLLoader

    /**
     * load game achievements.
     *
     * @param achievements points of player
     */
    @Override
    public final void setAchievements(final Map<AchievementType, Achievement> achievements) {

        this.kills.setText(String.valueOf(achievements
            .get(AchievementType.KILLED_DUCKS)
            .getCurrentValueOfAchievement())
            + " / " + (achievements.get(AchievementType.KILLED_DUCKS).getNextTarget().isPresent() 
            ? String.valueOf(achievements.get(AchievementType.KILLED_DUCKS).getNextTarget().get()) 
            : "ACHIEVEMENT COMPLETED"));

        this.matchPlayed.setText(String.valueOf(achievements
            .get(AchievementType.MATCHES_PLAYED)
            .getCurrentValueOfAchievement())
            + " / " + (achievements.get(AchievementType.MATCHES_PLAYED).getNextTarget().isPresent() 
            ? String.valueOf(achievements.get(AchievementType.MATCHES_PLAYED).getNextTarget().get()) 
            : "ACHIEVEMENT COMPLETED"));

        this.powerUpUsed.setText(String.valueOf(achievements
            .get(AchievementType.POWERUPS_USED)
            .getCurrentValueOfAchievement())
            + " / " + (achievements.get(AchievementType.POWERUPS_USED).getNextTarget().isPresent() 
            ? String.valueOf(achievements.get(AchievementType.POWERUPS_USED).getNextTarget().get()) 
            : "ACHIEVEMENT COMPLETED"));

        this.totalScore.setText(String.valueOf(achievements
            .get(AchievementType.SUM_OF_SCORES)
            .getCurrentValueOfAchievement()) 
            + " / " + (achievements.get(AchievementType.SUM_OF_SCORES).getNextTarget().isPresent() 
            ? String.valueOf(achievements.get(AchievementType.SUM_OF_SCORES).getNextTarget().get()) 
            : "ACHIEVEMENT COMPLETED"));
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    final void initialize() {
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'Achievements.fxml'.";
    }

}
