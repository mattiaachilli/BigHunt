package view.scenecontroller;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import com.sun.corba.se.spi.activation.ActivatorHolder;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.achievements.Achievement;
import model.achievements.AchievementType;

/**
 * 
 * Sample Skeleton for 'Achievements.fxml' Controller Class
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
    public final void setAchievements(final Map<AchievementType, Achievement> achievements) {
        // verificare cosa succede se non sono presenti
        //System.out.println("prova stampa fuori");
        for (final Entry<AchievementType, Achievement> a : achievements.entrySet()) {
            switch (a.getKey()) {
            case KILLED_DUCKS:
                this.kills.setText(String.valueOf(a.getValue().getCurrentValueOfAchievement()));
                break;
            case MATCHES_PLAYED:
                this.matchPlayed.setText(String.valueOf(a.getValue().getCurrentValueOfAchievement()));
                break;
            case POWERUPS_USED:
                this.powerUpUsed.setText(String.valueOf(a.getValue().getCurrentValueOfAchievement()));
                break;
            case SUM_OF_SCORES:
                this.totalScore.setText(String.valueOf(a.getValue().getCurrentValueOfAchievement()));
                break;
                default:
                    break;
            }

        }
        /*
         * aggiungere informazioni da achievements scorrerei la mappa farei uno switch
         * se label name == tipo assegna valore altrimenti valore = 0;
         */
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'Achievements.fxml'.";
    }

}
