package view.scenecontroller;

import model.data.Podium;

/**
 * 
 *  Interface that represents the scene for game high scores.
 */
public interface HighScoresSceneController {
    /**
     * 
     * @param storyPodium
     *          high score to use
     */
    void setStoryModePodium(Podium storyPodium);
    /**
     * 
     * @param survivalPodium
     *          survival mode score to use
     */
    void setSurvivalModePodium(Podium survivalPodium);
}
