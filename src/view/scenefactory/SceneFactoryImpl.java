package view.scenefactory;

import controller.matches.GameMode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import settings.SettingsImpl;
import view.View;
import view.utilities.Screens;

/**
 * 
 * Implements factory of the game.
 *
 */
public class SceneFactoryImpl implements SceneFactory {

    private Stage stage;
    private final View view;
    private GameMode gameMode = GameMode.STORY_MODE;
    
    public SceneFactoryImpl(View view) {
        this.view = view;
    }
    
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public Stage getStage() {
        return this.stage;
    }

    @Override
    public void openMenuScene() {
        this.openNewScene(Screens.MENU);
    }

    @Override
    public void openSettingsScene() {
        this.openNewScene(Screens.SETTINGS);
    }

    @Override
    public void openAchievementsScene() {
        this.openNewScene(Screens.ACHIEVEMENTS);
    }

    @Override
    public void openHighScoresScene() {
        this.openNewScene(Screens.HIGH_SCORES);
    }

    @Override
    public void OpenManualScene() {
        this.openNewScene(Screens.MANUAL);
    }

    @Override
    public void openGameScene() {
        this.openNewScene(Screens.GAME);
    }

    @Override
    public void openSelectModeScene() {
        this.openNewScene(Screens.SELECTMODE);
    }

    @Override
    public void openGameOverScene() {
        this.openNewScene(Screens.GAME_OVER);
    }

    @Override
    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }
    
    private void openNewScene(final Screens screen) {
        this.checkFullScreen();
        //sceneloaderIml
    }

    private void checkFullScreen() {
        if(SettingsImpl.getSettings().isFullScreen() && this.stage.getStyle().equals(StageStyle.UNDECORATED)) {
            this.createNewStage();
            this.stage.initStyle(StageStyle.UNDECORATED);
            this.stage.setMaximized(true);
        } else {
            this.createNewStage();
            this.stage.initStyle(StageStyle.DECORATED);
        }
    }
    
    private void createNewStage() { 
        this.stage.close();
        this.stage = new Stage();
        this.stage.setTitle("BIG HUNT");
        this.stage.setOnCloseRequest(e -> Runtime.getRuntime().exit(0));
    }
}
