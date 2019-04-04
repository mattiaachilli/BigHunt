package model.entities;

import java.util.Optional;

import model.entities.powerup.PowerUp;
import model.properties.DuckDirection;

/**
 * 
 * Represents a duck in the game, a Duck may have a Power Up.
 *
 */
public interface Duck extends Character {

    /**
     * Get the PowerUp if is present. 
     * 
     * @return optional of PowerUp
     */
    Optional<PowerUp> getPowerUp();

    /**
     * Check if this duck has a PowerUp.
     * 
     * @return true if this duck has a PowerUp.
     */
    boolean hasPowerUp();

    /**
     * Get the score that can be obtained killing this duck.
     * 
     * @return score of this duck.
     */
    int getScore();

    /**
     * Check if duck can fly away.
     * 
     * @return true if duck can fly away.
     */
    boolean canFlyAway();

    /**
     * Check if fly away is possible, if is possible change duck's status.
     */
    void computeFlyAway();

    /**
     * Get the actual direction of the duck.
     * 
     * @return duck's direction
     */
    DuckDirection getActualDirection();

    /**
     * Set the new direction of the duck.
     * 
     * @param direction
     *          new direction
     */
    void setDirection(DuckDirection direction);

    /**
     * Used for test.
     * 
     * @param change
     *          set the movement
     */
    void setMovementChange(boolean change);

    /**
     * Set the velocity of this duck decelerate.
     */
    void setDecelerate();

    /**
     * Get the property of the duck.
     * @return
     *       duck's property.
     */
    DuckProperty getProperty();
}
