package view.scenecontroller;

import java.util.List;

import model.data.HighScore;

/**
 * 
 *  Interface that represents the scene for game high scores.
 */
public interface HighScoresSceneController {
    /**
     * 
     * @param highScores
     *          high score to use
     */
    void setStoryModeHighScores(List<HighScore> highScores);
    /**
     * 
     * @param highScores
     *          survival mode score to use
     */
    void setSurvivalModeHighScores(List<HighScore> highScores);
}
