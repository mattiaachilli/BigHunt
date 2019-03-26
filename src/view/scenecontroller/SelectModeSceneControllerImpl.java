package view.scenecontroller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SelectModeSceneControllerImpl extends AbstractSecondarySceneController{
    
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="gamestorybtn"
    private Button gamestorybtn; // Value injected by FXMLLoader

    @FXML // fx:id="gamesurvivalstorybtn"
    private Button gamesurvivalstorybtn; // Value injected by FXMLLoader

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML
    protected void backToMenu() {
        ///
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert gamestorybtn != null : "fx:id=\"gamestorybtn\" was not injected: check your FXML file 'SelectMode.fxml'.";
        assert gamesurvivalstorybtn != null : "fx:id=\"gamesurvivalstorybtn\" was not injected: check your FXML file 'SelectMode.fxml'.";
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'SelectMode.fxml'.";

    }
}
