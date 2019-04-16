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
     * @return the list of powerups
     */
    List<PowerUp> getPowerUps();

    /**
     * 
     * @return data of the actual match
     */
    MatchData getMatchData();

    /**
     * 
     * @return the number of bullets remaining in the current magazine.
     */
    int getBullets();

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
     * Get powerUp active at the moment.
     * 
     * @return if exist the power up active.
     */
    Optional<PowerUpType> getPowerUpActive();

    /**
     * 
     * @return the game mode of the match
     */
    GameMode getGameMode();

    /**
     * End actual powerUp.
     */
    void endPowerUp();

    /**
     * @return the point to pass the round or max number flown away ducks.
     */
    int getInfo();

    /**
     * Get dog of the game.
     * 
     * @return dog
     */
    Dog getDog();

    /**
     * @return the actual round and max round to reach.
     */
    Pair<Integer, Integer> getRounds();
}
