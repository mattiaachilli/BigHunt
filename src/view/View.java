package view;

import java.util.List;
import java.util.Map;

import controller.Controller;
import model.achievements.Achievement;
import model.achievements.AchievementType;
import model.data.HighScore;
import model.data.Podium;
import view.scenefactory.SceneFactory;

/**
 * 
 * Interface that represents the view of the game.
 *
 */
public interface View {

    /**
     * 
     * @param controller the controller of the game
     */
    void viewLauncher(Controller controller);

    /**
     * 
     */
    void startGame();

    /**
     * 
     */
    void startViewRender();

    /**
     * 
     */
    void stopViewRender();

    /**
     * 
     */
    void closeGame();

    /**
     * 
     */
    void resetGame();

    /**
     * 
     */
    void setStateGame();

    /**
     * 
     * Sets the story podium for the view.
     * 
     * @param podium the podium
     */
    void setStoryPodium(Podium podium);

    /**
     * 
     * Sets the survival podium for the view.
     * 
     * @param podium the podium
     */
    void setSurvivalPodium(Podium podium);

    /**
     * 
     * @param achievements current achievements
     */
    void setAchievements(Map<AchievementType, Achievement> achievements);

    /**
     * 
     * @return a map of achievements.
     */
    Map<AchievementType, Achievement> getAchievements();

    /**
     * 
     * @return the story podium.
     */
    Podium getStoryPodium();

    /**
     * 
     * @return the survival podium.
     */
    Podium getSurvivalPodium();

    /**
     * 
     * @return .
     */
    SceneFactory getSceneFactory();
}
