package model.entities;

import java.util.Optional;

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
     * Attach a powerUp to this duck
     * 
     * @param powerUp
     * 		powerUp to attach
     */
    void attachPowerUp(PowerUp powerUp);
    
    /**
     * Get the time in seconds since the duck was created, the duck may be died.
     * @return time.
     */
    long getTimeFromBirth();
    
    /**
     * Check if this duck is flew away
     * 
     * @return true if the duck flew away
     */
    boolean canFlyAway();
    
    /**
     * Check if this duck has a PowerUp.
     * 
     * @return true if this duck has a PowerUp
     */
    boolean hasPowerUp();
    
    /**
     * Get the score that can be obtained killing this duck
     * 
     * @return score of this duck.
     */
    int getScore();
}
