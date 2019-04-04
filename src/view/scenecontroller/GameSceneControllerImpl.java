package view.scenecontroller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.ModelImpl;
import model.data.MatchData;
import model.gun.Magazine;
import model.matches.GameMode;
/**
 *
 * Sample Skeleton for 'Game.fxml' Controller Class.
 *
 */
public class GameSceneControllerImpl extends AbstractSceneController implements GameSceneController {

    private static final String STORY = "Score:";
    private static final String SURVIVAL = "Flown away:";
    private static final int UPDATE_TIME = 5000;
    private long time = System.currentTimeMillis();
    private boolean lastLabel;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="infoLabel"
    private Label infoLabel; // Value injected by FXMLLoader

    @FXML // fx:id="scoreGameLabel"
    private Label scoreGameLabel; // Value injected by FXMLLoader

    @FXML // fx:id="game"
    private BorderPane game; // Value injected by FXMLLoader

    @FXML // fx:id="canvas"
    private Canvas canvas; // Value injected by FXMLLoader

    @FXML // fx:id="botomHBox"
    private HBox botomHBox; // Value injected by FXMLLoader

    @FXML // fx:id="ammoLabel"
    private Label ammoLabel; // Value injected by FXMLLoader

    @FXML // fx:id="rechargeLabel"
    private Label rechargeLabel; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    final
    void initialize() {
        assert infoLabel != null : "fx:id=\"scoreLabel\" was not injected: check your FXML file 'Game.fxml'.";
        assert game != null : "fx:id=\"game\" was not injected: check your FXML file 'Game.fxml'.";
        assert scoreGameLabel != null : "fx:id=\"mainLable\" was not injected: check your FXML file 'Game.fxml'.";
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'Game.fxml'.";
        assert rechargeLabel != null : "fx:id=\"rechargeLabel\" was not injected: check your FXML file 'Game.fxml'.";
        assert botomHBox != null : "fx:id=\"botomHBox\" was not injected: check your FXML file 'Game.fxml'.";
        assert ammoLabel != null : "fx:id=\"ammoLabel\" was not injected: check your FXML file 'Game.fxml'.";
        this.rechargeLabel.setVisible(false);
    }

    @Override
    public final Canvas getCanvas() {
        this.canvas.setWidth(this.game.getWidth());
        this.canvas.setHeight(this.game.getHeight());
        return this.canvas;
    }

    @Override
    public final void setGameData(final MatchData gameData, final Magazine magazine, final int info) {
        this.ammoLabel.setTextFill(Color.BLACK);
        if (this.getSceneFactory().getView().getActualGameMode() == GameMode.STORY_MODE) {
            this.infoLabel.setText(STORY);
            this.scoreGameLabel.setText(String.valueOf(gameData.getGlobalScore()) + "/" + info);
            if (gameData.getGlobalScore() < info) {
                this.scoreGameLabel.setTextFill(Color.RED);
            } else {
                this.scoreGameLabel.setTextFill(Color.LIME);
            }
            this.ammoLabel.setText(String.valueOf(magazine.getAmmo()) + "(" + magazine.getNumber() + ")" + "/" + ModelImpl.MAX_MAGAZINES);
        } else {
            if (!lastLabel) {
                this.infoLabel.setText(SURVIVAL);
                this.scoreGameLabel.setText(String.valueOf(gameData.getFlownDucks()) + "/" + info);
                if ((System.currentTimeMillis() - time) >= UPDATE_TIME) {
                    time = System.currentTimeMillis();
                    this.lastLabel = true;
                }
            } else {
                this.infoLabel.setText(STORY);
                this.scoreGameLabel.setText(String.valueOf(gameData.getGlobalScore()));
                if ((System.currentTimeMillis() - time) >= UPDATE_TIME) {
                    time = System.currentTimeMillis();
                    this.lastLabel = false;
                }
            }
            this.ammoLabel.setText(String.valueOf(magazine.getAmmo()) + "(" + magazine.getNumber() + ")");
            this.scoreGameLabel.setTextFill(Color.BLACK);
        }
        if (magazine.getAmmo() == 0) {
            this.rechargeLabel.setVisible(true);
            this.ammoLabel.setTextFill(Color.RED);
        } else {
            this.rechargeLabel.setVisible(false);
        }
    }
}
