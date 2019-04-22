package model;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import model.data.MatchData;
import model.entities.Dog;
import model.entities.Duck;
import model.entities.Entity;
import model.entities.powerup.PowerUp;
import model.entities.powerup.PowerUpType;
import model.gun.Magazine;
import model.matches.GameMode;

/**
 * 
 * Model of the application.
 */
public interface Model {

    /**
     * Initializes the state of the model to start the game.
     * @param gameMode
     *          gameMode chosen at start
     */
    void initGame(GameMode gameMode);

    /**
     * Updates the state of the application.
     * 
     * @param timeElapsed
     *            time elapsed from last update
     */
    void update(int timeElapsed);

    /**
     * 
     * @return true if the game is over
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
     * @return the list of powerups
     */
    List<PowerUp> getPowerUps();

    /**
     * 
     * @return the data of the actual match
     */
    MatchData getMatchData();

    /**
     * 
     * @return the number of bullets remaining in the current magazine.
     */
    int getBullets();

    /**
     * 
     * Decrements the number of bullets if possible.
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
     * 
     * @return the active powerUp if present.
     */
    Optional<PowerUpType> getPowerUpActive();

    /**
     * 
     * @return the game mode of the match
     */
    GameMode getGameMode();

    /**
     * Ends the current powerUp.
     */
    void endPowerUp();

    /**
     * @return the point to pass the round or max number of flown away ducks.
     */
    int getInfo();

    /**
     * 
     * @return the dog of the current game.
     */
    Dog getDog();

    /**
     * @return the current round and max round to reach.
     */
    Pair<Integer, Integer> getRounds();
}
