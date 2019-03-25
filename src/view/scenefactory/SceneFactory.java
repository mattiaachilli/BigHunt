package view.scenefactory;

import controller.matches.GameMode;
import javafx.stage.Stage;

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
     * get the current stage
     * 
     * @return 
     *          the current stage
     */
    Stage getStage();
    
    /**
     * Opens the main menu scene
     */
    void openMenuScene();
    
    /**
     * Opens the Settings scene
     */
    void openSettingsScene();
    
    /**
     * Opens the achievements scene
     */
    void openAchievementsScene();
    
    /**
     * Opens the high scores scene
     */
    void openHighScoresScene();
    
    /**
     * Opens the manual of the game's scene
     */
    void OpenManualScene();
    
    /**
     * Open the game scene
     */
    void openGameScene();
    
    /**
     * Open the game mode selection scene
     */
    void openSelectModeScene();
    
    /**
     * Opens the game over scene
     */
    void openGameOverScene();
    
    /**
     * Set the game over scene
     * 
     * @param gameMode
     *          the game mode must be loaded
     */
    void setGameMode(GameMode gameMode);
}