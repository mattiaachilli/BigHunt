package view;

import java.util.List;

import controller.matches.GameMode;
import model.data.HighScore;
import model.data.MatchData;
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
    
    void startViewRender();
    
    void stopViewRender();
    
    void closeGame(MatchData matchData, boolean isHighScores);
    
    void resetGame();
    
    void setStateGame();
    
    void serHighScores();
    
    void setAchievements();
    
    //map<> get achievement
    
    List<HighScore> getHighScores();
    
    //getgamedata
    
    SceneFactory getSceneFactory();
}
