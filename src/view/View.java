package view;

import java.util.List;
import java.util.Map;

import controller.Controller;
import model.achievements.Achievement;
import model.achievements.AchievementType;
import model.data.HighScore;
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
     */
    void setHighScores();

    /**
     * 
     * @param achievements
     *            current achievements
     */
    void setAchievements(Map<AchievementType, Achievement> achievements);

    /**
     * 
     * @return a map of achievements.
     */
    Map<AchievementType, Achievement> getAchievements();

    /**
     * 
     * @return .
     */
    List<HighScore> getHighScores();

    /**
     * 
     * @return .
     */
    SceneFactory getSceneFactory();
}
