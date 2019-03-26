package view.sceneloader;

import controller.matches.GameMode;
import javafx.stage.Stage;
import view.utilities.Screens;

/**
 * 
 * Interface that provides methods for load game scenes.
 *
 */
public interface SceneLoader {

    /**
     * 
     * @param stage
     *          stage to be loaded
     * @param screen
     *          required screen scene
     * @param gameMode
     *          selected game mode
     */
    void loadScene(Stage stage, Screens screen, GameMode gameMode);
}
