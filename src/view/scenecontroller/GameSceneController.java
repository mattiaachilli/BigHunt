package view.scenecontroller;

import javafx.scene.canvas.Canvas;
import model.data.MatchData;

/**
 * 
 * Interface that represents game scene controller.
 *
 */
public interface GameSceneController {

    Canvas getCanvas();
    
    void setGameData(MatchData gameData);
}
