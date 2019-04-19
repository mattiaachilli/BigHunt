package view.scenecontroller;


import java.util.concurrent.TimeUnit;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.data.MatchData;


/**
 *
 * Sample Skeleton for 'GameOver.fxml' Controller Class.
 *
 */
public class GameOverSceneControllerImpl extends AbstractSecondarySceneController implements GameOverSceneController {

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

    /**
     * Open select mode scene.
     */
    @FXML
    protected final void startNewGame() {
        this.getSceneFactory().openSelectModeScene();
    }

    /**
     * Initialize elements.
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    protected final void initialize() {
        assert scoreLabel != null : "fx:id=\"scoreLabel\" was not injected: check your FXML file 'GameOver.fxml'.";
        assert killsLabel != null : "fx:id=\"killsLabel\" was not injected: check your FXML file 'GameOver.fxml'.";
        assert newgame != null : "fx:id=\"newgame\" was not injected: check your FXML file 'GameOver.fxml'.";
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'GameOver.fxml'.";
        assert title != null : "fx:id=\"title\" was not injected: check your FXML file 'GameOver.fxml'.";
        assert timePlayedLabel != null : "fx:id=\"timePlayedLabel\" was not injected: check your FXML file 'GameOver.fxml'.";

    }
    private String getTime(final int millis) {
        final int m = (int) TimeUnit.MILLISECONDS.toMinutes(millis);
        final int s = (int) TimeUnit.MILLISECONDS.toSeconds(millis) - (int) TimeUnit.MINUTES.toSeconds(m);
        final int ms = (int) TimeUnit.MILLISECONDS.toMillis(millis) - (int) TimeUnit.SECONDS.toMillis(s)
                - (int) TimeUnit.MINUTES.toMillis(m);
        return String.format("%d", m) + ":" + String.format("%02d", s) + ":" + String.format("%03d", ms);
    }

    @Override
    public final void setMatchData(final MatchData matchData) {
        this.timePlayedLabel.setText(this.getTime(matchData.getTimerFromStart()));
        this.killsLabel.setText(String.valueOf(matchData.getKilledDucks()));
        this.scoreLabel.setText(String.valueOf(matchData.getGlobalScore()));
    }
}
