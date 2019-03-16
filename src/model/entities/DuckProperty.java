package model.entities;

/**
 * 
 * Enum that represents the various properties of ducks such as velocity speed, 
 * score multiplier, fly away time and duckRarity.
 *
 */

public enum DuckProperty {

    /**
     * STANDARD DUCK INFORMATIONS.
     */
    STANDARD_DUCK(1, 600, 8000, "Standard Duck"),
    /**
     * YELLOW DUCK INFORMATIONS.
     */
    YELLOW_DUCK(2, 700, 7000, "Yellow Duck"),
    /**
     * ORANGE DUCK INFORMATIONS.
     */
    ORANGE_DUCK(3, 800, 6000, "Orange Duck"),
    /**
     * PINK DUCK INFORMATIONS.
     */
    PINK_DUCK(4, 1000, 5000, "Pink Duck");


    private final int multiplierScore;
    private final double velocity;
    private final int timeFlyAway;
    private final String duckType;


    DuckProperty(final int multiplierScore, final double velocity,
                final int timeFlyAway, final String duckType) {
        this.multiplierScore = multiplierScore;
        this.velocity = velocity;
        this.timeFlyAway = timeFlyAway;
        this.duckType = duckType;
    }

    /**
     * Get score multiplier.
     * 
     * @return the score multiplier of the duck.
     */
    public int getMultiplierScore() {
        return this.multiplierScore;
    }

    /**
     * Get velocity.
     * 
     * @return the velocity of the duck.
     */
    public double getVelocity() {
        return this.velocity;
    }

    /**
     * Get fly away time.
     * 
     * @return the fly away time.
     */
    public int getTimeFlyAway() {
        return this.timeFlyAway;
    }

    /**
     * Get duck type.
     * 
     * @return the duck type
     */
    public String getDuckType() {
        return this.duckType;
    }

    /**
     * To string.
     * 
     * @return description of the duck.
     */
    public String toString() {
        return this.duckType 
                + ", Multiplier score: " + this.multiplierScore 
                + ", Speed Up: " + this.velocity
                + ", Time fly away: " + this.timeFlyAway;
    }
}
