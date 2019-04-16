package view.scenecontroller;

import javafx.scene.canvas.Canvas;
import model.data.MatchData;
import model.gun.Magazine;

/**
 *
 * Interface that represents game scene controller.
 *
 */
public interface GameSceneController {

    /**
     *
     * @return
     *          the game page canvas
     */
    Canvas getCanvas();

    /**
     * 
     * @param gameData
     *          Data of the game.
     * @param magazine
     *          Current magazine.
     * @param infoLimit
     *          Info about rounds.
     */
    void setGameData(MatchData gameData, Magazine magazine, int infoLimit);

    /**
     * Set pause panel visibility.
     * 
     * @param isVisible
     *          true if is visible.
     */
    void setPausePanelVisibility(boolean isVisible);
}
