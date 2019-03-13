package model;

import java.util.List;

import controller.matches.GameMode;
import model.data.GlobalData;
import model.data.MatchData;
import model.entities.Entity;

/**
 * 
 * @author mattia
 *	Model of the application.
 */

public interface Model {
	
    /**
     * Initialize state of model to start the game.
     * 
     */
    public void initGame(final GameMode gameMode);
	
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
     * End the match
     */
    void endMatch();
    
    /**
     * Set the X position of the Aim
     */
    void setAimX(); //Parametro da passare
    
    /**
     * Set the Y position of the Aim
     */
    void setAimY(); //Parametro da passare
    
    /**
     * 
     * @return the list of bullets
     */
    List<Bullet> getBullets();
}
