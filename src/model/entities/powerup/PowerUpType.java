package model.entities.powerup;

import static model.rarity.ItemRarity.COMMON;
import static model.rarity.ItemRarity.VERY_RARE;
import model.rarity.ItemRarity;

/**
 * 
 * Enumeration used to classify the Power Up.
 *
 */
public enum PowerUpType {

    /**
     * INFINITE AMMO FOR A SHORT TIME.
     */
    INFINITE_AMMO("Infinite ammo", COMMON),
    /**
     * KILL ALL DUCK SPAWNED.
     */
    KILL_ALL("All the duck die", VERY_RARE);

    private final String powerUpBio;
    private final ItemRarity powerUpRarity;

    PowerUpType(final String actualBio, final ItemRarity rarity) {
        this.powerUpRarity = rarity;
        this.powerUpBio = actualBio;
    }

    /**
     * 
     * @return the rarity of the power up
     */
    public ItemRarity getPowerUpRarity() {
        return this.powerUpRarity;
    }

    /**
     * To string.
     * 
     * @return description of the power up
     */
    public String toString() {
        return this.powerUpBio;
    }
}
