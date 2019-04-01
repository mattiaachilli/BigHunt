package view.scenefactory;

import controller.matches.GameMode;
import javafx.stage.Stage;
import view.View;

/**
 * 
 * Represents the factory of the game scenes.
 *
 */
public interface SceneFactory {

    /**
     * Set a new game stage.
     * 
     * @param stage
     *          the stage to must be loaded
     */
    void setStage(Stage stage);
    /**
     * get the current stage.
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
     * open registration scene.
     */
    void openRegisterScene();
    /**
     * open login scene.
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
     * Open the game scene.
     */
    void openGameScene();
    /**
     * Open the game mode selection scene.
     */
    void openSelectModeScene();
    /**
     * Opens the game over scene.
     */
    void openGameOverScene();

    /**
     * Open pause scene menu.
     */
    void openPauseScene();
    /**
     * Set the game over scene.
     * 
     * @param gameMode
     *          the game mode must be loaded
     */
    void setGameMode(GameMode gameMode);

    /**
     * Get view.
     * 
     * @return view
     */
    View getView();
}
