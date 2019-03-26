package view.scenefactory;

import controller.matches.GameMode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import settings.SettingsImpl;
import view.View;
import view.sceneloader.SceneLoaderImpl;
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
    
    public SceneFactoryImpl(final View view) {
        this.view = view;
    }
    
    @Override
    public void setStage(final Stage stage) {
        this.stage = stage;
    }

    @Override
    public Stage getStage() {
        return this.stage;
    }
    
    @Override
    public void openAccountSelectionScene() {
        System.out.println("ok");
        this.openNewScene(Screens.SELECT_ACCOUNT);
    }
    
    @Override
    public void openRegisterScene() {
        this.openNewScene(Screens.REGISTER);
    }
    
    @Override
    public void openLoginScene() {
        this.openNewScene(Screens.LOGIN);
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
    public void openManualScene() {
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
    public void setGameMode(final GameMode gameMode) {
        this.gameMode = gameMode;
    }
    
    private void openNewScene(final Screens screen) {
        this.checkFullScreen();
        new SceneLoaderImpl(this.view).loadScene(this.stage, screen, this.gameMode);
    }

    private void checkFullScreen() {
        if (SettingsImpl.getSettings().isFullScreen() && this.stage.getStyle().equals(StageStyle.DECORATED)) {
            this.createNewStage();
            this.stage.initStyle(StageStyle.UNDECORATED);
            this.stage.setMaximized(true);
        } else if (!SettingsImpl.getSettings().isFullScreen() && this.stage.getStyle().equals(StageStyle.UNDECORATED)) {
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
