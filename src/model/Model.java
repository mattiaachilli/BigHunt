package model;

import java.util.List;

import model.data.GlobalData;
import model.data.MatchData;
import model.entities.Entity;
import utility.GameMode;

/**
 * 
 * Model of the application.
 */
public interface Model {

    /**
     * Initialize state of model to start the game.
     * @param gameMode
     *          gameMode chosen at start
     */
    void initGame(GameMode gameMode);

    /**
     * Update the state of the application.
     * 
     * @param timeElapsed
     *            time elapsed from last update
     */
    void update(int timeElapsed);

    /**
     * 
     * @return true if game is over
     */
    boolean isGameOver();

    /**
     * 
     * @return the list of entities
     */
    List<Entity> getEntities();

    /**
     * 
     * @return data of the actual match
     */
    MatchData getMatchData();

    /**
     * 
     * @return data of all matches
     */
    GlobalData getGlobalData();

    /**
     * 
     * @return true if is an highscore
     */
    boolean isHighScore();

    /**
     * 
     * End the match.
     */
    void endMatch();

    
    void setAimX();
    
    void setAimY();
    /**
     * 
     * @return the list of bullets
     */
    //List<Bullet> getBullets();
}
