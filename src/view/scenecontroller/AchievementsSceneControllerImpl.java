package view.scenecontroller;

import java.util.Map;

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

    private static final String SEPARATOR = " / ";
    private static final String ACHIEVEMENT_COMPLETE = "ACHIEVEMENT COMPLETED";

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

        this.kills.setText(achievements
            .get(AchievementType.KILLED_DUCKS)
            .getCurrentValueOfAchievement()
            + SEPARATOR + (achievements.get(AchievementType.KILLED_DUCKS).getNextTarget().isPresent()
            ? String.valueOf(achievements.get(AchievementType.KILLED_DUCKS).getNextTarget().get())
            : ACHIEVEMENT_COMPLETE));

        this.matchPlayed.setText(achievements
            .get(AchievementType.MATCHES_PLAYED)
            .getCurrentValueOfAchievement()
            + SEPARATOR + (achievements.get(AchievementType.MATCHES_PLAYED).getNextTarget().isPresent()
            ? String.valueOf(achievements.get(AchievementType.MATCHES_PLAYED).getNextTarget().get())
            : ACHIEVEMENT_COMPLETE));

        this.powerUpUsed.setText(achievements
            .get(AchievementType.POWERUPS_USED)
            .getCurrentValueOfAchievement()
            + SEPARATOR + (achievements.get(AchievementType.POWERUPS_USED).getNextTarget().isPresent()
            ? String.valueOf(achievements.get(AchievementType.POWERUPS_USED).getNextTarget().get())
            : ACHIEVEMENT_COMPLETE));

        this.totalScore.setText(achievements
            .get(AchievementType.SUM_OF_SCORES)
            .getCurrentValueOfAchievement()
            + SEPARATOR + (achievements.get(AchievementType.SUM_OF_SCORES).getNextTarget().isPresent()
            ? String.valueOf(achievements.get(AchievementType.SUM_OF_SCORES).getNextTarget().get())
            : ACHIEVEMENT_COMPLETE));
    }

    /**
     * Initialize.
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    protected final void initialize() {
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'Achievements.fxml'.";
    }

}
