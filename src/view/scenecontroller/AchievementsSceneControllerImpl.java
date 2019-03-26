package view.scenecontroller;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.corba.se.spi.activation.ActivatorHolder;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.achievements.Achievement;

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
     * @param achievements
     *          points of player
     */
    public final void setAchievements(final List<Achievement> achievements) {
        this.matchPlayed.setText("2");
        this.kills.setText("2");
        this.powerUpUsed.setText("3");
        this.totalScore.setText("4");
        /*
        aggiungere informazioni da achievements
        scorrerei la mappa 
        farei uno switch se label name == tipo assegna valore 
        altrimenti valore = 0;
        */
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'Achievements.fxml'.";

    }
}
