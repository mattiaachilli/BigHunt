package model.factories;

import java.util.Optional;

import model.entities.powerup.PowerUp;
import model.properties.Position;

/**
 * 
 * Interface that represent PowerUp Factory.
 *
 */

public interface PowerUpFactory {

    /**
     * 
     * @param position
     *          initial position to assign to the Power Up
     * @return
     *          a Optional and random Power Up
     */
    Optional<PowerUp> createRandomPowerUp(Position position);
}

