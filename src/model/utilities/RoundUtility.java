package model.utilities;

/**
 * 
 * Enum that contains the informations about rounds etc...
 *
 */
public enum RoundUtility {
    /**
     * FIVE ROUND, STORY MODE.
     */
    FIVE_ROUNDS(5), 

    /**
     * UNIQUE ROUND, SURVIVAL MODE.
     */
    UNIQUE_ROUND(1);

    private final int maxRounds;

    RoundUtility(final int max) {
        this.maxRounds = max;
    }

    /**
     * 
     * @return the number of round
     */
    public int getRounds() {
        return this.maxRounds;
    }
}
