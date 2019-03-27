package model;

import java.util.List;

import controller.matches.GameMode;
import model.data.MatchData;
import model.entities.Duck;
import model.entities.Entity;
import model.gun.Magazine;

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
     * @return a map containing magazine and bullets.
     */
    int getBullets();

    /**
     * 
     * @return the list of magazines carried.
     */
    List<Magazine> getAmmo();

    /**
     * 
     * Method used to handle bullets after each shot.
     */
    //List<Bullet> getBullets();
    
    /**
     * 
     */
    void shoot();

    /**
     * 
     * @return true if the magazine still contains ammo.
     */
    boolean canShoot();

    /**
     * 
     * changes magazine.
     */
    void recharge();

    /**
     * 
     * @return the magazine being used.
     */
    Magazine getCurrentMagazine();

    /**
     * test.
     */
    void activateInfAmmo();

    /**
     * test.
     */
    void deactivateInfAmmo();
}
