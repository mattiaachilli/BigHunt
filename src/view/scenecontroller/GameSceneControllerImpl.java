package view.scenecontroller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import model.data.MatchData;
import settings.SettingsImpl;

public class GameSceneControllerImpl extends AbstractSceneController implements GameSceneController {


    @FXML // fx:id="canvas"
    private Canvas canvas; // Value injected by FXMLLoader
    
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="game"
    private Pane game; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert game != null : "fx:id=\"game\" was not injected: check your FXML file 'Game.fxml'.";
    }

    @Override
    public final Canvas getCanvas() {
     //   this.game.setPrefHeight(SettingsImpl.getSettings().getSelectedResolution().getValue());
     //   this.game.setPrefWidth(SettingsImpl.getSettings().getSelectedResolution().getKey());
        this.canvas.setHeight(SettingsImpl.getSettings().getSelectedResolution().getValue());
        this.canvas.setWidth(SettingsImpl.getSettings().getSelectedResolution().getKey());

        return this.canvas;
    }

    @Override
    public void setGameData(MatchData gameData) {
        //set game data
    }
}