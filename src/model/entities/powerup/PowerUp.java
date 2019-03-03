package model.entities.powerup;

import model.entities.Entity;

/**
 *	Representation of Power Up, an entity of the game that can be dropped by ducks when killed,
 *	if we hit the Power Up we get extra skills.
 */

public interface PowerUp extends Entity {

	/**
	 * 
	 * @return the type of power up
	 */
	PowerUpType getType();
}