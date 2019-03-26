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
import settings.observers.Observer;
import settings.observers.ResolutionObserver;

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

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    private ComboBox<String> resolutionComboBox;
    private ComboBox<String> difficultyComboBox;
    private ComboBox<Integer> fpsComboBox;
    private CheckBox bgAudioCheckBox;
    
    private final List<Observer> observers = new ArrayList<>();
    
    @FXML
    private void applyChanges() {
        this.observers.forEach(observer -> observer.update());
        //this.saveSettingsToFile();
        this.getSceneFactory().openSettingsScene();
    }

    @FXML
    protected void backToMenu() {
     /*
      * non andrebbe poichè è nella classe da cui eredita
      */
        this.getSceneFactory().openMenuScene();
    }

   // @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        this.observers.addAll(Arrays.asList(
            new ResolutionObserver(this.resolutionComboBox),
            new FpsObserver(this.fpsComboBox),
            new BackGroundAudioObserver(this.bgAudioCheckBox)));
        //aggungere anche difficulty observer???
    }
    
    private void enableApplyButton() {
        this.settingsApplyBtn.setDisable(false);
    }
    
    private void saveSettingsToFile() {
        /// da fare ????
    }
}
