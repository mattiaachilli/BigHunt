package view;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import controller.Controller;
import model.achievements.Achievement;
import model.achievements.AchievementType;
import model.data.MatchData;
import model.data.Podium;
import model.gun.Magazine;
import model.matches.GameMode;
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
     * @return the scene factory.
     */
    SceneFactory getSceneFactory();

    /**
     * 
     * @param matchData    the final data of an ended match
     * @param matchCompleted true if the game is over, false if the player quits the game by returning to the menu
     */
    void closeGame(MatchData matchData, boolean matchCompleted);

    /**
     * View update.
     * 
     * @param viewEntities entities in the view
     * @param matchData    data of the match
     * @param magazine     current magazine useful to view bullets
     * @param info         info about limits round/flown away ducks
     * @param round        left actual round, right max round
     */
    void render(List<Optional<ViewEntity>> viewEntities, MatchData matchData, Magazine magazine, int info, Pair<Integer, Integer> round);

    /**
     * Starts a new match.
     * 
     * @param gameSceneController .
     * @param gameMode            the match's game mode
     */
    void startGame(GameSceneController gameSceneController, GameMode gameMode);

    /**
     * Starts the view's thread.
     */
    void startRender();

    /**
     * Stops the view's thread.
     */
    void stopRender();

    /**
     * 
     * @return the controller.
     */
    Controller getController();

    /**
     * Gets the current game mode.
     * 
     * @return the current game mode.
     */
    GameMode getCurrentGameMode();

    /**
     * Set image cursor.
     */
    void setCursor();
}
