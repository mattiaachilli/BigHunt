package model.factories;

import javafx.scene.shape.Shape;
import model.entities.powerup.PowerUp;
import model.entities.powerup.PowerUpType;
import model.properties.Position;

/**
 * 
 * Interface that represent PowerUp Factory
 *
 */

public interface PowerUpFactory {

    /**
     * 
     * @param type
     *          type of the Power Up
     * @param position
     *          initial position to assign to the Power Up
     * @return
     *          a Power Up by specific type
     */
    PowerUp createPowerUp(PowerUpType type, Position position);
    
    /**
     * 
     * @param position
     *          initial position to assign to the Power Up
     * @return
     *          a random Power Up
     */
    PowerUp createRandomPowerUp(Position position);
      
}
