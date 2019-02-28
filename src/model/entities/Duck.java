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
