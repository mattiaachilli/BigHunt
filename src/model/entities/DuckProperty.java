package model.entities;
import static model.rarity.ItemRarity.*;
import model.rarity.ItemRarity;

/**
 * 
 * Enum that represents the various properties of ducks such as velocity speed, 
 * score multiplier, fly away time and duckRarity.
 *
 */

public enum DuckProperty {
    
    STANDARD_DUCK(1, 1.0, 15, "Standard Duck", VERY_COMMON),
    
    YELLOW_DUCK(2, 1.50, 12, "Yellow Duck", COMMON),
    
    ORANGE_DUCK(3, 2.0, 10, "Orange Duck", RARE),
    
    PINK_DUCK(4, 2.50, 8, "Pink Duck", VERY_RARE);
    
    
    private final int multiplierScore;
    private final double velocitySpeedUp;
    private final int timeFlyAway;
    private final String duckType;
    private final ItemRarity duckRarity;
    
    DuckProperty(final int multiplierScore, final double velocitySpeedUp, 
	    final int timeFlyAway, final String duckType, final ItemRarity duckRarity) {
	this.multiplierScore = multiplierScore;
	this.velocitySpeedUp = velocitySpeedUp;
	this.timeFlyAway = timeFlyAway;
	this.duckType = duckType;
	this.duckRarity = duckRarity;
    }
    
    public int getMultiplierScore() {
	return this.multiplierScore;
    }
    
    public double getVelocitySpeedUp() {
	return this.velocitySpeedUp;
    }
    
    public int getTimeFlyAway() {
	return this.timeFlyAway;
    }
    
    public String getDuckType() {
	return this.duckType;
    }
    
    public ItemRarity getDuckRarity() {
        return this.duckRarity;
    }
    
    public String toString() {
	return this.duckType 
		+ ", Multiplier score: " + this.multiplierScore 
		+ ", Speed Up: " + this.velocitySpeedUp
		+ ", Time fly away: " + this.timeFlyAway
	        + ", Duck rarity: " + this.duckRarity;
    }
}
