package model.entities.powerup;

import model.entities.Entity;

/**
 * Representation of Power Up,
 *       an entity of the game that can be dropped by ducks when killed,
 *       that if we hit them we get extra skills.
 */

public interface PowerUp extends Entity {

    /**
     * 
     * @return 
     *          the type of power up
     */
    PowerUpType getType();

    /**
     * Sets the power up visible when the duck has been hit.
     */
    void setVisible();

    /**
     * Gets if the power up is visible.
     * 
     * @return 
     *          true if is visible.
     */
    boolean isVisible();

    /**
     * 
     * @return 
     *          true if this powerUp has been hit.
     */
    boolean isHit();

    /**
     * Set the powerUp status to hit.
     */
    void hit();
}
