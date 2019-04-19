package model.entities;

import java.util.Optional;

import model.entities.powerup.PowerUp;
import model.properties.DuckDirection;

/**
 * 
 * Represents a duck in the game.
 *
 */
public interface Duck extends Character {

    /**
     * Gets the PowerUp if it is present. 
     * 
     * @return optional of PowerUp
     */
    Optional<PowerUp> getPowerUp();

    /**
     * Checks if this duck has a PowerUp.
     * 
     * @return true if this duck has a PowerUp.
     */
    boolean hasPowerUp();

    /**
     * Gets the score that can be obtained killing this duck.
     * 
     * @return score of this duck.
     */
    int getScore();

    /**
     * Checks if the duck can fly away.
     * 
     * @return true if the duck can fly away.
     */
    boolean canFlyAway();

    /**
     * Changes duck's status, from alive to flownAway.
     */
    void computeFlyAway();

    /**
     * Gets the actual direction of the duck.
     * 
     * @return duck's direction
     */
    DuckDirection getActualDirection();

    /**
     * Sets the new direction of the duck.
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
     * Sets the velocity of this duck decelerate.
     */
    void setDecelerate();

    /**
     * @return true if the velocity of this duck is decelerated.
     */
    boolean isDecelerated();
}
