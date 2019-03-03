package model.entities.powerup;

import static model.rarity.ItemRarity.*;
import model.rarity.ItemRarity;

/**
 * 
 * Enumeration used to classify the Power Up.
 *
 */
public enum PowerUpType {
	INFINITE_AMMO("infinite ammo", COMMON),
	KILL_ALL("all the duck die", VERY_RARE);
	
	private final ItemRarity powerUpRarity;
	private final String powerUpBio;
	
	private PowerUpType(final String actualBio, final ItemRarity rarity) {
		this.powerUpRarity = rarity;
		this.powerUpBio = actualBio;
	}
	
	public ItemRarity getPowerUpRarity() {
		return this.powerUpRarity;
	}
	
	public String toString() {
		return this.powerUpBio;
	}
}
