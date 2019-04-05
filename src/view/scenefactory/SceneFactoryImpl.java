
package view.scenefactory;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.matches.GameMode;
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
    private GameMode gameMode;

    /**
     * Constructor of SceneFactory.
     * 
     * @param view
     *          view of the game.
     */
    public SceneFactoryImpl(final View view) {
        this.view = view;
    }

    @Override
    public final void setStage(final Stage stage) {
        this.stage = stage;
    }

    @Override
    public final Stage getStage() {
        return this.stage;
    }

    @Override
    public final void openAccountSelectionScene() {
        this.openNewScene(Screens.SELECT_ACCOUNT);
    }

    @Override
    public final void openRegisterScene() {
        this.openNewScene(Screens.REGISTER);
    }

    @Override
    public final void openLoginScene() {
        this.openNewScene(Screens.LOGIN);
    }
    @Override
    public final void openMenuScene() {
        this.openNewScene(Screens.MENU);
    }

    @Override
    public final void openSettingsScene() {
        this.openNewScene(Screens.SETTINGS);
    }

    @Override
    public final void openAchievementsScene() {
        this.openNewScene(Screens.ACHIEVEMENTS);
    }

    @Override
    public final void openHighScoresScene() {
        this.openNewScene(Screens.HIGH_SCORES);
    }

    @Override
    public final void openManualScene() {
        this.openNewScene(Screens.MANUAL);
    }

    @Override
    public final void openGameScene() {
        //System.out.println("Apre scena partita");
        this.openNewScene(Screens.GAME);
    }

    @Override
    public final void openSelectModeScene() {
        this.openNewScene(Screens.SELECTMODE);
    }

    @Override
    public final void openGameOverScene() {
        this.openNewScene(Screens.GAME_OVER);
    }

    @Override
    public final void openPauseScene() {
        this.openNewScene(Screens.PAUSE);
    }

    @Override
    public final void setGameMode(final GameMode gameMode) {
        this.gameMode = gameMode;
    }

    @Override
    public final View getView() {
        return this.view;
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
