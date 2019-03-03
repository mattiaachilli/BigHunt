package model.entities.powerup;

import static model.rarity.ItemRarity.*;
import model.rarity.ItemRarity;

/**
 * 
 * Enumeration used to classify the Power Up.
 *
 */
public enum PowerUpType {
    
    INFINITE_AMMO("Infinite ammo", COMMON),
    KILL_ALL("All the duck die", VERY_RARE);
	
    private final String powerUpBio;
    private final ItemRarity powerUpRarity;
	
    PowerUpType(final String actualBio, final ItemRarity rarity) {
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
