package view.scenefactory;

import javafx.stage.Stage;
import model.matches.GameMode;
import view.View;

/**
 * 
 * Represents the factory of the game scenes.
 *
 */
public interface SceneFactory {

    /**
     * Sets a new game stage.
     * 
     * @param stage
     *          the stage to must be loaded
     */
    void setStage(Stage stage);
    /**
     * gets the current stage.
     * 
     * @return 
     *          the current stage
     */
    Stage getStage();

    /**
     * Opens the account method.
     */
    void openAccountSelectionScene();
    /**
     * opens the registration scene.
     */
    void openRegisterScene();
    /**
     * opens the login scene.
     */
    void openLoginScene();
    /**
     * Opens the main menu scene.
     */
    void openMenuScene();
    /**
     * Opens the Settings scene.
     */
    void openSettingsScene();
    /**
     * Opens the achievements scene.
     */
    void openAchievementsScene();
    /**
     * Opens the high scores scene.
     */
    void openHighScoresScene();
    /**
     * Opens the manual of the game's scene.
     */
    void openManualScene();
    /**
     * Opens the game scene.
     */
    void openGameScene();
    /**
     * Opens the game mode selection scene.
     */
    void openSelectModeScene();
    /**
     * Opens the game over scene.
     */
    void openGameOverScene();

    /**
     * Opens the pause scene menu.
     */
    void openPauseScene();
    /**
     * Sets the game over scene.
     * 
     * @param gameMode
     *          the game mode must be loaded
     */
    void setGameMode(GameMode gameMode);

    /**
     * Gets view.
     * 
     * @return view
     */
    View getView();
}
