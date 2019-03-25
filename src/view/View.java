package view;

import java.util.List;

import model.data.HighScore;
import view.scenefactory.SceneFactory;

/**
 * 
 * Interface that represents the view of the game.
 *
 */
public interface View {

    void viewLauncher();
    
    void startGame();
    
    void startViewRender();
    
    void stopViewRender();
    
    void closeGame();
    
    void resetGame();
    
    void setStateGame();
    
    void serHighScores();
    
    void setAchievements();
    
    //map<> get achievement
    
    List<HighScore> getHighScores();
    
    //getgamedata
    
    SceneFactory getSceneFactory();
}
