package view.sceneloader;

import javafx.stage.Stage;
import model.matches.GameMode;
import view.utilities.Screens;

/**
 * 
 * Interface that provides the methods to load the game scenes.
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
