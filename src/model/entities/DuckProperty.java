package model.entities;

/**
 * 
 * Enum that represents the various properties of ducks such as velocity speed, 
 * score multiplier, fly away time.
 *
 */

public enum DuckProperty {
    
    STANDARD_DUCK(1, 1.0, 15, "Standard Duck"),
    
    YELLOW_DUCK(2, 1.50, 12, "Yellow Duck"),
    
    ORANGE_DUCK(3, 2.0, 10, "Orange Duck"),
    
    RED_DUCK(4, 2.50, 8, "Red Duck");
    
    
    private final int multiplierScore;
    private final double velocitySpeedUp;
    private final int timeFlyAway;
    private final String duckType;
    
    private DuckProperty(final int multiplierScore, final double velocitySpeedUp, 
	    final int timeFlyAway, final String duckType) {
	this.multiplierScore = multiplierScore;
	this.velocitySpeedUp = velocitySpeedUp;
	this.timeFlyAway = timeFlyAway;
	this.duckType = duckType;
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
    
    public String toString() {
	return this.duckType 
		+ ", Multiplier score: " + this.multiplierScore 
		+ ", Speed Up: " + this.velocitySpeedUp
		+ ", Time fly away: " + this.timeFlyAway;
    }
}
