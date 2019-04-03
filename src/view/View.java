package view;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import controller.Controller;
import controller.matches.GameMode;
import model.achievements.Achievement;
import model.achievements.AchievementType;
import model.data.MatchData;
import model.data.Podium;
import model.gun.Magazine;
import view.entities.ViewEntity;
import view.scenecontroller.GameSceneController;
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
     * @return the story pods.
     */
    Podium getStoryPodium();

    /**
     * 
     * @return the survival pods.
     */
    Podium getSurvivalPodium();

    /**
     * 
     * @return the match data of logged user.
     */

    MatchData getMatchData();

    /**
     * 
     * @return .
     */
    SceneFactory getSceneFactory();

    /**
     * 
     * @param matchData    the final data of an ended match
     * @param isHighScores true if the score of the match is an high score
     */
    void closeGame(MatchData matchData, boolean isHighScores);

    /**
     * View update.
     * 
     * @param viewEntities entities in the view
     * @param matchData    data of the match
     * @param magazine     current magazine useful to view bullets
     * @param info         info about limits round/flown away ducks
     */
    void render(List<Optional<ViewEntity>> viewEntities, MatchData matchData, Magazine magazine, int info);

    /**
     * Start a new match.
     * 
     * @param gameSceneController .
     * @param gameMode            the match's game mode
     */
    void startGame(GameSceneController gameSceneController, GameMode gameMode);

    /**
     * Start the view thread.
     */
    void startRender();

    /**
     * Stop the view thread.
     */
    void stopRender();

    /**
     * 
     * @return the controller.
     */
    Controller getController();

    /**
     * Get actual game mode.
     * 
     * @return the actual game mode.
     */
    GameMode getActualGameMode();

    /**
     * Reset the game when GameOver.
     */
    void reset();
}
