package view.scenecontroller;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.data.HighScore;
import model.data.Podium;

/**
 *
 * Sample Skeleton for 'HighScores.fxml' Controller Class.
 *
 */
public class HighScoresSceneControllerImpl extends AbstractSecondarySceneController
implements HighScoresSceneController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="story5"
    private Label story5; // Value injected by FXMLLoader

    @FXML // fx:id="story1"
    private Label story1; // Value injected by FXMLLoader

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="story2"
    private Label story2; // Value injected by FXMLLoader

    @FXML // fx:id="story3"
    private Label story3; // Value injected by FXMLLoader

    @FXML // fx:id="story4"
    private Label story4; // Value injected by FXMLLoader

    @FXML // fx:id="survival1"
    private Label survival1; // Value injected by FXMLLoader

    @FXML // fx:id="survival2"
    private Label survival2; // Value injected by FXMLLoader

    @FXML // fx:id="survival3"
    private Label survival3; // Value injected by FXMLLoader

    @FXML // fx:id="survival4"
    private Label survival4; // Value injected by FXMLLoader

    @FXML // fx:id="survival5"
    private Label survival5; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    final void initialize() {
        assert story5 != null : "fx:id=\"story5\" was not injected: check your FXML file 'HighScores.fxml'.";
        assert story1 != null : "fx:id=\"story1\" was not injected: check your FXML file 'HighScores.fxml'.";
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'HighScores.fxml'.";
        assert story2 != null : "fx:id=\"story2\" was not injected: check your FXML file 'HighScores.fxml'.";
        assert story3 != null : "fx:id=\"story3\" was not injected: check your FXML file 'HighScores.fxml'.";
        assert story4 != null : "fx:id=\"story4\" was not injected: check your FXML file 'HighScores.fxml'.";
        assert survival1 != null : "fx:id=\"survival1\" was not injected: check your FXML file 'HighScores.fxml'.";
        assert survival2 != null : "fx:id=\"survival2\" was not injected: check your FXML file 'HighScores.fxml'.";
        assert survival3 != null : "fx:id=\"survival3\" was not injected: check your FXML file 'HighScores.fxml'.";
        assert survival4 != null : "fx:id=\"survival4\" was not injected: check your FXML file 'HighScores.fxml'.";
        assert survival5 != null : "fx:id=\"survival5\" was not injected: check your FXML file 'HighScores.fxml'.";

    }

    @Override
    public final void setStoryModePodium(final Podium storyPodium) {
        if (!storyPodium.getHighScores().isEmpty()) {
            final Iterator<HighScore> it = storyPodium.getHighScores().iterator();
            this.storyLabels().forEach(l -> l.setText(String.valueOf(it.next().getScore())));
        }
    }

    @Override
    public final void setSurvivalModePodium(final Podium survivalPodium) {
        if (!survivalPodium.getHighScores().isEmpty()) {
            final Iterator<HighScore> it = survivalPodium.getHighScores().iterator();
            this.survivalLabels().forEach(l -> l.setText(String.valueOf(it.next().getScore())));

        }
    }

    private Stream<Label> storyLabels() {
        return Stream.of(story1, story2, story3, story4, story5);
    }

    private Stream<Label> survivalLabels() {
        return Stream.of(survival1, survival2, survival3, survival4, survival5);
    }

}
