package model;

import java.util.List;

import controller.matches.GameMode;
import model.data.MatchData;
import model.entities.Duck;
import model.entities.Entity;

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
     * @return the list of ducks
     */
    List<Duck> getDucks();

    /**
     * 
     * @return data of the actual match
     */
    MatchData getMatchData();

    /**
     * 
     * End the match.
     */
    void endMatch();

    /**
     * 
     * Set the X position of the Aim.
     */
    void setAimX(); //Parametro da passare

    /**
     * 
     * Set the Y position of the Aim.
     */
    void setAimY(); //Parametro da passare

    /**
     * 
     * @return the list of bullets
     */
    //List<Bullet> getBullets();
}
