package view.scenecontroller;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.data.MatchData;
import settings.SettingsImpl;
/**
 * 
 * Sample Skeleton for 'Game.fxml' Controller Class
 *
 */
public class GameSceneControllerImpl extends AbstractSceneController implements GameSceneController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="scoreLabel"
    private Label scoreLabel; // Value injected by FXMLLoader

    @FXML // fx:id="game"
    private BorderPane game; // Value injected by FXMLLoader

    @FXML // fx:id="canvas"
    private Canvas canvas; // Value injected by FXMLLoader

    @FXML // fx:id="botomHBox"
    private HBox botomHBox; // Value injected by FXMLLoader

    @FXML // fx:id="ammoLabel"
    private Label ammoLabel; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert scoreLabel != null : "fx:id=\"scoreLabel\" was not injected: check your FXML file 'Game.fxml'.";
        assert game != null : "fx:id=\"game\" was not injected: check your FXML file 'Game.fxml'.";
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'Game.fxml'.";
        assert botomHBox != null : "fx:id=\"botomHBox\" was not injected: check your FXML file 'Game.fxml'.";
        assert ammoLabel != null : "fx:id=\"ammoLabel\" was not injected: check your FXML file 'Game.fxml'.";

    }

    @Override
    public final Canvas getCanvas() {
     //   this.game.setPrefHeight(SettingsImpl.getSettings().getSelectedResolution().getValue());
     //   this.game.setPrefWidth(SettingsImpl.getSettings().getSelectedResolution().getKey());
      //  this.canvas.setHeight(SettingsImpl.getSettings().getSelectedResolution().getValue());
     //   this.canvas.setWidth(SettingsImpl.getSettings().getSelectedResolution().getKey());
   ////     this.canvas.setHeight(this.game.getHeight()) ;
  ////      this.canvas.setWidth(this.game.getWidth());
   //         SettingsImpl.getSettings().setGameResolution((int) this.botomHBox.getHeight());
           this.canvas.setHeight(SettingsImpl.getSettings().getDefaultResolutions().getValue() /*+ this.bottomGrid.getHeight()*/);
           this.canvas.setWidth(SettingsImpl.getSettings().getDefaultResolutions().getKey());
     //   this.vGameBox.setMaxHeight(SettingsImpl.getSettings().getDefaultResolutions().getValue());
    //    this.vGameBox.setMaxWidth(SettingsImpl.getSettings().getDefaultResolutions().getKey());
       // this.vGameBox.setPrefHeight(SettingsImpl.getSettings().getDefaultResolutions().getValue());
        //this.vGameBox.setPrefWidth(SettingsImpl.getSettings().getDefaultResolutions().getKey());
        System.out.println(this.canvas.getWidth());
        System.out.println(this.canvas.getHeight()); 
        System.out.println(this.game.getWidth());
        System.out.println(this.game.getHeight()); 

        return this.canvas;
    }

    @Override
    public void setGameData(MatchData gameData) {
        //set game data
    }
}
