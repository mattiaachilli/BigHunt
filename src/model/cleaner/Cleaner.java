package model.cleaner;

import java.util.List;
import model.entities.powerup.PowerUp;

/**
 * 
 * Interface that represents a cleaner, an object that remove the powerUp for example out of the screen.
 *
 */
public interface Cleaner {

    /**
     * Remove powerUps out of the screen.
     * 
     * @param powerUp
     *          list of powerUp.
     */
    void cleanPowerUp(List<PowerUp> powerUp);
}
