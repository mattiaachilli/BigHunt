package model.cleaner;

import java.util.List;

import model.entities.Duck;
import model.entities.powerup.PowerUp;

/**
 * 
 * Interface that represents a cleaner, an object that remove the powerUp for example out of the screen.
 *
 */
public interface Cleaner {

    /**
     * Remove entities out of the screen or not necessary.
     * 
     * @param ducks
     *          list of ducks.
     * @param powerUps
     *          list of powerUps.
     */
    void clean(List<Duck> ducks, List<PowerUp> powerUps);
}
