package model.entities;

import model.ModelImpl;

/**
 * 
 * Enumeration that represents the various properties of ducks such as velocity speed, 
 * score multiplier, fly away time and duckRarity.
 *
 */

public enum DuckProperty {

    /**
     * STANDARD DUCK INFORMATIONS.
     */
    STANDARD_DUCK(ModelImpl.GAME_WIDTH / 3, 8000, "Standard Duck"),
    /**
     * YELLOW DUCK INFORMATIONS.
     */
    YELLOW_DUCK(ModelImpl.GAME_WIDTH / 3 + 200, 7000, "Yellow Duck"),
    /**
     * ORANGE DUCK INFORMATIONS.
     */
    ORANGE_DUCK(ModelImpl.GAME_WIDTH / 3 + 500, 6000, "Orange Duck"),
    /**
     * PINK DUCK INFORMATIONS.
     */
    PINK_DUCK(ModelImpl.GAME_WIDTH / 3 + 800, 4000, "Pink Duck");


    private final double velocity;
    private final int timeFlyAway;
    private final String duckType;


    DuckProperty(final double velocity, final int timeFlyAway, final String duckType) {
        this.velocity = velocity;
        this.timeFlyAway = timeFlyAway;
        this.duckType = duckType;
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
                + ", Speed Up: " + this.velocity
                + ", Time fly away: " + this.timeFlyAway;
    }
}
