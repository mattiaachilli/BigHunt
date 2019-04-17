package view.scenecontroller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import settings.observers.BackGroundAudioObserver;
import settings.observers.FpsObserver;
import settings.observers.GameDifficultyObserver;
import settings.observers.Observer;

/**
 * Sample Skeleton for 'Settings.fxml' Controller Class.
 */
public class SettingsSceneControllerImpl extends AbstractSecondarySceneController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="settingsApplyBtn"
    private Button settingsApplyBtn; // Value injected by FXMLLoader

    @FXML // fx:id="bgAudioCheckBox"
    private CheckBox bgAudioCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="difficultyComboBox"
    private ComboBox<String> difficultyComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="fpsComboBox"
    private ComboBox<String> fpsComboBox; // Value injected by FXMLLoader

    private final List<Observer> observers = new ArrayList<>();

    @FXML
    private void applyChanges() {
        this.observers.forEach(observer -> observer.update());
        this.getSceneFactory().openSettingsScene();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    private void initialize() {
        this.observers.addAll(Arrays.asList(new FpsObserver(this.fpsComboBox),
        new BackGroundAudioObserver(this.bgAudioCheckBox), new GameDifficultyObserver(this.difficultyComboBox)));
        this.settingsApplyBtn.setDisable(true);
    }

    @FXML
    private void enableApplyBtn() {
        this.settingsApplyBtn.setDisable(false);
    }
}
