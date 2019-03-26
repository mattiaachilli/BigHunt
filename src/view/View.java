package view;

import java.util.List;

import controller.matches.GameMode;
import model.achievements.Achievement;
import model.data.HighScore;
import model.data.MatchData;
import view.entities.ViewEntity;
import view.scenecontroller.GameSceneController;
import view.scenefactory.SceneFactory;

/**
 * 
 * Interface that represents the view of the game.
 *
 */
public interface View {

    void viewLauncher();
    
    void startGame(GameSceneController gameSceneController, GameMode gameMode);
    
    void startRender();
    
    void stopRender();
    
    void closeGame(MatchData matchData, boolean isHighScores);
    
    void resetGame();
    
    /**
     * 
     * @param viewEntities
     *          view entities converted from the model.
     * @param matchData
     *          current matchData
     */
    void render(List<ViewEntity> viewEntities, MatchData matchData);
    
    void setHighScores();
    
    void setAchievements(List<Achievement> achievements);
    
    List<Achievement> getAchievements();
    
    List<HighScore> getHighScores();
    
    //getgamedata
    
    SceneFactory getSceneFactory();
}
